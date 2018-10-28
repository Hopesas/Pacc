/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.CuidadosNutricionales;
import SessionBeans.CuidadosNutricionalesFacade;
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
 * a los Cuidados Nutricionales "cuidadosNutricionales.xhtml".
 * @author Freddy
 */
@ManagedBean(name="CuidadosBean")
@ViewScoped
public class CuidadosBean {
    @EJB
    private CuidadosNutricionalesFacade cuidadosNutricionalesFacade;
    private List<CuidadosNutricionales> cuidadosNutricionalesList;
    private CuidadosNutricionales selectedCuidado;
    private boolean carga;
    
    /**
     * Constructor que se encarga de inicializar las variables necesarias.
     */
    public CuidadosBean(){
        carga = false;
        cuidadosNutricionalesList = new ArrayList<>();
        selectedCuidado = new CuidadosNutricionales();
    }

    /**
     * Devuelve la lista de Cuidados Nutricionales obteniendolos desde la base de datos.
     * @return 
     */
    public List<CuidadosNutricionales> getCuidadosNutricionalesList() {
        if(!carga){
            carga = true;
            cuidadosNutricionalesList = cuidadosNutricionalesFacade.findAll();
        }
        return cuidadosNutricionalesList;
    }

    
    /**
     * Devuelve el Cuidado seleccionado por el usuario.
     * @return 
     */
    public void setSelectedCuidado(CuidadosNutricionales cuidadosNutricionalesSeleccionadas) {
        if(cuidadosNutricionalesSeleccionadas != null){
            this.selectedCuidado = cuidadosNutricionalesSeleccionadas;
        }
    }
    
    /**
     * Devuelve el Cuidado seleccionado por el usuario.
     * @return 
     */
    public CuidadosNutricionales getSelectedCuidado() {
        return selectedCuidado;
    }
    
    /**
     * Este evento se dispara cuando el usuario selecciona un Cuidado.
     * @param event 
     */
    public void onRowSelectCuidadosNutricionales(SelectEvent event) {
        selectedCuidado = ((CuidadosNutricionales) event.getObject());
    }
    
    /**
     * Este método se usa para poder pasar al siguiente paso "step2.xhtml" 
     * guardando los datos que el usuario ha ingresado para el Cuidado.
     * @return 
     */
    public String continueStepThree(){
        if(selectedCuidado.getId() == null){
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
            return "";
        }else {
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("pasoCuidados", selectedCuidado.getId());
            return "cuidadosEspeciales.xhtml?faces-redirect=true";
        }
    }
    
     /**
     * Se devuelve al paso 2 "step2.xhtml" eliminando los datos del paso 1 para resetearlos.
     * @return 
     */
    public String backStepTwo(){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("paso2");
        return "step2.xhtml?faces-redirect=true";
    }
}
