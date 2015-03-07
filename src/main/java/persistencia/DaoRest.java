package persistencia;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Izaquiel Cruz
 * @param <T>
 */
@Stateless
public class DaoRest<T>{
    
    @PersistenceContext(unitName = "com.mycompany_RestFBPos_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public void salvar(Object novo){
        em.persist(novo);
    }
    
    public void atualizar(Object o) {
        em.merge(o);
    }
    
    public void remover(Object o) {
        em.remove(em.merge(o));
    }
    
    public T buscar(String namedQuery, Map paramentros)throws NoResultException{
        
        Query consulta = em.createNamedQuery(namedQuery);
        
        Set<String> chaves = paramentros.keySet();
        
        for (String chave : chaves) {
            consulta.setParameter(chave, paramentros.get(chave));
        }
    
        return  (T) consulta.getSingleResult();
    
    }    
    
    public List<T> buscarTodos(String namedQuery, Map paramentros)throws NoResultException{
        
        Query consulta = em.createNamedQuery(namedQuery);
        
        Set<String> chaves = paramentros.keySet();
        
        for (String chave : chaves) {
            consulta.setParameter(chave, paramentros.get(chave));
        }
    
        return  consulta.getResultList();
    
    }    
    
    public List<T> buscarTodos(String namedQuery) {
        
        Query consulta = em.createNamedQuery(namedQuery);
        
        return consulta.getResultList();
        
    }    
    
    public void setStatus(String namedQuery, Map paramentros) {
        
        Query consulta = em.createNamedQuery(namedQuery);
        Set<String> chaves = paramentros.keySet();
        
        for (String chave : chaves) {
            consulta.setParameter(chave, paramentros.get(chave));
        }
        
        consulta.executeUpdate();
        
    }
    
}
