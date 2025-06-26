
package Modelo.Estructuras;

import Modelo.Entidades.DetalleVenta;
import Modelo.Entidades.Venta;
import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class ArbolBB {
    private NodoABB raiz;
    
    public ArbolBB(){
        this.raiz = null;
    }
    
    public void insert(Venta venta){
        raiz = insertRec(raiz, venta);
    }
    
    public NodoABB insertRec(NodoABB nodo, Venta venta){
        if (nodo == null){
            return new NodoABB(venta);
        }
        
        LocalDateTime fechaNueva = venta.getFechaVenta();
        LocalDateTime fechaActual = nodo.getVenta().getFechaVenta();
        
        if (fechaNueva.isBefore(fechaActual)){
            nodo.setIzquierda(insertRec(nodo.getIzquierda(), venta));
        } else {
            nodo.setDerecha(insertRec(nodo.getDerecha(), venta));
        }
        
        return nodo;
    }
    
    public void filtrarFecha(LocalDateTime inicioF, LocalDateTime finF, 
            ArregloDinamico<Venta> resultado, String op){
        filtrarFechaRec(raiz, inicioF, finF, resultado, op);
    }
    
    public void filtrarFechaRec(NodoABB nodo, LocalDateTime inicioF,
            LocalDateTime finF, ArregloDinamico<Venta> resultado, String op){
        if (nodo != null){
        
            filtrarFechaRec(nodo.getIzquierda(), inicioF, finF, resultado, op);

            LocalDateTime fecha = nodo.getVenta().getFechaVenta();
            boolean cumple = false;
            switch(op){
                case "menor" -> cumple = !fecha.isBefore(inicioF);
                case "mayor" -> cumple = !fecha.isAfter(inicioF);
                case "entre" -> cumple = (!fecha.isBefore(inicioF) && !fecha.isAfter(finF));
                case "igual" -> cumple = fecha.equals(inicioF);
            }
            
            if (cumple){
                resultado.add(nodo.getVenta());
            }
            
            filtrarFechaRec(nodo.getDerecha(), inicioF, finF, resultado, op);
        }
    }
    
    public void filtrarId(int idProducto, ArregloDinamico<Venta> resultado){
        filtrarIdRec(raiz, idProducto, resultado);
    }
    
    public void filtrarIdRec(NodoABB nodo, int idProducto, 
            ArregloDinamico<Venta> resultado){
        if (nodo != null){
        
            filtrarIdRec(nodo.getIzquierda(), idProducto, resultado);

            ArregloDinamico<DetalleVenta> detallesVentas = nodo.getVenta().getDetallesVentas();
            
            for (int i = 0; i < detallesVentas.getDimension(); i++){
                if (detallesVentas.get(i).getIdProducto() == idProducto){
                    resultado.add(nodo.getVenta());
                    break;
                }
            }
                 
            filtrarIdRec(nodo.getDerecha(), idProducto, resultado);
        }
    }
    
    public void filtrarMonto(double montoI, double montoF, 
            ArregloDinamico<Venta> resultado, String op){
        filtrarMontoRec(raiz, montoI, montoF, resultado, op);
    }
    
    public void filtrarMontoRec(NodoABB nodo, double montoI, double montoF, 
            ArregloDinamico<Venta> resultado, String op){
        if (nodo != null){
        
            filtrarMontoRec(nodo.getIzquierda(), montoI, montoF, resultado, op);

            double totalVenta = nodo.getVenta().getTotalVenta();
            boolean cumple = false;
            switch(op){
                case "menor" -> cumple = totalVenta <= montoI;
                case "mayor" -> cumple = totalVenta >= montoI;
                case "entre" -> cumple = totalVenta >= montoI && totalVenta <= montoF;
                case "igual" -> cumple = totalVenta == montoI;
            }
            
            if (cumple){
                resultado.add(nodo.getVenta());
            }
            
            filtrarMontoRec(nodo.getDerecha(), montoI, montoF, resultado, op);
        }
    }
    
    public void clean(){raiz = null;}

    public NodoABB getRaiz() {return raiz;}

    public void setRaiz(NodoABB raiz) {this.raiz = raiz;}

    public void limpiar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
