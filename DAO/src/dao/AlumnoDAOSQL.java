package dao;

import java.sql.Connection;
import java.sql.DriverManager;// con esta libreria se conecta a la base de datos 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.MiCalendario;
import persona.PersonaException;

/**
 *
 * @author Grupo 13
 */
public class AlumnoDAOSQL extends DAO<Alumno, Long>{

    private Connection conn;
    private PreparedStatement insertPS;
    private PreparedStatement selectPS;
    private PreparedStatement updatePS;
    private PreparedStatement deletePS;
    private PreparedStatement selectAllPS;
    
    AlumnoDAOSQL (String url, String usuario, String password) throws DAOException {
        
        try {
            conn = DriverManager.getConnection(url, usuario, password);
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al conectarse con la BD ==> "+ex.getMessage());
        }
        String insertSQL = "INSERT INTO alumnos\n" +
                "(dni,\n" +
                "nombre,\n" +
                "apellido,\n" +
                "sexo,\n" +
                "fechaNac,\n" +
                "fechaIngreso,\n" +
                "cantMatAprob,\n" +
                "promedio\n" +
                "estado)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?,?);";
        
        try {
            insertPS = conn.prepareStatement(insertSQL);
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear sentencia para INSERT ==> "+ex.getMessage());
        }
         
        String selectSQL = "SELECT * FROM  alumnos WHERE dni = ?";
        
        try {
            selectPS = conn.prepareStatement(selectSQL);
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear sentencia para SELECT ==> "+ex.getMessage());
        }
        
        String updateSQL= "UPDATE alumnos\n SET " +
                "dni = ?,\n" +
                "nombre = ?,\n" +
                "apellido = ?,\n" +
                "sexo = ?,\n" +
                "fechaNac = ?,\n" +
                "fechaIngreso = ?,\n" +
                "cantMatAprob = ?,\n" +
                "promedio = ?,\n" +
                "estado = ?\n" +
                " WHERE dni = ?";
        
        try {
            updatePS = conn.prepareStatement(updateSQL);
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear sentencia para UPDATE ==> "+ex.getMessage());
        }
         
          String deleteSQL= "DELETE FROM alumnos\n where DNI=?" ;
        
        try {
            deletePS = conn.prepareStatement(deleteSQL);
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear sentencia para delete ==> "+ex.getMessage());
        } 
        
        String selectAllSQL = "SELECT * FROM alumnos WHERE estado IN (?,?)";
        
        try {
            selectAllPS = conn.prepareStatement(selectAllSQL);
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear sentencia para SELECT ==> "+ex.getMessage());
        }        
    }
    @Override
    public void create(Alumno alu) throws DAOException {
        try {
            int index = 1;
            insertPS.setLong(index++, alu.getDni());
            insertPS.setString(index++, alu.getNombre());
            insertPS.setString(index++, alu.getApellido());
            insertPS.setString(index++, String.valueOf(alu.getSexo()));
            insertPS.setDate(index++, alu.getFechaNac().toSQLDate());
            insertPS.setDate(index++, alu.getFechaIngreso().toSQLDate());
            insertPS.setDouble(index++, alu.getCantMatAprob());
            insertPS.setDouble(index++, alu.getPromedio());
            insertPS.setString(index++, alu.isActivo()?"A":"B");
            
            insertPS.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al insertar en la BD ==>"+ex.getMessage());
        }
    }

    @Override
    public Alumno read(Long dni) throws DAOException {
        Alumno alu = null;
        try {
            selectPS.setLong(1, dni);
            ResultSet rs = selectPS.executeQuery();
            if (rs.next()) {
                alu = new Alumno();
                alu.setDni(dni);
                alu.setNombre(rs.getString("nombre"));
                alu.setApellido(rs.getString("apellido"));
                alu.setSexo(rs.getString("sexo").charAt(0));
                alu.setFechaNac(new MiCalendario(rs.getDate("fechaNac")));
                alu.setFechaIngreso(new MiCalendario(rs.getDate("fechaIngreso")));
                alu.setCantMatAprob(rs.getInt("cantMatAprob"));
                alu.setPromedio(rs.getDouble("promedio"));
                alu.setActivo(rs.getString("estado")=="A"?true:false);
            }
            
        }
        catch (SQLException | PersonaException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            // TODO throw new DAOException...
        }
        if (alu==null)
            throw new DAOException ("El Alumno no existe");
        return alu;
    }
    @Override
    public void update(Alumno alu) throws DAOException{
        try {
            int index = 1;
            updatePS.setLong(index++, alu.getDni());
            updatePS.setString(index++, alu.getNombre());
            updatePS.setString(index++, alu.getApellido());
            updatePS.setString(index++, String.valueOf(alu.getSexo()));
            updatePS.setDate(index++, alu.getFechaNac().toSQLDate());
            updatePS.setDate(index++, alu.getFechaIngreso().toSQLDate());
            updatePS.setInt(index++, alu.getCantMatAprob());
            updatePS.setDouble(index++, alu.getPromedio());
            updatePS.setString(index++, alu.isActivo()?"A":"B");
            updatePS.setLong(index++, alu.getDni());
            updatePS.execute();
            
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al actualizar en la BD ==>"+ex.getMessage());
        }
    }

    @Override
    public void delete(Long dni) throws DAOException{
        try {
            if (!existe(dni))
                throw new DAOException ("El Alumno no existe");

            int index = 1;
            deletePS.setLong(index++, dni); 
            deletePS.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al eliminar en la BD ==>"+ex.getMessage());
        }
    }

    @Override
    public boolean existe(Long dni) throws DAOException {
        return read(dni)!=null;
    }

    @Override
    public List<Alumno> findAll(Boolean activos) throws DAOException {
        List<Alumno> alumnos = new ArrayList<>();
        Alumno alu;
        try {
            int index = 1;
            if (activos==null) {
                selectAllPS.setString(index++, "A");
                selectAllPS.setString(index++, "B");
            } 
            else if (activos==true) {
                selectAllPS.setString(index++,"A");
                selectAllPS.setString(index++,"A");
            } 
            else {
                selectAllPS.setString(index++,"B");
                selectAllPS.setString(index++,"B");
            }
            
            ResultSet rs = selectAllPS.executeQuery();
            while(rs.next()) {
                alu = new Alumno();
                alu.setDni(rs.getLong("dni"));
                alu.setNombre(rs.getString("nombre"));
                alu.setApellido(rs.getString("apellido"));
                alu.setSexo(rs.getString("sexo").charAt(0));
                alu.setFechaNac(new MiCalendario(rs.getDate("fechaNac")));
                alu.setFechaIngreso(new MiCalendario(rs.getDate("fechaIngreso")));
                alu.setCantMatAprob(rs.getInt("cantMatAprob"));
                alu.setPromedio(rs.getDouble("promedio"));
                alu.setActivo(rs.getString("estado")=="A"?true:false);
                alumnos.add(alu);
            }
            
        } catch (SQLException | PersonaException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return alumnos;
    } // metodo reutilizar en read + findall // luego filtrado de tipos de estado (otro ps)

    @Override
    public void close() throws DAOException {

        try {
            conn.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al cerrar la BD ==> "+ex.getMessage());
        }
    }
    
}
