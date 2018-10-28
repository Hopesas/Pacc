/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Esta clase bean controla todos los procesos que se llevan a cabo en la interfaz
 * Buscar Plan "buscarPAIE.xhtml".
 * @author Freddy
 */
@ManagedBean(name="BusquedaBean")
@SessionScoped
public class BusquedaBean {
    private List<Busqueda> busquedaList;
    private Busqueda selectedCriterio;
    private String campo;
    
    /**
     * Este constructor define las opciones que se van a colocar en la interfaz, 
     * además de inicializar las variables.
     */
    public BusquedaBean(){
        selectedCriterio = new Busqueda(new Long(0), "No se ha seleccionado el criterio", "Seleccione");
        busquedaList = new ArrayList<>();
        busquedaList.add(new Busqueda(new Long(1), "Buscar por Grupo", "Grupo"));
        busquedaList.add(new Busqueda(new Long(2), "Buscar por Documento de Paciente", "Documento del Paciente"));
        busquedaList.add(new Busqueda(new Long(3), "Buscar por nombre de Usuario", "Nombre del Usuario"));
    }

    /**
     * Devuelve el criterio seleccionado.
     * @return 
     */
    public Busqueda getSelectedCriterio() {
        return selectedCriterio;
    }

    /**
     * Define el criterio seleccionado por el usario.
     * @param selectedCriterio 
     */
    public void setSelectedCriterio(Busqueda selectedCriterio) {
        if(selectedCriterio != null){
            this.selectedCriterio = selectedCriterio;
        }
    }

    /**
     * Devuelve la lista con los criterios de busqueda.
     * @return 
     */
    public List<Busqueda> getBusquedaList() {
        return busquedaList;
    }

    /**
     * Define la lista con los criterios de busqueda.
     * @param busquedaList 
     */
    public void setBusquedaList(List<Busqueda> busquedaList) {
        this.busquedaList = busquedaList;
    }
    
    /**
     * Este evento define el criterio cuando el usuario lo selecciona de la lista.
     * @param event 
     */
    public void onRowSelect(SelectEvent event){
        selectedCriterio = ((Busqueda) event.getObject());
    }

    /**
     * Devuelve la una string con el criterio seleccionado para colocarlo en la 
     * interfaz "campoPAIE.xhtml"
     * @return 
     */
    public String getCampo() {
        return campo;
    }

    /**
     * Define el campo, string del criterio que ha seleccionado el usario.
     * @param campo 
     */
    public void setCampo(String campo) {
        if(campo != null){
            this.campo = campo;
        }
    }
    
    /**
     * Permite al navegador continuar a la ágina "listaPAIE.xhtml".
     * @return 
     */
    public String buscarDiagnosticos(){
        if(campo != null){
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("campo", campo);
            System.out.println();
            return "listaPacc.xhtml?faces-redirect=true";
        }else{
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
            return "";
        }
    }
    
    /**
     * Permite al navegador continuar con la página "campoPAIE.xhtml"
     * @return 
     */
    public String continueToList(){
        if(selectedCriterio.getId()==null){
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
            return "";
        }else{
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("criterio", selectedCriterio);
            System.out.println();
            return "campoPacc.xhtml?faces-redirect=true";
        }
    }
}
