/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entity.Paciente;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Esta clase se encarga de controlar el CRUD de los Pacientes en la base de datos.
 * @author Freddy
 */
@Stateless
public class PacienteFacade extends AbstractFacade<Paciente> {
    @PersistenceContext(unitName = "PACC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteFacade() {
        super(Paciente.class);
    }
    
    /**
     * Busca los Pacientes según el documento de identidad.
     * @param documento
     * @return 
     */
    public Paciente findByDocumento(String documento) {
        try{
            Query nq = getEntityManager().createNamedQuery("Paciente.findByDocumento");
            nq.setParameter("documento", documento);

            Paciente paciente = (Paciente)nq.getSingleResult();
            if(paciente == null){
                return null;
            }else{
                return paciente;
            }
        }catch (Exception ex){
            return null;
        }
    }
    
    /**
     * Busca los pacientes según el id.
     * @param id
     * @return 
     */
    public Paciente findById(Long id) {
        try{
            Query nq = getEntityManager().createNamedQuery("Paciente.findById");
            nq.setParameter("id", id);

            Paciente paciente = (Paciente)nq.getSingleResult();
            if(paciente == null){
                return null;
            }else{
                return paciente;
            }
        }catch (Exception ex){
            return null;
        }
    }
    
}
