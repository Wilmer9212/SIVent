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
 * @author Elliot
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p")
    , @NamedQuery(name = "Productos.findByIdproducto", query = "SELECT p FROM Productos p WHERE p.idproducto = :idproducto")
    , @NamedQuery(name = "Productos.findByNombre", query = "SELECT p FROM Productos p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Productos.findByUmedidap", query = "SELECT p FROM Productos p WHERE p.umedidap = :umedidap")
    , @NamedQuery(name = "Productos.findByPreciop", query = "SELECT p FROM Productos p WHERE p.preciop = :preciop")
    , @NamedQuery(name = "Productos.findByPrecioc", query = "SELECT p FROM Productos p WHERE p.precioc = :precioc")
    , @NamedQuery(name = "Productos.findByStock", query = "SELECT p FROM Productos p WHERE p.stock = :stock")
    , @NamedQuery(name = "Productos.findByProveedor", query = "SELECT p FROM Productos p WHERE p.proveedor = :proveedor")
    , @NamedQuery(name = "Productos.findByPadquicision", query = "SELECT p FROM Productos p WHERE p.padquicision = :padquicision")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idproducto")
    private Integer idproducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "umedidap")
    private String umedidap;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preciop")
    private Double preciop;
    @Column(name = "precioc")
    private Double precioc;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "padquicision")
    private Double padquicision;

    public Productos() {
    }

    public Productos(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUmedidap() {
        return umedidap;
    }

    public void setUmedidap(String umedidap) {
        this.umedidap = umedidap;
    }

    public Double getPreciop() {
        return preciop;
    }

    public void setPreciop(Double preciop) {
        this.preciop = preciop;
    }

    public Double getPrecioc() {
        return precioc;
    }

    public void setPrecioc(Double precioc) {
        this.precioc = precioc;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Double getPadquicision() {
        return padquicision;
    }

    public void setPadquicision(Double padquicision) {
        this.padquicision = padquicision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Productos[ idproducto=" + idproducto + " ]";
    }
    
}
