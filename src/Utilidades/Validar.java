
package Utilidades;

/**
 *
 * @author Jesus
 */
public class Validar {
    
    public static boolean entradaValida(String entrada){
        return entrada != null && !entrada.trim().isEmpty();
    }
    
    public static boolean importeValido(double monto){
        return monto >= 0;
    }
    
    public static boolean cBarrasValido(String entrada){
        return !(entrada == null || entrada.length() != 13 || !entrada.matches("\\d+"));
    }
}
