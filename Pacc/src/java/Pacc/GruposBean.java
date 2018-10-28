/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.Grupos;
import SessionBeans.GruposFacade;
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
 * a los Grupoes "step1.xhtml".
 * @author Freddy
 */
@ManagedBean(name="GruposBean")
@ViewScoped
public class GruposBean {
    @EJB
    private GruposFacade gruposFacade;
    private List<Grupos> gruposList;
    private Grupos grupoSeleccionado;
    private boolean carga;
    
    /**
     * Constructor que se encarga de inicializar las variables necesarias.
     */
    public GruposBean(){
        carga = false;
        gruposList = new ArrayList<>();
        grupoSeleccionado = new Grupos();
    }

    /**
     * Devuelve la lista de Grupoes obteniendolos desde la base de datos.
     * @return 
     */
    public List<Grupos> getGruposList() {
        if(!carga){
            carga = true;
            gruposList = gruposFacade.findAll();
        }
        return gruposList;
    }

    /**
     * Devuelve el Grupo seleccionado por el usuario.
     * @return 
     */
    public Grupos getSelectedGrupo() {
        return grupoSeleccionado;
    }

    /**
     * Define el Grupo que el usuario ha selecciónado en la interfaz.
     * @param selectedGrupo 
     */
    public void setSelectedGrupo(Grupos selectedGrupo) {
        if(selectedGrupo != null){
            this.grupoSeleccionado = selectedGrupo;
        }
    }
    
    /**
     * Este evento se dispara cuando el usuario selecciona un Grupo.
     * @param event 
     */
    public void onRowSelect(SelectEvent event){
        grupoSeleccionado = gruposFacade.find(((Grupos) event.getObject()).getId());
    }
    
    /**
     * Este método se usa para poder pasar al siguiente paso "step2.xhtml" 
     * guardando los datos que el usuario ha ingresado para el Grupo.
     * @return 
     */
    public String continueStepOne(){
        if(grupoSeleccionado.getId()==null){
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
            return "";
        }else{
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("paso1", grupoSeleccionado.getCapitulo());
            System.out.println();
            return "step2.xhtml?faces-redirect=true";
        }
    }
}
