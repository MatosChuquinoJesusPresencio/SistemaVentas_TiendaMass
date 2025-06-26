
package Modelo.DAOs;

import Modelo.BDatos.ComponentesBD;
import Modelo.Entidades.Producto;
import Modelo.Estructuras.ArregloDinamico;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jesus
 */
public class ProductoDAO extends ComponentesBD{
    private static ProductoDAO instancia;
    
    private ProductoDAO() throws SQLException{
        super();
    }
    
    public Producto mapearProducto (ResultSet rs) throws SQLException{
        Producto producto = new Producto();
        
        producto.setIdProducto(rs.getInt("id_producto"));
        producto.setIdCategoria(rs.getInt("id_categoria"));
        producto.setCodigoBarras(rs.getString("codigo_barras"));
        producto.setNombre(rs.getString("nombre_producto"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setActivo(rs.getBoolean("producto_activo"));
        
        return producto;
    }
    
    public Producto buscarProductoID (int idProducto) throws SQLException{
        String consulta = "SELECT * FROM productos WHERE id_producto = ?";
        
        try {
            pst = prepararConsulta(consulta);
            pst.setInt(1, idProducto);
            rs = ejecutarConsulta();
            
            if (rs.next()){
                return mapearProducto(rs);
            }
            
            return null;
        } catch (SQLException e){
            throw new SQLException("Error al buscar producto por ID: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }  
    }
    
    public Producto buscarProductoCB (String codigoBarras) throws SQLException{
        String consulta = "SELECT * FROM productos WHERE codigo_barras = ?";
        
        try {
            pst = prepararConsulta(consulta);
            pst.setString(1, codigoBarras);
            rs = ejecutarConsulta();
            
            if (rs.next()){
                return mapearProducto(rs);
            }
            
            return null;
        } catch (SQLException e){
            throw new SQLException("Error al buscar producto por SKU: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }  
    }
    
    public ArregloDinamico<Producto> buscarProductoCategoria (int idCategoria) throws SQLException{
        ArregloDinamico<Producto> productos = new ArregloDinamico<>();
        String sql_codigo_barras = "SELECT * FROM productos WHERE id_categoria = ?";
        
        try {
            pst = prepararConsulta(sql_codigo_barras);
            
            pst.setInt(1, idCategoria);
            rs = ejecutarConsulta();
            
            while (rs.next()){
                productos.add(mapearProducto(rs)); 
            }
            
            return productos;
            
        } catch (SQLException e){
            throw new SQLException("Error al buscar producto por SKU: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }  
    }
    
    public ArregloDinamico<Producto> buscarPorNombreParcial(String nombre) throws SQLException{
        ArregloDinamico<Producto> productos = new ArregloDinamico<>();
        String consulta = "SELECT * FROM productos WHERE LOWER(nombre_producto) LIKE ?";
        
        try {
            pst = prepararConsulta(consulta);
            
            pst.setString(1, "%" + nombre + "%");
            rs = ejecutarConsulta();
            
            while (rs.next()){
                productos.add(mapearProducto(rs)); 
            }
            
            return productos;
            
        } catch (SQLException e){
            throw new SQLException("Error al buscar producto por nombre: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }  
    }
    
    public static ProductoDAO getInstance() throws SQLException{
        if (instancia == null){
            instancia = new ProductoDAO();
        }
        return instancia;
    }
}
