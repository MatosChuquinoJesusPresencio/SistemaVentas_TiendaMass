
package Modelo.DAOs;

import Modelo.BDatos.ComponentesBD;
import Modelo.Entidades.Proveedor;
import Modelo.Estructuras.ArregloDinamico;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class ProveedorDAO extends ComponentesBD{
    private static ProveedorDAO instancia;
    
    private ProveedorDAO() throws SQLException {
        super();
    }
    
    public ArregloDinamico<Proveedor> cargarProveedores() throws SQLException {
        ArregloDinamico<Proveedor> proveedores = new ArregloDinamico<>();
        String consulta = "SELECT * FROM proveedores";
        
        try {
            pst = prepararConsulta(consulta);
            rs = ejecutarConsulta();
            
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getInt("id_proveedor"), rs.getString("nombre_proveedor"),
                        rs.getString("telefono"),
                        rs.getString("email"), rs.getString("direccion"),
                        rs.getBoolean("proveedor_habilitado")
                );
                proveedores.add(proveedor);
            }
            return proveedores;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los proveedores:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public static ProveedorDAO getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new ProveedorDAO();
        }
        return instancia;
    }
}
