
package persona;

/**
 *
 * @author Grupo 13
 */
public class Alumno extends Persona{
    private MiCalendario fechaIngreso;
    private Integer cantMatAprob;
    private Double promedio;
    private boolean activo = true;

    /**
     * Suits de constructores
     * @throws persona.PersonaException
     */
    public Alumno() throws PersonaException{
       super();
   } 


    public Alumno(MiCalendario fechaIngreso, Integer cantMatAprob, Double promedio)throws PersonaException{
        setFechaIngreso (fechaIngreso);
        setCantMatAprob (cantMatAprob);
        setPromedio (promedio);
    }

    
    public Alumno(long dni, MiCalendario fechaIngreso, Integer cantMatAprob, Double promedio) throws PersonaException{
        super(dni);
        setFechaIngreso (fechaIngreso);
        setCantMatAprob (cantMatAprob);
        setPromedio (promedio);
    }

    public Alumno(long dni, String nombre, String apellido, char sexo, MiCalendario fechaNac,
            MiCalendario fechaIngreso, Integer cantMatAprob, Double promedio) throws PersonaException {
        super(dni, nombre, apellido, sexo, fechaNac);
        setFechaIngreso (fechaIngreso);
        setCantMatAprob (cantMatAprob);
        setPromedio (promedio);
    }
    
    

    public Alumno(long dni, String nombre, String apellido, char sexo, MiCalendario fechaNac, 
            MiCalendario fechaIngreso, Integer cantMatAprob, Double promedio, boolean activo) throws PersonaException{
        super(dni, nombre, apellido, sexo, fechaNac); // está invocando el contructor del padre
        setFechaIngreso (fechaIngreso);
        setCantMatAprob (cantMatAprob);
        setPromedio (promedio);
        setActivo (activo);
    }
    

    
    
    public MiCalendario getFechaIngreso (){
        return fechaIngreso;
    }

    public void setFechaIngreso(MiCalendario fechaIngreso) throws AlumnoException {
        if (fechaNac==null) {
            throw new AlumnoException ("La fecha de nacimiento debe ser ingresada");
        }
        
        if (fechaNac.after(fechaIngreso)) {
            throw new AlumnoException ("La fecha de ingreso debe ser mayor a la fecha de nacimiento");
        }
       
        this.fechaIngreso = fechaIngreso;
    }

    
    
    public Integer getCantMatAprob(){
        return cantMatAprob;
    }

    
    public void setCantMatAprob(Integer cantMatAprob) throws AlumnoException {
         if (cantMatAprob<0){
            throw new AlumnoException ("La cantidad de materia aprobada debe ser igual a 0 o mayor");
         }
        this.cantMatAprob = cantMatAprob;
    }

    
    public Double getPromedio() {
        return promedio;
    }

    
    
    public void setPromedio(Double promedio) throws AlumnoException {
        if (promedio<0 || promedio>10)
            throw new AlumnoException ("El promedio debe ser 'igual o mayor a 0' y 'igual o menor a 10'");
        
        this.promedio = promedio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    

    @Override
    public String toString() {
       
        
        return super.toString () + DELIM + 
               fechaIngreso + DELIM + 
               String.format("%2d", cantMatAprob) + DELIM + 
               String.format("%5.2f", promedio)+ DELIM + 
               (activo?'A':'B');
    }                  
        
    
    
}
