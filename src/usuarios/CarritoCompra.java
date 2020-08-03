/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import productos.ProductoComprado;
import java.util.*;
import productos.Producto;

/**
 *
 * @author diego
 */
public class CarritoCompra {

    private ArrayList<ProductoComprado> productosRegistrados;
    private String nombreCliente;

    public CarritoCompra(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        productosRegistrados = new ArrayList<>();
    }

    public ArrayList<ProductoComprado> getProductosRegistrados() {
        return productosRegistrados;
    }

    public void setProductosRegistrados(ArrayList<ProductoComprado> productosRegistrados) {
        this.productosRegistrados = productosRegistrados;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double subtotal() {
        double subtotal = 0;
        for (ProductoComprado p : productosRegistrados) {
            double total = p.getPrecio() * p.getCantidad();
            subtotal += total;
        }
        return subtotal;
    }

    public double totalPagar() {
        return (subtotal() * 0.12) + subtotal();

    }

    public ArrayList<String> cantidadProveedores() {
        ArrayList<String> nombre = new ArrayList<>();
        for (ProductoComprado p : productosRegistrados) {
            if (!nombre.contains(p.getNUProv())) {
                nombre.add(p.getNUProv());
            }
        }
        return nombre;
    }

    public ArrayList<ProductoComprado> productosporProveedor(String n) {
        ArrayList<ProductoComprado> producto = new ArrayList<>();
        for (ProductoComprado p : productosRegistrados) {
            if (p.getNUProv().equalsIgnoreCase(n)) {
                producto.add(p);
            }
        }
        return producto;
    }

    public void mostarInfoProductos() {
        for (ProductoComprado p : productosRegistrados) {
            System.out.println("Codigo:" + p.getCodigo() + " Nombre:" + p.getNombre() + " Cantidad:" + p.getCantidad() + " Precio:" + p.getPrecio());
        }
        System.out.println("Subtotal:" + subtotal() + "Total a pagar:" + totalPagar());
    }
}
