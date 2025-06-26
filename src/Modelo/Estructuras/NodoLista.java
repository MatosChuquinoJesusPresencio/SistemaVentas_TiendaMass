
package Modelo.Estructuras;

import Modelo.Entidades.Producto;

/**
 *
 * @author Jesus
 */
public class NodoLista {
    private final Producto producto;
    private int cantidad;
    private NodoLista siguiente;

    public NodoLista(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.siguiente = null;
    }
    
    public double subTotal(){
        return producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {return producto;}
    public int getCantidad() {return cantidad;}
    public NodoLista getSiguiente() {return siguiente;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setSiguiente(NodoLista siguiente) {this.siguiente = siguiente;} 
}
