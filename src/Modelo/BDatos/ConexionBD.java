
package Modelo.BDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jesus
 */
public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/dbTiendaMass";
    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "u23230482";
    private Connection conexion;
    private static ConexionBD instancia;
    
    private ConexionBD() throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
            this.conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (ClassNotFoundException | SQLException e){
            throw new SQLException("Error al conectar a la base de datos:\n" + e.getMessage());
        }
    }
    
    public static synchronized ConexionBD getInstancia() throws SQLException{
        if (instancia == null){
            instancia = new ConexionBD();
        }
        return instancia;
    }
    
    public Connection getConexion () throws SQLException{
        if (conexion == null || conexion.isClosed()){
            throw new SQLException("La conexion esta cerrada o es nula.");
        }
        return conexion;
    }
    
    public void cerrarConexion() throws SQLException{
        if (conexion != null){
            try {
                conexion.isClosed(); 
            } catch (SQLException e){
                throw new SQLException("Error al cerrar la conexion:\n" + e.getMessage());
            }
        }
    }
}
