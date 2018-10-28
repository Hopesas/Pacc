/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Freddy
 */
@Entity
@Table(name = "deteccion_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeteccionPlan.findAll", query = "SELECT d FROM DeteccionPlan d"),
    @NamedQuery(name = "DeteccionPlan.findByIdPlan", query = "SELECT d FROM DeteccionPlan d WHERE d.idPlan = :idPlan"),
    @NamedQuery(name = "DeteccionPlan.findByIdDeteccion", query = "SELECT d FROM DeteccionPlan d WHERE d.idDeteccion = :idDeteccion"),
    @NamedQuery(name = "DeteccionPlan.findById", query = "SELECT d FROM DeteccionPlan d WHERE d.id = :id")})
public class DeteccionPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "id_plan", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Plan idPlan;
    @JoinColumn(name = "id_deteccion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DeteccionTemprana idDeteccion;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="my_seq_det", allocationSize=1, sequenceName="my_seq_det", initialValue=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_seq_det")
    private BigDecimal id;

    public DeteccionPlan() {
    }

    public DeteccionPlan(BigDecimal id) {
        this.id = id;
    }

    public DeteccionPlan(BigDecimal id, Plan idPlan, DeteccionTemprana idDeteccion) {
        this.id = id;
        this.idPlan = idPlan;
        this.idDeteccion = idDeteccion;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }

    public DeteccionTemprana getIdDeteccion() {
        return idDeteccion;
    }

    public void setIdDeteccion(DeteccionTemprana idDeteccion) {
        this.idDeteccion = idDeteccion;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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
        if (!(object instanceof DeteccionPlan)) {
            return false;
        }
        DeteccionPlan other = (DeteccionPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DeteccionPlan[ id=" + id + " ]";
    }
    
}
