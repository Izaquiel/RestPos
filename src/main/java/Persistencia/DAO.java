/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

/**
 *
 * @author Joew
 */
public interface DAO {
    public boolean save(Object o);
    
    public Object find(Class classe, Object objetct);
    
    public boolean Update(Object o);
    
    public boolean delete(Object o);
}
