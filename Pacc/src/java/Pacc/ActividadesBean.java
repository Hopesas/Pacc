/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.ActividadesEspecificas;
import SessionBeans.ActividadesEspecificasFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 * Esta clase bean se encarga de controlar todos los procesos correspondientes
 * a los Actividades "step1.xhtml".
 * @author Freddy
 */
@ManagedBean(name="ActividadesBean")
@ViewScoped
public class ActividadesBean {
    @EJB
    private ActividadesEspecificasFacade actividadesFacade;
    private List<ActividadesEspecificas> actividadesList;
    private List<ActividadesEspecificas> actividadesSeleccionadas;
    private List<Long> actividadesIndex;
    private boolean carga;
    
    /**
     * Constructor que se encarga de inicializar las variables necesarias.
     */
    public ActividadesBean(){
        carga = false;
        actividadesIndex = new ArrayList<>();
        actividadesList = new ArrayList<>();
        actividadesSeleccionadas = new ArrayList<>();
    }

    /**
     * Devuelve la lista de Actividades obteniendolos desde la base de datos.
     * @return 
     */
    public List<ActividadesEspecificas> getActividadesList() {
        if(!carga){
            carga = true;
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            actividadesList = actividadesFacade.findByGrupo((String)session.getAttribute("paso1"));
        }
        return actividadesList;
    }

    
    /**
     * Devuelve el Actividad seleccionado por el usuario.
     * @return 
     */
    public void setSelectedActividad(List<ActividadesEspecificas> actividadesSeleccionadas) {
        if(actividadesSeleccionadas != null){
            this.actividadesSeleccionadas = actividadesSeleccionadas;
        }
    }
    
    /**
     * Devuelve el Actividad seleccionado por el usuario.
     * @return 
     */
    public List<ActividadesEspecificas> getSelectedActividad() {
        return actividadesSeleccionadas;
    }
    
    /**
     * Este evento se dispara cuando el usuario selecciona un Actividad.
     * @param event 
     */
    public void onRowSelectActividadesOut(SelectEvent event) {
        actividadesIndex = new ArrayList<>();
        actividadesIndex.add(((ActividadesEspecificas) event.getObject()).getId());
    }
    
    /**
     * Este evento se activa cuando se selecciona una característica.
     * @param event 
     */
    public void onRowSelectActividades(SelectEvent event){
        actividadesIndex.add(((ActividadesEspecificas) event.getObject()).getId());
    }
    
    /**
     * Este evento se activa cuando se des-seleccionan una característica desde la interfaz.
     * @param event 
     */
    public void onRowUnselectActividades(UnselectEvent event){
        actividadesIndex.remove(((ActividadesEspecificas) event.getObject()).getId());
    }
    
    /**
     * Este método se usa para poder pasar al siguiente paso "step2.xhtml" 
     * guardando los datos que el usuario ha ingresado para el Actividad.
     * @return 
     */
    public String continueStepThree(){
        if(actividadesIndex.isEmpty()){
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
            return "";
        } else {
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("paso2", actividadesIndex);
            System.out.println();
            return "cuidadosNutricionales.xhtml?faces-redirect=true";
        }
    }
    
     /**
     * Se devuelve al paso 1 "step1.xhtml" eliminando los datos del paso 1 para resetearlos.
     * @return 
     */
    public String backStepOne(){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("paso1");
        return "step1.xhtml?faces-redirect=true";
    }
}
