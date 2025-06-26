
package Modelo.DAOs;

import Modelo.BDatos.ComponentesBD;
import Modelo.Entidades.SesionUsuario;
import java.sql.SQLException;

/**
 *
 * @author Jesus
 */
public class SesionUsuarioDAO extends ComponentesBD{
    private static SesionUsuarioDAO instancia;
    
    private SesionUsuarioDAO() throws SQLException{
        super();
    }
    
    public SesionUsuario obtenerSesion(String nombreUsuario, String clave) throws SQLException{
        String consulta = """
                          SELECT 
                            u.id_usuario, 
                            u.nombre_usuario, 
                            u.usuario_habilitado, 
                            e.nombre,
                            e.id_empleado,
                            r.nombre_rol
                          FROM usuarios u 
                          INNER JOIN empleados e ON u.id_empleado = e.id_empleado 
                          INNER JOIN roles r ON e.id_rol = r.id_rol 
                          WHERE u.nombre_usuario = ? AND u.clave = ?
                          """;
        try {
            pst = prepararConsulta(consulta);
            pst.setString(1, nombreUsuario);
            pst.setString(2, clave);
            rs = ejecutarConsulta();
            
            if (rs.next()){
                SesionUsuario sesionUsuario = new SesionUsuario(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_empleado"),
                        rs.getString("nombre_usuario"),
                        rs.getString("nombre"),
                        rs.getBoolean("usuario_habilitado"),
                        rs.getString("nombre_rol")
                );
                actualizarLogin(sesionUsuario);
                return sesionUsuario;
            }
            return null;
            
        } catch (SQLException e){
            throw new SQLException("Error al verificar las credenciales:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public void actualizarLogin(SesionUsuario sesionUsuario) throws SQLException{
        String consulta = """
                          UPDATE usuarios 
                          SET ultimo_login = CURRENT_TIMESTAMP
                          WHERE id_usuario = ?
                          """;
        try {
            pst = prepararConsulta(consulta);
            pst.setInt(1, sesionUsuario.getIdUsuario());
            ejecutarActualizacion();
            
        } catch (SQLException e){
            throw new SQLException("Erro al actualizar el login:\n" + e.getMessage());
        }
    }
    
    public static SesionUsuarioDAO getInstance() throws SQLException{
        if (instancia == null){
            instancia = new SesionUsuarioDAO();
        }
        return instancia;
    }
}
