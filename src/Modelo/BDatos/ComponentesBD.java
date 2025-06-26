
package Modelo.BDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jesus
 */
public class ComponentesBD {
    protected Connection conexion;
    protected PreparedStatement pst;
    protected ResultSet rs;
    
    public ComponentesBD() throws SQLException{
        conexion = ConexionBD.getInstancia().getConexion();
    }
    
    protected PreparedStatement prepararConsulta (String consulta) throws SQLException{
        pst = conexion.prepareStatement(consulta);
        return pst;
    } 
    
    protected PreparedStatement prepararConsultaConClave (String consulta) throws SQLException{
        pst = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
        return pst;
    } 
    
    protected ResultSet ejecutarConsulta() throws SQLException{
        return pst.executeQuery();
    }
    
    protected int ejecutarActualizacion() throws SQLException{
        return pst.executeUpdate();
    }
    
    protected ResultSet obtenerClaves() throws SQLException{
        return pst.getGeneratedKeys();
    }
    
    protected void cerrarRecursos() throws SQLException{
        if (rs != null){
            rs.close();
        }
        if (pst != null){
            pst.close();
        }
    }
}
