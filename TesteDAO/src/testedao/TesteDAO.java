
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
            
            //DAO SQL
            config.put(DAOFactory.TIPO_DAO, "SQL");                              
            config.put(DAOFactory.URL_DB, "jdbc:mysql://localhost:3306/unlam");
            config.put(DAOFactory.USUARIO_DB, "user");
            config.put(DAOFactory.PASS_DB, "");
            dao = DAOFactory.getIntance().createDAO(config);
            
            
            
            //CREATE
            MiCalendario fechaNac = new MiCalendario(1, 10, 1991);
            MiCalendario fechaIng = new MiCalendario(1, 3, 2020);
            alumno = new Alumno(35766182, "Luciano", "Salgado", 'm', fechaNac, fechaIng, 1, 8.9);
            dao.create(alumno);
            System.out.println("Alumno creado ==>" + alumno);
            
            // delete MYSQL: 
            /*alumno = new Alumno();
            alumno.setDni(35766183);
            dao.delete(alumno.getDni());
            System.out.println("El alumno con DNI " + alumno.getDni() + " fue eliminado .");*/
            
            /*update MYSQL: Alumno alu1 = dao.read(Long.valueOf(35766182));
            alu1.setApellido("Gomez");
            dao.update(alu1);*/
            
            /*select MYSQL: Alumno alu2 = dao.read(Long.valueOf(94489249));
            if (alu2!=null) {
                System.out.println("Alumno encontrado ==> "+alu2);
            }*/
            
            /* findall MYSQL: List<Alumno> alumnos = dao.findAll(true);
            for (Alumno alumno5 : alumnos){
                System.out.println("Alumno ==> "+ alumno5);
            };*/
            
            /*Alumno aluRead = dao.read(alumno.getDni());
            if (aluRead!=null) {
                System.out.println("Alumno encontrado ==> "+aluRead);
            }*/
            
            
            
            
            
            
            //DAO TXT
            /*
            config.put(DAOFactory.TIPO_DAO, "TXT");                              
            config.put(DAOFactory.FILENAME, "alumno.txt");
            dao = DAOFactory.getIntance().createDAO(config);
            */
            //CREATE
            /*
            MiCalendario fechaNac = new MiCalendario(2, 11, 1981);
            MiCalendario fechaIng = new MiCalendario(1, 3, 2020);
            alumno = new Alumno(90909088, "Gisele", "Galera", 'f', fechaNac, fechaIng, 10, 8.0);
            dao.create(alumno);
            System.out.println("Alumno creado ==>" + alumno);
            */
            
            //READ
            /*
            alumno = new Alumno();
            alumno.setDni(90909018);
            if (dao.read(alumno.getDni())!=null)
                System.out.println("Alumno encontrado");
            else
                System.out.println("Alumno no encontrado");
            */
            
            //READ && UPDATE
            /*
            alumno = dao.read(Long.valueOf(909090828));
            System.out.println("Alumno original   ==> " + alumno);
            alumno.setApellido("Souza");
            dao.update(alumno);
            System.out.println("Alumno modificado ==> " + alumno);
            */
            
            //UPDATE
            /*
            MiCalendario fechaNac = new MiCalendario(2, 11, 1981);
            MiCalendario fechaIng = new MiCalendario(1, 3, 2020);
            alumno = new Alumno(90909088, "Gisele", "Galera", 'f', fechaNac, fechaIng, 10, 8.0);
            dao.update(alumno);
            System.out.println("Alumno modificado ==> " + alumno);
            */
            
            //DELETE
            /*
            alumno = new Alumno();
            alumno.setDni(90909088);
            dao.delete(alumno.getDni());
            System.out.println("El alumno con DNI " + alumno.getDni() + " fue eliminado .");
            */
            
                        
            //LIST
            /*
            List<Alumno> alumnos = dao.findAll(null); //true=activos  false=borrados  null=todos
            //for (Alumno alumno : alumnos){
            alumnos.forEach((alu) -> {
                System.out.println("Alumno ==> "+ alu);
            });
            */
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
