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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elliot
 */
@Entity
@Table(name = "ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name = "Ventas.findByIdventa", query = "SELECT v FROM Ventas v WHERE v.idventa = :idventa")
    , @NamedQuery(name = "Ventas.findByFechav", query = "SELECT v FROM Ventas v WHERE v.fechav = :fechav")
    , @NamedQuery(name = "Ventas.findByTotal", query = "SELECT v FROM Ventas v WHERE v.total = :total")
    , @NamedQuery(name = "Ventas.findByIdcliente", query = "SELECT v FROM Ventas v WHERE v.idcliente = :idcliente")
    , @NamedQuery(name = "Ventas.findByIdtipov", query = "SELECT v FROM Ventas v WHERE v.idtipov = :idtipov")
    , @NamedQuery(name = "Ventas.findByIdproducto", query = "SELECT v FROM Ventas v WHERE v.idproducto = :idproducto")
    , @NamedQuery(name = "Ventas.findByHora", query = "SELECT v FROM Ventas v WHERE v.hora = :hora")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idventa")
    private Integer idventa;
    @Column(name = "fechav")
    private String fechav;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "idcliente")
    private Integer idcliente;
    @Column(name = "idtipov")
    private Integer idtipov;
    @Column(name = "idproducto")
    private Integer idproducto;
    @Column(name = "hora")
    private String hora;

    public Ventas() {
    }

    public Ventas(Integer idventa, String fechav, Double total, Integer idcliente, Integer idtipov, Integer idproducto, String hora) {
        this.idventa = idventa;
        this.fechav = fechav;
        this.total = total;
        this.idcliente = idcliente;
        this.idtipov = idtipov;
        this.idproducto = idproducto;
        this.hora = hora;
    }
    
    public Ventas(Integer idventa) {
        this.idventa = idventa;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public String getFechav() {
        return fechav;
    }

    public void setFechav(String fechav) {
        this.fechav = fechav;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdtipov() {
        return idtipov;
    }

    public void setIdtipov(Integer idtipov) {
        this.idtipov = idtipov;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idventa != null ? idventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.idventa == null && other.idventa != null) || (this.idventa != null && !this.idventa.equals(other.idventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ventas[ idventa=" + idventa + " ]";
    }
    
}
