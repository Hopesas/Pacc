/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
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
@Table(name = "programas_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramasPlan.findAll", query = "SELECT p FROM ProgramasPlan p"),
    @NamedQuery(name = "ProgramasPlan.findByIdPlan", query = "SELECT p FROM ProgramasPlan p WHERE p.idPlan = :idPlan"),
    @NamedQuery(name = "ProgramasPlan.findByIdPrograma", query = "SELECT p FROM ProgramasPlan p WHERE p.idPrograma = :idPrograma"),
    @NamedQuery(name = "ProgramasPlan.findById", query = "SELECT p FROM ProgramasPlan p WHERE p.id = :id")})
public class ProgramasPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "id_plan", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Plan idPlan;
    @JoinColumn(name = "id_programa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProgramasEspeciales idPrograma;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="my_seq_progra", allocationSize=1, sequenceName="my_seq_progra", initialValue=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_seq_progra")
    private Long id;

    public ProgramasPlan() {
    }

    public ProgramasPlan(Long id) {
        this.id = id;
    }

    public ProgramasPlan(Long id, Plan idPlan, ProgramasEspeciales idPrograma) {
        this.id = id;
        this.idPlan = idPlan;
        this.idPrograma = idPrograma;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }

    public ProgramasEspeciales getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(ProgramasEspeciales idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(object instanceof ProgramasPlan)) {
            return false;
        }
        ProgramasPlan other = (ProgramasPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProgramasPlan[ id=" + id + " ]";
    }
    
}
