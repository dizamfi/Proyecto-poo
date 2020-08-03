/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

/**
 *
 * @author diego
 */
public class Producto {
    private String nombre;
    private String codigo;
    private String descripcion;
    private CategoriaProducto categoria;
    private double precio;
    private String NUProv;
 
    public Producto(String nombre, String codigo, String descripcion,CategoriaProducto categoria,double precio, String  NUProv){
        this.codigo=codigo;
        this.descripcion=descripcion;
        this.nombre=nombre;
        this.precio=precio;
        this.categoria=categoria;
        this.NUProv = NUProv;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNUProv() {
        return NUProv;
    }
    
    public boolean equals(Object obj){
        if(obj!=null){
            if(obj instanceof Producto){
                Producto objeto = (Producto)obj;
                if(objeto.categoria.equals(categoria))
                    return true;
                if(objeto.codigo.equals(codigo)) return true;
                if((objeto.categoria.equals(categoria)&&(objeto.codigo.equals(codigo))))return true;
            }
        } return false;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", codigo: " + codigo + ", categoria: " + categoria + ", precio: " + precio;
    }
}
