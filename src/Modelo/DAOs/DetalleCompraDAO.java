
package Modelo.DAOs;

import Modelo.BDatos.ComponentesBD;
import Modelo.Entidades.DetalleCompra;
import java.sql.SQLException;

/**
 *
 * @author Jesus
 */
public class DetalleCompraDAO extends ComponentesBD{
    private static DetalleCompraDAO instancia;
    
    private DetalleCompraDAO() throws SQLException {
        super();
    }
    
    public void crearDetalleCompra(DetalleCompra detalleCompra) throws SQLException {
        String consulta = "INSERT INTO detalle_compra (id_compra, id_producto, "
                + "cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = prepararConsulta(consulta);
            
            pst.setInt(1, detalleCompra.getIdCompra());
            pst.setInt(2, detalleCompra.getIdProducto());
            pst.setInt(3, detalleCompra.getCantidad());
            pst.setDouble(4, detalleCompra.getPrecioUnitario());
            pst.setDouble(5, detalleCompra.getSubTotal());
            
            ejecutarActualizacion();        
        } catch (SQLException e) {
            throw new SQLException("Error al insertar el detalle de compra:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public static DetalleCompraDAO getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new DetalleCompraDAO();
        }
        return instancia;
    }
}
