/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.security.cuenta;

import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

/**
 *
 * @author christian.romero
 */
@Model
public class LdnCreditoCuenta {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private LdnObtSaldoCuenta ldnObtSaldoCuenta;

    @Transactional(Transactional.TxType.MANDATORY)
    @RolesAllowed("cuenta_credito")
    public String acredita(
            @NotNull String codCuenta,
            @NotNull Double importe) throws  NoExisteCuentaException {

        
        Cuenta c = null;
        try {
            c = (Cuenta) em.createQuery("SELECT c FROM Cuenta c WHERE c.cod = ?1")
                    .setParameter(1, codCuenta)
                    .getSingleResult();
        } catch (Exception e) {
            
        }

        //Validaciones
        if(c == null){
            throw new NoExisteCuentaException("La cuenta nro " + codCuenta + " no existe!!");
        }
         
        MovCuenta m = new MovCuenta(c, new Date(), TipoMovCuenta.CREDITO, importe);
        em.persist(m);
        em.flush();
        em.refresh(m);
        c.setBalance(((c.getBalance() == null?0d:c.getBalance()) + importe));
        
        return "MovCuenta #" + m.getId();
    }

}
