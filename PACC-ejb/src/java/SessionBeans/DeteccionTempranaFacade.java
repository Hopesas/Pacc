/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.DeteccionTemprana;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Freddy
 */
@Stateless
public class DeteccionTempranaFacade extends AbstractFacade<DeteccionTemprana> {
    @PersistenceContext(unitName = "PACC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeteccionTempranaFacade() {
        super(DeteccionTemprana.class);
    }
    
}
