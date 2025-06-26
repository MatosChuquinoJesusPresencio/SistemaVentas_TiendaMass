
package Modelo.Entidades;

import Modelo.Estructuras.ArregloDinamico;
import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class Compra {
    private int idCompra;
    private int idEmpleado;
    private int idProveedor;
    private String numeroCompra;
    private LocalDateTime fechaCompra;
    private double totalCompra;
    private String metodoPago;
    private ArregloDinamico<DetalleCompra> detallesCompras;

    public Compra() {}

    public Compra(int idEmpleado, int idProveedor, 
            String numeroCompra, double totalCompra, String metodoPago) {
        this.idEmpleado = idEmpleado;
        this.idProveedor = idProveedor;
        this.numeroCompra = numeroCompra;
        this.totalCompra = totalCompra;
        this.metodoPago = metodoPago;
    }

    public Compra(int idCompra, int idEmpleado, int idProveedor, String numeroCompra, 
            LocalDateTime fechaCompra, double totalCompra, String metodoPago, 
            ArregloDinamico<DetalleCompra> detallesCompras) {
        this.idCompra = idCompra;
        this.idEmpleado = idEmpleado;
        this.idProveedor = idProveedor;
        this.numeroCompra = numeroCompra;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
        this.metodoPago = metodoPago;
        this.detallesCompras = detallesCompras;
    }

    public int getIdCompra() {return idCompra;}
    public int getIdEmpleado() {return idEmpleado;}
    public int getIdProveedor() {return idProveedor;}
    public String getNumeroCompra() {return numeroCompra;}
    public LocalDateTime getFechaCompra() {return fechaCompra;}
    public double getTotalCompra() {return totalCompra;}
    public String getMetodoPago() {return metodoPago;}
    public ArregloDinamico<DetalleCompra> getDetallesCompras() {return detallesCompras;}

    public void setIdCompra(int idCompra) {this.idCompra = idCompra;}
    public void setIdEmpleado(int idEmpleado) {this.idEmpleado = idEmpleado;}
    public void setIdProveedor(int idProveedor) {this.idProveedor = idProveedor;}
    public void setNumeroCompra(String numeroCompra) {this.numeroCompra = numeroCompra;}
    public void setFechaCompra(LocalDateTime fechaCompra) {this.fechaCompra = fechaCompra;}
    public void setTotalCompra(double totalCompra) {this.totalCompra = totalCompra;}
    public void setMetodoPago(String metodoPago) {this.metodoPago = metodoPago;}
    public void setDetallesCompras(ArregloDinamico<DetalleCompra> detallesCompras) {this.detallesCompras = detallesCompras;}
}
