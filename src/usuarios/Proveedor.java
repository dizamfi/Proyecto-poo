/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import productos.Producto;
import pedido.*;
import productos.CategoriaProducto;

/**
 *
 * @author diego
 */
public class Proveedor extends Usuario {

    private String telefono;
    private ArrayList<Producto> productos;

    public Proveedor(String nombre, String identificacion, String correo, String direccion, Coordenada coordenada, String nombreUsuario, String clave, String telefono, ArrayList<Producto> productos) {
        super(nombre, identificacion, correo, direccion, coordenada, nombreUsuario, clave);
        this.telefono = telefono;
        this.productos = productos;
    }

    public Proveedor(String telefono, String nombre, String identificacion, String correo, String direccion, Coordenada coordenada, String nombreUsuario, String clave) {
        super(nombre, identificacion, correo, direccion, coordenada, nombreUsuario, clave);
        this.telefono = telefono;
        productos = new ArrayList<>();
    }

    public void registrarProducto(Producto producto) {
        productos.add(producto);
    }

    public void consultarInformacion(ArrayList<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            if (pedido.getProveedor().equals(nombreUsuario)) {
                System.out.println("Codigo del pedido: " + pedido.getCodigo());
                System.out.println("fecha del pedido: " + pedido.getFechaInicio());
                System.out.println("Productos: ");
                for (Producto producto : pedido.getProductosPedidos()) {
                    System.out.println("Codigo de producto: " + producto.getCodigo() + ", Nombre de producto: " + producto.getNombre() + ", cantidad: ");//agregar metodo para obtener cantidad
                }
                System.out.println("Datos del Cliente:");
                System.out.println("Nombre del Cliente: " + pedido.getDatosCliente().getNombre());
                System.out.println("Direccion: " + pedido.getDatosCliente().getDireccion());
                System.out.println("Correo de Contacto: " + pedido.getDatosCliente().getCorreo());
                System.out.println("Metodo de Pago:");//falta completar
                System.out.println("Total del pedido: " + pedido.getCostoPedido());
            }
        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean gestionarEstadoPedido(Pedido pedido) {
        switch (pedido.getEstadoPedido()) {
            case Solicitado:
                pedido.getEstado().setFechaDespacho(LocalDateTime.now());
                pedido.getEstado().setEstado(EstadoPedido.Procesado);
                return true;
            case Procesado:
                pedido.getEstado().setFechaDespacho(LocalDateTime.now());
                pedido.getEstado().setEstado(EstadoPedido.Despachado);
                return true;
        }
        return false;
    }

    /**
     *
     * @param nombre
     * @return
     */
    public ArrayList<Producto> buscarProductosN(String nombre) {
        ArrayList<Producto> lista = new ArrayList<>();
        for (Producto e : productos) {
            String eg = e.getNombre();
            if (eg.toLowerCase().contains(nombre.toLowerCase())) {
                lista.add(e);
            }
        }
        return lista;
    }

    public ArrayList<Producto> buscarProductosC(String cat) {
        ArrayList<Producto> lista = new ArrayList<>();
        for (Producto e : productos) {
            String eg = e.getCategoria().toString();
            if (eg.toLowerCase().equals(cat.toLowerCase())) {
                lista.add(e);
            }
        }
        return lista;
    }

    public Producto buscarProducto(String c) {
        for (Producto e : productos) {
            String eg = e.getCodigo();
            if (eg.equalsIgnoreCase(c)) {
                return e;
            }
        }
        return null;
    }
}
