
package Modelo.Entidades;

import Modelo.Estructuras.ArregloDinamico;
import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class Venta {
    private int idVenta;
    private int idEmpleado;
    private String numeroVenta;
    private LocalDateTime fechaVenta;
    private double totalVenta;
    private String metodoPago;
    private ArregloDinamico<DetalleVenta> detallesVentas;
    
    public Venta(){}
    
    public Venta(int idEmpleado, String numeroVenta,
            double totalVenta, String metodoPago){
        this.idEmpleado = idEmpleado;
        this.numeroVenta = numeroVenta;
        this.totalVenta = totalVenta;
        this.metodoPago = metodoPago;
    }

    public Venta(int idVenta, int idEmpleado, String numeroVenta, 
            LocalDateTime fechaVenta, double totalVenta, String metodoPago) {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.numeroVenta = numeroVenta;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.metodoPago = metodoPago;
    }
    
    public Venta(int idVenta, int idEmpleado, String numeroVenta, 
            LocalDateTime fechaVenta, double totalVenta, 
            String metodoPago, ArregloDinamico detallesVentas) {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.numeroVenta = numeroVenta;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.metodoPago = metodoPago;
        this.detallesVentas = detallesVentas;
    }
    
    public int numProductosPorVenta(){
        int cantidadProductos = 0;
        for (int i = 0; i < detallesVentas.getDimension(); i++){
            DetalleVenta detalleVenta = detallesVentas.get(i);
            cantidadProductos += detalleVenta.getCantidad();
        }
        return cantidadProductos;
    }

    public int getIdVenta() {return idVenta;}
    public int getIdEmpleado() {return idEmpleado;}
    public String getNumeroVenta() {return numeroVenta;}
    public LocalDateTime getFechaVenta() {return fechaVenta;}
    public double getTotalVenta() {return totalVenta;}
    public String getMetodoPago() {return metodoPago;}
    public ArregloDinamico getDetallesVentas() {return detallesVentas;}

    public void setIdVenta(int idVenta) {this.idVenta = idVenta;}
    public void setIdEmpleado(int idEmpleado) {this.idEmpleado = idEmpleado;}
    public void setNumeroVenta(String numeroVenta) {this.numeroVenta = numeroVenta;}
    public void setFechaVenta(LocalDateTime fechaVenta) {this.fechaVenta = fechaVenta;}
    public void setTotalVenta(double totalVenta) {this.totalVenta = totalVenta;}
    public void setMetodoPago(String metodoPago) {this.metodoPago = metodoPago;}
    public void setDetallesVentas(ArregloDinamico<DetalleVenta> detallesVentas) {this.detallesVentas = detallesVentas;} 
}
