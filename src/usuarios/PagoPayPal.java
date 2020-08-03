/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

/**
 *
 * @author Nati
 */
public class PagoPayPal extends MetodoPago {

    private String nombreUsuario;
    private String contra;

    public PagoPayPal(String nombreUsuario, String contra) {
        this.nombreUsuario = nombreUsuario;
        this.contra = contra;
    }

    @Override
    public boolean procesarpago(double dineroApagar, Cliente cliente) {
        int dineroCuenta = (int) Math.floor(Math.random() * (1000 - 100 + 1) + 100);
        System.out.println("Cliente: "+cliente.getNombre()+"\tDinero en cuenta: "+dineroCuenta);
        return dineroApagar<=dineroCuenta;
    }

}
