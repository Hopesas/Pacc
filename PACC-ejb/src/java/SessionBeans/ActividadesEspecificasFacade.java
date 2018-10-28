/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.ActividadesEspecificas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Freddy
 */
@Stateless
public class ActividadesEspecificasFacade extends AbstractFacade<ActividadesEspecificas> {
    @PersistenceContext(unitName = "PACC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadesEspecificasFacade() {
        super(ActividadesEspecificas.class);
    }
    
        
    /**
     * Busca los Nanda según el grupo.
     * @param grupo
     * @return 
     */
    public List<ActividadesEspecificas> findByGrupo(String grupo) {
        try{
            Query nq = getEntityManager().createNamedQuery("ActividadesEspecificas.findByGrupo");
            nq.setParameter("grupo", grupo);

            List grupoList = nq.getResultList();
            if(grupoList == null){
                return null;
            }else{
                return grupoList;
            }
        }catch (Exception ex){
            return null;
        }
    }
}
