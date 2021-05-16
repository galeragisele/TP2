
package testedao;

import dao.AlumnoDAOTXT;
import dao.DAOException;
import java.util.List;
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
public class TesteDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersonaException, MiCalendarioException {
        
        try {
            AlumnoDAOTXT dao = new AlumnoDAOTXT("alumnos.txt");
            MiCalendario fechaNac = new MiCalendario(2, 11, 1981);
            MiCalendario fechaIng = new MiCalendario(1, 3, 2020);
             Alumno alu = new Alumno(90909084, "Gisele", "Galera", 'f', fechaNac, fechaIng, 10, 8.0);
            //  System.out.println("Alumno ==>"+ alu);
            
            // dao.create(alu);
           /*if (dao.read(alu.getDni())!=null)
                System.out.println("Alumno Encontrado");
            */
            // dao.delete(alu.getDni());
            Alumno gisele = dao.read(Long.valueOf(90909081));
            gisele.setApellido("Souza");
            dao.update(gisele);
            
            
            dao.delete(Long.valueOf(90909082));
            /*List<Alumno> alumnos = dao.findAll(false);
            for (Alumno alumno : alumnos){
                System.out.println("Alumno ==>"+ alumno.getDni());
            }
            */
        } catch (DAOException ex) {
            Logger.getLogger(TesteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
