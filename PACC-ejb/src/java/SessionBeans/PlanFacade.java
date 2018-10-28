/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Plan;
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
public class PlanFacade extends AbstractFacade<Plan> {
    @PersistenceContext(unitName = "PACC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanFacade() {
        super(Plan.class);
    }

    public List<Plan> findByIdEnfermero(String idEnfermero) {
        try{
            Query nq = getEntityManager().createNamedQuery("Plan.findByIdUseras");
            nq.setParameter("idUseras", idEnfermero);

            List<Plan> planes = (List<Plan>)nq.getResultList();
            if(planes == null){
                return null;
            }else{
                return planes;
            }
        }catch (Exception ex){
            return null;
        }
    }
    
    public List<Plan> findByIdPaciente(Long idPaciente) {
        try{
            Query nq = getEntityManager().createNamedQuery("Plan.findByIdPaciente");
            nq.setParameter("idPaciente", idPaciente);

            List<Plan> planes = (List<Plan>)nq.getResultList();
            if(planes == null){
                return null;
            }else{
                return planes;
            }
        }catch (Exception ex){
            return null;
        }
    }

    public List<Plan> findByGrupo(Long grupo) {
        try{
            Query nq = getEntityManager().createNamedQuery("Plan.findByIdGrupo");
            nq.setParameter("idGrupo", grupo);

            List<Plan> planes = (List<Plan>)nq.getResultList();
            if(planes == null){
                return null;
            }else{
                return planes;
            }
        }catch (Exception ex){
            return null;
        }
    }
    
}
