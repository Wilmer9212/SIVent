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
@Table(name = "municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m")
    , @NamedQuery(name = "Municipio.findByIdmunicipio", query = "SELECT m FROM Municipio m WHERE m.idmunicipio = :idmunicipio")
    , @NamedQuery(name = "Municipio.findByNombrem", query = "SELECT m FROM Municipio m WHERE m.nombrem = :nombrem")
    , @NamedQuery(name = "Municipio.findByIdestado", query = "SELECT m FROM Municipio m WHERE m.idestado = :idestado")})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idmunicipio")
    private Integer idmunicipio;
    @Column(name = "nombrem")
    private String nombrem;
    @Column(name = "idestado")
    private Integer idestado;

    public Municipio() {
    }

    public Municipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getNombrem() {
        return nombrem;
    }

    public void setNombrem(String nombrem) {
        this.nombrem = nombrem;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmunicipio != null ? idmunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.idmunicipio == null && other.idmunicipio != null) || (this.idmunicipio != null && !this.idmunicipio.equals(other.idmunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Municipio[ idmunicipio=" + idmunicipio + " ]";
    }
    
}
