/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.security.cuenta;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.AccessLocalException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

/**
 *
 * @author christian.romero
 */

@Stateless 
public class CuentaService {

    @PersistenceContext
    private EntityManager em;
    @Inject
    private LdnDebitoCuenta ldnDebitoCuenta;
    @Inject
    private LdnCreditoCuenta ldnCreditoCuenta;

    //@Transactional(Transactional.TxType.REQUIRES_NEW)
    @RolesAllowed(value = "cuenta_create")
    public Long crear(
            @NotNull String codEmpresa,
            @NotNull String cod,
            @NotNull String nombre,
            @NotNull Double saldoMinimo) throws AccessLocalException{

        System.out.println("Empresa: " + codEmpresa);
        System.out.println("Cod: " + cod);
        System.out.println("Nombre: " + nombre);
        System.out.println("Saldo Minimo: " + saldoMinimo);
        Cuenta c = new Cuenta(codEmpresa, cod, nombre, saldoMinimo);

        em.persist(c);
        em.flush();
        em.refresh(c);

        return c.getId();
    }

    //@Transactional(Transactional.TxType.REQUIRES_NEW)
    @RolesAllowed("cuenta_debito")
    public String debita(
            @NotNull String codCuenta,
            @NotNull Double importe) throws SinSaldoException, NoExisteCuentaException, SaldoMinimoExcedidoException ,AccessLocalException{

        return ldnDebitoCuenta.debita(codCuenta, importe);
    }

    //@Transactional(Transactional.TxType.REQUIRES_NEW)
    @RolesAllowed("cuenta_credito")
    public String acredita(
            @NotNull String codCuenta,
            @NotNull Double importe) throws NoExisteCuentaException,AccessLocalException {
        return ldnCreditoCuenta.acredita(codCuenta, importe);
    }

    //@Transactional(Transactional.TxType.REQUIRES_NEW)
    @RolesAllowed("cuenta_consulta")
    public Cuenta find(Long cuentaId)throws AccessLocalException {
        return em.find(Cuenta.class, cuentaId);
    }

    //@Transactional(Transactional.TxType.REQUIRES_NEW)
    @RolesAllowed("cuenta_consulta")
    public List<Cuenta> findAll() throws  AccessLocalException{
        return em.createQuery("SELECT c FROM Cuenta c ").getResultList();
    }
}
