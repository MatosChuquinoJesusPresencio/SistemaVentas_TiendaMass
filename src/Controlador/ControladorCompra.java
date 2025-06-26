
package Controlador;

import Modelo.DAOs.CompraDAO;
import Modelo.DAOs.DetalleCompraDAO;
import Modelo.DAOs.ProductoDAO;
import Modelo.DAOs.ProveedorDAO;
import Modelo.Entidades.Compra;
import Modelo.Entidades.DetalleCompra;
import Modelo.Entidades.Producto;
import Modelo.Entidades.Proveedor;
import Modelo.Estructuras.ArregloDinamico;
import Modelo.Estructuras.ListaSimple;
import Modelo.Estructuras.NodoLista;
import Utilidades.GeneraraDatos;
import Utilidades.Mensajes;
import Utilidades.Validar;
import Vista.FrmMenuPrincipal;
import Vista.PnlCompra;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class ControladorCompra {
    private ListaSimple lista;
    private FrmMenuPrincipal frmMenuPrincipal;
    private PnlCompra pnlCompra;
    private ProductoDAO productoDAO;
    private CompraDAO compraDAO;
    private DetalleCompraDAO detalleCompraDAO;
    private ProveedorDAO proveedorDAO;

    public ControladorCompra(FrmMenuPrincipal frmMenuPrincipal, PnlCompra pnlCompra) {
        this.frmMenuPrincipal = frmMenuPrincipal;
        this.pnlCompra = pnlCompra;
        this.lista = new ListaSimple();
        try {
            this.compraDAO = CompraDAO.getInstancia();
            this.productoDAO = ProductoDAO.getInstance();
            this.proveedorDAO = ProveedorDAO.getInstancia();
            this.detalleCompraDAO = DetalleCompraDAO.getInstancia();
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void agregarCB(String codigoBarras, String strCantidad){
        try {  
            if (Validar.entradaValida(codigoBarras) || Validar.cBarrasValido(codigoBarras)){
                if (!pnlCompra.nombreProveedor().equals("SELECCIONAR PRODUCTO")){
                    if (Validar.entradaValida(strCantidad)){
                        int cantidad = Integer.parseInt(strCantidad);
                        if (cantidad >= 0){
                            Producto producto = productoDAO.buscarProductoCB(codigoBarras);
                            if (producto != null){
                                boolean agregado = lista.add(producto, cantidad);
                                if (agregado){
                                    pnlCompra.actualizarCarrito();
                                    pnlCompra.actualizarTotal(lista.montoTotal());
                                    Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Producto agregado.");
                                }
                            } else {
                                Mensajes.mostrarError(frmMenuPrincipal, "Error", "Error al agregar el producto.");
                            }
                        } else {
                            Mensajes.mostrarAd(frmMenuPrincipal, "Cantidad invalida", "La cantidad debe ser mayor a cero");
                        }
                    } else {
                        Mensajes.mostrarAd(frmMenuPrincipal, "Espera", "Ingrese una cantidad");
                    }
                } else {
                    Mensajes.mostrarAd(frmMenuPrincipal, "Espera", "Busque el producto primero");
                }
            } else {
                Mensajes.mostrarError(frmMenuPrincipal, "Codigo de barras invalido", "Ingrese un codigo de barras valida");
            }
        } catch (NumberFormatException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Cantidad invalida", "Ingrese una cantidad valida");
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void eliminar(int Id){
        if (Id == -1){
            return;
        }
        
        int confirmar = JOptionPane.showConfirmDialog(frmMenuPrincipal, 
                        "¿Estas seguro que quieres eliminar el producto?", 
                        "Confirmar eliminar:",
                        JOptionPane.YES_NO_OPTION);
                
        if (confirmar == JOptionPane.YES_OPTION){
            boolean eliminado = lista.delete(Id);
            if (eliminado){
                pnlCompra.actualizarCarrito();
                pnlCompra.actualizarTotal(lista.montoTotal());
                Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Producto eliminado.");
            } else {
                Mensajes.mostrarError(frmMenuPrincipal, "Error", "Error al eliminar el producto.");
            }
        }
    }

    public void modificar(int Id){
        String strCantidad = JOptionPane.showInputDialog("Ingrese la cantidad:"); 
        if (Validar.entradaValida(strCantidad)){
            int cantidad = Integer.parseInt(strCantidad);
            if (cantidad > 0){
                boolean modificado = lista.modify(Id, cantidad);
                if (modificado){
                    pnlCompra.actualizarCarrito();
                    pnlCompra.actualizarTotal(lista.montoTotal());
                    Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Producto modificado.");
                } else {
                    Mensajes.mostrarError(frmMenuPrincipal, "Error", "Error al modificar la cantidad.");
                }
            } else {
                Mensajes.mostrarAd(frmMenuPrincipal, "Cantidad incorrecta", "La cantidad debe ser mayor a 0.");
            }
        } else {
            Mensajes.mostrarAd(frmMenuPrincipal, "Valor ingresado invalido", "Debe ingresar una cantidad.");
        }
    }
    
    public String buscarProducto(String codigoBarras){
        try {
            Producto producto = productoDAO.buscarProductoCB(codigoBarras);
            if (producto != null){
                return producto.getNombre();
            }
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
        return null;
    }
    
    public void procesarCompra() {
        try {
            if (lista.empty()) {
                Mensajes.mostrarAd(frmMenuPrincipal, "Espera", "El carrito está vacío.");
                return;
            }

            int opcion = JOptionPane.showConfirmDialog(
                    frmMenuPrincipal, "¿Procesar compra?", "Confirmar", 
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {

                int idEmpleado = frmMenuPrincipal.getSesion().getIdEmpleado();
                String numeroCompra = GeneraraDatos.generarNumeroCompra();
                int idProveedor = 0;

                ArregloDinamico<Proveedor> proveedores = proveedorDAO.cargarProveedores();

                for (int i = 0; i < proveedores.getDimension(); i++){
                    if (proveedores.get(i).getNombre().equals(pnlCompra.nombreProveedor())){
                        idProveedor = proveedores.get(i).getIdProveedor();
                        break;
                    }
                }

                Compra compra = new Compra(
                        idEmpleado, 
                        idProveedor,
                        numeroCompra, 
                        lista.montoTotal(),
                        "TARJETA"
                );
                
                int idCompra = compraDAO.crearCompra(compra);
                System.out.println("ID: " + idCompra);

                NodoLista puntero = lista.getCabeza();
                while(puntero != null){
                    DetalleCompra detalleCompra = new DetalleCompra(
                            idCompra, 
                            puntero.getProducto().getIdProducto(),
                            puntero.getCantidad(),
                            puntero.getProducto().getPrecio(),
                            puntero.subTotal()
                    );
                    detalleCompraDAO.crearDetalleCompra(detalleCompra);
                    puntero = puntero.getSiguiente();
                }

                lista.clean();
                pnlCompra.actualizarCarrito();
                pnlCompra.actualizarTotal(lista.montoTotal());
                pnlCompra.reiniciarValores();
                pnlCompra.seleccionarProveedor(false);
                Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Venta procesada exitosamente");
            }
            
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }    
    }
    
    public void vaciarCarrito(){
        if (lista.empty()) {
            Mensajes.mostrarAd(frmMenuPrincipal, "Espera", "El carrito se encuentra vacío.");
            return;
        }
        
        int opcion = JOptionPane.showConfirmDialog(
                frmMenuPrincipal, "¿Vaciar carrito?", "Confirmar", 
                JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            lista.clean();
            pnlCompra.reiniciarValores();
            pnlCompra.actualizarCarrito();
            pnlCompra.actualizarTotal(lista.montoTotal());
            Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Carrito limpiado.");
        }
    }
    
    public void actualizarProveedores(){
        try {
            ArregloDinamico<Proveedor> proveedores = proveedorDAO.cargarProveedores();
            pnlCompra.cargarCombo(proveedores);
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public ListaSimple getLista(){return lista;}
}
