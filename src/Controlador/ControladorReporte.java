
package Controlador;

import Modelo.DAOs.DetalleVentaDAO;
import Modelo.DAOs.VentaDAO;
import Modelo.Entidades.DetalleVenta;
import Modelo.Entidades.Venta;
import Modelo.Estructuras.ArbolBB;
import Modelo.Estructuras.ArregloDinamico;
import Utilidades.ExportadorPDF;
import Utilidades.Mensajes;
import Vista.FrmMenuPrincipal;
import Vista.PnlReporte;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Jesus
 */
public class ControladorReporte {
    private final FrmMenuPrincipal frmMenuPrincipal;
    private final PnlReporte pnlReporte;
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleVentaDAO;
    private final ArbolBB arbolVentas;
    
    public ControladorReporte(FrmMenuPrincipal frmMenuPrincipal1, PnlReporte pnlReporte){
        this.arbolVentas = new ArbolBB();
        this.frmMenuPrincipal = frmMenuPrincipal1;
        this.pnlReporte = pnlReporte;
        try {
            this.ventaDAO = VentaDAO.getInstancia();
            this.detalleVentaDAO = DetalleVentaDAO.getInstancia();
        } catch (SQLException e){
                Mensajes.mostrarError(frmMenuPrincipal1, "Error", e.getMessage());
        }
    }
    
    public void cargarTablaReportes() {
        try{
            ArregloDinamico<Venta> ventas = ventaDAO.cargarVentas();
            arbolVentas.clean();

            for (int i = 0; i < ventas.getDimension(); i++) {
                Venta venta = ventas.get(i);

                ArregloDinamico<DetalleVenta> detalleVentas = detalleVentaDAO.listarDetalleVenta(venta.getIdVenta());
                venta.setDetallesVentas(detalleVentas);

                arbolVentas.insert(venta);
            }

            ArregloDinamico<Venta> resultado = new ArregloDinamico<>();
            arbolVentas.filtrarFecha(LocalDateTime.MIN, LocalDateTime.MAX, resultado, "entre");
            pnlReporte.actualizarTablaReportes(resultado);
        } catch (SQLException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", e.getMessage());
        }
    }
    
    public void filtrarPorFechas(LocalDateTime desde, LocalDateTime hasta, String operacion) {
        ArregloDinamico<Venta> resultado = new ArregloDinamico<>();
        arbolVentas.filtrarFecha(desde, hasta, resultado, operacion);
        pnlReporte.actualizarTablaReportes(resultado);
    }

    public void filtrarPorProducto(int idProducto) {
        ArregloDinamico<Venta> resultado = new ArregloDinamico<>();
        arbolVentas.filtrarId(idProducto, resultado);
        pnlReporte.actualizarTablaReportes(resultado);
    }

    public void filtrarPorTotal(double limInf, double limSup, String operacion) {
        ArregloDinamico<Venta> resultado = new ArregloDinamico<>();
        arbolVentas.filtrarMonto(limInf, limSup, resultado, operacion);
        pnlReporte.actualizarTablaReportes(resultado);
    }
    
    public void exportarReportePDF() {
        ArregloDinamico<Venta> ventas = pnlReporte.getListaMostrada();
        String ruta = System.getProperty("user.home") + "/Desktop/reporte_ventas.pdf";
        ExportadorPDF.exportarVentasPDF(frmMenuPrincipal, ventas, frmMenuPrincipal.getSesion().getNombreEmplado());
        Mensajes.mostrarInfo(frmMenuPrincipal, "Exportado", "Reporte exportado en el escritorio");
    }
}
