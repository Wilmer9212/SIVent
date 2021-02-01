/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anonymous
 */
@Entity
@Table(name = "empvendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empvendedor.findAll", query = "SELECT e FROM Empvendedor e")
    , @NamedQuery(name = "Empvendedor.findByIdvendedor", query = "SELECT e FROM Empvendedor e WHERE e.idvendedor = :idvendedor")
    , @NamedQuery(name = "Empvendedor.findByNombre", query = "SELECT e FROM Empvendedor e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empvendedor.findByEdad", query = "SELECT e FROM Empvendedor e WHERE e.edad = :edad")
    , @NamedQuery(name = "Empvendedor.findByDireccion", query = "SELECT e FROM Empvendedor e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empvendedor.findByTelefono", query = "SELECT e FROM Empvendedor e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empvendedor.findByGenero", query = "SELECT e FROM Empvendedor e WHERE e.genero = :genero")
    , @NamedQuery(name = "Empvendedor.findByMail", query = "SELECT e FROM Empvendedor e WHERE e.mail = :mail")
    , @NamedQuery(name = "Empvendedor.findByIdcolonia", query = "SELECT e FROM Empvendedor e WHERE e.idcolonia = :idcolonia")})
public class Empvendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idvendedor")
    private Integer idvendedor;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private Integer telefono;
    @Column(name = "genero")
    private String genero;
    @Column(name = "mail")
    private String mail;
    @Column(name = "idcolonia")
    private Integer idcolonia;

    public Empvendedor() {
    }

    public Empvendedor(Integer idvendedor) {
        this.idvendedor = idvendedor;
    }

    public Integer getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(Integer idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(Integer idcolonia) {
        this.idcolonia = idcolonia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvendedor != null ? idvendedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empvendedor)) {
            return false;
        }
        Empvendedor other = (Empvendedor) object;
        if ((this.idvendedor == null && other.idvendedor != null) || (this.idvendedor != null && !this.idvendedor.equals(other.idvendedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Empvendedor[ idvendedor=" + idvendedor + " ]";
    }
    
}
