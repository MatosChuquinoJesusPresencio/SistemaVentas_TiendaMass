
package Modelo.Estructuras;

import Modelo.Entidades.Venta;

/**
 *
 * @author Jesus
 */
public class NodoABB {
    private Venta venta;
    private NodoABB izquierda, derecha;
    
    public NodoABB(Venta venta){
        this.venta = venta;
        this.izquierda = this.derecha = null;
    }

    public Venta getVenta() {return venta;}
    public NodoABB getIzquierda() {return izquierda;}
    public NodoABB getDerecha() {return derecha;}

    public void setVenta(Venta venta) {this.venta = venta;}
    public void setIzquierda(NodoABB izquierda) {this.izquierda = izquierda;}
    public void setDerecha(NodoABB derecha) {this.derecha = derecha;}
}
