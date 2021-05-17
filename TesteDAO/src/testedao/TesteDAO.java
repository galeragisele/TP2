
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
     * Llama a un
     * Tanto para TXT, como para SQL
     * 
     * @param args the command line arguments
     * @throws persona.PersonaException
     * @throws persona.MiCalendarioException
     */    
    public static void main(String[] args) throws PersonaException, MiCalendarioException, DAOException {        
        DAO<Alumno, Long> dao = null;
        Alumno alumno;
        try {
            Map<String, String> config = new HashMap<>();
            config.put(DAOFactory.TIPO_DAO, "SQL");                              
            config.put(DAOFactory.URL_DB, "jdbc:mysql://localhost:3306/unlam");
            config.put(DAOFactory.USUARIO_DB, "root");
            config.put(DAOFactory.PASS_DB, "");
                    
            dao = DAOFactory.getIntance().createDAO(config);
            
            MiCalendario fechaNac = new MiCalendario(1, 10, 1984);
            MiCalendario fechaIng = new MiCalendario(1, 3, 2020);
            alumno = new Alumno(24467027, "Ezequiel", "Saint", 'm', fechaNac, fechaIng, 1, 4.0);

            // create MYSQL: dao.create(alumno);
            
            // delete MYSQL: dao.delete(Long.valueOf(94489249));
            
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
            
            
            
            
            
            
            
            //AlumnoDAOTXT dao = new AlumnoDAOTXT("alumnos2.txt");
            
            //CREATE
            /*
            MiCalendario fechaNac = new MiCalendario(2, 11, 1981);
            MiCalendario fechaIng = new MiCalendario(1, 3, 2020);
            Alumno alu2 = new Alumno(90909088, "Gisele", "Galera", 'f', fechaNac, fechaIng, 10, 8.0);
            dao.create(alu2);
            System.out.println("Alumno ==>"+ alu2);
            */
            
            //READ
            /*
            Alumno alu3 = new Alumno();
            alu3.setDni(90909088);
            if (dao.read(alu3.getDni())!=null)
                System.out.println("Alumno Encontrado");
            */
            //UPDATE
            /*
            Alumno alu4 = dao.read(Long.valueOf(90909081));
            alu4.setApellido("Souza");
            dao.update(alu4);
            */
            
            //DELETE
            /*
            Alumno aluDeleteTXT = new Alumno();
            alu3.setDni(90909088);
            dao.delete(alu.getDni());
            */
            
            
            //daoTXT.delete(Long.valueOf(90909082));
            
            //LIST
            /*
            List<Alumno> alumnos = dao.findAll(false);
            //for (Alumno alumno : alumnos){
            alumnos.forEach((alumno) -> {
                System.out.println("Alumno ==> "+ alumno);
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
