
package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.MiCalendario;
import persona.MiCalendarioException;
import persona.PersonaException;

/**
 *
 * @author gisele.galera
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
     *
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
            throw new DAOException("Error al crear el Alumno ==> "+ex.getMessage());
        }
       
    }

    
    /**
     *
     * @param dni (clave)
     * @return 
     * @throws dao.DAOException 
     */
    @Override
    public Alumno read(Long dni)throws DAOException { // se recibe un DNI y se devuelve un alumno
        try {
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
        
        return new Alumno (dniAlu, nombre, apellido, sexo, fechaNac,
                fechaIngreso, cantMatAprob, promedio);
    }

    
    
    /**
     *
     * @param entidad
     * @throws dao.DAOException
     */
    
    @Override
    public void update(Alumno entidad)throws DAOException {
        
    }

    
    /**
     *
     * @param dni (clave)
     * @throws dao.DAOException
     */
    @Override
    public void delete(Long dni) throws DAOException {
        
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
  
    
    
  
}
