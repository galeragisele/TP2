
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
public class AlumnoDAOSQL_Gisele extends DAO<Alumno, Long>{

    private Connection conn;
    private PreparedStatement insertPS;
    private PreparedStatement selectPS;
    private PreparedStatement updatePS;
    private PreparedStatement deletePS;
    private PreparedStatement selectAllPS;
    
    AlumnoDAOSQL_Gisele(String url, String usuario, String password) throws DAOException {
        
        try {
            conn = DriverManager.getConnection("127.0.0.1", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al conectarse con la BD ==> "+ex.getMessage());
        }
        String insertSQL = "insert into alumnos\n" +
                "(dni,\n" +
                "nombre,\n" +
                "apellido,\n" +
                "sexo,\n" +
                "fechaNac,\n" +
                "fechaIngreso,\n" +
                "promedio)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?);";
        
        try {
            insertPS = conn.prepareStatement(insertSQL);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear sentencia para INSERT ==> "+ex.getMessage());
        }
         
        String selectSQL = "select * from  alumnos where dni = ?";
        
        try {
            selectPS = conn.prepareStatement(selectSQL);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear sentencia para SELECT ==> "+ex.getMessage());
        }
        
        String updateSQL= "update alumnos\n SET" +
                 "(dni,\n" +
                "nombre,\n" +
                "apellido,\n" +
                "sexo,\n" +
                "fechaNac,\n" +
                "fechaIngreso,\n" +
                "promedio)\n" +
                "(?,?,?,?,?,?);";
        
        try {
            updatePS = conn.prepareStatement(updateSQL);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear sentencia para UPDATE ==> "+ex.getMessage());
        }
         
          String deleteSQL= "delete from alumnos\n where DNI=?" ;
        
        try {
            deletePS = conn.prepareStatement(deleteSQL);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear sentencia para delete ==> "+ex.getMessage());
        } 
        
        String selectAllSQL = "select * from alumnos";
        
        try {
            selectAllPS = conn.prepareStatement(selectAllSQL);
        } catch (SQLException ex) {
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
            insertPS.setDate(index++, alu.getFechaNac().toSQLDate());
            insertPS.setString(index++, String.valueOf(alu.getSexo()));
            insertPS.setDouble(index++, alu.getPromedio());
            
            insertPS.execute();
            
        } catch (SQLException ex) {
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
                alu.setPromedio(rs.getDouble("promedio"));
            }
            
        } catch (SQLException | PersonaException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            // TODO throw new DAOException...
        }        
        return alu;
    }
    @Override
    public void update(Alumno alu) throws DAOException{
        try {
            int index = 1;
            updatePS.setLong(index++, alu.getDni());
            updatePS.setString(index++, alu.getNombre());
            updatePS.setString(index++, alu.getApellido());
            updatePS.setDate(index++, alu.getFechaNac().toSQLDate());
            updatePS.setString(index++, String.valueOf(alu.getSexo()));
            updatePS.setDouble(index++, alu.getPromedio());
            
            updatePS.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al actualizar en la BD ==>"+ex.getMessage());
        }
    }

    @Override
    public void delete(Long dni) throws DAOException{
    try {
            int index = 1;
            deletePS.setLong(index++, dni); 
            boolean execute = deletePS.execute();
        } catch (SQLException ex) {
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
        Alumno alu = null;
        try {
          
            ResultSet rs = selectAllPS.executeQuery();
            while(rs.next()) {
                alu = new Alumno();
                alu.setDni(rs.getLong("dni"));
                alu.setNombre(rs.getString("nombre"));
                alu.setApellido(rs.getString("apellido"));
                alu.setSexo(rs.getString("sexo").charAt(0));
                alu.setFechaNac(new MiCalendario(rs.getDate("fechaNac")));
                alu.setFechaIngreso(new MiCalendario(rs.getDate("fechaIngreso")));
                alu.setPromedio(rs.getDouble("promedio"));
                alumnos.add(alu);
            }
            
        } catch (SQLException | PersonaException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return alumnos;
    }

    @Override
    public void close() throws DAOException {

        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al cerrar la BD ==> "+ex.getMessage());
        }
    }
    
}
