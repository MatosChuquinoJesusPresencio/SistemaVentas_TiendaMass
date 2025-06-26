package Utilidades;

import Modelo.Entidades.DetalleVenta;
import Modelo.Entidades.Venta;
import Modelo.Estructuras.ArregloDinamico;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExportadorPDF {

    public static void exportarVentasPDF(Component frame, ArregloDinamico<Venta> ventas, String nombreEmpleado) {
        String fechaHoraActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"));
        String nombreArchivo = "Reporte_Ventas_" + fechaHoraActual + ".pdf";
        String rutaArchivo = System.getProperty("user.home") + "/Desktop/" + nombreArchivo;

        Document documento = new Document(PageSize.A4, 40, 40, 50, 40);

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();

            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fuenteFecha = FontFactory.getFont(FontFactory.HELVETICA, 10);
            Font fuenteSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13);
            Font fuenteNormal = FontFactory.getFont(FontFactory.HELVETICA, 11);
            Font fuenteNegrita = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);

            try {
                Image logo = Image.getInstance("src/Imagenes/logo.png");
                logo.scaleToFit(100, 50);
                logo.setAlignment(Image.ALIGN_LEFT);
                documento.add(logo);
            } catch (DocumentException | IOException e) {
                System.out.println("No se pudo cargar el logo: " + e.getMessage());
            }

            Paragraph titulo = new Paragraph("REPORTE DE VENTAS", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            String fechaImpresion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            Paragraph infoReporte = new Paragraph(
                "Fecha de impresión: " + fechaImpresion + "\n" +
                "Empleado que generó el informe: " + nombreEmpleado,
                fuenteFecha
            );
            infoReporte.setAlignment(Element.ALIGN_LEFT);
            infoReporte.setSpacingAfter(15f);
            documento.add(infoReporte);

            for (int i = 0; i < ventas.getDimension(); i++) {
                Venta venta = ventas.get(i);

                Paragraph encabezado = new Paragraph("Venta Nº: " + venta.getNumeroVenta(), fuenteSubtitulo);
                encabezado.setSpacingBefore(10f);
                documento.add(encabezado);

                Paragraph datosVenta = new Paragraph(
                    "Fecha: " + venta.getFechaVenta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n" +
                    "Método de Pago: " + venta.getMetodoPago() + "\n" +
                    "Cantidad de Productos: " + venta.numProductosPorVenta(),
                    fuenteNormal
                );
                datosVenta.setSpacingAfter(8f);
                documento.add(datosVenta);

                PdfPTable tabla = new PdfPTable(4);
                tabla.setWidthPercentage(100);
                tabla.setWidths(new float[]{3f, 2f, 3f, 3f});

                String[] encabezados = {"ID Producto", "Cantidad", "Precio Unitario", "Subtotal"};
                for (String tituloCol : encabezados) {
                    PdfPCell celda = new PdfPCell(new Phrase(tituloCol, fuenteNegrita));
                    celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(celda);
                }

                for (int j = 0; j < venta.getDetallesVentas().getDimension(); j++) {
                    DetalleVenta d = (DetalleVenta) venta.getDetallesVentas().get(j);

                    tabla.addCell(new PdfPCell(new Phrase(String.valueOf(d.getIdProducto()), fuenteNormal)));
                    tabla.addCell(new PdfPCell(new Phrase(String.valueOf(d.getCantidad()), fuenteNormal)));
                    tabla.addCell(new PdfPCell(new Phrase("S/. " + d.getPrecioUnitario(), fuenteNormal)));
                    tabla.addCell(new PdfPCell(new Phrase("S/. " + d.getSubTotal(), fuenteNormal)));
                }

                documento.add(tabla);

                Paragraph totalVenta = new Paragraph("TOTAL VENTA: S/. " + venta.getTotalVenta(), fuenteNegrita);
                totalVenta.setAlignment(Element.ALIGN_RIGHT);
                totalVenta.setSpacingAfter(10f);
                documento.add(totalVenta);

                LineSeparator separador = new LineSeparator();
                separador.setLineColor(BaseColor.GRAY);
                documento.add(separador);
            }

            documento.close();

        } catch (DocumentException | FileNotFoundException e) {
            Mensajes.mostrarError(frame, "Error", e.getMessage());
        }
    }

}
