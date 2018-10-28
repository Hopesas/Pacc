/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Esta clase bean controla los procesos de verificación de sesión y deslogueo.
 * @author Freddy
 */
@ManagedBean(name="LogoutBean")
@SessionScoped
public class LogoutBean implements Serializable{
    
    /**
     * Método que desloguea al usuario de la aplicación.
     * @param request
     * @return 
     */
    public String logout(HttpServletRequest request){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "login.xhtml?faces-redirect=true";
    }
    
    /**
     * Devuelve el nombre para ser mostrado en las interfaces.
     * @param request
     * @return 
     */
    public String displayName(HttpServletRequest request) {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session == null){
            return "";
        }else{
            return (String)session.getAttribute("nombre") + " " + (String)session.getAttribute("apellido");
        }
    }
    
    /**
     * Redirige a la página de administración de usuarios.
     * @param request
     * @return 
     */
    public String gotoAdminUser(HttpServletRequest request) {
        return "administrarusuarios.xhtml?faces-redirect=true";
    }
    
    /**
     * Verifica si la sesión se encuentra activa, en caso contrario devuelve a la página "logín.xhtml"
     * @param request
     * @return
     * @throws IOException 
     */
    public String isSessionActive(HttpServletRequest request) throws IOException{
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("nombre") == null){
            session.invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
        return (String)session.getAttribute("nombre") + " " + (String)session.getAttribute("apellido");
    }
    
    /**
     * Determina si el usuario posee permisos de administrador.
     * @param request
     * @return 
     */
    public boolean isAdministrator(HttpServletRequest request){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("nombre") == null){
            try {
                session.invalidate();
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LogoutBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (boolean)session.getAttribute("administrator");
    }
}
