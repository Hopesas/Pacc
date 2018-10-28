/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.DeteccionTemprana;
import Entity.ProgramasEspeciales;
import Entity.ProteccionEspecifica;
import SessionBeans.DeteccionTempranaFacade;
import SessionBeans.ProgramasEspecialesFacade;
import SessionBeans.ProteccionEspecificaFacade;
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
@ManagedBean(name="ProgramasBean")
@ViewScoped
public class ProgramasBean {
    @EJB
    private DeteccionTempranaFacade deteccionTempranaFacade;
    @EJB
    private ProteccionEspecificaFacade proteccionEspecificaFacade;
    @EJB
    private ProgramasEspecialesFacade programasEspecialesFacade;
    private List<ProteccionEspecifica> proteccionEspecificaList;
    private List<DeteccionTemprana> deteccionList;
    private List<ProgramasEspeciales> programasEspecialesList;
    private List<ProteccionEspecifica> programasProteccionSeleccionadas;
    private List<DeteccionTemprana> deteccionSeleccionadas;
    private List<ProgramasEspeciales> programasEspecialesSeleccionadas;
    private List<Long> programasProteccionIndex;
    private List<Long> deteccionIndex;
    private List<Long> programasEspecialesIndex;
    private boolean cargaProteccionEspecifica = false;
    private boolean cargaProteccionEspecial = false;
    private boolean cargaDeteccion = false;
    
    /**
     * Constructor que se encarga de inicializar las variables necesarias.
     */
    public ProgramasBean(){
        cargaProteccionEspecifica = false;
        cargaProteccionEspecial = false;
        cargaDeteccion = false;
        proteccionEspecificaList = new ArrayList<>();
        deteccionList = new ArrayList<>();
        programasEspecialesList = new ArrayList<>();
        programasProteccionSeleccionadas = new ArrayList<>();
        deteccionSeleccionadas = new ArrayList<>();
        programasEspecialesSeleccionadas = new ArrayList<>();
        programasProteccionIndex = new ArrayList<>();
        deteccionIndex = new ArrayList<>();
        programasEspecialesIndex = new ArrayList<>();
    }

    /**
     * Devuelve la lista de Actividades obteniendolos desde la base de datos.
     * @return 
     */
    public List<ProteccionEspecifica> getProteccionEspecificaList() {
        if(!cargaProteccionEspecifica){
            cargaProteccionEspecifica = true;
            proteccionEspecificaList = proteccionEspecificaFacade.findAll();
        }
        return proteccionEspecificaList;
    }
    

    /**
     * Devuelve la lista de Actividades obteniendolos desde la base de datos.
     * @return 
     */
    public List<DeteccionTemprana> getDeteccionList() {
        if(!cargaDeteccion){
            cargaDeteccion = true;
            deteccionList = deteccionTempranaFacade.findAll();
        }
        return deteccionList;
    }
    

    /**
     * Devuelve la lista de Actividades obteniendolos desde la base de datos.
     * @return 
     */
    public List<ProgramasEspeciales> getProteccionEspecialList() {
        if(!cargaProteccionEspecial){
            cargaProteccionEspecial = true;
            programasEspecialesList = programasEspecialesFacade.findAll();
        }
        return programasEspecialesList;
    }

    
    /**
     * Devuelve el Actividad seleccionado por el usuario.
     * @return 
     */
    public void setSelectedProteccionEspecifica(List<ProteccionEspecifica> proteccionEspecificaList) {
        if(proteccionEspecificaList != null){
            this.programasProteccionSeleccionadas = proteccionEspecificaList;
        }
    }
    
    /**
     * Devuelve el Actividad seleccionado por el usuario.
     * @return 
     */
    public List<ProteccionEspecifica> getSelectedProteccionEspecifica() {
        return programasProteccionSeleccionadas;
    }

    
    /**
     * Devuelve el Actividad seleccionado por el usuario.
     * @return 
     */
    public void setSelectedDeteccionTemprana(List<DeteccionTemprana> deteccionTempranas) {
        if(deteccionTempranas != null){
            this.deteccionSeleccionadas = deteccionTempranas;
        }
    }
    
