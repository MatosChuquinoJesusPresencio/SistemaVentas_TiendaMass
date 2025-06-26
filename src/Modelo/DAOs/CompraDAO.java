
package Modelo.DAOs;

import Modelo.BDatos.ComponentesBD;
import Modelo.Entidades.Compra;
import java.sql.SQLException;

/**
 *
 * @author Jesus
 */
public class CompraDAO extends ComponentesBD{
    private static CompraDAO instancia;
    
    private CompraDAO() throws SQLException {
        super();
    }
    
    public int crearCompra(Compra compra) throws SQLException {
        String consulta = "INSERT INTO compras (id_empleado, id_proveedor, numero_compra, "
                + "total) VALUES (?, ?, ?, ?)";
        try {
            pst = prepararConsultaConClave(consulta);
            
            pst.setInt(1, compra.getIdEmpleado());
            pst.setInt(2, compra.getIdProveedor());
            pst.setString(3, compra.getNumeroCompra());
            pst.setDouble(4, compra.getTotalCompra());
            
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
            throw new SQLException("Error al insertar la compra:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public static CompraDAO getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new CompraDAO();
        }
        return instancia;
    }
}
