
package testedao;

import dao.AlumnoDAOTXT;
import dao.DAO;
import dao.DAOException;
import dao.DAOFactory;
import dao.DAOFactoryException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.AlumnoException;
import persona.MiCalendario;
import persona.MiCalendarioException;
import persona.PersonaException;


/**
 *
 * @author Grupo 13
 */
public class TesteDAO {

    /**
     * Este metodo sirve para hacer testing de las funciones de ABM
     * Llama a un factory que instancia un dao<T, K>
     * 
     * Sirve tanto para TXT, como para SQL
     * 
     * @param args the command line arguments
     * @throws PersonaException
     * @throws MiCalendarioException
     */    
    public static void main(String[] args) throws PersonaException, MiCalendarioException, DAOException {        
        DAO<Alumno, Long> dao = null;
        //AlumnoDAOTXT dao = new AlumnoDAOTXT("alumnos2.txt");
        Alumno alumno;
        try {
            Map<String, String> config = new HashMap<>();
            
            //Config SQL
            /*
            config.put(DAOFactory.TIPO_DAO, "SQL");                              
            config.put(DAOFactory.URL_DB, "jdbc:mysql://localhost:3306/unlam");
            config.put(DAOFactory.USUARIO_DB, "root");
            config.put(DAOFactory.PASS_DB, "");
            */
            
            //Config TXT
            /**/
            config.put(DAOFactory.TIPO_DAO, "TXT");                              
            config.put(DAOFactory.FILENAME, "alumnos.txt");
            
            dao = DAOFactory.getIntance().createDAO(config);
         
            
            //CREATE
            
            MiCalendario fechaNac = new MiCalendario(1, 12, 2001);
            MiCalendario fechaIng = new MiCalendario(1, 3, 2020);
            alumno = new Alumno(90909010, "Pablo", "Jose",'m', fechaNac, fechaIng, 3, 7.0);
            dao.create(alumno);
            System.out.println("Alumno creado ==>" + alumno);
            
            
            //READ
            /* 
            Long dni = Long.valueOf(90909010);
            if ((alumno = dao.read(dni))!=null)
                System.out.println("Alumno encontrado => " + alumno);
            else
                System.out.println("Alumno no encontrado");
            */
            
              
            //READ && UPDATE
            /*   
            alumno = dao.read(Long.valueOf(90909031));
            System.out.println("Alumno original   ==> " + alumno);
            alumno.setNombre("Carmen");
            dao.update(alumno);
            System.out.println("Alumno modificado ==> " + alumno);
          */
            
            //UPDATE
            /*
            MiCalendario fechaNac = new MiCalendario(2, 11, 1971);
            MiCalendario fechaIng = new MiCalendario(1, 3, 2020);
            alumno = new Alumno(90909041, "Luciano", "Roberto", 'm', fechaNac, fechaIng, 5, 8.33);
            dao.update(alumno);
            System.out.println("Alumno modificado ==> " + alumno);
            */
            
            //DELETE
            /*   
            alumno = new Alumno();
            alumno.setDni(90909031);
            dao.delete(alumno.getDni());
            System.out.println("El alumno con DNI " + alumno.getDni() + " fue eliminado .");
         */
            
                        
            //LIST
            /*  
            List<Alumno> alumnos = dao.findAll(true); //true=activos  false=borrados  null=todos
            //for (Alumno alumno : alumnos){
            alumnos.forEach((alu) -> {
                System.out.println("Alumno ==> "+ alu);
            });*/
            
        } catch (/*AlumnoException | DAO*/Exception ex) {
            Logger.getLogger(TesteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (dao!=null) {
                try {
                    dao.close();
                } catch (DAOException ex) {
                    Logger.getLogger(TesteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
       
        }
    }
    
}
