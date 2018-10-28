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
@Table(name = "deteccion_temprana")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeteccionTemprana.findAll", query = "SELECT d FROM DeteccionTemprana d"),
    @NamedQuery(name = "DeteccionTemprana.findById", query = "SELECT d FROM DeteccionTemprana d WHERE d.id = :id"),
    @NamedQuery(name = "DeteccionTemprana.findByDescripcion", query = "SELECT d FROM DeteccionTemprana d WHERE d.descripcion = :descripcion")})
public class DeteccionTemprana implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDeteccion")
    private Collection<DeteccionPlan> deteccionPlanCollection;
    
    public DeteccionTemprana() {
    }

    public DeteccionTemprana(Long id) {
        this.id = id;
    }

    public DeteccionTemprana(Long id, String descripcion) {
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

    public Collection<DeteccionPlan> getDeteccionPlanCollection() {
        return deteccionPlanCollection;
    }

    public void setDeteccionPlanCollection(Collection<DeteccionPlan> deteccionPlanCollection) {
        this.deteccionPlanCollection = deteccionPlanCollection;
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
        if (!(object instanceof DeteccionTemprana)) {
            return false;
        }
        DeteccionTemprana other = (DeteccionTemprana) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DeteccionTemprana[ id=" + id + " ]";
    }
    
}
