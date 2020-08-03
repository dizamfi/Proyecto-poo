/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;
import java.time.LocalDateTime;
import java.util.*;
/**
 *
 * @author diego
 */
public class EstadoActual {
    
    private EstadoPedido estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaProcesamiento;
    private LocalDateTime fechaDespacho;


    
    public EstadoActual(){
        this.estado= EstadoPedido.Solicitado;
        this.fechaInicio = LocalDateTime.now();
    }


    public EstadoPedido getEstado() {
        return estado;
    }

    public LocalDateTime getFechaProcesamiento() {
        return fechaProcesamiento;
    }

    public LocalDateTime getFechaDespacho() {
        return fechaDespacho;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }


    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setFechaProcesamiento(LocalDateTime fechaProcesamiento) {
        this.fechaProcesamiento = fechaProcesamiento;
    }

    public void setFechaDespacho(LocalDateTime fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }


    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    @Override
    public String toString(){
        String s ="";
        switch(estado){
            case Solicitado:
                s = "Estado: Solicitado\nFecha de Inicio: "+fechaInicio.toString();
                break;
            case Procesado:
                s = "Estado: Procesado\nFecha de Inicio: "+fechaInicio.toString()+"\nFecha de Procesado: "+fechaProcesamiento.toString();
                break;
            case Despachado:
                s = "Estado: Despachado\nFecha de Inicio: "+fechaInicio.toString()+"\nFecha de Procesado: "
                        +fechaProcesamiento.toString()+"\nFecha de Despacho: "+fechaDespacho.toString();
                break;
        }
        return s;
    }
    
    
    
}
