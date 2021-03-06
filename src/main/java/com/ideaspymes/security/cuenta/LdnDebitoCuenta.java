/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.security.cuenta;

import java.util.Date;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
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
public class LdnDebitoCuenta {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext ctx;
    @Inject
    private LdnObtSaldoCuenta ldnObtSaldoCuenta;

    @Transactional(Transactional.TxType.MANDATORY)
    @RolesAllowed("cuenta_debito")
    public String debita(
            @NotNull String codCuenta,
            @NotNull Double importe) throws SinSaldoException, NoExisteCuentaException, SaldoMinimoExcedidoException {

        
        Double saldo = ldnObtSaldoCuenta.getSaldo(codCuenta);
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
        
        if ((saldo-importe) < c.getSaldoMinimo()) {
            throw new SaldoMinimoExcedidoException("El saldo mínimo de la cuenta nro " + codCuenta + " sobrepasado");
        }
        
        if (saldo < importe) {
            throw new SinSaldoException("Cuenta nro " + codCuenta + " con fondo insuficiente");
        }
        
        if(importe > 10000000d && !ctx.isCallerInRole("cuenta_autoriza_monto_grande")){
            throw new SinSaldoException("El importe " + importe + " necesita autorización gerencia");
        }
        
        MovCuenta m = new MovCuenta(c, new Date(), TipoMovCuenta.DEBITO, importe);
        em.persist(m);
        em.flush();
        em.refresh(m);
        c.setBalance(((c.getBalance() == null?0d:c.getBalance()) - importe));
        
        return "MovCuenta #" + m.getId();
    }

}
