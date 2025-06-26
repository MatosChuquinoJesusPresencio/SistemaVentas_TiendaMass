
package Modelo.DAOs;

import Modelo.BDatos.ComponentesBD;
import Modelo.Entidades.DetalleVenta;
import Modelo.Estructuras.ArregloDinamico;
import java.sql.SQLException;

/**
 *
 * @author Jesus
 */
public class DetalleVentaDAO extends ComponentesBD{
    private static DetalleVentaDAO instancia;
    
    private DetalleVentaDAO() throws SQLException {
        super();
    }
    
    public void crearDetalleVenta(DetalleVenta detalleVenta) throws SQLException {
        String consulta = "INSERT INTO detalle_venta (id_venta, id_producto, "
                + "cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = prepararConsulta(consulta);
            
            pst.setInt(1, detalleVenta.getIdVenta());
            pst.setInt(2, detalleVenta.getIdProducto());
            pst.setInt(3, detalleVenta.getCantidad());
            pst.setDouble(4, detalleVenta.getPrecioUnitario());
            pst.setDouble(5, detalleVenta.getSubTotal());
            
            ejecutarActualizacion();
        } catch (SQLException e) {
            throw new SQLException("Error al insertar el detalle de venta:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public ArregloDinamico<DetalleVenta> listarDetalleVenta(int idVenta) throws SQLException {
        ArregloDinamico<DetalleVenta> detallesVentas = new ArregloDinamico<>();
        String consulta = "SELECT * FROM detalle_venta WHERE id_venta = ?";
        
        try {
            pst = prepararConsulta(consulta);
            pst.setInt(1, idVenta);
            rs = ejecutarConsulta();
            
            while (rs.next()) {
                DetalleVenta detalleVenta = new DetalleVenta(
                        rs.getInt("id_detalle"), rs.getInt("id_venta"),
                        rs.getInt("id_producto"), rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal")
                );
                detallesVentas.add(detalleVenta);
            }
            return detallesVentas;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los detalles de venta:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public static DetalleVentaDAO getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new DetalleVentaDAO();
        }
        return instancia;
    }
}
