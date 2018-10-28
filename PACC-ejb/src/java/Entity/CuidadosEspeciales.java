/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Freddy
 */
@Entity
@Table(name = "cuidados_especiales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuidadosEspeciales.findAll", query = "SELECT c FROM CuidadosEspeciales c"),
    @NamedQuery(name = "CuidadosEspeciales.findById", query = "SELECT c FROM CuidadosEspeciales c WHERE c.id = :id"),
    @NamedQuery(name = "CuidadosEspeciales.findByDescripcion", query = "SELECT c FROM CuidadosEspeciales c WHERE c.descripcion = :descripcion")})
public class CuidadosEspeciales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    public CuidadosEspeciales() {
    }

    public CuidadosEspeciales(Long id) {
        this.id = id;
    }

    public CuidadosEspeciales(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuidadosEspeciales)) {
            return false;
        }
        CuidadosEspeciales other = (CuidadosEspeciales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CuidadosEspeciales[ id=" + id + " ]";
    }
    
}
