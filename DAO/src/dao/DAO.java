
package dao;

import java.util.List;

/**
 *
 * @author gisele.galera
 * @param <T> Tipo de dato para la entidad
 * @param <K> Tipo de dato para la clave (dni)
 */
public abstract class DAO<T, K> {
    
    public abstract void create(T entidad)throws DAOException; //cualquier error se devuelve con una expception
    public abstract T read (K clave)throws DAOException; // acá se pasa la T
    public abstract void update (T entidad)throws DAOException; // se recibe una entidad y se pisa todos los registros - no se devuelve nada
    public abstract void delete (K clave)throws DAOException; // se recibe una clave y se borra la linea referente - no se devuelve nada
    public abstract List<T> findAll(Boolean activos) throws DAOException;  // devuelve una lista de alumnos
    
    
    /**
     *
     * @param clave
     * @return
     * @throws DAOException
     */
    public abstract boolean existe(K clave)throws DAOException;
}
