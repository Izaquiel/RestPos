/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Joew
 */
public class DaoJPA implements DAO{

    private EntityManagerFactory emf;
    private EntityManager em;
    
    //construtores
    public DaoJPA (String persistenceUnitName){
        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        this.em = emf.createEntityManager();
    }
    
    
    public boolean save(Object o) {
      try{
            getEm().getTransaction().begin();
            getEm().persist(o);
            getEm().getTransaction().commit();
        return true;
      } catch (Exception e) {
            e.printStackTrace();
            getEm().getTransaction().rollback();
            return false;
        }
    }

    public Object find(Class classe, Object objetct) {
        return getEm().find(classe, objetct);
    }

    public boolean Update(Object o) {
       try{
            getEm().getTransaction().begin();
            getEm().merge(o);
            getEm().getTransaction().commit();
           return true;
       }catch (Exception e) {
            e.printStackTrace();
            getEm().getTransaction().rollback();
            return false;
        }
    }

    public boolean delete(Object o) {
       try{
            getEm().getTransaction().begin();
            getEm().remove(o);
            getEm().getTransaction().commit();
           return true;
       }catch (Exception e) {
            e.printStackTrace();
            getEm().getTransaction().rollback();
            return false;
        }
    }
    
    
    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
    
}
