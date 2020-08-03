/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

/**
 *
 * @author david
 */
public abstract class MetodoPago {
    public abstract boolean procesarpago(double dineroApagar, Cliente cliente);
}
