/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Parametros;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Freddy
 */
@Stateless
public class ParametrosFacade extends AbstractFacade<Parametros> {
    @PersistenceContext(unitName = "PACC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametrosFacade() {
        super(Parametros.class);
    }
    
    public Parametros findByNombre(String nombre) {
        try{
            Query nq = getEntityManager().createNamedQuery("Parametros.findByNombre");
            nq.setParameter("nombre", nombre);

            Parametros parametro = (Parametros)nq.getSingleResult();
            if(parametro == null){
                return null;
            }else{
                return parametro;
            }
        }catch (Exception ex){
            return null;
        }
    }
}
