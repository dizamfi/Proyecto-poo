/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

/**
 *
 * @author diego
 */
public class Coordenada {
    
    private double latitud;
    private double longitud;
    private final static double radioTierra= 6378.137;

    public Coordenada(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    
    
    
    public static double calcularDistancia(Coordenada c1, Coordenada c2){
        double dlat=c2.latitud-c1.latitud;
        double dlong=c2.longitud-c1.longitud;
        double a= Math.pow(Math.sin(Math.toRadians(dlat)/2), 2)+
                Math.cos(Math.toRadians(c1.latitud))*
                Math.cos(Math.toRadians(c2.latitud))*
                Math.pow(Math.sin(Math.toRadians(dlong)/2), 2);
        double x= 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distancia=radioTierra*x;
        return distancia; 
    }           
    
}
