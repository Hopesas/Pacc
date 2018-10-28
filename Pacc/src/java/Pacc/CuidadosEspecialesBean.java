/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.CuidadosEspeciales;
import SessionBeans.CuidadosEspecialesFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Esta clase bean se encarga de controlar todos los procesos correspondientes
 * a los Cuidados Especiales "cuidadosEspeciales.xhtml".
 * @author Freddy
 */
@ManagedBean(name="CuidadosEspecialesBean")
@ViewScoped
public class CuidadosEspecialesBean {
    @EJB
    private CuidadosEspecialesFacade cuidadosFacade;
    private List<CuidadosEspeciales> cuidadosList;
    private CuidadosEspeciales cuidadoSeleccionado;
    private boolean carga;
    
    /**
     * Constructor que se encarga de inicializar las variables necesarias.
     */
    public CuidadosEspecialesBean(){
        carga = false;
        cuidadosList = new ArrayList<>();
        cuidadoSeleccionado = new CuidadosEspeciales();
    }

    /**
     * Devuelve la lista de CuidadoEspeciales obteniendolos desde la base de datos.
     * @return 
     */
    public List<CuidadosEspeciales> getCuidadosEspecialesList() {
        if(!carga){
            carga = true;
            cuidadosList = cuidadosFacade.findAll();
        }
        return cuidadosList;
    }

    /**
     * Devuelve el CuidadoEspecial seleccionado por el usuario.
     * @return 
     */
    public CuidadosEspeciales getSelectedCuidadoEspecial() {
        return cuidadoSeleccionado;
    }

    /**
     * Define el CuidadoEspecial que el usuario ha selecciónado en la interfaz.
     * @param selectedCuidadoEspecial 
     */
    public void setSelectedCuidadoEspecial(CuidadosEspeciales selectedCuidadoEspecial) {
        if(selectedCuidadoEspecial != null){
            this.cuidadoSeleccionado = selectedCuidadoEspecial;
        }
    }
    
    /**
     * Este evento se dispara cuando el usuario selecciona un CuidadoEspecial.
     * @param event 
     */
    public void onRowSelect(SelectEvent event){
        cuidadoSeleccionado = cuidadosFacade.find(((CuidadosEspeciales) event.getObject()).getId());
    }
    
    /**
     * Este método se usa para poder pasar al siguiente paso "step2.xhtml" 
     * guardando los datos que el usuario ha ingresado para el CuidadoEspecial.
     * @return 
     */
    public String continueStepThree(){
        if(cuidadoSeleccionado.getId()==null){
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
            return "";
        }else{
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("cuidadoEspecial", cuidadoSeleccionado.getId());
            System.out.println();
            return "step3.xhtml?faces-redirect=true";
        }
    }
    
     /**
     * Se devuelve al paso 2 "step2.xhtml" eliminando los datos del paso 1 para resetearlos.
     * @return 
     */
    public String backStepCuidadosNutricionales(){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("pasoCuidados");
        return "cuidadosNutricionales.xhtml?faces-redirect=true";
    }
}
