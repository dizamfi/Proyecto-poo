/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plataforma;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import productos.CategoriaProducto;
import usuarios.Cliente;
import usuarios.Coordenada;
import usuarios.MetodoPago;
import usuarios.PagoPayPal;
import usuarios.PagoTarjeta;
import usuarios.Proveedor;

/**
 *
 * @author Nati
 */
public class plataforma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SistemaVenta sistema = new SistemaVenta();
        sistema.inicializarDatos();        
        Scanner sn = new Scanner(System.in);
        int opc = 0;
                while (opc != 3) {
            System.out.println("VENTA DE ALIMENTOS ONLINE");
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("1. INICIAR SESIÓN");
            System.out.println("2. REGISTRARSE");
            System.out.println("3. SALIR");
            try {
                System.out.print("Ingrese una opción para continuar: ");
                opc = sn.nextInt();
                sn.nextLine();
                switch (opc) {
                    case 1:
                        System.out.println("\n\n\n");
                        System.out.println("\n\t\t\tINICIAR SESION\n");
                        System.out.print("Escriba su nombre de usuario: ");
                        String nUsuario = sn.nextLine();
                        System.out.print("Escriba su contraseña: ");
                        String password = sn.nextLine();
                        sistema.iniciarSesion(nUsuario, password);
                        break;
                    case 2:
                        System.out.println("\n\t\t\tREGISTRO\n");
                        System.out.println("\n¿Desea REGISTRAR como PROVEEDOR o como COMPRADOR?");
                        String user = sn.nextLine();
                        System.out.println("\nINGRESO DE DATOS");
                        System.out.print("Nombre completo: ");
                        String nombre = sn.nextLine();
                        System.out.print("Identificación: ");
                        String id = sn.nextLine();
                        System.out.print("Correo electrónico: ");
                        String correo = sn.nextLine();
                        System.out.print("Dirección domiciliaria: ");
                        String direc = sn.nextLine();
                        System.out.print("COORDENADAS (Ingresar latitud y longitud): (usar la coma(,) como separador decimal: ");
                        System.out.println("ingresar primero la latitud, dar enter y luego la longitud");
                        Double x = sn.nextDouble();
                        Double y = sn.nextDouble();
                        sn.nextLine();
                        boolean validar = true;
                        String nombreUsuario = null;
                        System.out.print("Nombre de usuario: ");
                        nombreUsuario = sn.nextLine();
                        validar = sistema.comprobarUsuario(nombreUsuario);
                        while (validar) {
                            System.out.println("Nombre ya existe, ingrese otro");
                            System.out.print("Nombre de usuario: ");
                            nombreUsuario = sn.nextLine();
                            validar = sistema.comprobarUsuario(nombreUsuario);
                        }
                        System.out.print("Contraseña: ");
                        String pswd = sn.nextLine();
                        switch (user.toUpperCase()) {
                            case "PROVEEDOR":
                                System.out.println("NUMERO DE CONTACTO");
                                System.out.print("Ingrese su numero de contacto: ");
                                String numero = sn.nextLine();
                                Proveedor usuario = new Proveedor(numero, nombre, id,correo,
                                        direc,new Coordenada(x,y), nombreUsuario, pswd);
                                sistema.registrarUsuario(usuario);
                                break;
                            case "COMPRADOR":
                                System.out.print("Ingrese su método de pago: (PayPal o Tarjeta)");
                                String m = sn.nextLine();
                                MetodoPago pt=null;
                                switch (m.toLowerCase()){
                                    case "tarjeta":
                                        System.out.println("DATOS DE SU TARJETA DE CRÉDITO");
                                        System.out.print("Tipo de tarjeta: ");
                                        String tipo = sn.nextLine();
                                        System.out.print("Número de la tarjeta: ");
                                        String n = sn.nextLine();
                                        System.out.print("Nombre del titular: ");
                                        String nombreT = sn.nextLine();
                                        pt=new PagoTarjeta(tipo,n,nombreT);
                                        break;
                                    case "paypal":
                                        System.out.println("DATOS DE SU CUENTA");
                                        System.out.print("Nombre de usuario: ");
                                        String nu = sn.nextLine();
                                        System.out.print("Contraseña: ");
                                        String pp = sn.nextLine(); 
                                        pt=new PagoPayPal(nu,pp);
                                        break;
                                    }
                                Cliente clientes = new Cliente(nombre, id, correo,direc, new Coordenada(x,y),nombreUsuario, pswd, pt);
                                sistema.registrarUsuario(clientes);
                                break;
                            default:
                                System.out.println("Esta no es una opción válida.");
                                break;
                    }
                        break;
                    case 3:
                        System.out.println("Salida del sistema.");
                        break;
                    default:
                        System.out.println("Esta no es una opción. Ingrese otra");
                }
                System.out.println("\n\n\n");
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

}
