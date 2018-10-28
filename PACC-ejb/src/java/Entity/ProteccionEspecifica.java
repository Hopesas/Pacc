/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Freddy
 */
@Entity
@Table(name = "proteccion_especifica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteccionEspecifica.findAll", query = "SELECT p FROM ProteccionEspecifica p"),
    @NamedQuery(name = "ProteccionEspecifica.findById", query = "SELECT p FROM ProteccionEspecifica p WHERE p.id = :id"),
    @NamedQuery(name = "ProteccionEspecifica.findByDescripcion", query = "SELECT p FROM ProteccionEspecifica p WHERE p.descripcion = :descripcion")})
public class ProteccionEspecifica implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProteccion")
    private Collection<ProteccionPlan> proteccionPlanCollection;

    public ProteccionEspecifica() {
    }

    public ProteccionEspecifica(Long id) {
        this.id = id;
    }

    public ProteccionEspecifica(Long id, String descripcion) {
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

    public Collection<ProteccionPlan> getProteccionPlanCollection() {
        return proteccionPlanCollection;
    }

    public void setProteccionPlanCollection(Collection<ProteccionPlan> proteccionPlanCollection) {
        this.proteccionPlanCollection = proteccionPlanCollection;
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
        if (!(object instanceof ProteccionEspecifica)) {
            return false;
        }
        ProteccionEspecifica other = (ProteccionEspecifica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProteccionEspecifica[ id=" + id + " ]";
    }
    
}
