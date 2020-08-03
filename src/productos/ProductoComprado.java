/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

/**
 *
 * @author aleja
 */
public class ProductoComprado extends Producto {
    private int cantidad;
    
    public ProductoComprado(String nombre, String codigo, String descripcion,CategoriaProducto categoria,double precio, String  NUProv,int cantidad){
        super( nombre, codigo,  descripcion, categoria, precio, NUProv);
        this.cantidad =cantidad;
    }

    public ProductoComprado(Producto p, int cant) {
        super(p.getNombre(), p.getCodigo(), p.getDescripcion(), p.getCategoria(), p.getPrecio(), p.getNUProv());
        cantidad = cant;
    }
    
    public int getCantidad(){
        return this.cantidad;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}
