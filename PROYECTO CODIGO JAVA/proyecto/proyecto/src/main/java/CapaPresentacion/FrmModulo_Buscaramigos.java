/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaPresentacion;

import java.awt.Color;
import CapaNegocio.CN_Usuario;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author leona
 */
public class FrmModulo_Buscaramigos extends javax.swing.JFrame {
         
    CN_Usuario usuario = new CN_Usuario(); 
    /**
     * Creates new form FrmModulo_Principal
     */
    public FrmModulo_Buscaramigos(String username) {
        initComponents();
        
        lbNombreUsuario.setText(username);
        
        
        this.setLocationRelativeTo(null);
        agregarBotonEnTabla();
        tbResultados.setVisible(false);
        
    
    }
    
    public FrmModulo_Buscaramigos(){
        
    }
    
     // Función para agregar un botón en la tabla
    private void agregarBotonEnTabla() {
    // Agrega un botón en la columna de acción (ajusta esto según tu necesidad)
    int columnaBoton = 3; // Ajusta este valor al número de la columna donde deseas el botón
    new ButtonColumn(tbResultados, columnaBoton);
    }


    // Clase interna ButtonColumn
    class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

    private final JButton renderButton;
    private final JButton editButton;
    private Object value;
    private JTable table;
    private int visibleColumn; // Nueva variable para almacenar el número de la columna visible

    public ButtonColumn(JTable table, int column) {
        this.table = table;
        this.visibleColumn = column; // Asigna el número de la columna donde deseas que el botón sea visible
        renderButton = new JButton("Enviar solicitud");
        editButton = new JButton("Enviar solicitud");
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);

        // Oculta el botón inicialmente
        renderButton.setVisible(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus) {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        } else if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        // Verifica si la celda en la columna visible tiene un valor
        if (table.getValueAt(row, visibleColumn) != null && !table.getValueAt(row, visibleColumn).toString().isEmpty()) {
            renderButton.setVisible(true);
            renderButton.setText("Enviar solicitud");
        } else {
            renderButton.setVisible(false);
        }

