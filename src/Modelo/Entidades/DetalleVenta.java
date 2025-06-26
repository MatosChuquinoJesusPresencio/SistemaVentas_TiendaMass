
package Modelo.Entidades;

/**
 *
 * @author Jesus
 */
public class DetalleVenta {
    private int idDetalle;
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;

    public DetalleVenta() {}

    public DetalleVenta(int idVenta, int idProducto, int cantidad,
            double precioUnitario, double subTotal) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }

    public DetalleVenta(int idDetalle, int idVenta, int idProducto,
            int cantidad, double precioUnitario, double subTotal) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }

    public int getIdDetalle() {return idDetalle;}
    public int getIdVenta() {return idVenta;}
    public int getIdProducto() {return idProducto;}
    public int getCantidad() {return cantidad;}
    public double getPrecioUnitario() {return precioUnitario;}
    public double getSubTotal() {return subTotal;}
    
    public void setIdDetalle(int idDetalle) {this.idDetalle = idDetalle;}
    public void setIdVenta(int idVenta) {this.idVenta = idVenta;}
    public void setIdProducto(int idProducto) {this.idProducto = idProducto;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setPrecioUnitario(double precioUnitario) {this.precioUnitario = precioUnitario;}
    public void setSubTotal(double subTotal) {this.subTotal = subTotal;}
}
