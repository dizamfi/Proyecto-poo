/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import java.util.Scanner;

/**
 *
 * @author david
 */
public class PagoTarjeta extends MetodoPago {

    private String tipoTarjeta;
    private String numeroTarjeta;
    private String nameTitular;

    public PagoTarjeta(String tipoTarjeta, String numeroTarjeta, String nameTitular) {
        this.tipoTarjeta = tipoTarjeta;
        this.numeroTarjeta = numeroTarjeta;
        this.nameTitular = nameTitular;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNameTitular() {
        return nameTitular;
    }

    public void setNameTitular(String nameTitular) {
        this.nameTitular = nameTitular;
    }

    @Override
    public boolean procesarpago(double dineroApagar, Cliente cliente) {
        int codigo = (int) Math.floor(Math.random() * (100000 - 10000 + 1) + 10000);
        System.out.println("Se ha enviado un código de verificación a tu correo");
        String cuerpo = "Hola " + cliente.nombre + "<br/>Tu código de verificación "
                + "para realizar el pago es:<b>" + codigo + "</b><br/>Gracias por comprar &#128521;";
        cliente.enviarConGMail("CODIGO DE VERIFICACION", cuerpo);
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresa el código: ");
        int cC= sc.nextInt();
        sc.close();
       return cC==codigo;
    }
}
