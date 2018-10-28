/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Freddy
 */
@Entity
@Table(name = "plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
    @NamedQuery(name = "Plan.findById", query = "SELECT p FROM Plan p WHERE p.id = :id"),
    @NamedQuery(name = "Plan.findByIdGrupo", query = "SELECT p FROM Plan p WHERE p.idGrupo = :idGrupo"),
    @NamedQuery(name = "Plan.findByIdUseras", query = "SELECT d FROM Plan d WHERE d.idUseras = :idUseras"),
    @NamedQuery(name = "Plan.findByIdPaciente", query = "SELECT d FROM Plan d WHERE d.idPaciente = :idPaciente")
    })
public class Plan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @SequenceGenerator(name="my_seq_plan", allocationSize=1, sequenceName="my_seq_plan", initialValue=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_seq_plan")
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_grupo")
    private Long idGrupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_Cuidado")
    private Long idCuidado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_paciente")
    private String idPaciente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_useras")
    private String idUseras;
    @Column(name = "cuidados_especiales")
    private Long cuidadosEspeciales;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan")
    private Collection<ActividadesPlan> actividadesPlanCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan")
    private Collection<DeteccionPlan> deteccionPlanCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan")
    private Collection<ProgramasPlan> programasPlanCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan")
    private Collection<ProteccionPlan> proteccionPlanCollection;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdUseras() {
        return idUseras;
    }

    public void setIdUseras(String idUseras) {
        this.idUseras = idUseras;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Collection<ActividadesPlan> getActividadesPlanCollection() {
        return actividadesPlanCollection;
    }

    public void setActividadesPlanCollection(Collection<ActividadesPlan> actividadesPlanCollection) {
        this.actividadesPlanCollection = actividadesPlanCollection;
    }

    public Long getIdCuidado() {
        return idCuidado;
    }

    public void setIdCuidado(Long idCuidado) {
        this.idCuidado = idCuidado;
    }

    public Long getCuidadosEspeciales() {
        return cuidadosEspeciales;
    }

    public void setCuidadosEspeciales(Long cuidadosEspeciales) {
        this.cuidadosEspeciales = cuidadosEspeciales;
    }

    public Collection<DeteccionPlan> getDeteccionPlanCollection() {
        return deteccionPlanCollection;
    }

    public void setDeteccionPlanCollection(Collection<DeteccionPlan> deteccionPlanCollection) {
        this.deteccionPlanCollection = deteccionPlanCollection;
    }

    public Collection<ProgramasPlan> getProgramasPlanCollection() {
        return programasPlanCollection;
    }

    public void setProgramasPlanCollection(Collection<ProgramasPlan> programasPlanCollection) {
        this.programasPlanCollection = programasPlanCollection;
    }

    public Collection<ProteccionPlan> getProteccionPlanCollection() {
        return proteccionPlanCollection;
    }

    public void setProteccionPlanCollection(Collection<ProteccionPlan> proteccionPlanCollection) {
        this.proteccionPlanCollection = proteccionPlanCollection;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Plan[ id=" + id + " ]";
    }
    
}
