/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "actividades_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadesPlan.findAll", query = "SELECT a FROM ActividadesPlan a"),
    @NamedQuery(name = "ActividadesPlan.findByIdPlan", query = "SELECT a FROM ActividadesPlan a WHERE a.idPlan = :idPlan"),
    @NamedQuery(name = "ActividadesPlan.findByIdActividad", query = "SELECT a FROM ActividadesPlan a WHERE a.idActividad = :idActividad"),
    @NamedQuery(name = "ActividadesPlan.findById", query = "SELECT a FROM ActividadesPlan a WHERE a.id = :id")})
public class ActividadesPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="my_seq_act", allocationSize=1, sequenceName="my_seq_act", initialValue=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_seq_act")
    @Column(name = "id")
    private BigDecimal id;
    @JoinColumn(name = "id_plan", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Plan idPlan;
    @JoinColumn(name = "id_actividad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ActividadesEspecificas idActividad;

    public ActividadesPlan() {
    }

    public ActividadesPlan(BigDecimal id) {
        this.id = id;
    }

    public ActividadesPlan(BigDecimal id, Plan idPlan, ActividadesEspecificas idActividad) {
        this.id = id;
        this.idPlan = idPlan;
        this.idActividad = idActividad;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }

    public ActividadesEspecificas getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(ActividadesEspecificas idActividad) {
        this.idActividad = idActividad;
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
        if (!(object instanceof ActividadesPlan)) {
            return false;
        }
        ActividadesPlan other = (ActividadesPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ActividadesPlan[ id=" + id + " ]";
    }
    
}
