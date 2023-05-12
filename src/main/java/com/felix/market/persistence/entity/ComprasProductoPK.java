package com.felix.market.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ComprasProductoPK implements Serializable {

    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComprasProductoPK)) return false;
        ComprasProductoPK that = (ComprasProductoPK) o;
        return Objects.equals(getIdCompra(), that.getIdCompra()) && Objects.equals(getIdProducto(), that.getIdProducto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCompra(), getIdProducto());
    }
}
