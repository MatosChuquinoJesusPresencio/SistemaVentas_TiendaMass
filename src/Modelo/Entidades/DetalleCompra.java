
package Modelo.Entidades;

/**
 *
 * @author Jesus
 */
public class DetalleCompra {
    private int idDetalle;
    private int idCompra;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;
    
    public DetalleCompra() {}

    public DetalleCompra(int idCompra, int idProducto, int cantidad, 
            double precioUnitario, double subTotal) {
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }

    public DetalleCompra(int idDetalle, int idCompra, 
            int idProducto, int cantidad, double precioUnitario, 
            double subTotal) {
        this.idDetalle = idDetalle;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }

    public int getIdDetalle() {return idDetalle;}
    public int getIdCompra() {return idCompra;}
    public int getIdProducto() {return idProducto;}
    public int getCantidad() {return cantidad;}
    public double getPrecioUnitario() {return precioUnitario;}
    public double getSubTotal() {return subTotal;}
    
    public void setIdDetalle(int idDetalle) {this.idDetalle = idDetalle;}
    public void setIdCompra(int idCompra) {this.idCompra = idCompra;}
    public void setIdProducto(int idProducto) {this.idProducto = idProducto;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setPrecioUnitario(double precioUnitario) {this.precioUnitario = precioUnitario;}
    public void setSubTotal(double subTotal) {this.subTotal = subTotal;} 
}
