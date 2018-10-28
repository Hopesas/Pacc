/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.Paciente;
import Entity.Plan;
import SessionBeans.PacienteFacade;
import SessionBeans.PlanFacade;
import SessionBeans.ServicesBean;
import SessionBeans.UserasFacade;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;

/**
 * Clase bean que controla los procesos relacionados con la interfaz "misPlanes.xhtml"
 * @author Freddy
 */
@ManagedBean(name="MisPlanesBean")
@ViewScoped
public class MisPlanesBean {
    @EJB private ServicesBean servicesBean;
    @EJB private PacienteFacade pacienteFacade;
    @EJB private PlanFacade planFacade;
    @EJB private UserasFacade userasFacade;
    private List<PlanMP> planesMPList;
    private List<Plan> planList;
    private boolean carga;
    private PlanMP selectedPlanMP;
    
    /**
     * Constructor que inicializa las variables necesarias.
     */
    public MisPlanesBean(){
        carga = false;
        selectedPlanMP = new PlanMP();
        planesMPList = new ArrayList<>();
        planList = new ArrayList<>();
    }

    /**
     * Retorna la lista de los planes que ha realizado el usuario.
     * @param diagnosticosMPList 
     */
    public void setPlansMPList(List<PlanMP> diagnosticosMPList) {
        this.planesMPList = diagnosticosMPList;
    }
    
    /**
     * Método que obtiene los Planes que ha realizado el enfermero desde la base de datos, 
     * les da formato para mostrarlos en la interfaz.
     * @return 
     */
    public List<PlanMP> getPlansMPList() {
        if(!carga){
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            String idEnfermero = session.getAttribute("usuario").toString();
            planList = planFacade.findByIdEnfermero(idEnfermero);
            for(Plan d: planList){
                PlanMP dmp = new PlanMP();
                Paciente paciente = servicesBean.buscarPaciente(d.getIdPaciente());
                String docpaciente = paciente.getDocumento();
                String nombre = paciente.getNombre() + " " + paciente.getSegundoNombre() + " " + paciente.getApellido()
                        + " " + paciente.getSegundoApellido();
                dmp.setDocumentoPaciente(docpaciente);
                dmp.setNombre(nombre);
                
                Date fecha = d.getFecha();
                Calendar cal = Calendar.getInstance();
                cal.setTime(fecha);
                String fechaStr = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
                
                
                dmp.setFecha(fechaStr);
                dmp.setId(d.getId());
                 
                planesMPList.add(dmp);
            }
            carga = true;
        }
        return planesMPList;
    }    

    /**
     * Devuelve el Plan seleccionado por el usuario.
     * @return 
     */
    public PlanMP getSelectedPlanMP() {
        return selectedPlanMP;
    }

    /**
     * Este método se dispara cuando el usuario selecciona un plan.
     * @param event 
     */
    public void onRowSelect(SelectEvent event){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("plan", ((PlanMP)event.getObject()).getId());
    }
    
    /**
     * Este método define el plan seleccionado.
     * @param selectedPlanMP 
     */
    public void setSelectedPlanMP(PlanMP selectedPlanMP) {
        if(selectedPlanMP != null){
            this.selectedPlanMP = selectedPlanMP;
        }
    }
}