    /**
     * Devuelve el Actividad seleccionado por el usuario.
     * @return 
     */
    public List<DeteccionTemprana> getSelectedDeteccionTemprana() {
        return deteccionSeleccionadas;
    }

    
    /**
     * Devuelve el Actividad seleccionado por el usuario.
     * @return 
     */
    public void setSelectedProgramasEspeciales(List<ProgramasEspeciales> proteccionEspeciales) {
        if(proteccionEspeciales != null){
            this.programasEspecialesSeleccionadas = proteccionEspeciales;
        }
    }
    
    /**
     * Devuelve el Actividad seleccionado por el usuario.
     * @return 
     */
    public List<ProgramasEspeciales> getSelectedProgramasEspeciales() {
        return programasEspecialesSeleccionadas;
    }
    
    /**
     * Este evento se dispara cuando el usuario selecciona un Actividad.
     * @param event 
     */
    public void onRowSelectProgramasProteccionOut(SelectEvent event) {
        programasProteccionIndex = new ArrayList<>();
        programasProteccionIndex.add(((ProteccionEspecifica) event.getObject()).getId());
    }
    
    /**
     * Este evento se activa cuando se selecciona una característica.
     * @param event 
     */
    public void onRowSelectProgramasProteccion(SelectEvent event){
        programasProteccionIndex.add(((ProteccionEspecifica) event.getObject()).getId());
    }
    
    /**
     * Este evento se activa cuando se des-seleccionan una característica desde la interfaz.
     * @param event 
     */
    public void onRowUnselectProgramasProteccion(UnselectEvent event){
        programasProteccionIndex.remove(((ProteccionEspecifica) event.getObject()).getId());
    }
    
        
    /**
     * Este evento se dispara cuando el usuario selecciona un Actividad.
     * @param event 
     */
    public void onRowSelectProgramasEspecialesOut(SelectEvent event) {
        programasEspecialesIndex = new ArrayList<>();
        programasEspecialesIndex.add(((ProgramasEspeciales) event.getObject()).getId());
    }
    
    /**
     * Este evento se activa cuando se selecciona una característica.
     * @param event 
     */
    public void onRowSelectProgramasEspeciales(SelectEvent event){
        programasEspecialesIndex.add(((ProgramasEspeciales) event.getObject()).getId());
    }
    
    /**
     * Este evento se activa cuando se des-seleccionan una característica desde la interfaz.
     * @param event 
     */
    public void onRowUnselectProgramasEspeciales(UnselectEvent event){
        programasEspecialesIndex.remove(((ProgramasEspeciales) event.getObject()).getId());
    }
        
    /**
     * Este evento se dispara cuando el usuario selecciona un Actividad.
     * @param event 
     */
    public void onRowSelectDeteccionTempranaOut(SelectEvent event) {
        deteccionIndex = new ArrayList<>();
        deteccionIndex.add(((DeteccionTemprana) event.getObject()).getId());
    }
    
    /**
     * Este evento se activa cuando se selecciona una característica.
     * @param event 
     */
    public void onRowSelectDeteccionTemprana(SelectEvent event){
        deteccionIndex.add(((DeteccionTemprana) event.getObject()).getId());
    }
    
    /**
     * Este evento se activa cuando se des-seleccionan una característica desde la interfaz.
     * @param event 
     */
    public void onRowUnselectDeteccionTemprana(UnselectEvent event){
        deteccionIndex.remove(((DeteccionTemprana) event.getObject()).getId());
    }
    
    /**
     * Este método se usa para poder pasar al siguiente paso "step4.xhtml" 
     * guardando los datos que el usuario ha ingresado para el Programa.
     * @return 
     */
    public String continueStepFour(){
        int suma = programasEspecialesIndex.size() + programasProteccionIndex.size() + deteccionIndex.size();
        if(suma == 0){
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
            return "";
        }else {
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("paso3proteccionEspecificas", programasProteccionIndex);
            session.setAttribute("paso3programasEspeciales", programasEspecialesIndex);
            session.setAttribute("paso3deteccion", deteccionIndex);
            System.out.println();
            return "finalStep.xhtml?faces-redirect=true";
        }
    }
    
     /**
     * Se devuelve al paso 2 "step2.xhtml" eliminando los datos del paso 1 para resetearlos.
     * @return 
     */
    public String backStepTwo(){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("cuidadoEspecial");
        return "cuidadosEspeciales.xhtml?faces-redirect=true";
    }
}
