package persona;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 *
 * @author Grupo 13
 */
public class MiCalendario extends GregorianCalendar{

    public MiCalendario() {
    }
    
    public MiCalendario(Date date) {
        setTimeInMillis(date.getTime());
    }

    public MiCalendario(int dia, int mes, int anio) throws MiCalendarioException {
        super(anio, mes-1, dia); //no se puede poner nada antes del constructor padre - sino salta error (-1 para que el 0 del gregorian sea el enero)
        setLenient(false); // no permitir que seteen un valor erroneo
        
        try {
            get(Calendar.YEAR);
        }
        catch (Exception ex) {
            throw new MiCalendarioException ("Error en la fecha");
        }
    }
    

    /**
     * Getters
     * @return int  
     */
    public int getAnio(){
        return get(Calendar.YEAR);
    }

    public int getMes(){
        return get(Calendar.MONTH)+1;
    }

    public int getDia(){
        return get(Calendar.DAY_OF_MONTH);
    }

    public Date toSQLDate() {
        return new Date(this.getTimeInMillis());
    }

    /**
     * ToString
     * @return String DD/MM/YYYY 
     */
    @Override
    public String toString() {
        return String.format ("%2d/%2d/%4d", getDia(), getMes(), getAnio()); //va a leer el tostring de la clase padre GregorionCalendar o Calendar
    }
        
        
}
