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
@Table(name = "colonia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colonia.findAll", query = "SELECT c FROM Colonia c")
    , @NamedQuery(name = "Colonia.findByIdcolonia", query = "SELECT c FROM Colonia c WHERE c.idcolonia = :idcolonia")
    , @NamedQuery(name = "Colonia.findByNombre", query = "SELECT c FROM Colonia c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Colonia.findByIdmunicipio", query = "SELECT c FROM Colonia c WHERE c.idmunicipio = :idmunicipio")})
public class Colonia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcolonia")
    private Integer idcolonia;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "idmunicipio")
    private Integer idmunicipio;

    public Colonia() {
    }

    public Colonia(Integer idcolonia) {
        this.idcolonia = idcolonia;
    }

    public Integer getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(Integer idcolonia) {
        this.idcolonia = idcolonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcolonia != null ? idcolonia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colonia)) {
            return false;
        }
        Colonia other = (Colonia) object;
        if ((this.idcolonia == null && other.idcolonia != null) || (this.idcolonia != null && !this.idcolonia.equals(other.idcolonia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Colonia[ idcolonia=" + idcolonia + " ]";
    }
    
}
