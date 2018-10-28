/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import Entity.Useras;
import SessionBeans.UserasFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 * Esta clase bean se encarga de controlar los procesos correspondientes a los
 * usuarios que manipulan la aplicación.
 * @author Freddy
 */
@ManagedBean(name="UserAsBean")
@ViewScoped
public class UserAsBean implements Serializable{
    @EJB
    private UserasFacade userasFacade;
    private Pattern pattern;
    private Matcher matcher;
    private Useras selectedUsera;
    private Vector userasList;
    private String newPassword;
    private String newPassword2;
    private Useras newUsera;
    private boolean carga;
    
    /**
     * Constructor que inicializa las variables necesarias para la clase, 
     * la variable <code> patter </code>, es el patrón que deben seguir las contraseñas de los usuarios.
     */
    public UserAsBean(){
        pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@.#$&%_-]).{6,15})");
        carga = false;
        userasList = new Vector();
        selectedUsera = new Useras();
        newUsera = new Useras();
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public Useras getNewUsera() {
        return newUsera;
    }

    public void setNewUsera(Useras newUsera) {
        if(newUsera != null){
            this.newUsera = newUsera;
        }
    }
    
    /**
     * Se encarga de devolver la lista de todos los usuarios inscritos en la aplicación.
     * @return 
     */
    public Vector getUserasList() {
        if(!carga){
            carga = true;
            userasList = (Vector) userasFacade.findAll();
        }
        return userasList;
    }
    
    public Useras getSelectedUsera() {
        return selectedUsera;
    }

    public void setSelectedUsera(Useras selectedUsera) {
        if(selectedUsera != null){
            this.selectedUsera = selectedUsera;
        }
    }
 
    /**
     * Este método se dispara en el momento en el que el administrador seleccione
     * un usuario de la lista en la interfaz.
     * @param event 
     */
    public void onRowSelect(SelectEvent event){
        selectedUsera = userasFacade.findByName(((Useras) event.getObject()).getUsername());
    }
    
    /**
     * Muestra el dialogo de creación de un nuevo usuario.
     */
    public void showDialog(){
        RequestContext.getCurrentInstance().execute("PF('createDialog').show()");
    }
    
    /**
     * Muestra el dialogo de edición de los usuarios.
     */
    public void showEditDialog(){
        if(selectedUsera.getNombre() == null){
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
        }else{
            RequestContext.getCurrentInstance().execute("PF('modifyDialog').show()");
        }
    }
    
    /**
     * Muestra un dialogo de confirmación de eliminación de usuario.
     */
    public void showDeleteDialog(){
        if(selectedUsera.getNombre() == null){
            RequestContext.getCurrentInstance().execute("PF('errorDialog').show()");
        }else{
            RequestContext.getCurrentInstance().execute("PF('deleteDialog').show()");
        }
    }
    
    /**
     * Determina si el administrador tiene derechos para poder entrar a la página
     * de administración de usuarios.
     * @return
     * @throws IOException 
     */
    public String hasRights() throws IOException{
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(!(boolean)session.getAttribute("administrator")){
            FacesContext.getCurrentInstance().getExternalContext().redirect("errorAdminPage.xhtml");
        }
        return "";
    }
    
    /**
     * Se encarga de crear un nuevo usuario y colocarlo en la base de datos.
     * @return 
     */
    public String createUseras(){
        try{
            userasFacade.create(newUsera);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("passMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Error al crear el usuario, por favor contacte al administrador.", "Sample error message"));
            RequestContext.getCurrentInstance().update("passMessage");
        }
        return "administrarusuarios.xhtml?faces-redirect=true";
    }
    
    /**
     * Cambia la contraseña personal por parte del usuario, verificando siempre
     * que cumpla con el patrón dado y además de que estas no sean nulas.
     */
    public void changePassword(){
        if(newPassword!= null && newPassword2 != null){
            if(newPassword.length() > 6 && newPassword.length() < 15 && newPassword.equals(newPassword2)){
                matcher = pattern.matcher(newPassword);
                if(matcher.matches()){
                    HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    Useras currentUser = userasFacade.find(session.getAttribute("id"));
                    currentUser.setPassword(newPassword);
                    userasFacade.edit(currentUser);
                    FacesContext.getCurrentInstance().addMessage("passMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                            "La contraseña se ha cambiado con exito.", "Sample error message"));
                    RequestContext.getCurrentInstance().update("passMessage");
                }else{
                    FacesContext.getCurrentInstance().addMessage("passMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "La contraseña debe tener al menos un caracter en minúscula, uno en mayúscula, debe tener un número,"
                            + " un caracter especial @.#$&%_- y la longitud debe ser mayor a 6 y menor a 15", "Sample error message"));
                    RequestContext.getCurrentInstance().update("passMessage");
                }
            }else{
                FacesContext.getCurrentInstance().addMessage("passMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "No se puede cambiar la contraseña", "Sample error message"));
                RequestContext.getCurrentInstance().update("passMessage");
            }
        }else {
            FacesContext.getCurrentInstance().addMessage("passMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Por favor escriba una contraseña", "Sample error message"));
            RequestContext.getCurrentInstance().update("passMessage");
        }
    }
    
    /**
     * Cambia la contraseña de un usuario por parte del Administrador, verificando siempre
     * que cumpla con el patrón dado y además de que estas no sean nulas.
     */
    public void editUseras(){
        if(newPassword!= null && newPassword2 != null){
            if(newPassword.length() > 6 && newPassword.length() < 15 && newPassword.equals(newPassword2)){
                Crypt c = new Crypt();
                selectedUsera.setPassword(c.cryptWithMD5(newPassword));
                userasFacade.edit(selectedUsera);
                FacesContext.getCurrentInstance().addMessage("passMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "La contraseña se ha cambiado con exito.", "Sample error message"));
                RequestContext.getCurrentInstance().update("passMessage");
                
            }else{
                FacesContext.getCurrentInstance().addMessage("passMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "No se puede cambiar la contraseña", "Sample error message"));
                RequestContext.getCurrentInstance().update("passMessage");
            }
        }else {
            FacesContext.getCurrentInstance().addMessage("passMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Por favor escriba una contraseña", "Sample error message"));
            RequestContext.getCurrentInstance().update("passMessage");
        }
        RequestContext.getCurrentInstance().execute("PF('modifyDialog').hide()");
    }
    
    /**
     * Método que se encarga de eliminar de la base de datos
     * el usuario seleccionado por el administrador.
     */
    public void deleteUseras(){
        try {
            userasFacade.remove(selectedUsera);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("passMessage", new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                    "Error, por favor contacte a su administrador..", "Sample error message"));
            RequestContext.getCurrentInstance().update("passMessage");
        }
        RequestContext.getCurrentInstance().execute("PF('deleteDialog').hide()");
    }
    
    public String createAdminUseras(){
        Crypt c = new Crypt();
        Useras ad = new Useras();
        ad.setNombre("Admin");
        ad.setApellido("Admin");
        ad.setPassword(c.cryptWithMD5("fforer"));
        ad.setDocumento(123);
        ad.setSegundoNombre("Ad");
        ad.setSegundoApellido("Ad");
        ad.setUsername("admin");
        ad.setAdministrator(true);
        try{
            Useras aux = userasFacade.findByName(ad.getUsername());
            if(aux == null){
                userasFacade.create(ad);
            }
        } catch (Exception ex){
            
        }
        
        return "";
    }
}