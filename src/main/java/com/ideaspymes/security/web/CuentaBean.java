/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.security.web;

import com.ideaspymes.security.cuenta.Cuenta;
import com.ideaspymes.security.cuenta.CuentaService;
import com.ideaspymes.security.cuenta.NoExisteCuentaException;
import com.ideaspymes.security.cuenta.SaldoMinimoExcedidoException;
import com.ideaspymes.security.cuenta.SinSaldoException;
import java.io.Serializable;
import javax.ejb.AccessLocalException;
import javax.ejb.EJB;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author christian.romero
 */
@Named
@ViewScoped
public class CuentaBean implements Serializable {

    @EJB
    private CuentaService cuentaService;
    private Cuenta actual;
    private Cuenta cuentaElegida;
    private long cuentaId;
    private Double importe;

    public Cuenta getCuentaElegida() {
        return cuentaElegida;
    }

    public void setCuentaElegida(Cuenta cuentaElegida) {
        this.cuentaElegida = cuentaElegida;
    }

    
    
    public long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public Cuenta getActual() {
        if (actual == null) {
            actual = new Cuenta();
        }
        return actual;
    }

    public void setActual(Cuenta actual) {
        this.actual = actual;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public void cargaDatos() {
        if (cuentaId > 0) {
            actual = cuentaService.find(cuentaId);
        }
    }

    public String crear() {
        try {
            Long cuenta = cuentaService.crear(
                    actual.getEmpresa(),
                    actual.getCod(),
                    actual.getName(),
                    actual.getSaldoMinimo());

            JsfUtil.addSuccessMessage("Cuenta Creada con id # " + cuenta);
            actual = null;
        } catch (AccessLocalException e) {
            JsfUtil.addErrorMessage(e.getMessage());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e.getMessage());
        }
        return null;
    }

    public String acredita() {
        if (cuentaElegida == null) {
            JsfUtil.addErrorMessage("No se eligio la cuenta para debitar");
            return null;
        }
        if (importe == null || importe <= 0) {
            JsfUtil.addErrorMessage("El importe debe ser mayor a Cero");
            return null;
        }
        try {
            String mensaje = cuentaService.acredita(cuentaElegida.getCod(), importe);
            JsfUtil.addSuccessMessage("Se generó con exito: " + mensaje);
        } catch (AccessLocalException e) {
            JsfUtil.addErrorMessage(e.getMessage());
        } catch (NoExisteCuentaException e) {
            JsfUtil.addErrorMessage(e.getMessage());
        }

        return null;
    }

    public String debita() {
        if (cuentaElegida == null) {
            JsfUtil.addErrorMessage("No se eligio la cuenta para debitar");
            return null;
        }
        if (importe == null || importe <= 0) {
            JsfUtil.addErrorMessage("El importe debe ser mayor a Cero");
            return null;
        }
        try {
            String mensaje = cuentaService.debita(cuentaElegida.getCod(), importe);
            JsfUtil.addSuccessMessage("Se generó con exito: " + mensaje);
        }catch (AccessLocalException e) {
            JsfUtil.addErrorMessage(e.getMessage());
        } catch (SinSaldoException | NoExisteCuentaException | SaldoMinimoExcedidoException e) {
            JsfUtil.addErrorMessage(e.getMessage());
        }

        return null;
    }

    public SelectItem[] getItemsCuenta() {
        return JsfUtil.getSelectItems(cuentaService.findAll(), true);
    }
    
    public Cuenta find(Long id){
        return cuentaService.find(id);
    }
}
