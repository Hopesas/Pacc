/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Useras;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Esta clase se encarga de controlar el CRUD de los Usuarios en la base de datos.
 * @author Freddy
 */
@Stateless
public class UserasFacade extends AbstractFacade<Useras> {
    @PersistenceContext(unitName = "PACC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserasFacade() {
        super(Useras.class);
    }
    
    /**
     * Busca los usuarios según el username, el alias usado para ingresar.
     * @param username
     * @return 
     */
    public Useras findByName(String username) {
        try{
            Query nq = getEntityManager().createNamedQuery("Useras.findByUsername");
            nq.setParameter("username", username);

            List userasList = nq.getResultList();
            if(userasList == null){
                return null;
            }else{
                return (Useras)userasList.get(0);
            }
        }catch (Exception ex){
            return null;
        }
    }

    public Useras findById(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
