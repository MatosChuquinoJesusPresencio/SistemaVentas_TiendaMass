
package Modelo.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class Producto {
    private int idProducto;
    private int idCategoria;
    private String codigoBarras;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean activo;
    
    public Producto(){}

    public Producto(int idProducto, int idCategoria, String codigoBarras, 
            String nombre, String descripcion, double precio, boolean activo) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
    }

    public int getIdProducto() {return idProducto;}
    public int getIdCategoria() {return idCategoria;}
    public String getCodigoBarras() {return codigoBarras;}
    public String getNombre() {return nombre;}
    public String getDescripcion() {return descripcion;}
    public double getPrecio() {return precio;}
    public boolean isActivo() {return activo;}
    
    public void setIdProducto(int idProducto) {this.idProducto = idProducto;}
    public void setIdCategoria(int idCategoria) {this.idCategoria = idCategoria;}
    public void setCodigoBarras(String codigoBarras) {this.codigoBarras = codigoBarras;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setPrecio(double precio) {this.precio = precio;}
    public void setActivo(boolean activo) {this.activo = activo;}
}
