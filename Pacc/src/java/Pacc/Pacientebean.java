/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.Paciente;
import SessionBeans.PacienteFacade;
import SessionBeans.ServicesBean;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 * Esta clase bean es la que se encarga de todos los procesos relacionados con el 
 * manejo de datos de los pacientes.
 * @author Freddy
 */
@ManagedBean(name="PacienteBean")
@ViewScoped
public class Pacientebean {

    @EJB
    private ServicesBean servicesBean;
    @EJB
    private PacienteFacade pacienteFacade;
    private Paciente paciente;
    private BigInteger documento;
    
    /**
     * Constructor que inicializa el paciente.
     */
    public Pacientebean(){
        paciente = new Paciente();
    }

    /**
     * Este métoddo devuelve el objeto paciente.
     * @return 
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Este método define el paciente.
     * @param paciente 
     */
    public void setPaciente(Paciente paciente) {
        if(paciente != null){
            this.paciente = paciente;
        }
    }

    /**
     * Este método devuelve el documento definido por el usuario.
     * @return 
     */
    public BigInteger getDocumento() {
        return documento;
    }

    /**
     * Este método define el documento que tiene que ingresar el usuario.
     * @param documento 
     */
    public void setDocumento(BigInteger documento) {
        this.documento = documento;
    }
    
    /**
     * Este método válida si el paciente ya existe, y lo selecciona para que
     * se le pueda realizar un Plan de Atención.
     */
    public void buscarPaciente(){
        paciente = servicesBean.buscarPaciente(documento.toString());
                //pacienteFacade.findByDocumento(documento);
        if(paciente.getDocumento() == null){
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
        }else{
        FacesContext.getCurrentInstance().addMessage("pacienteMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Se ha seleccionado el paciente: " + paciente.getNombre() + " " + paciente.getApellido() ,  
                "Sample message"));
        RequestContext.getCurrentInstance().update("pacienteMessage");
        }
    }
    
    /**
     * Verifica si el paciente existe, de no ser así, se crea el paciente.
     */
    public void createPaciente(){
        Paciente p = pacienteFacade.findByDocumento(documento.toString());
        if(p == null){
            pacienteFacade.create(paciente);
            paciente = pacienteFacade.findByDocumento(paciente.getDocumento());
            if(paciente != null){
                FacesContext.getCurrentInstance().addMessage("pacienteMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Se ha creado el paciente." ,  
                        "Sample message"));
                RequestContext.getCurrentInstance().update("pacienteMessage");
            }else {
                FacesContext.getCurrentInstance().addMessage("pacienteMessage", new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                        "No se pudo crear el paciente." ,  
                        "Sample message"));
                RequestContext.getCurrentInstance().update("pacienteMessage");
            }
            paciente = null;
            documento = null;
        }else{
                FacesContext.getCurrentInstance().addMessage("pacienteMessage", new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                        "El paciente ya existe." ,  
                        "Sample message"));
                RequestContext.getCurrentInstance().update("pacienteMessage");
            
        }
    }
    
    /**
     * Se dirige al paso 1 "step1.xhtml" para iniciar el Plan de atención al paciente
     * correspondiente.
     * @return 
     */
    public String goStepOne(){
        if(paciente.getDocumento() == null){
            RequestContext.getCurrentInstance().execute("PF('errorSelDialog').show()");
            return "";
        }else {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("paciente", paciente);
            return "step1.xhtml?faces-redirect=true";
        }
    }
}
