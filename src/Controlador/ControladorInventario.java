
package Controlador;

import Modelo.DAOs.CategoriaDAO;
import Modelo.DAOs.InventarioDAO;
import Modelo.DAOs.ProductoDAO;
import Modelo.Entidades.Categoria;
import Modelo.Entidades.Inventario;
import Modelo.Entidades.Producto;
import Modelo.Estructuras.ArregloDinamico;
import Utilidades.Mensajes;
import Vista.FrmMenuPrincipal;
import Vista.PnlInventario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jesus
 */
public class ControladorInventario {
    private ProductoDAO productoDAO;
    private InventarioDAO inventarioDAO;
    private final FrmMenuPrincipal frmMenuPrincipal;
    private final PnlInventario pnlInventario;
    private CategoriaDAO categoriaDAO; 
    
    public ControladorInventario(FrmMenuPrincipal frmMenuPrincipal, PnlInventario pnlInventario) {
        try {
            this.inventarioDAO = InventarioDAO.getInstancia();
            this.productoDAO = ProductoDAO.getInstance();
            this.categoriaDAO = CategoriaDAO.getInstancia();
        } catch (SQLException e) {
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
        this.pnlInventario = pnlInventario;
        this.frmMenuPrincipal = frmMenuPrincipal;
    }
    
    public void actualizarCategorias(){
        try {
            ArregloDinamico<Categoria> categorias = categoriaDAO.cargarCategorias();
            pnlInventario.CargarCategorias(categorias);
        } catch (SQLException e) {
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void actualizarInventario(){
        try {
            ArregloDinamico<Inventario> inventarios = inventarioDAO.cargarInventarios();
            pnlInventario.actualizarTablaInventario(inventarios);
        } catch (SQLException e) {
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public String obtenerNombreProducto(int idProducto){
        try {
            Producto producto = productoDAO.buscarProductoID(idProducto);
            return producto.getNombre();
        } catch (SQLException e) {
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
        return null;
    }
    
    public void actualizarTablaPorParametro(String parametro, String seleccion){
        try {
        
            if (seleccion == null){
                Mensajes.mostrarAd(frmMenuPrincipal, "Espera", "Seleccione un criterio antes");
                return;
            }

            switch (seleccion.toLowerCase()) {
                case "id" -> {
                    try {
                        int id = Integer.parseInt(parametro);
                        inventarioID(id);
                    } catch (NumberFormatException e) {
                        Mensajes.mostrarError(frmMenuPrincipal, "Error", "El ID debe ser un número.");
                    }
                }

                case "codigo barras" -> inventarioCB(parametro);

                case "categoria" -> inventarioCategoria(parametro);

                case "stock menor a" -> {
                    try {
                        int cantidad = Integer.parseInt(parametro);
                        inventarioMenor(cantidad);
                    } catch (NumberFormatException e) {
                        Mensajes.mostrarError(frmMenuPrincipal, "Error", "Debe ingresar un número para comparar stock.");
                    }
                }

                case "stock mayor a" -> {
                    try {
                        int cantidad = Integer.parseInt(parametro);
                        inventarioMayor(cantidad);
                    } catch (NumberFormatException e) {
                        Mensajes.mostrarError(frmMenuPrincipal, "Error", "Debe ingresar un número para comparar stock.");
                    }
                }

                case "nombre" -> inventarioNombre(parametro.toLowerCase());

                default -> Mensajes.mostrarError(frmMenuPrincipal, "Error", "Criterio de búsqueda no válido.");
            }
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void inventarioID(int idProducto) throws SQLException{
        Inventario inventario = inventarioDAO.buscarInventarioProducto(idProducto);
        ArregloDinamico<Inventario> datos = new ArregloDinamico<>();
        if (inventario != null){
            datos.add(inventario);       
        }
        pnlInventario.actualizarTablaInventario(datos);
    }
    
    public void inventarioCB(String codigoBarras){
        try{
            Producto producto = productoDAO.buscarProductoCB(codigoBarras);
            ArregloDinamico<Inventario> datos = new ArregloDinamico<>();
            if (producto != null){
                Inventario inventario = inventarioDAO.buscarInventarioProducto(producto.getIdProducto());
                if (inventario != null){
                    datos.add(inventario);
                }
            }
            pnlInventario.actualizarTablaInventario(datos);
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void inventarioCategoria(String nombreCategoria){
        try {
            Categoria categoriaElegida = null;
            ArregloDinamico<Categoria> categorias = categoriaDAO.cargarCategorias();
            for (int i = 0; i < categorias.getDimension(); i++){
                Categoria categoria = categorias.get(i);
                if (categoria.getNombreCategoria().equals(nombreCategoria)){
                    categoriaElegida = categoria;
                    break;
                }
            }
            if (categoriaElegida != null){
                ArregloDinamico<Producto> productos;
                productos = productoDAO.buscarProductoCategoria(categoriaElegida.getIdCategoria());
                ArregloDinamico<Inventario> datos = new ArregloDinamico<>();
                for (int i = 0; i < productos.getDimension(); i++){
                    Producto producto = productos.get(i);
                    Inventario inventario = inventarioDAO.buscarInventarioProducto(producto.getIdProducto());
                    if (inventario != null){
                        datos.add(inventario);
                    }
                }
                pnlInventario.actualizarTablaInventario(datos); 
            }
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void inventarioMayor(int stock){
        try{
            ArregloDinamico<Inventario> inventarios = inventarioDAO.cargarInventarios();
            ArregloDinamico<Inventario> datos = new ArregloDinamico<>();

            for (int i = 0; i < inventarios.getDimension(); i++) {
                Inventario inventario = inventarios.get(i);
                if (inventario.getStockActual() >= stock){
                    datos.add(inventario);
                }
            } 
            pnlInventario.actualizarTablaInventario(datos);
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void inventarioMenor(int stock){
        try{
            ArregloDinamico<Inventario> inventarios = inventarioDAO.cargarInventarios();
            ArregloDinamico<Inventario> datos = new ArregloDinamico<>();

            for (int i = 0; i < inventarios.getDimension(); i++) {
                Inventario inventario = inventarios.get(i);
                if (inventario.getStockActual() <= stock){
                    datos.add(inventario);
                }
            } 
            pnlInventario.actualizarTablaInventario(datos);
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void inventarioNombre(String nombre){
        try {
            ArregloDinamico<Producto> productos = productoDAO.buscarPorNombreParcial(nombre);
            ArregloDinamico<Inventario> datos = new ArregloDinamico<>();

            for (int i = 0; i < productos.getDimension(); i++){
                Producto producto = productos.get(i);
                Inventario inventario = inventarioDAO.buscarInventarioProducto(producto.getIdProducto());
                if (inventario != null){
                    datos.add(inventario);
                }
            }
            pnlInventario.actualizarTablaInventario(datos);
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
}
