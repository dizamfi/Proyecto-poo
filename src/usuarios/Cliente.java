/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import javax.mail.Transport;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import productos.Producto;
import productos.ProductoComprado;
import plataforma.SistemaVenta;
import productos.CategoriaProducto;
import pedido.*;

/**
 *
 * @author aleja
 */
public class Cliente extends Usuario {

    private MetodoPago datosFormaPago;
    private CarritoCompra carritoCompra;

    public Cliente(String nombre, String identificacion, String correo, String direccion, Coordenada coordenada, String nombreUsuario, String clave, MetodoPago datosFormaPago) {
        super(nombre, identificacion, correo, direccion, coordenada, nombreUsuario, clave);
        this.datosFormaPago = datosFormaPago;
        carritoCompra=new CarritoCompra(nombre);
    }

    public ArrayList<Producto> consultarProductos(ArrayList<Proveedor> proveedores) {
        ArrayList<Producto> productos = new ArrayList<>();
        for (Proveedor p : proveedores) {
            double distancia = Coordenada.calcularDistancia(p.coordenada, coordenada);
            if (distancia <= 50) {
                for (Producto pro : p.getProductos()) {
                    productos.add(pro);
                }
            }
        }
        return productos;
    }

    public CarritoCompra getCarritoCompra() {
        return carritoCompra;
    }

    public ArrayList<Producto> consultarProductosC(ArrayList<Proveedor> proveedores, String categoria) {
        ArrayList<Producto> productos = consultarProductos(proveedores);
        ArrayList<Producto> productosSelec = new ArrayList<>();
        if (productos != null) {
            for (Producto p : productos) {
                if (p.getCategoria().toString().equals(categoria)) {
                    productosSelec.add(p);
                }
            }
        }
        return productosSelec;
    }

    public MetodoPago getDatosFormaPago() {
        return datosFormaPago;
    }

    public ArrayList<Producto> consultarProductos(ArrayList<Proveedor> proveedores, String codigo) {
        ArrayList<Producto> productos = consultarProductos(proveedores);
        ArrayList<Producto> productosSelec = new ArrayList<>();
        if (productos != null) {
            for (Producto p : productos) {
                if (p.getCodigo().toLowerCase().contains(codigo.toLowerCase())) {
                    productosSelec.add(p);
                }

            }
        }
        return productosSelec;
    }

    public ArrayList<Producto> consultarProductos(ArrayList<Proveedor> proveedores, double precioMinimo, double precioMaximo) {
        ArrayList<Producto> productos = consultarProductos(proveedores);
        ArrayList<Producto> productosSelec = new ArrayList<>();
        if (productos != null) {
            for (Producto p : productos) {
                if ((p.getPrecio() >= precioMinimo) && (p.getPrecio() <= precioMaximo)) {
                    productosSelec.add(p);

                }
            }
        }
        return productosSelec;
    }

    public ArrayList<Producto> consultarProductos(ArrayList<Proveedor> proveedores, String codigo, String categoria) {
        ArrayList<Producto> productos = consultarProductos(proveedores);
        ArrayList<Producto> productosSelec = new ArrayList<>();
        if (productos != null) {
            for (Producto p : productos) {
                if ((p.getCodigo().toLowerCase().contains(codigo.toLowerCase())) && (p.getCategoria().toString().equals(categoria))) {
                    productosSelec.add(p);
                }
            }
        }
        return productosSelec;
    }

    public boolean comprarProducto(Producto producto, int cantidad) {
        String nom = producto.getNombre();
        String cod = producto.getCodigo();
        String des = producto.getDescripcion();
        CategoriaProducto cat = producto.getCategoria();
        double pre = producto.getPrecio();
        String NUP = producto.getNUProv();
        ProductoComprado Prod = new ProductoComprado(nom, cod, des, cat, pre, NUP, cantidad);
        carritoCompra.getProductosRegistrados().add(Prod);
        return true;
    }

    public boolean eliminarProducto(String codigo) {
        for (int i = 0; i < carritoCompra.getProductosRegistrados().size(); i++) {
            Producto producto = carritoCompra.getProductosRegistrados().get(i);
            if (producto.getCodigo().equals(codigo)) {
                carritoCompra.getProductosRegistrados().remove(i);
                return true;
            }
        }
        return false;
    }

    public void enviarConGMail(String asunto, String cuerpo) {
        String emisor = "alarcon.david.2497";
        String contra = "raPlife24";
        Properties pro = System.getProperties();
        pro.put("mail.smtp.host", "smtp.gmail.com");
        pro.put("mail.smtp.user", emisor);
        pro.put("mail.smtp.clave", contra);
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.starttls.enable", "true");
        pro.put("mail.smtp.port", "587");
        Session ss = Session.getDefaultInstance(pro);
        MimeMessage mensaje = new MimeMessage(ss);
        try {
            mensaje.setFrom(new InternetAddress(emisor));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(getCorreo()));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            Transport tr = ss.getTransport("smtp");
            tr.connect("smtp.gmail.com", emisor, contra);
            tr.sendMessage(mensaje, mensaje.getAllRecipients());
            tr.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    public String toString() {
        return "nombre: " + nombre + " direccion: " + direccion + " correo: " + correo;
    }
}
