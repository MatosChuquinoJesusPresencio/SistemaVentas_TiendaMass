
package Modelo.Estructuras;

import java.util.Arrays;

/**
 *
 * @author Jesus
 * @param <T>
 */
public class ArregloDinamico <T>{
    private T[] elementos;
    private int dimension;
    private int capacidad;
    private static final int CAPACIDAD_INICIAL = 30;
    
    public ArregloDinamico(){
        this.capacidad = CAPACIDAD_INICIAL;
        this.dimension = 0;
        elementos = (T[]) new Object[CAPACIDAD_INICIAL];
    }
    
    public void add(T elemento){
        if (estaLleno()){
            redimensionar();
        }
        elementos[dimension++] = elemento;
    }
    
    public T get(int indice){
        return (T) elementos[indice];
    }
    
    public void redimensionar(){
        int nuevaCapacidad = capacidad * 2;
        elementos = Arrays.copyOf(elementos, nuevaCapacidad);
        capacidad = nuevaCapacidad;
    }
    
    public boolean estaLleno(){
        return (capacidad == dimension);
    }

    public T[] getElementos() {return elementos;}
    public int getDimension() {return dimension;}
    public int getCapacidad() {return capacidad;}
    public static int getCAPACIDAD_INICIAL() {return CAPACIDAD_INICIAL;}

    public void setElementos(T[] elementos) {this.elementos = elementos;}
    public void setDimension(int dimension) {this.dimension = dimension;}
    public void setCapacidad(int capacidad) {this.capacidad = capacidad;}  
}