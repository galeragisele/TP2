
package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.MiCalendario;
import persona.MiCalendarioException;
import persona.PersonaException;

/**
 * DAO TXT para alumno
 * @author Grupo 13
 */
public class AlumnoDAOTXT extends DAO<Alumno, Long>{
    
    RandomAccessFile raf; // hasta que no se llama el constructor esa validación es nula
    
    
    public AlumnoDAOTXT(String filename) throws DAOException {
        try {
            raf = new RandomAccessFile(filename, "rws");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlumnoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear el DAO ==> "+ex.getMessage());
        }
        
    }

    /**
     * Crear un alumno
     * Si el dni ya existe, no lo crea.
     * @param alumno
     * @throws dao.DAOException
     */
    @Override
    public void create(Alumno alumno)throws DAOException {
        
        try {
            if (existe(alumno.getDni())) {
                throw new DAOException ("El Alumno ya existe");
            }
            raf.seek(raf.length()); //setea el puntero al final del file
            raf.writeBytes(alumno.toString()+System.lineSeparator()); // lineseparator es para el salto de línea 
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al crear el Alumno ==> " + ex.getMessage());
        }
       
    }

    
    /**
     * 
     * @param dni (clave)
     * @return Alumno
     * @throws dao.DAOException 
     */
    @Override
    public Alumno read(Long dni)throws DAOException { // se recibe un DNI y se devuelve un alumno
        try {
            if (!existe(dni)) {
                throw new DAOException ("El Alumno no existe");
            }
            raf.seek(0); // se posiciona al inicio
            String linea; // hasta el line separator
            String [] campos; // asignacion del vector
            while((linea = raf.readLine())!=null){ // si la linea es distinto de null se sigue leyendo
                campos = linea.split(persona.Persona.DELIM);
                if (Long.valueOf(campos[0].trim()).equals(dni)){
                    return str2Alu(campos);
                }
            }
        }   catch (IOException | MiCalendarioException | NumberFormatException | PersonaException ex) {
            Logger.getLogger(AlumnoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return null;
    }

    private Alumno str2Alu(String[] campos) throws NumberFormatException, PersonaException,
            MiCalendarioException {
        int i=0;
        long dniAlu = Long.valueOf(campos[i++].trim());
        String nombre = campos[i++].trim();
        String apellido = campos[i++].trim();
        
        char sexo = campos[i++].charAt(0);
        
        
        String[] fecha = campos[i++].split("/");
        MiCalendario fechaNac = new MiCalendario(Integer.valueOf(fecha[0].trim()),
                Integer.valueOf(fecha[1].trim()), Integer.valueOf(fecha[2]));
       
        
        fecha = campos[i++].split("/");
        MiCalendario fechaIngreso = new MiCalendario(Integer.valueOf(fecha[0].trim()),
                Integer.valueOf(fecha[1].trim()), Integer.valueOf(fecha[2]));
        
        Integer cantMatAprob = Integer.valueOf(campos[i++].trim());
        
        Double promedio = Double.valueOf(campos[i++].trim().replaceAll(",", "."));
        
        boolean activo = campos[i].equals("A");
        
        return new Alumno (dniAlu, nombre, apellido, sexo, fechaNac,
                fechaIngreso, cantMatAprob, promedio, activo);
    }

    
    
    /**
     *
     * @param entidad
     * @throws dao.DAOException
     */
    
    @Override
    public void update(Alumno entidad)throws DAOException {
        // raf.getFilePointer - devuelve el punto con su valor actual
        //raf.seek (puntero)
        // cuando actualizo - sobreescribir toda la líneaL
        if (!existe(entidad.getDni()))
            throw new DAOException ("El alumno a actualizar no existe");
        try {
            raf.seek(0); // se posiciona al inicio
            String linea; // hasta el line separator
            String [] campos; // asignacion del vector
            while((linea = raf.readLine())!=null){ // si la linea es distinta de null -  sigue leyendo
                campos = linea.split(persona.Persona.DELIM);
                if (Long.valueOf(campos[0].trim()).equals(entidad.getDni())){
                    //guardar el alumno en esa posición 
                    Long posicion = raf.getFilePointer() - linea.length() - System.lineSeparator().length();
                    raf.seek(posicion);
                    raf.writeBytes(entidad.toString()+System.lineSeparator());
                    break;
                }
            }
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(AlumnoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     *
     * @param dni (clave)
     * @throws dao.DAOException
     */
    @Override
    public void delete(Long dni) throws DAOException {
        Alumno alu = read (dni);
        if (alu==null){
            throw new DAOException ("El alumno a eliminar no existe");
        }
        alu.setActivo(false);
        update(alu);
    }

    @Override
    public boolean existe(Long dni) throws DAOException {
        try {
            raf.seek(0); // se posiciona al inicio
            String linea; // hasta el line separator
            String [] campos; // asignacion del vector
            while((linea = raf.readLine())!=null){ // si la linea es distinto de null se sigue leyendo
                campos = linea.split(persona.Persona.DELIM);
                if (Long.valueOf(campos[0].trim()).equals(dni)){
                    return true; // si encuentra se va del método
                }
            }
       }   catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
            
            return false;
        }

    
    /*
     * @param Activos = TRUE / Bajas = FALSE / null = otherwise / A+B = all
     * @return
     * @throws DAOException 
     */
    @Override
    public List<Alumno> findAll(Boolean activos) throws DAOException {
        List<Alumno> alumnos = new ArrayList<>();
        
        try {
            raf.seek(0); // Se posiciona al inicio
            String linea;
            String[] campos; 
            while((linea = raf.readLine())!=null) {
                campos = linea.split(persona.Persona.DELIM);
                Alumno alu = str2Alu(campos);
                if (activos==null || activos == alu.isActivo()) {
                    alumnos.add(alu);
                }
            }
        } catch (IOException | NumberFormatException | PersonaException | MiCalendarioException ex) {
            Logger.getLogger(AlumnoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al leer los alumnos ==> "+ex.getMessage());
        }
        
        return alumnos;
    }
    
    /**
     * Cierra la conexion
     * @throws DAOException 
     */
    @Override
    public void close() throws DAOException {

        try {
            raf.close();
        } catch (IOException ex) {
            Logger.getLogger(AlumnoDAOTXT.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Error al cerrar el archivo ==> "+ex.getMessage());
        }
    }
    
    
  
}
