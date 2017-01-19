/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.security.cuenta;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

/**
 *
 * @author christian.romero
 */
@Model
public class LdnObtSaldoCuenta {

    @PersistenceContext
    private EntityManager em;
    

    @RolesAllowed("cuenta_consulta_saldo")
    public Double getSaldo(
            @NotNull String codCuenta) {

        Double saldo = 0d;
        Double creditos = 0d;
        Double debitos = 0d;
        try {
            creditos = (Double) em.createQuery("SELECT sum(m.importe) FROM MovCuenta m WHERE m.tipoMov = ?1 AND m.cuenta.cod = ?2")
                    .setParameter(1, TipoMovCuenta.CREDITO)
                    .setParameter(2, codCuenta)
                    .getSingleResult();
            
            debitos = (Double) em.createQuery("SELECT sum(m.importe) FROM MovCuenta m WHERE m.tipoMov = ?1  AND m.cuenta.cod = ?2")
                    .setParameter(1, TipoMovCuenta.DEBITO)
                    .setParameter(2, codCuenta)
                    .getSingleResult();
        } catch (Exception e) {
            
        }
        
        saldo = (creditos==null?0d:creditos) - (debitos==null?0d:debitos);
        
        return saldo;
    }

}
