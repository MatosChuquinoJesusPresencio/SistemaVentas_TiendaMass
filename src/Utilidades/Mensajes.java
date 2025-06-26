
package Utilidades;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class Mensajes {
    
    public static void mostrarError(Component frame, String titulo, String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void mostrarAd(Component frame, String titulo, String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void mostrarInfo(Component frame, String titulo, String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
