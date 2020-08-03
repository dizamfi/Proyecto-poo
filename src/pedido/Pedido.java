/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;
import usuarios.Cliente;
import productos.ProductoComprado;
import usuarios.MetodoPago;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 *
 * @author aleja
 */
public class Pedido {
    private Cliente datosCliente;
    private String codigo;
    private LocalDateTime fechaInicio;
    private ArrayList<ProductoComprado> productosPedidos;
    private double costoPedido;
    private EstadoActual estadoPedido;
    private String proveedor;
    private MetodoPago metodoPago;
    
    public Pedido(Cliente datosCliente, String codigo,ArrayList<ProductoComprado> productosPedidos){
        this.datosCliente = datosCliente;
        this.codigo = codigo;
        this.fechaInicio = LocalDateTime.now();
        this.productosPedidos = productosPedidos;
        this.costoPedido = calcularCosto();
        this.estadoPedido = new EstadoActual();
        proveedor = productosPedidos.get(0).getNUProv();
    }

    public Cliente getDatosCliente() {
        return datosCliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public ArrayList<ProductoComprado> getProductosPedidos() {
        return productosPedidos;
    }

    public double getCostoPedido() {
        return costoPedido;
    }

    public EstadoActual getEstado(){
        return estadoPedido;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido.getEstado();
    }
    
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public String getProveedor() {
        return proveedor;
    }
    
    private double calcularCosto(){
        double i= 0;
        for (ProductoComprado prod:productosPedidos){
             i += prod.getCantidad()*prod.getPrecio();
        }
        return i;
        }
    
    @Override
    public boolean equals(Object obj){
        if(obj!=null){
            if(obj instanceof Pedido){
                Pedido objeto = (Pedido)obj;
                if(proveedor.equals(objeto.proveedor)){
                    return true;
                }
            }
        } return false;
    }
    
    @Override
    public String toString(){
        return "Codigo del pedido: "+ codigo +"fecha del pedido: " + fechaInicio+ "Productos pedidos: "+
                productosPedidos+" Datos del cliente: "+ datosCliente+" Datos metodo de pago: "+metodoPago+ "total del pedido: "+costoPedido;
    }

}
