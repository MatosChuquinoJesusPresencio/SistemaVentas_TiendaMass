
package Vista;

import Controlador.ControladorInventario;
import Modelo.Entidades.Categoria;
import Modelo.Entidades.Inventario;
import Modelo.Estructuras.ArregloDinamico;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import Utilidades.Validar;
import Utilidades.Mensajes;
import javax.swing.ButtonGroup;

/**
 *
 * @author Jesus
 */
public class PnlInventario extends javax.swing.JPanel {
    private FrmMenuPrincipal frmMenuPrincipal;
    private DefaultTableModel modeloTabla;
    private ControladorInventario conInventario;
    
    /**
     * Creates new form pnlContenido
     */
    public PnlInventario() {
        initComponents();
    }
    
    public PnlInventario(FrmMenuPrincipal frmMenuPrincipal) throws SQLException {
        initComponents();
        inicializarTabla();
        configurarRadioBotones();
        accionBotones();
        this.frmMenuPrincipal = frmMenuPrincipal;
        this.conInventario = new ControladorInventario(frmMenuPrincipal, this);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e){
                conInventario.actualizarCategorias();
                conInventario.actualizarInventario();
            }
            @Override
            public void componentHidden(java.awt.event.ComponentEvent e){
                btnGroupBotones.clearSelection();
                txtIngreseDato.setText("");
            }
        });
        btnBuscar.addActionListener(e -> buscarInventario());
        btnReiniciar.addActionListener(e -> {
            conInventario.actualizarInventario();
            txtIngreseDato.setText("");
        });
        btnStock.addActionListener(e -> conInventario.actualizarStock(obtenerID()));
        btnStockMinimo.addActionListener(e -> conInventario.actualizarStockMinimo(obtenerID()));
    } 
    
    private void configurarRadioBotones(){
        btnGroupBotones.add(rdBtnId);
        btnGroupBotones.add(rdBtnNombre);
        btnGroupBotones.add(rdBtnCb);
        btnGroupBotones.add(rdBtnCategoria);
        btnGroupBotones.add(rdBtnStockMenor);
        btnGroupBotones.add(rdBtnStockMayor);
    }
    
    private void accionBotones(){
        Enumeration<AbstractButton> botones = btnGroupBotones.getElements();
        while (botones.hasMoreElements()) {
            AbstractButton btn = botones.nextElement();
            btn.addActionListener(e -> actualizarEntradaBusqueda());
        }
    }
    
    private void actualizarEntradaBusqueda() {
        if (rdBtnCategoria.isSelected()) {
            cmbCategorias.setEnabled(true);
            txtIngreseDato.setEnabled(false);
        } else {
            cmbCategorias.setEnabled(false);
            txtIngreseDato.setEnabled(true);
        }
    }
    
    private void inicializarTabla(){
        String[] columnasTablaCarrito = {"ID", "Producto", "Stock Actual", "Stock Minimo", "F. Actualizacion"};
        modeloTabla = new DefaultTableModel(columnasTablaCarrito, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; 
        tableInventario.setModel(modeloTabla);
        tableInventario.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        tableInventario.getTableHeader().setForeground(Color.BLACK);
        tableInventario.getTableHeader().setPreferredSize(new Dimension(0, 45));
        tableInventario.getTableHeader().setReorderingAllowed(false);
        tableInventario.getTableHeader().setBackground(new Color(255, 255, 102));
        
        configurarColumnasCarrito();
    }
    
    private void configurarColumnasCarrito(){
        int[] anchos = {40, 120, 55, 55, 130};
        for (int i = 0; i < anchos.length; i++){
            TableColumn columna = tableInventario.getColumnModel().getColumn(i);
            columna.setPreferredWidth(anchos[i]);
            columna.setResizable(false);
        }
    }
    
    public void actualizarTablaInventario(ArregloDinamico<Inventario> inventarios){
        modeloTabla.setRowCount(0);
        for (int i = 0; i < inventarios.getDimension(); i++){
            Inventario inventario = inventarios.get(i);
            modeloTabla.addRow(new Object[]{
                inventario.getIdProducto(),
                conInventario.obtenerNombreProducto(inventario.getIdProducto()),
                inventario.getStockActual(),
                inventario.getStockMinimo(),
                inventario.getActualizacionF()
            });
        }
    }
    
    public void CargarCategorias(ArregloDinamico<Categoria> categorias){
        cmbCategorias.removeAllItems();
        cmbCategorias.addItem("SELECCIONAR");
        for (int i = 0; i < categorias.getDimension(); i++){
            cmbCategorias.addItem(categorias.get(i).getNombreCategoria());
        }
    }
    
    private void buscarInventario(){
        String criterio = getTextoSeleccionado(btnGroupBotones);
        String buscar = obtenerValorBusqueda();
        if (btnGroupBotones.getSelection() != null){
            if (!rdBtnCategoria.isSelected()){
                if (Validar.entradaValida(txtIngreseDato.getText())){
                    conInventario.actualizarTablaPorParametro(buscar, criterio);
                } else {
                    Mensajes.mostrarAd(frmMenuPrincipal, "Complete el campo antes", "Espera");
                }
            } else {
                conInventario.actualizarTablaPorParametro(buscar, criterio);
            }
        } else {
            Mensajes.mostrarAd(frmMenuPrincipal, "Seleccione una opcion", "Espera");
        }
    }
    
    private String obtenerValorBusqueda() {
        if (rdBtnCategoria.isSelected()) {
            Object seleccionado = cmbCategorias.getSelectedItem();
            if (seleccionado != null && !seleccionado.toString().equalsIgnoreCase("SELECCIONAR")) {
                return seleccionado.toString();
            } else {
                Mensajes.mostrarAd(frmMenuPrincipal, "Espera", "Por favor selecciona una categoría válida.");
                return null;
            }
        } else {
            return txtIngreseDato.getText();
        }
    }    

    public String getTextoSeleccionado(ButtonGroup grupo) {
        Enumeration<AbstractButton> botones = grupo.getElements();
        while (botones.hasMoreElements()) {
            AbstractButton boton = botones.nextElement();
            if (boton.isSelected()) {
                return boton.getText();
            }
        }
        return null; 
    }
    
    public int obtenerID() {
        int filaSeleccionada = tableInventario.getSelectedRow();
        if (filaSeleccionada != -1) {
            return Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
        } else {
            Mensajes.mostrarAd(frmMenuPrincipal, "Selecciona un producto de la tabla", "Aviso");
            return -1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupBotones = new javax.swing.ButtonGroup();
        jspInventario = new javax.swing.JScrollPane();
        tableInventario = new javax.swing.JTable();
        txtIngreseDato = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        rdBtnId = new javax.swing.JRadioButton();
        rdBtnCb = new javax.swing.JRadioButton();
        rdBtnNombre = new javax.swing.JRadioButton();
        rdBtnCategoria = new javax.swing.JRadioButton();
        rdBtnStockMenor = new javax.swing.JRadioButton();
        rdBtnStockMayor = new javax.swing.JRadioButton();
        cmbCategorias = new javax.swing.JComboBox<>();
        btnReiniciar = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnStockMinimo = new javax.swing.JButton();

        setBackground(new java.awt.Color(232, 232, 232));
        setPreferredSize(new java.awt.Dimension(850, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableInventario.setModel(new javax.swing.table.DefaultTableModel(
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
        tableInventario.setGridColor(new java.awt.Color(204, 204, 204));
        tableInventario.setRowHeight(35);
        jspInventario.setViewportView(tableInventario);

        add(jspInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 154, 715, 560));

        txtIngreseDato.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        add(txtIngreseDato, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 65, 214, 32));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Ingrese un dato:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 41, -1, -1));

        btnBuscar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 65, -1, -1));

        rdBtnId.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        rdBtnId.setText("ID");
        add(rdBtnId, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        rdBtnCb.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        rdBtnCb.setText("Codigo Barras");
        add(rdBtnCb, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, -1, -1));

        rdBtnNombre.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        rdBtnNombre.setText("Nombre");
        add(rdBtnNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 65, -1, -1));

        rdBtnCategoria.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        rdBtnCategoria.setText("Categoria");
        add(rdBtnCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, -1));

        rdBtnStockMenor.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        rdBtnStockMenor.setText("Stock menor a");
        add(rdBtnStockMenor, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        rdBtnStockMayor.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        rdBtnStockMayor.setText("Stock mayor a");
        add(rdBtnStockMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 103, -1, -1));

        cmbCategorias.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cmbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cmbCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 103, 214, 33));

        btnReiniciar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnReiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reiniciar.png"))); // NOI18N
        add(btnReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 103, -1, -1));

        btnStock.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnStock.setText("Cambiar Stock");
        add(btnStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 740, -1, -1));

        btnStockMinimo.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        btnStockMinimo.setText("Cambiar Stock Minimo");
        add(btnStockMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 740, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.ButtonGroup btnGroupBotones;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnStockMinimo;
    private javax.swing.JComboBox<String> cmbCategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jspInventario;
    private javax.swing.JRadioButton rdBtnCategoria;
    private javax.swing.JRadioButton rdBtnCb;
    private javax.swing.JRadioButton rdBtnId;
    private javax.swing.JRadioButton rdBtnNombre;
    private javax.swing.JRadioButton rdBtnStockMayor;
    private javax.swing.JRadioButton rdBtnStockMenor;
    private javax.swing.JTable tableInventario;
    private javax.swing.JTextField txtIngreseDato;
    // End of variables declaration//GEN-END:variables
}
