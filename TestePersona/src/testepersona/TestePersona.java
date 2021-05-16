package testepersona;



import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.AlumnoException;
import persona.MiCalendario;
import persona.MiCalendarioException;
import persona.Persona;
import persona.PersonaException;



/**

 *

 * @author Grupo 13

 */

public class TestePersona {



    /**

     * @param args the command line arguments
     * @throws persona.PersonaException

     */

    public static void main(String[] args) throws PersonaException {

        try {
/**
            persona.setDni(10);

            persona.setNombre("   José  Maria ");

            persona.setApellido("Garcia");

            

            persona.setFechaNac(new MiCalendario(29, 2, 2020));

            System.out.println(persona.getFechaNac());

            

            

            Persona maria = new Persona(20);

            maria.setNombre("María");

            maria.setApellido("Gomez");



            // Persona otraPersona = new Persona(30, "Juana", "Martinez");



            // Persona otraPersona2 = new Persona(40, "Miguel", "Juarez", null, 'm');

            // otraPersona2.setSexo('f');

            // otraPersona2.setFechaNac(new MiCalendario(2, 10, 2021));

            

            // System.out.println("Fecha Nac: "+ otraPersona2.getFechaNac());

             */ 

            MiCalendario fechaNac = new MiCalendario(23, 8, 1992);

            MiCalendario fechaIng = new MiCalendario(1, 3, 2021);

            Alumno alu = new Alumno(4004600, "Juan Luis", "Perez", 'M', fechaNac, fechaIng, 5, 0.335);

            // alu.setSexo('M');

            // alu.setActivo(false);

            

            System.out.println("Alumno ==>"+ alu);

            

        } catch (AlumnoException alumnoException) {

            Logger.getLogger(TestePersona.class.getName()).log(Level.SEVERE, null, alumnoException);

            return;

        } catch (PersonaException | MiCalendarioException ex) {

            Logger.getLogger(TestePersona.class.getName()).log(Level.SEVERE, null, ex);

            return;

        }

        

        System.out.println("TODO OK");

    }

    

}