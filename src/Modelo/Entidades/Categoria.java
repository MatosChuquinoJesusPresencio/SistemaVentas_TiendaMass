
package Modelo.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class Categoria {
    private int idCategoria;
    private String nombreCategoria;
    private String descripcion;
    private boolean categoriaActiva;

    public Categoria() {}

    public Categoria(int idCategoria, String nombreCategoria, 
            String descripcion, boolean categoriaActiva) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
        this.categoriaActiva = categoriaActiva;
    }

    public int getIdCategoria() {return idCategoria;}
    public String getNombreCategoria() {return nombreCategoria;}
    public String getDescripcion() {return descripcion;}
    public boolean isCategoriaActiva() {return categoriaActiva;}
    
    public void setIdCategoria(int idCategoria) {this.idCategoria = idCategoria;}
    public void setNombreCategoria(String nombreCategoria) {this.nombreCategoria = nombreCategoria;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setCategoriaActiva(boolean categoriaActiva) {this.categoriaActiva = categoriaActiva;}
}
