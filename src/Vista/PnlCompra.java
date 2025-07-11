
package Vista;

import Controlador.ControladorCompra;
import Modelo.Entidades.Proveedor;
import Modelo.Estructuras.ArregloDinamico;
import Modelo.Estructuras.NodoLista;
import Utilidades.Mensajes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Jesus
 */
public class PnlCompra extends javax.swing.JPanel {
    private FrmMenuPrincipal frmMenuPrincipal;
    private ControladorCompra conCompra;
    private DefaultTableModel modeloCarrito;
    
    /**
     * Creates new form pnlContenido
     */
    public PnlCompra() {
        initComponents();
        
    }
    
    public PnlCompra(FrmMenuPrincipal frmMenuPrincipal) throws SQLException {
        initComponents();
        this.frmMenuPrincipal = frmMenuPrincipal;
        conCompra = new ControladorCompra(frmMenuPrincipal, this);
        inicializarTabla();
        configurarBotones();
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e){
               conCompra.actualizarProveedores();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspContenedorTabla = new javax.swing.JScrollPane();
        tbleCarrito = new javax.swing.JTable();
        btnProcesar = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        lblTotalValor = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        lblProducto = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        lblProveedor = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        btnBuscarProducto = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAñadir = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        lblNombreProducto = new javax.swing.JLabel();
        txtCantidadValor = new javax.swing.JTextField();

        setBackground(new java.awt.Color(232, 232, 232));
        setPreferredSize(new java.awt.Dimension(850, 800));

        tbleCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbleCarrito.setGridColor(new java.awt.Color(204, 204, 204));
        tbleCarrito.setRowHeight(35);
        jspContenedorTabla.setViewportView(tbleCarrito);

        btnProcesar.setBackground(new java.awt.Color(255, 255, 102));
        btnProcesar.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        btnProcesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/procesar.png"))); // NOI18N
        btnProcesar.setText("Procesar");

        lblTotal.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblTotal.setText("Total:");

        lblTotalValor.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblTotalValor.setText("S/. 0.00");

        btnCancelar.setBackground(new java.awt.Color(255, 255, 102));
        btnCancelar.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        lblProducto.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblProducto.setText("Producto:");

        lblCantidad.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblCantidad.setText("Cantidad:");

        txtCodigoBarras.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txtCodigoBarras.setEnabled(false);

        lblProveedor.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblProveedor.setText("Proveedor:");

        cmbProveedor.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR" }));
        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });

        btnBuscarProducto.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        btnBuscarProducto.setEnabled(false);

        btnEliminar.setBackground(new java.awt.Color(255, 255, 102));
        btnEliminar.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);

        btnAñadir.setBackground(new java.awt.Color(255, 255, 102));
        btnAñadir.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar.png"))); // NOI18N
        btnAñadir.setText("Añadir");
        btnAñadir.setEnabled(false);

        btnModificar.setBackground(new java.awt.Color(255, 255, 102));
        btnModificar.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);

        lblNombreProducto.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblNombreProducto.setText("SELECCIONAR PRODUCTO");
        lblNombreProducto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblNombreProducto.setEnabled(false);

        txtCantidadValor.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txtCantidadValor.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblProveedor)
                            .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto)
                            .addComponent(lblNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblCantidad))
                            .addComponent(btnAñadir, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(txtCantidadValor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnCancelar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotal)
                            .addGap(46, 46, 46)
                            .addComponent(lblTotalValor)
                            .addGap(97, 97, 97)
                            .addComponent(btnProcesar))
                        .addComponent(jspContenedorTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(36, Short.MAX_VALUE)
                        .addComponent(lblProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreProducto)
                            .addComponent(lblCantidad)
                            .addComponent(txtCantidadValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jspContenedorTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotal)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalValor)))
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed
        String seleccion = (String) cmbProveedor.getSelectedItem();
        
        if (seleccion != null && !seleccion.equals("SELECCIONAR")){
            seleccionarProveedor(true);
        } else {
            seleccionarProveedor(false);
        }
    }//GEN-LAST:event_cmbProveedorActionPerformed
    
    private void configurarBotones() {
        btnAñadir.addActionListener(e -> conCompra.agregarCB(txtCodigoBarras.getText(), 
                txtCantidadValor.getText()));
        btnBuscarProducto.addActionListener(e -> buscarProducto());
        btnEliminar.addActionListener(e -> conCompra.eliminar(obtenerIdTabla()));
        btnModificar.addActionListener(e -> conCompra.modificar(obtenerIdTabla()));
        btnProcesar.addActionListener(e -> conCompra.procesarCompra());
        btnCancelar.addActionListener(e -> conCompra.vaciarCarrito());
    }
    
    public void cargarCombo(ArregloDinamico<Proveedor> proveedores) throws SQLException{
        cmbProveedor.removeAllItems();
        cmbProveedor.addItem("SELECCIONAR");
        for (int i = 0; i < proveedores.getDimension(); i++){
            cmbProveedor.addItem(proveedores.get(i).getNombre());
        }
    }
    
    private void inicializarTabla(){
        String[] columnasTablaCarrito = {"Código", "Producto", "Cantidad", "Precio Unit.", "Subtotal"};
        modeloCarrito = new DefaultTableModel(columnasTablaCarrito, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; 
        tbleCarrito.setModel(modeloCarrito);
        tbleCarrito.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        tbleCarrito.getTableHeader().setForeground(Color.BLACK);
        tbleCarrito.getTableHeader().setPreferredSize(new Dimension(0, 45));
        tbleCarrito.getTableHeader().setReorderingAllowed(false);
        tbleCarrito.getTableHeader().setBackground(new Color(255, 255, 102));
        
        configurarColumnasCarrito();
    }
    
    private void configurarColumnasCarrito(){
        int[] anchos = {40, 160, 60, 85, 55};
        for (int i = 0; i < anchos.length; i++){
            TableColumn columna = tbleCarrito.getColumnModel().getColumn(i);
            columna.setPreferredWidth(anchos[i]);
            columna.setResizable(false);
        }
    }
    
    private void buscarProducto(){
        String codigoBarras = txtCodigoBarras.getText();
        String nombreProducto = conCompra.buscarProducto(codigoBarras);
        if (nombreProducto != null){
            lblNombreProducto.setText(nombreProducto);
            return;
        }
        txtCodigoBarras.setText("");
        Mensajes.mostrarAd(frmMenuPrincipal, "Producto no encontrado", "Verifique el codigo de barras");
    }
    
    private int obtenerIdTabla(){
        try {
            int filaSeleccionada = tbleCarrito.getSelectedRow();
        
            if (filaSeleccionada >= 0){
                int id = Integer.parseInt(tbleCarrito.getValueAt(filaSeleccionada, 0).toString());
                return id;
            } else {
                Mensajes.mostrarAd(frmMenuPrincipal, "!Espere!", "Seleccione una opcion antes");
            }
        } catch (NumberFormatException e){
            Mensajes.mostrarError(frmMenuPrincipal, "Error", "Error al obtener el ID del producto.");
        }
        return -1;
    }
    
    public void actualizarCarrito(){
        modeloCarrito.setRowCount(0);
        NodoLista puntero = conCompra.getLista().getCabeza();
        while(puntero != null){
            Object[] fila = {
                puntero.getProducto().getIdProducto(),
                puntero.getProducto().getNombre(),
                puntero.getCantidad(),
                String.format("S/. %.2f", puntero.getProducto().getPrecio()),
                String.format("S/. %.2f", puntero.subTotal())
            };
            modeloCarrito.addRow(fila);
            puntero = puntero.getSiguiente();
        }
    }
    
    public void reiniciarValores(){
        lblNombreProducto.setText("SELECCIONAR PRODUCTO");
        txtCodigoBarras.setText("");
        cmbProveedor.setSelectedIndex(0);
    }
    
    public void seleccionarProveedor(boolean valor){
        txtCodigoBarras.setEnabled(valor);
        txtCantidadValor.setEnabled(valor);
        btnBuscarProducto.setEnabled(valor);
        btnAñadir.setEnabled(valor);
        btnEliminar.setEnabled(valor);
        btnModificar.setEnabled(valor);
    }
    
    public void actualizarTotal(double total){
        lblTotalValor.setText(String.format("S/. %.2f", total));
    }
    
    public String nombreProveedor(){
        return (String) cmbProveedor.getSelectedItem();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JScrollPane jspContenedorTabla;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalValor;
    private javax.swing.JTable tbleCarrito;
    private javax.swing.JTextField txtCantidadValor;
    private javax.swing.JTextField txtCodigoBarras;
    // End of variables declaration//GEN-END:variables
}
