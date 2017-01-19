/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.security.cuenta;

import javax.ejb.ApplicationException;

/**
 *
 * @author christian.romero
 */
@ApplicationException(rollback = true)
public class SaldoMinimoExcedidoException extends Exception{

    public SaldoMinimoExcedidoException(String message) {
        super(message);
    }
    
}
