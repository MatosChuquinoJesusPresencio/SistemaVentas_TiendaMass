
package Controlador;

import Modelo.DAOs.SesionUsuarioDAO;
import Modelo.Entidades.SesionUsuario;
import Utilidades.Mensajes;
import Utilidades.Validar;
import Vista.FrmLogin;
import Vista.FrmMenuPrincipal;
import java.sql.SQLException;

/**
 *
 * @author Jesus
 */
public class ControladorLogin {
    private final FrmLogin frmLogin;
    private SesionUsuarioDAO sesionUsuarioDAO;
    
    public ControladorLogin(FrmLogin frmLogin){
        this.frmLogin = frmLogin;
        try {
            this.sesionUsuarioDAO = SesionUsuarioDAO.getInstance();
        } catch (SQLException e) {
            Mensajes.mostrarError(frmLogin, "Error", e.getMessage());
        }
    }
    
    public void iniciarSesion(String nombre, String clave){
        try {
            if (!Validar.entradaValida(clave) || !Validar.entradaValida(nombre)){
                Mensajes.mostrarError(frmLogin, "¡Espera!", "Complete las campos necesarios");
                return;
            }
            SesionUsuario usuarioActual = sesionUsuarioDAO.obtenerSesion(nombre, clave);
            if (usuarioActual != null){
                if (usuarioActual.getHabilitado()){
                    Mensajes.mostrarInfo(frmLogin, "Acceso Exitoso", "Bienvenido " + usuarioActual.getNombreEmplado());
                    FrmMenuPrincipal menuInicio = new FrmMenuPrincipal(usuarioActual);
                    menuInicio.setVisible(true);
                    frmLogin.dispose();
                } else {
                    Mensajes.mostrarAd(frmLogin, "Cuenta desactivada", "Su cuenta ha sido desactivada por el administrador");
                }
            } else {
                Mensajes.mostrarError(frmLogin, "Credenciales incorrectas", "Los datos ingresados son incorrectos");
            }
        } catch (SQLException e) {
            Mensajes.mostrarError(frmLogin, "Error en la conexion", e.getMessage());
        }
    }
}
