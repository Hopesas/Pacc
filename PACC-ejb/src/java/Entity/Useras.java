/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Posee todos los usuarios que pueden ingresar a la aplicación.
 * @author Freddy
 */
@Entity
@Table(name = "useras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Useras.findAll", query = "SELECT u FROM Useras u"),
    @NamedQuery(name = "Useras.findById", query = "SELECT u FROM Useras u WHERE u.id = :id"),
    @NamedQuery(name = "Useras.findByAdministrator", query = "SELECT u FROM Useras u WHERE u.administrator = :administrator"),
    @NamedQuery(name = "Useras.findByApellido", query = "SELECT u FROM Useras u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Useras.findByDocumento", query = "SELECT u FROM Useras u WHERE u.documento = :documento"),
    @NamedQuery(name = "Useras.findByNombre", query = "SELECT u FROM Useras u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Useras.findByPassword", query = "SELECT u FROM Useras u WHERE u.password = :password"),
    @NamedQuery(name = "Useras.findByUsername", query = "SELECT u FROM Useras u WHERE u.username = :username"),
    @NamedQuery(name = "Useras.findBySegundoNombre", query = "SELECT u FROM Useras u WHERE u.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "Useras.findBySegundoApellido", query = "SELECT u FROM Useras u WHERE u.segundoApellido = :segundoApellido")})
public class Useras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="my_seq", allocationSize=1, sequenceName="my_seq", initialValue=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "administrator")
    private Boolean administrator;
    @Size(max = 255)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "documento")
    private Integer documento;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Size(max = 255)
    @Column(name = "segundo_apellido")
    private String segundoApellido;

    public Useras() {
    }

    public Useras(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
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
        if (!(object instanceof Useras)) {
            return false;
        }
        Useras other = (Useras) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Useras[ id=" + id + " ]";
    }
    
}
