
package Modelo.Entidades;

/**
 *
 * @author Jesus
 */
public class SesionUsuario {
    private int idUsuario;
    private int idEmpleado;
    private String nombreUsuario;
    private String nombreEmplado;
    private boolean habilitado;
    private String nombreRol;
    
    public SesionUsuario(){}
    
    public SesionUsuario(int idUsuario, int idEmpleado, String nombreUsuario, 
            String nombreEmpleado, boolean habilitado, String nombreRol){
        this.idUsuario = idUsuario;
        this.idEmpleado = idEmpleado;
        this.nombreUsuario = nombreUsuario;
        this.nombreEmplado = nombreEmpleado;
        this.habilitado = habilitado;
        this.nombreRol = nombreRol;
    }
    
    public int getIdUsuario() {return idUsuario;}
    public int getIdEmpleado() {return idEmpleado;}
    public String getNombreUsuario() {return nombreUsuario;}
    public String getNombreEmplado() {return nombreEmplado;}
    public boolean getHabilitado() {return habilitado;}
    public String getNombreRol() {return nombreRol;}
    
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}
    public void setIdEmpleado(int idEmpleado) {this.idEmpleado = idEmpleado;}
    public void setNombreUsuario(String nombreUsuario) {this.nombreUsuario = nombreUsuario;}
    public void setNombreEmplado(String nombreEmplado) {this.nombreEmplado = nombreEmplado;}
    public void setHabilitado(boolean habilitado) {this.habilitado = habilitado;}
    public void setNombreRol(String nombreRol) {this.nombreRol = nombreRol;}
}
