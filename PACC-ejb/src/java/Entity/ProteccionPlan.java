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
@Table(name = "proteccion_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProteccionPlan.findAll", query = "SELECT p FROM ProteccionPlan p"),
    @NamedQuery(name = "ProteccionPlan.findByIdPlan", query = "SELECT p FROM ProteccionPlan p WHERE p.idPlan = :idPlan"),
    @NamedQuery(name = "ProteccionPlan.findByIdProteccion", query = "SELECT p FROM ProteccionPlan p WHERE p.idProteccion = :idProteccion"),
    @NamedQuery(name = "ProteccionPlan.findById", query = "SELECT p FROM ProteccionPlan p WHERE p.id = :id")})
public class ProteccionPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "id_plan", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Plan idPlan;
    @JoinColumn(name = "id_proteccion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProteccionEspecifica idProteccion;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="my_seq_prote", allocationSize=1, sequenceName="my_seq_prote", initialValue=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_seq_prote")
    private BigDecimal id;

    public ProteccionPlan() {
    }

    public ProteccionPlan(BigDecimal id) {
        this.id = id;
    }

    public ProteccionPlan(BigDecimal id, Plan idPlan, ProteccionEspecifica idProteccion) {
        this.id = id;
        this.idPlan = idPlan;
        this.idProteccion = idProteccion;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }

    public ProteccionEspecifica getIdProteccion() {
        return idProteccion;
    }

    public void setIdProteccion(ProteccionEspecifica idProteccion) {
        this.idProteccion = idProteccion;
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
        if (!(object instanceof ProteccionPlan)) {
            return false;
        }
        ProteccionPlan other = (ProteccionPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProteccionPlan[ id=" + id + " ]";
    }
    
}
