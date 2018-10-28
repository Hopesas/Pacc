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
@Table(name = "programas_especiales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramasEspeciales.findAll", query = "SELECT p FROM ProgramasEspeciales p"),
    @NamedQuery(name = "ProgramasEspeciales.findById", query = "SELECT p FROM ProgramasEspeciales p WHERE p.id = :id"),
    @NamedQuery(name = "ProgramasEspeciales.findByDescripcion", query = "SELECT p FROM ProgramasEspeciales p WHERE p.descripcion = :descripcion")})
public class ProgramasEspeciales implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrograma")
    private Collection<ProgramasPlan> programasPlanCollection;

    public ProgramasEspeciales() {
    }

    public ProgramasEspeciales(Long id) {
        this.id = id;
    }

    public ProgramasEspeciales(Long id, String descripcion) {
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

    public Collection<ProgramasPlan> getProgramasEspecialesCollection() {
        return programasPlanCollection;
    }

    public void setProgramasEspecialesCollection(Collection<ProgramasPlan> programasPlanCollection) {
        this.programasPlanCollection = programasPlanCollection;
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
        if (!(object instanceof ProgramasEspeciales)) {
            return false;
        }
        ProgramasEspeciales other = (ProgramasEspeciales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProgramasEspeciales[ id=" + id + " ]";
    }
    
}
