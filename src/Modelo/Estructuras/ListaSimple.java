
package Modelo.Estructuras;

import Modelo.Entidades.Producto;

/**
 *
 * @author Jesus
 */
public class ListaSimple {
    private NodoLista cabeza;

    public ListaSimple() {
        this.cabeza = null;
    }
    
    public NodoLista search(int idProducto){
        NodoLista puntero = cabeza;
        while (puntero != null){
            if (puntero.getProducto().getIdProducto() == idProducto){
                return puntero;
            }
            puntero = puntero.getSiguiente();
        }
        return null;
    }
    
    public boolean add(Producto producto, int cantidad){
        if (producto == null || cantidad <= 0){
            return false;
        }
        
        NodoLista enCarrito = search(producto.getIdProducto());
        
        if (enCarrito != null){
            enCarrito.setCantidad(enCarrito.getCantidad() + cantidad);
        } else {
            NodoLista nuevoP = new NodoLista(producto, cantidad);
            
            if (empty()){
                cabeza = nuevoP;
            } else {
                NodoLista puntero = cabeza;
                while (puntero.getSiguiente() != null){
                    puntero = puntero.getSiguiente();
                }
                puntero.setSiguiente(nuevoP);
            }
        }
        return true;
    }
    
    public boolean modify(int idProducto, int cantidad){
        if (cantidad <= 0){
            return false;
        }
        
        NodoLista enCarrito = search(idProducto);
        
        if (enCarrito != null){
            enCarrito.setCantidad(cantidad);
        }
        return true;
    }
    
    public boolean delete(int idProducto){
        if (empty()){
            return false;
        }
        
        if (!empty()){
            if (cabeza.getProducto().getIdProducto() == idProducto){
                cabeza = cabeza.getSiguiente();
                return true;
            } else {
                NodoLista puntero = cabeza;
                while (puntero.getSiguiente() != null){
                    if (puntero.getSiguiente().getProducto().getIdProducto() == idProducto){
                        puntero.setSiguiente(puntero.getSiguiente().getSiguiente());
                        return true;
                    }
                    puntero = puntero.getSiguiente();
                }
            }
        }
        return false;
    }
    
    public double montoTotal(){
        double total = 0;
        NodoLista puntero = cabeza;
        while (puntero != null){
            total += puntero.subTotal();
            puntero = puntero.getSiguiente();
        }
        return total;
    }
    
    public int cantidadTotal(){
        int cantidad = 0;
        NodoLista puntero = cabeza;
        while (puntero != null){
            cantidad += puntero.getCantidad();
            puntero = puntero.getSiguiente();
        }
        return cantidad;
    }
    
    public void clean(){cabeza = null;}
    
    public boolean empty(){return cabeza == null;}

    public NodoLista getCabeza() {return cabeza;}

    public void setCabeza(NodoLista cabeza) {this.cabeza = cabeza;}  
}
