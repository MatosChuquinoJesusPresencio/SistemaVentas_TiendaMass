
package Modelo.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class Inventario {
    private int idInventario;
    private int idProducto;
    private int stockActual;
    private int stockMinimo;
    private LocalDateTime actualizacionF;
    
    public Inventario(){}

    public Inventario(int idInventario, int idProducto, 
            int stockActual, int stockMinimo, LocalDateTime actualizacionF) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.actualizacionF = actualizacionF;
    }
    
    public int getIdInventario() {return idInventario;}
    public int getIdProducto() {return idProducto;}
    public int getStockActual() {return stockActual;}
    public int getStockMinimo() {return stockMinimo;}
    public LocalDateTime getActualizacionF() {return actualizacionF;}
    
    public void setIdInventario(int idInventario) {this.idInventario = idInventario;}
    public void setIdProducto(int idProducto) {this.idProducto = idProducto;}
    public void setStockActual(int stockActual) {this.stockActual = stockActual;}
    public void setStockMinimo(int stockMinimo) {this.stockMinimo = stockMinimo;}
    public void setActualizacionF(LocalDateTime actualizacionF) {this.actualizacionF = actualizacionF;} 
}
