/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.Plan;
import Entity.Paciente;
import SessionBeans.GruposFacade;
import SessionBeans.PlanFacade;
import SessionBeans.PacienteFacade;
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
 * Esta clase bean se encarga de tener los elementos suficientes para poder mostrar
 * correctamente la interfaz "listaPAIE.xhtml".
 * @author Freddy
 */
@ManagedBean(name="PlanBusquedaBean")
@ViewScoped
public class PlanBusquedaBean {
    @EJB private ServicesBean servicesBean;
    @EJB private GruposFacade gruposFacade;
    @EJB private PacienteFacade pacienteFacade;
    @EJB private PlanFacade planFacade;
    @EJB private UserasFacade userasFacade;
    private List<PlanMP> planMPList;
    private List<Plan> planList;
    private boolean carga;
    private PlanMP selectedPlanMP;
    
    /**
     * Método constructor que inicializa las variables necesarias
     */
    public PlanBusquedaBean(){
        carga = false;
        selectedPlanMP = new PlanMP();
        planMPList = new ArrayList<>();
        planList = new ArrayList<>();
    }
    
    /**
     * Define el valor para la lista de plan de atención encontrados.
     * @param planesMPList 
     */
    public void setPlanesMPList(List<PlanMP> planesMPList) {
        this.planMPList = planesMPList;
    }
    
    /**
     * Devuelve la lista de plan de atención obteniendolos según el criterio 
     * específicado por el usuario y obteniendolo luego de la base de datos.
     * @return 
     */
    public List<PlanMP> getPlanesMPList() {
        if(!carga){
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            if(((Busqueda)session.getAttribute("criterio")).getId() == 1){
                System.err.println("entra a grupo");
                Long id = gruposFacade.findByCapitulo((String)session.getAttribute("campo")).getId();
                planList = planFacade.findByGrupo(id);
            }
            if(((Busqueda)session.getAttribute("criterio")).getId() == 2){
                Long id = pacienteFacade.findByDocumento(session.getAttribute("campo").toString()).getId();
                planList = planFacade.findByIdPaciente(id);
            }
            if(((Busqueda)session.getAttribute("criterio")).getId() == 3){
                String id = session.getAttribute("campo").toString().toString();
                planList = planFacade.findByIdEnfermero(id);
            }
            if(planList != null){
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

                    planMPList.add(dmp);
                }
                carga = true;
            }
        }
        return planMPList;
    }    

    /**
     * Devuelve el plan de atención seleccionado por el usuario en la interfaz.
     * @return 
     */
    public PlanMP getSelectedPlanMP() {
        return selectedPlanMP;
    }

    /**
     * Evento que define el Plan de atención seleccionado por el usuario.
     * @param event 
     */
    public void onRowSelect(SelectEvent event){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("plan", ((PlanMP)event.getObject()).getId());
    }
    
    /**
     * Define el plan de atención seleccionado.
     * @param selectedPlanMP 
     */
    public void setSelectedPlanMP(PlanMP selectedPlanMP) {
        if(selectedPlanMP != null){
            this.selectedPlanMP = selectedPlanMP;
        }
    }
}
