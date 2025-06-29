
package Modelo.DAOs;

import Modelo.BDatos.ComponentesBD;
import Modelo.Entidades.Categoria;
import Modelo.Estructuras.ArregloDinamico;
import java.sql.SQLException;

/**
 *
 * @author Jesus
 */
public class CategoriaDAO extends ComponentesBD{
    private static CategoriaDAO instancia;
    
    private CategoriaDAO() throws SQLException{
        super();
    }
    
    public ArregloDinamico<Categoria> cargarCategorias() throws SQLException{
        ArregloDinamico<Categoria> categorias = new ArregloDinamico<>();
        String consulta = "SELECT * FROM categorias";
        
        try {
            pst = prepararConsulta(consulta);
            rs = ejecutarConsulta();
            
            while (rs.next()){
                Categoria categoria = new Categoria(
                        rs.getInt("id_categoria"), rs.getString("nombre_categoria"), 
                        rs.getString("descripcion"), rs.getBoolean("categoria_activa")
                );
                categorias.add(categoria);
            }
            return categorias;
        } catch (SQLException e){
            throw new SQLException("Error al obtener las categorias:\n" + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }
    
    public static CategoriaDAO getInstancia() throws SQLException{
        if (instancia == null){
            instancia = new CategoriaDAO();
        }
        return instancia;
    }
}