        return renderButton;
    }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.value = value;
            editButton.setText("Enviar solicitud");
            return editButton;
        }

        @Override
        public Object getCellEditorValue() {
            return value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            
            // Obtén la fila y columna de la celda en la que se hizo clic
            int fila = table.getSelectedRow();
            int columna = table.getSelectedColumn();

            // Verifica que la acción se realice en la columna correcta (la de "Enviar Solicitud")
            if (columna == visibleColumn) {
                // Obtiene los valores de sender_id y receiver_id desde la fila seleccionada
                int senderId = Integer.parseInt(table.getValueAt(fila, /* índice de la columna sender_id */).toString());
                int receiverId = Integer.parseInt(table.getValueAt(fila, /* índice de la columna receiver_id */).toString());

                // Llama al método para enviar la solicitud de amistad
                boolean solicitudEnviada = usuario.enviarSolicitudAmistad(senderId, receiverId);

            }
        
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

        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblCargarfoto = new javax.swing.JLabel();
        lbUsername = new javax.swing.JLabel();
        lbNombreUsuario = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        btnBuscaramigos = new javax.swing.JButton();
        btnSolicitudes = new javax.swing.JButton();
        btnpublicarperfil = new javax.swing.JButton();
        btnAmigos = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbBUsername = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbResultados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.BorderLayout(10, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 647));

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setLayout(null);

        lblCargarfoto.setText("Foto del usuario");
        lblCargarfoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(lblCargarfoto);
        lblCargarfoto.setBounds(60, 60, 180, 160);

        lbUsername.setFont(new java.awt.Font("Segoe UI Historic", 2, 18)); // NOI18N
        lbUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lbUsername);
        lbUsername.setBounds(70, 80, 190, 30);

        lbNombreUsuario.setFont(new java.awt.Font("Segoe UI Historic", 2, 18)); // NOI18N
        lbNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lbNombreUsuario);
        lbNombreUsuario.setBounds(50, 20, 190, 30);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(102, 255, 204));
        jPanel7.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 2, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menu");
        jPanel7.add(jLabel2);
        jLabel2.setBounds(50, 10, 180, 30);

        jPanel8.setBackground(new java.awt.Color(204, 255, 204));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnBuscaramigos.setBackground(new java.awt.Color(51, 51, 255));
        btnBuscaramigos.setFont(new java.awt.Font("Segoe UI Emoji", 2, 18)); // NOI18N
        btnBuscaramigos.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscaramigos.setText("Buscar Amigos ");
        btnBuscaramigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscaramigosMouseClicked(evt);
            }
        });
        btnBuscaramigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaramigosActionPerformed(evt);
            }
        });

        btnSolicitudes.setBackground(new java.awt.Color(51, 51, 255));
        btnSolicitudes.setFont(new java.awt.Font("Segoe UI Emoji", 2, 18)); // NOI18N
        btnSolicitudes.setForeground(new java.awt.Color(255, 255, 255));
        btnSolicitudes.setText("Solicitudes");
        btnSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitudesActionPerformed(evt);
            }
        });

        btnpublicarperfil.setBackground(new java.awt.Color(51, 51, 255));
        btnpublicarperfil.setFont(new java.awt.Font("Segoe UI Emoji", 2, 18)); // NOI18N
        btnpublicarperfil.setForeground(new java.awt.Color(255, 255, 255));
        btnpublicarperfil.setText("Publicar en perfil ");
        btnpublicarperfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpublicarperfilActionPerformed(evt);
            }
        });

        btnAmigos.setBackground(new java.awt.Color(51, 51, 255));
        btnAmigos.setFont(new java.awt.Font("Segoe UI Emoji", 2, 18)); // NOI18N
        btnAmigos.setForeground(new java.awt.Color(255, 255, 255));
        btnAmigos.setText("Amigos");
        btnAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmigosActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 51, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("SALIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscaramigos, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpublicarperfil, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscaramigos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(btnAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnpublicarperfil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel10);

        jPanel8.add(jScrollPane1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        lbBUsername.setText("Username:");

        tbResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Username", "Nombre", "Apellido", "Acción"
            }
        ));
        jScrollPane2.setViewportView(tbResultados);

        jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 2, 36)); // NOI18N
        jLabel1.setText("Encuentra nuevos amigos ");

        btnBuscar.setText("Buscar ");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("\"Advertencia: evalúa antes de aceptar. Calidad sobre cantidad en tus amistades en línea.\"");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(148, 148, 148)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(lbBUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(89, 89, 89)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(66, 66, 66)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscaramigosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscaramigosMouseClicked
      btnBuscaramigos.setBackground(Color.BLUE); 
      

// TODO add your handling code here:
    }//GEN-LAST:event_btnBuscaramigosMouseClicked

    private void btnSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudesActionPerformed
    // Cierra el formulario actual
    this.dispose();

    // Crea e muestra el nuevo formulario FrmSolicitudes
    FrmModulo_Solicitudes FrmSolicitud = new FrmModulo_Solicitudes();
    FrmSolicitud.setVisible(true);
    }//GEN-LAST:event_btnSolicitudesActionPerformed

    private void btnBuscaramigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaramigosActionPerformed
         // Cierra el formulario actual
    this.dispose();

    // Crea e muestra el nuevo formulario FrmSolicitudes
    FrmModulo_Buscaramigos frmBuscar = new FrmModulo_Buscaramigos();
    frmBuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscaramigosActionPerformed

    private void btnpublicarperfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpublicarperfilActionPerformed
           // Cierra el formulario actual
    this.dispose();

    // Crea e muestra el nuevo formulario FrmSolicitudes
    FrmModulo_Publicarenperfil frmpublicar = new FrmModulo_Publicarenperfil();
    frmpublicar.setVisible(true);
    }//GEN-LAST:event_btnpublicarperfilActionPerformed

    private void btnAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmigosActionPerformed
       // Cierra el formulario actual
    this.dispose();

    // Crea e muestra el nuevo formulario FrmSolicitudes
    FrmModulo_Amigos frmamigos = new FrmModulo_Amigos();
    frmamigos.setVisible(true);
    }//GEN-LAST:event_btnAmigosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          // Cierra el formulario actual
    this.dispose();
    
    // Crea e muestra el nuevo formulario FrmSolicitudes
     FrmModulo_Principal frmsalir = new FrmModulo_Principal();
    frmsalir.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // Obtiene el username del campo de texto
        String username = txtBuscar.getText();

        // Llama a la función de la capa de negocio para obtener datos del usuario
        String[] resultado = usuario.obtenerDatosUsuario(username);

        // Actualiza la tabla con los resultados obtenidos
        actualizarTabla(resultado);
        
        // Muestra la tabla después de la búsqueda
        tbResultados.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    // Función para actualizar la tabla con los datos del usuario
    private void actualizarTabla(String[] resultado) {
        DefaultTableModel modelo = (DefaultTableModel) tbResultados.getModel();
        // Limpia la tabla antes de agregar nuevos datos
        modelo.setRowCount(0);

        // Agrega los nuevos datos a la tabla
        modelo.addRow(resultado);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmModulo_Buscaramigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmModulo_Buscaramigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmModulo_Buscaramigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmModulo_Buscaramigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmModulo_Buscaramigos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAmigos;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscaramigos;
    private javax.swing.JButton btnSolicitudes;
    private javax.swing.JButton btnpublicarperfil;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lbBUsername;
    private javax.swing.JLabel lbNombreUsuario;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JLabel lblCargarfoto;
    private javax.swing.JTable tbResultados;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
