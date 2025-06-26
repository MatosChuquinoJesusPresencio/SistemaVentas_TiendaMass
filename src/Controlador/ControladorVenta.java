
package Controlador;

import Modelo.DAOs.DetalleVentaDAO;
import Modelo.DAOs.ProductoDAO;
import Modelo.DAOs.VentaDAO;
import Modelo.Entidades.DetalleVenta;
import Modelo.Entidades.Producto;
import Modelo.Entidades.Venta;
import Modelo.Estructuras.ListaSimple;
import Modelo.Estructuras.NodoLista;
import Utilidades.GeneraraDatos;
import Utilidades.Mensajes;
import Utilidades.Validar;
import Vista.FrmMenuPrincipal;
import Vista.PnlVenta;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class ControladorVenta {
    private final ListaSimple lista;
    private final FrmMenuPrincipal frmMenuPrincipal;
    private final PnlVenta pnlVenta;
    private ProductoDAO productoDAO;
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleVentaDAO;
    
    public ControladorVenta(FrmMenuPrincipal frmMenuPrincipal, PnlVenta pnlVenta){
        this.lista = new ListaSimple();
        this.pnlVenta = pnlVenta;
        this.frmMenuPrincipal = frmMenuPrincipal;
        try {
            this.ventaDAO = VentaDAO.getInstancia();
            this.detalleVentaDAO = DetalleVentaDAO.getInstancia();
            this.productoDAO = ProductoDAO.getInstance();
        } catch (SQLException e) {
            Mensajes.mostrarError(pnlVenta, "Error", e.getMessage());
        }
    }
    
    public void agregarPorId(){
        try{
            String strID = JOptionPane.showInputDialog("Ingrese el ID del producto: ");
            if (Validar.entradaValida(strID)){
                int idProducto = Integer.parseInt(strID);
                
                String strCantidad = JOptionPane.showInputDialog("Ingrese la cantidad del producto: ");
                if (Validar.entradaValida(strCantidad)){
                    int cantidad = Integer.parseInt(strCantidad);
                    if (cantidad > 0){
                        Producto producto = productoDAO.buscarProductoID(idProducto);
                        if (producto != null){
                            boolean agregado = lista.add(producto, cantidad);
                            if (agregado){
                                pnlVenta.actualizarCarrito();
                                pnlVenta.actualizarVuelto();
                                pnlVenta.actualizarTotal(lista.montoTotal());
                                Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Producto agregado");
                            } else {
                                Mensajes.mostrarError(frmMenuPrincipal, "Error", "Error al agregar el producto");
                            }
                        } else {
                            Mensajes.mostrarError(frmMenuPrincipal, "Error", "Id no encontrado en la base de datos");
                        }
                    } else {
                        Mensajes.mostrarAd(frmMenuPrincipal, "Cantidad erronea", "La cantidad debe ser mayor a 0");
                    } 
                } else {
                    Mensajes.mostrarAd(frmMenuPrincipal, "Cantidad vacia", "Debe ingresar una cantidad.");
                }    
            } else {
               Mensajes.mostrarAd(frmMenuPrincipal, "ID vacio", "Debe ingresar un ID.");    
            }
        } catch (NumberFormatException e){
            Mensajes.mostrarAd(frmMenuPrincipal, "Dato incorrecto", "El dato ingresado no es una cantidad/id valido");
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void agregarPorCB(){
        try {
            String codigoBarras = JOptionPane.showInputDialog("Ingrese el codigo del producto: ");
            if (Validar.entradaValida(codigoBarras)){
                if (Validar.cBarrasValido(codigoBarras)){
                    String strCantidad = JOptionPane.showInputDialog("Ingrese la cantidad del producto: ");
                    if (Validar.entradaValida(strCantidad)){
                        int cantidad = Integer.parseInt(strCantidad);
                        if (cantidad > 0){
                            Producto producto = productoDAO.buscarProductoCB(codigoBarras);
                            if (producto != null){
                                boolean agregado = lista.add(producto, cantidad);
                                if (agregado){
                                    pnlVenta.actualizarCarrito();
                                    pnlVenta.actualizarVuelto();
                                    pnlVenta.actualizarTotal(lista.montoTotal());
                                    Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Producto agregado");
                                } else {
                                    Mensajes.mostrarError(frmMenuPrincipal, "Error", "Error al agregar el producto");
                                }
                            } else {
                                Mensajes.mostrarError(frmMenuPrincipal, "Error", "Codigo de barras no encontrado en la base de datos");
                            } 
                        } else {
                            Mensajes.mostrarAd(frmMenuPrincipal, "Error en la cantidad", "La cantidad debe ser mayor a 0");
                        }   
                    } else {
                        Mensajes.mostrarAd(frmMenuPrincipal, "Cantidad vacia", "Debe ingresar una cantidad");
                    }
                }  else {
                    Mensajes.mostrarAd(frmMenuPrincipal, "Error en el codigo de barras", "Codigo de barras no valido");
                }
            } else {
                Mensajes.mostrarAd(frmMenuPrincipal, "Codigo vacio", "Debe ingresar un codigo de barras");
            }
        } catch (NullPointerException e) {
            Mensajes.mostrarAd(frmMenuPrincipal, "Valor invalido", "Ingrese un valor valido");
        } catch (SQLException e){
            Mensajes.mostrarAd(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void modificar(int Id){
        try {
            String strCantidad = JOptionPane.showInputDialog("Ingrese la cantidad:"); 
            if (Validar.entradaValida(strCantidad)){
            int cantidad = Integer.parseInt(strCantidad);
                if (cantidad > 0){
                    boolean modificado = lista.modify(Id, cantidad);
                    if (modificado){
                        pnlVenta.actualizarCarrito();
                        pnlVenta.actualizarVuelto();
                        pnlVenta.actualizarTotal(lista.montoTotal());
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
        } catch (NumberFormatException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", "Ingrese una cantidad invalida");
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
                pnlVenta.actualizarCarrito();
                pnlVenta.actualizarVuelto();
                pnlVenta.actualizarTotal(lista.montoTotal());
                Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Producto eliminado.");
            } else {
                Mensajes.mostrarError(frmMenuPrincipal, "Error", "Error al eliminar el producto.");
            }
        }
    }
    
    public void vaciar(){
        if (lista.empty()) {
            Mensajes.mostrarAd(frmMenuPrincipal, "Espera", "El carrito se encuentra vacío.");
            return;
        }
        
        int opcion = JOptionPane.showConfirmDialog(
                frmMenuPrincipal, "¿Vaciar carrito?", "Confirmar", 
                JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            lista.clean();
            pnlVenta.actualizarCarrito();
            pnlVenta.actualizarImporte(0);
            pnlVenta.actualizarVuelto();
            pnlVenta.actualizarTotal(lista.montoTotal());
            Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Carrito limpiado.");
        }
    }
    
    public void procesarVenta(){
        try {
            if (lista.empty()) {
                Mensajes.mostrarAd(frmMenuPrincipal, "Espera", "El carrito está vacío.");
                return;
            }

            if (pnlVenta.getImporteMonto()<= 0) {
                Mensajes.mostrarAd(frmMenuPrincipal, "Espere", "Debe ingresar un importe válido antes de procesar la compra.");
                return;
            }

            int opcion = JOptionPane.showConfirmDialog(
                    frmMenuPrincipal, "¿Procesar compra?", "Confirmar", 
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {

                int idEmpleado = frmMenuPrincipal.getSesion().getIdEmpleado();
                String numeroVenta = GeneraraDatos.generarNumeroVenta();

                Venta venta = new Venta(
                        idEmpleado, 
                        numeroVenta, 
                        lista.montoTotal(), 
                        "EFECTIVO");
                int idVenta = ventaDAO.crearVenta(venta);

                NodoLista puntero = lista.getCabeza();
                while(puntero != null){
                    DetalleVenta detalleVenta = new DetalleVenta(
                            idVenta, 
                            puntero.getProducto().getIdProducto(),
                            puntero.getCantidad(),
                            puntero.getProducto().getPrecio(),
                            puntero.subTotal()
                    );
                    detalleVentaDAO.crearDetalleVenta(detalleVenta);
                    puntero = puntero.getSiguiente();
                }

                lista.clean();
                pnlVenta.actualizarCarrito();
                pnlVenta.actualizarImporte(0);
                pnlVenta.actualizarVuelto();
                pnlVenta.actualizarTotal(lista.montoTotal());
                Mensajes.mostrarInfo(frmMenuPrincipal, "Exito", "Venta procesada exitosamente");
            }
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void setImporte(){
        try {
            String strImporte = JOptionPane.showInputDialog("Ingrese el importe S/.:");
            if (Validar.entradaValida(strImporte)){
                double importe = Double.parseDouble(strImporte); 
                
                if (Validar.importeValido(importe)){
                    pnlVenta.actualizarImporte(importe);
                    pnlVenta.actualizarVuelto();
                } else {
                    Mensajes.mostrarAd(frmMenuPrincipal, "Valor incorrecto", "Ingrese un importe valido.");
                }
            } 
        } catch (NumberFormatException e){
            Mensajes.mostrarAd(frmMenuPrincipal, "Error", "El importe no puede ser vacio.");
        }
    }
    
    public ListaSimple getLista(){
        return lista;
    }
}
