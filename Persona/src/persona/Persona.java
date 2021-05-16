package persona;

import java.util.Calendar;

/**
 *
 * @author Grupo 13
 */
public class Persona {
// definir atributos de una persona - si sacamos el private, los atributos pasan a ser privados en paquete, o sea, dentro del proyecto son publico y fuera privados
    public static final String DELIM = "\t";
    
    private long dni;
    private String nombre;
    private String apellido;
    private char sexo;
    protected MiCalendario fechaNac; //es publico para la própia clase y sus hijas - pero es privado para afuera
    
        

/**
 * suit de constructores
     * @throws persona.PersonaException
 */
   
    public Persona() throws PersonaException{
        
    }  
    
/**
     * @param dni
     * @throws persona.PersonaException
 */  
    public Persona(long dni) throws PersonaException {
        setDni(dni);
    }  
    
/**
     * @param dni
     * @param nombre
     * @param apellido
     * @param fechaNac
     * @throws persona.PersonaException
 */    
    public Persona(long dni, String nombre, String apellido, char sexo, MiCalendario fechaNac) 
            throws PersonaException {
        setDni(dni);
        setNombre(nombre);
        setApellido(apellido);
        setSexo (sexo);
        setFechaNac (fechaNac);
    
    }
  
       
    /**
     * GETTER Y SETTERS
     * los atributos son privados pero los getters (consultar) / setters (modificar- escribir) son publicos para que puedan ser accedidos desde afuera
     * si los campos no tienen el modo de acceso private, al crear los getters/setters se puede elegir encapsulate field - eso hace que los atributos pasen a ser privados
     * @return   
     */
    public long getDni() {
        return dni;
    }

    public void setDni(long dni) throws PersonaException {
        if (dni<=0){
            throw new PersonaException("El DNI debe ser positivo ("+dni+")"); //si no se cumple con el IF - se le el this.dni
            
        }
        this.dni = dni; 
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws PersonaException {
        if (nombre==null || nombre.trim().equals("")){
            throw new PersonaException("El nombre no debe estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws PersonaException {
        if (apellido==null || apellido.trim().equals("")){
            throw new PersonaException("El apellido no debe estar vacío");
        }
        this.apellido = apellido;
    }

    public MiCalendario getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(MiCalendario fechaNac) {
        this.fechaNac = fechaNac;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) throws PersonaException {
        sexo = Character.toUpperCase(sexo);
        if (sexo!='F' && sexo!='M'){
            throw new PersonaException("El sexo debe ser F o M");
            
        }
        this.sexo = sexo; // para almacenar todo en mayúscula
    }
    
     
     @Override
    public String toString() {
        String nombreStr = nombre.length()>20?nombre.substring(0, 20):nombre;//corto y solo recupero hasta el 20
        String apellidoStr = apellido.length()>30?apellido.substring(0, 30):apellido;
        
        return String.format("%8d", dni) + DELIM + // el simbolo de menos es para agregar espacio si el valor no llega a 8 por ejemplo
               String.format("%15s", nombre) + DELIM + 
               String.format("%15s", apellido) + DELIM + 
               String.format("%1s", sexo) + DELIM + 
               fechaNac;
    }                  
                
}
