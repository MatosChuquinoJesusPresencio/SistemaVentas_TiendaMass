
package Modelo.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private boolean habilitado;
    
    public Proveedor(){}
    
    public Proveedor(int idProveedor, String nombre, String telefono,
            String email, String direccion, boolean habilitado) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.habilitado = habilitado;
    }
    
    public int getIdProveedor() {return idProveedor;}
    public String getNombre() {return nombre;}
    public String getTelefono() {return telefono;}
    public String getEmail() {return email;}
    public String getDireccion() {return direccion;}
    public boolean getHabilitado() {return habilitado;}
    
    public void setIdProveedor(int idProveedor) {this.idProveedor = idProveedor;}
    public void setNombre(String nombre) { this.nombre = nombre;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public void setEmail(String email) {this.email = email;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setHabilitado(boolean habilitado) {this.habilitado = habilitado;}
}
