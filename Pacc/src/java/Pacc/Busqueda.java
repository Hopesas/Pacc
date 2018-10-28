/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

/**
 * Clase entidad que define el criterio de busqueda de los planes 
 * en la base de datos.
 * @author Freddy
 */
public class Busqueda {
    private Long id;
    private String definicion;
    private String criterio;
    
    public Busqueda(Long id, String definicion, String criterio) {
        this.id = id;
        this.definicion = definicion;
        this.criterio = criterio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
    
}
