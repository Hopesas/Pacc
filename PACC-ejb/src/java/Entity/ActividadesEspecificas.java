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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Freddy
 */
@Entity
@Table(name = "actividades_especificas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadesEspecificas.findAll", query = "SELECT a FROM ActividadesEspecificas a"),
    @NamedQuery(name = "ActividadesEspecificas.findById", query = "SELECT a FROM ActividadesEspecificas a WHERE a.id = :id"),
    @NamedQuery(name = "ActividadesEspecificas.findByGrupo", query = "SELECT a FROM ActividadesEspecificas a WHERE a.grupo = :grupo"),
    @NamedQuery(name = "ActividadesEspecificas.findByDescripcion", query = "SELECT a FROM ActividadesEspecificas a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "ActividadesEspecificas.findByDefinicion", query = "SELECT a FROM ActividadesEspecificas a WHERE a.definicion = :definicion")})
public class ActividadesEspecificas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "grupo")
    private String grupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4095)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4095)
    @Column(name = "definicion")
    private String definicion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActividad")
    private Collection<ActividadesPlan> actividadesPlanCollection;

    public ActividadesEspecificas() {
    }

    public ActividadesEspecificas(Long id) {
        this.id = id;
    }

    public ActividadesEspecificas(Long id, String grupo, String descripcion, String definicion) {
        this.id = id;
        this.grupo = grupo;
        this.descripcion = descripcion;
        this.definicion = definicion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    @XmlTransient
    public Collection<ActividadesPlan> getActividadesPlanCollection() {
        return actividadesPlanCollection;
    }

    public void setActividadesPlanCollection(Collection<ActividadesPlan> actividadesPlanCollection) {
        this.actividadesPlanCollection = actividadesPlanCollection;
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
        if (!(object instanceof ActividadesEspecificas)) {
            return false;
        }
        ActividadesEspecificas other = (ActividadesEspecificas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ActividadesEspecificas[ id=" + id + " ]";
    }
    
}
