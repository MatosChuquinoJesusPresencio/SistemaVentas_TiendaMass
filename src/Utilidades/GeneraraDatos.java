
package Utilidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jesus
 */
public class GeneraraDatos {
    
    public static String generarNumeroVenta(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fechaHora = LocalDateTime.now().format(formatter);
        return "VEN-" + fechaHora;
    }
    
    public static String generarNumeroCompra(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fechaHora = LocalDateTime.now().format(formatter);
        return "COM-" + fechaHora;
    }
    
    public static LocalDateTime obtenerFecha(String fecha) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(fecha, formatter).atStartOfDay();
        } catch (Exception e) {
            return null;
        }
    }
}
