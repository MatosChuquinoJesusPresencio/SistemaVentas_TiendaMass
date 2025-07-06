
package Modelo.DAOs;

import Modelo.BDatos.ComponentesBD;
import Modelo.Entidades.Inventario;
import Modelo.Estructuras.ArregloDinamico;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class InventarioDAO extends ComponentesBD{
    private static InventarioDAO instancia;
    
    private InventarioDAO() throws SQLException {
        super();
    }
    
    public ArregloDinamico<Inventario> cargarInventarios() throws SQLException {
        ArregloDinamico<Inventario> inventarios = new ArregloDinamico<>();
        String consulta = "SELECT * FROM inventario";
        
        try {
            pst = prepararConsulta(consulta);
            rs = ejecutarConsulta();
            
            while (rs.next()) {
                Inventario inventario = new Inventario(
                        rs.getInt("id_inventario"), rs.getInt("id_producto"),
                        rs.getInt("stock_actual"), rs.getInt("stock_minimo"),
                        rs.getObject("fecha_actualizacion", LocalDateTime.class)
                );
                inventarios.add(inventario);
            }
            return inventarios;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los inventarios:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public Inventario buscarInventarioProducto(int idProducto) throws SQLException{
        String consulta = "SELECT * FROM inventario WHERE id_producto = ?";
        try {
            pst = prepararConsulta(consulta);
            pst.setInt(1, idProducto);
            rs = ejecutarConsulta();
            
            if (rs.next()){
                Inventario inventario = new Inventario(
                        rs.getInt("id_inventario"),
                        rs.getInt("id_producto"),
                        rs.getInt("stock_actual"),
                        rs.getInt("stock_minimo"),
                        rs.getObject("fecha_actualizacion", LocalDateTime.class)
                );
                return inventario;
            }
            return null;
        } catch (SQLException e){
            throw new SQLException("Error al buscar el inventario:\n"
                    + e.getMessage());
        }
    }
    
    public boolean actualizarStockActual(int idProducto, int nuevoStock) throws SQLException {
        String consulta = "UPDATE inventario SET stock_actual = ?, fecha_actualizacion = ? WHERE id_producto = ?";
        try {
            pst = prepararConsulta(consulta);
            pst.setInt(1, nuevoStock);
            pst.setObject(2, LocalDateTime.now());
            pst.setInt(3, idProducto);
            int filasAfectadas = ejecutarActualizacion();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el stock actual:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }

    public boolean actualizarStockMinimo(int idProducto, int nuevoStockMinimo) throws SQLException {
        String consulta = "UPDATE inventario SET stock_minimo = ?, fecha_actualizacion = ? WHERE id_producto = ?";
        try {
            pst = prepararConsulta(consulta);
            pst.setInt(1, nuevoStockMinimo);
            pst.setObject(2, LocalDateTime.now());
            pst.setInt(3, idProducto);
            int filasAfectadas = ejecutarActualizacion();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el stock mínimo:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public static InventarioDAO getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new InventarioDAO();
        }
        return instancia;
    }
}
