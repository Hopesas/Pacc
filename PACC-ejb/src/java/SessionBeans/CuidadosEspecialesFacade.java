/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.CuidadosEspeciales;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Freddy
 */
@Stateless
public class CuidadosEspecialesFacade extends AbstractFacade<CuidadosEspeciales> {
    @PersistenceContext(unitName = "PACC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuidadosEspecialesFacade() {
        super(CuidadosEspeciales.class);
    }
    
}
