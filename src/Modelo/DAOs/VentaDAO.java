
package Modelo.DAOs;

import Modelo.BDatos.ComponentesBD;
import Modelo.Entidades.Venta;
import Modelo.Estructuras.ArregloDinamico;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class VentaDAO extends ComponentesBD{
    private static VentaDAO instancia;
    
    private VentaDAO() throws SQLException {
        super();
    }
    
    public int crearVenta(Venta venta) throws SQLException {
        String consulta = "INSERT INTO ventas (id_empleado, numero_venta, "
                + "total, metodo_pago) VALUES (?, ?, ?, ?)";
        try {
            pst = prepararConsultaConClave(consulta);
            
            pst.setInt(1, venta.getIdEmpleado());
            pst.setString(2, venta.getNumeroVenta());
            pst.setDouble(3, venta.getTotalVenta());
            pst.setString(4, venta.getMetodoPago());
            
            int filas = ejecutarActualizacion();
            
            if (filas > 0){
                rs = obtenerClaves();
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    return idGenerado;
                }
            }
            return 0;
        } catch (SQLException e) {
            throw new SQLException("Error al insertar la venta:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public ArregloDinamico<Venta> cargarVentas() throws SQLException {
        ArregloDinamico<Venta> ventas = new ArregloDinamico<>();
        String consulta = "SELECT * FROM ventas";
        
        try {
            pst = prepararConsulta(consulta);
            rs = ejecutarConsulta();
            
            while (rs.next()) {
                Venta venta = new Venta(
                        rs.getInt("id_venta"), rs.getInt("id_empleado"),
                        rs.getString("numero_venta"),
                        rs.getObject("fecha_venta", LocalDateTime.class),
                        rs.getDouble("total"),
                        rs.getString("metodo_pago")
                );
                ventas.add(venta);
            }
            return ventas;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener las ventas:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public static VentaDAO getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new VentaDAO();
        }
        return instancia;
    }
}
