/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Grupos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Freddy
 */
@Stateless
public class GruposFacade extends AbstractFacade<Grupos> {
    @PersistenceContext(unitName = "PACC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GruposFacade() {
        super(Grupos.class);
    }
    
    /**
     * Busca los patrónes funcionales según el id.
     * @param id
     * @return 
     */
    public Grupos findById(long id){
        try{
            Query nq = getEntityManager().createNamedQuery("Grupos.findById");
            nq.setParameter("id", id);

            Grupos grupo = (Grupos)nq.getSingleResult();
            if(grupo == null){
                return null;
            }else{
                return grupo;
            }
        }catch (Exception ex){
            return null;
        }
    }
    
    /**
     * Busca los patrónes funcionales según el id.
     * @param capitulo
     * @return 
     */
    public Grupos findByCapitulo(String capitulo){
        try{
            Query nq = getEntityManager().createNamedQuery("Grupos.findByCapitulo");
            nq.setParameter("capitulo", capitulo);

            Grupos grupo = (Grupos)nq.getSingleResult();
            if(grupo == null){
                return null;
            }else{
                return grupo;
            }
        }catch (Exception ex){
            return null;
        }
    }
}
