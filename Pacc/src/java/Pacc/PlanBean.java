/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.*;
import SessionBeans.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 * Esta clase bean es la que se encarga de darle forma y de guardar en la base de datos
 * el plan que se esté realizando, se ejecuta en las intefaces "finalStep.xhtml" y "paie.xhtml".
 * @author Freddy
 */
@ManagedBean(name="PlanBean")
@ViewScoped
public class PlanBean {
    @EJB private DeteccionTempranaFacade deteccionTempranaFacade;
    @EJB private ProteccionEspecificaFacade proteccionEspecificaFacade;
    @EJB private ProgramasEspecialesFacade programasEspecialesFacade;
    @EJB private ActividadesEspecificasFacade actividadesEspecificasFacade;
    @EJB private PlanFacade planFacade;
    @EJB private GruposFacade gruposFacade;
    
    
    private Plan plan;
    private Long codigo;
    private boolean isDiagnostico;
    
    /**
     * Inicializa las variables.
     */
    public PlanBean(){
        plan = new Plan();
        isDiagnostico = false;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("diagnostico", new Long(-1));
    }

    /**
     * Devuelve el código del plan que se esté realizando
     * @return 
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * define el código del plan que se esté realizando.
     * @param codigo 
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Variable booleana que define si hay diagnóstico o no.
     * @return 
     */
    public boolean isIsDiagnostico() {
        return isDiagnostico;
    }
    
    /**
     * Método que crea el diagnóstico al final del proceso.
     */
    public void createPlan(){
    try{
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            //Se obtiene el paciente y sus atributos.
            Paciente paciente = (Paciente)session.getAttribute("paciente");

            //Se obtienen los datos de todos los pasos.
            Grupos grupo =  gruposFacade.findByCapitulo((String)session.getAttribute("paso1"));
                       
            
            //Obtiene los demás datos faltantes para completar la base de datos.
            plan.setIdUseras(session.getAttribute("usuario").toString());
            plan.setIdPaciente(paciente.getDocumento());
            plan.setIdGrupo(grupo.getId().longValue());
            plan.setFecha(new Date());
            plan.setIdCuidado((Long)session.getAttribute("pasoCuidados"));
            plan.setCuidadosEspeciales((Long)session.getAttribute("cuidadoEspecial"));
            
            List<Long> actividadesIndex = (List<Long>)session.getAttribute("paso2");
            List<Long> programasProteccionIndex = (List<Long>)session.getAttribute("paso3proteccionEspecificas");
            List<Long> programasEspecialesIndex = (List<Long>)session.getAttribute("paso3programasEspeciales");
            List<Long> deteccionIndex = (List<Long>)session.getAttribute("paso3deteccion");
            
            
            List<ActividadesPlan> actividadesList = new ArrayList<>();
            List<ProgramasPlan> programasList = new ArrayList<>();
            List<ProteccionPlan> proteccionList = new ArrayList<>();
            List<DeteccionPlan> deteccionList = new ArrayList<>();
            
            //Da formato a las características.
            for(Long l : actividadesIndex){
                ActividadesEspecificas aes = actividadesEspecificasFacade.find(l);
                ActividadesPlan ap = new ActividadesPlan();
                ap.setIdActividad(aes);
                ap.setIdPlan(plan);
                actividadesList.add(ap);
            }
            
            //Da formato a las características.
            for(Long l : programasEspecialesIndex){
                ProgramasEspeciales aes = programasEspecialesFacade.find(l);
                ProgramasPlan ap = new ProgramasPlan();
                ap.setIdPrograma(aes);
                ap.setIdPlan(plan);
                programasList.add(ap);
            }
            
            //Da formato a las características.
            for(Long l : programasProteccionIndex){
                ProteccionEspecifica aes = proteccionEspecificaFacade.find(l);
                ProteccionPlan ap = new ProteccionPlan();
                ap.setIdProteccion(aes);
                ap.setIdPlan(plan);
                proteccionList.add(ap);
            }
            
            //Da formato a las características.
            for(Long l : deteccionIndex){
                DeteccionTemprana aes = deteccionTempranaFacade.find(l);
                DeteccionPlan ap = new DeteccionPlan();
                ap.setIdDeteccion(aes);
                ap.setIdPlan(plan);
                deteccionList.add(ap);
            }

            plan.setProgramasPlanCollection(programasList);
            plan.setActividadesPlanCollection(actividadesList);
            plan.setProteccionPlanCollection(proteccionList);
            plan.setDeteccionPlanCollection(deteccionList);
           
            planFacade.create(plan);
            session.setAttribute("plan", plan.getId());
            RequestContext.getCurrentInstance().update("createPdflink");
            
        }catch (javax.persistence.RollbackException ex){
            FacesContext.getCurrentInstance().addMessage("finalMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Error, no se pudo crear el pdf", "Sample error message"));
                    RequestContext.getCurrentInstance().update("finalMessage");
        }
    }
    
    /**
     * Se busca el diganóstico en la base de datos y se coloca en memoria para poder
     * realizar más acciones con este.
     */
    public void buscarPlan(){
        Plan d = planFacade.find(codigo);
        if(d != null){
           HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
           session.setAttribute("plan", d.getId());
            isDiagnostico = true;
            RequestContext.getCurrentInstance().update("createPdflink");
            FacesContext.getCurrentInstance().addMessage("planMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Se encontró el plan." ,  
                    "Sample message"));
            RequestContext.getCurrentInstance().update("planMessage");
        }else{
            FacesContext.getCurrentInstance().addMessage("planMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "No se ha encontrado el plan." ,  
                    "Sample message"));
            RequestContext.getCurrentInstance().update("planMessage");
        }
    }
}