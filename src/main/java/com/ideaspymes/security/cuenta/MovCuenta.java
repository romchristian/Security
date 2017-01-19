/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.security.cuenta;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author christian.romero
 */
@Entity
public class MovCuenta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne
    private Cuenta cuenta;
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TipoMovCuenta tipoMov;
    @NotNull
    private Double importe;

    public MovCuenta(Cuenta cuenta, Date fecha, TipoMovCuenta tipoMov, Double importe) {
        this.cuenta = cuenta;
        this.fecha = fecha;
        this.tipoMov = tipoMov;
        this.importe = importe;
    }

    public MovCuenta() {
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovCuenta getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(TipoMovCuenta tipoMov) {
        this.tipoMov = tipoMov;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
    
}
