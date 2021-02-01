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
@Table(name = "tipov")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipov.findAll", query = "SELECT t FROM Tipov t")
    , @NamedQuery(name = "Tipov.findByIdtipov", query = "SELECT t FROM Tipov t WHERE t.idtipov = :idtipov")
    , @NamedQuery(name = "Tipov.findByDescr", query = "SELECT t FROM Tipov t WHERE t.descr = :descr")})
public class Tipov implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipov")
    private Integer idtipov;
    @Basic(optional = false)
    @Column(name = "descr")
    private String descr;

    public Tipov() {
    }

    public Tipov(Integer idtipov) {
        this.idtipov = idtipov;
    }

    public Tipov(Integer idtipov, String descr) {
        this.idtipov = idtipov;
        this.descr = descr;
    }

    public Integer getIdtipov() {
        return idtipov;
    }

    public void setIdtipov(Integer idtipov) {
        this.idtipov = idtipov;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipov != null ? idtipov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipov)) {
            return false;
        }
        Tipov other = (Tipov) object;
        if ((this.idtipov == null && other.idtipov != null) || (this.idtipov != null && !this.idtipov.equals(other.idtipov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tipov[ idtipov=" + idtipov + " ]";
    }
    
}
