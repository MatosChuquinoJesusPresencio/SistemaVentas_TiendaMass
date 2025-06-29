
package Controlador;

import Vista.FrmLogin;
import Vista.FrmMenuPrincipal;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class ControladorMenu {
    private final FrmMenuPrincipal frmMenu;
    
    public ControladorMenu(FrmMenuPrincipal frmMenu){
        this.frmMenu = frmMenu;
    }
    
    public void cerrarSesion(){
        int opcion = JOptionPane.showConfirmDialog(frmMenu, 
                  "¿Estas seguro que desear cerrar la sesion?",
                  "Finalizar Sesion", 
                  JOptionPane.YES_NO_OPTION);
          if (opcion == JOptionPane.YES_OPTION){
              FrmLogin frmLogin = new FrmLogin();
              frmLogin.setVisible(true);
              frmMenu.dispose();
        }  
    }
}
