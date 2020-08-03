/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;
import usuarios.Coordenada;
/**
 *
 * @author diego
 */
public class Usuario {
    protected String nombre;
    protected String identificacion;
    protected String correo;
    protected String direccion;
    protected Coordenada coordenada;
    protected String nombreUsuario;
    protected String clave;
   
    public Usuario(String nombre,String identificacion,String correo,String direccion,Coordenada coordenada,String nombreUsuario,String clave){
        this.nombre=nombre;
        this.identificacion=identificacion;
        this.correo=correo;
        this.direccion=direccion;
        this.coordenada=coordenada;
        this.nombreUsuario=nombreUsuario;
        this.clave=clave;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getIdentificacion() {
        return identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }
    
    public Coordenada getCoordenada(){
        return coordenada;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public String getClave(){
        return clave;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setIdentificacion(String identificacion){
        this.identificacion = identificacion;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    public void setCoordenada(Coordenada coordenada){
        this.coordenada = coordenada;
    }
    
    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }
    
    public void setClave(String clave){
        this.clave = clave;
    }
    public boolean equals(Object obj){
        if(obj!=null){
            if(obj instanceof Usuario){
                Usuario objeto = (Usuario)obj;
                if(nombreUsuario.equals(objeto.nombreUsuario)){
                    return true;
                }
            }
        } return false;
    }

    
    
}

