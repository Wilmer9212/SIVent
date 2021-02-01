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
@Table(name = "unidadesm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidadesm.findAll", query = "SELECT u FROM Unidadesm u")
    , @NamedQuery(name = "Unidadesm.findByIdunidad", query = "SELECT u FROM Unidadesm u WHERE u.idunidad = :idunidad")
    , @NamedQuery(name = "Unidadesm.findByDescripcion", query = "SELECT u FROM Unidadesm u WHERE u.descripcion = :descripcion")})
public class Unidadesm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idunidad")
    private Integer idunidad;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    public Unidadesm() {
    }

    public Unidadesm(Integer idunidad) {
        this.idunidad = idunidad;
    }

    public Unidadesm(Integer idunidad, String descripcion) {
        this.idunidad = idunidad;
        this.descripcion = descripcion;
    }

    public Integer getIdunidad() {
        return idunidad;
    }

    public void setIdunidad(Integer idunidad) {
        this.idunidad = idunidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idunidad != null ? idunidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidadesm)) {
            return false;
        }
        Unidadesm other = (Unidadesm) object;
        if ((this.idunidad == null && other.idunidad != null) || (this.idunidad != null && !this.idunidad.equals(other.idunidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Unidadesm[ idunidad=" + idunidad + " ]";
    }
    
}
