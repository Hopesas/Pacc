/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacc;

import java.math.BigInteger;

/**
 *
 * @author Freddy
 */
public class PlanMP {
    private Long id;
    private String documentoPaciente;
    private String Nombre;
    private String fecha;

    public PlanMP(Long id, String documentoPaciente, String Nombre, String fecha) {
        this.id = id;
        this.documentoPaciente = documentoPaciente;
        this.Nombre = Nombre;
        this.fecha = fecha;
    }

    public PlanMP() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentoPaciente() {
        return documentoPaciente;
    }

    public void setDocumentoPaciente(String documentoPaciente) {
        this.documentoPaciente = documentoPaciente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
