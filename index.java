package db4o;

public class Articulos {

    private int codigo;
    private String nombre;
    private int cantidad;
    private String descripcion;

    public Articulos() {
    }

    public Articulos(int codigo, String nombre, int cantidad,
            String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

package db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class BaseDatos {

    private final String path = "tarea8.yap";
    private ObjectContainer bd;

    public BaseDatos() {
    }

    public boolean insert(Articulos ar) {
        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                path);
        Query query = bd.query();
        query.constrain(Articulos.class);
        query.descend("codigo").constrain(ar.getCodigo()).equal();
        ObjectSet ob = query.execute();
        if (ob.hasNext()) {
            bd.close();
            return false;
        } else {
            bd.store(ar);
        }
        bd.close();
        return true;
    }

    public boolean search(int codigo, javax.swing.JTextArea tl) {
        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                path);
        Articulos articulosquery = new Articulos(codigo, null, 0,
                null);
        ObjectSet&lt;Articulos&gt; query = bd.queryByExample(articulosquery);
        if (query.hasNext()) {
            Articulos ar = query.next();
            tl.setText(ar.getCodigo() + "\n" + ar.getNombre() + "\n"
                    + ar.getCantidad() + "\n" + ar.getDescripcion() + "\n\n");
            bd.close();
            return true;
        } else {
            bd.close();
            return false;
        }
    }

    public boolean modify(int codigo, String nombre, int cantidad,
            String descripcion) {
        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                path);
        Articulos articulosquery = new Articulos(codigo, null, 0,
                null);
        ObjectSet&lt;Articulos&gt; query = bd.queryByExample(articulosquery);
        if (query.hasNext()) {
            Articulos ar = query.next();
            ar.setNombre(nombre);
            ar.setCantidad(cantidad);
            ar.setDescripcion(descripcion);
            bd.store(ar);
            bd.close();
            return true;
        } else {
            bd.close();
            return false;
        }
    }

    public void delete(int codigo) {
        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                path);
        Articulos articulosquery = new Articulos(codigo, null, 0,
                null);
        ObjectSet&lt;Articulos&gt; query = bd.queryByExample(articulosquery);
        Articulos ar = query.next();
        bd.delete(ar);
        bd.close();
    }

    public void show(javax.swing.JTextArea tl) {
        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                path);
        Articulos ar = new Articulos();
        ObjectSet&lt;Articulos&gt; result = bd.queryByExample(ar);
        tl.setText("");
        while (result.hasNext()) {
            Articulos Ar = (Articulos) result.next();
            tl.append(Ar.getCodigo() + "\n" + Ar.getNombre() + "\n"
                    + Ar.getCantidad() + "\n" + Ar.getDescripcion() + "\n\n");
        }
        bd.close();
    }

}

package db4o;

import javax.swing.JOptionPane;

public class Formulario extends javax.swing.JFrame {

    public Formulario() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // &lt;editor-fold defaultstate="collapsed" desc="Generated Code"&gt;//GEN-BEGIN:initComponents

    private void initComponents() {

        btninsert = new javax.swing.JButton();
        btnsearch = new javax.swing.JButton();
        lblcode = new javax.swing.JLabel();
        lblname = new javax.swing.JLabel();
        lblamount = new javax.swing.JLabel();
        lbldescription = new javax.swing.JLabel();
        txtcode = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtamount = new javax.swing.JTextField();
        txtdescription = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtlist = new javax.swing.JTextArea();
        btnshow = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        lblproductslist = new javax.swing.JLabel();
        btndelete = new javax.swing.JButton();
        btnmodify = new javax.swing.JButton();
        btnclean = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formulario de Gestión");

        btninsert.setText("Insertar");
        btninsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninsertActionPerformed(evt);
            }
        });

        btnsearch.setText("Buscar");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        lblcode.setText("Código");

        lblname.setText("Nombre");

        lblamount.setText("Cantidad");

        lbldescription.setText("Descripción");

        txtcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodeActionPerformed(evt);
            }
        });

        txtlist.setColumns(20);
        txtlist.setRows(5);
        jScrollPane1.setViewportView(txtlist);

        btnshow.setText("Mostrar");
        btnshow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnshowActionPerformed(evt);
            }
        });

        btnexit.setText("Salir");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        lblproductslist.setText("Listado de productos");

        btndelete.setText("Borrar");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnmodify.setText("Modificar");
        btnmodify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifyActionPerformed(evt);
            }
        });

        btnclean.setText("Limpiar");
        btnclean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbldescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(btnmodify, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblamount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnsearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                    .addComponent(btndelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtcode, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addComponent(txtname)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdescription)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblproductslist, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnclean, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnshow, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblproductslist, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblamount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbldescription, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btninsert)
                    .addComponent(btnsearch)
                    .addComponent(btnshow)
                    .addComponent(btnexit))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btndelete)
                    .addComponent(btnmodify)
                    .addComponent(btnclean))
                .addGap(30, 30, 30))
        );

        pack();
    }// &lt;/editor-fold&gt;//GEN-END:initComponents

    private void txtcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodeActionPerformed

    }//GEN-LAST:event_txtcodeActionPerformed

    private void btninsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninsertActionPerformed
        try {
            BaseDatos bd = new BaseDatos();
            Articulos ar = new Articulos(Integer.parseInt(
                    txtcode.getText()),
                    txtname.getText(),
                    Integer.parseInt(txtamount.getText()),
                    txtdescription.getText()
            );
            boolean bo = bd.insert(ar);
            if (bo) {
                txtlist.setText("los datos se han\ninsertado correctamente");
            } else {
                txtlist.setText("el codigo ya existe\nen la base de datos");
            }
        } catch (Exception ex) {
            txtlist.setText("introduzca un numero valido:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btninsertActionPerformed

    private void btnshowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnshowActionPerformed
        try {
            BaseDatos bd = new BaseDatos();
            bd.show(txtlist);
        } catch (Exception ex) {
            txtlist.setText("no se puede mostrar:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnshowActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        try {
            int codigo = Integer.parseInt(txtcode.getText());
            BaseDatos bd = new BaseDatos();
            boolean bo = bd.search(codigo, txtlist);
            if (bo) {
                txtlist.setText(txtlist.getText());
            } else {
                txtlist.setText("el codigo no existe");
            }
        } catch (Exception ex) {
            txtlist.setText("introduzca un numero valido:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnsearchActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnexitActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        try {
            int codigo = Integer.parseInt(txtcode.getText());
            int option = JOptionPane.showConfirmDialog(null,
                    "confirmar",
                    "confirmacion", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {

                BaseDatos bd = new BaseDatos();
                bd.delete(codigo);
                txtlist.setText("borrado realizado");
            } else {
                txtlist.setText("borrado cancelado");
            }
        } catch (Exception ex) {
            txtlist.setText("introduzca un numero valido:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnmodifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifyActionPerformed
        try {
            BaseDatos bd = new BaseDatos();
            int codigo = Integer.parseInt(txtcode.getText());
            String nombre = txtname.getText();
            int cantidad = Integer.parseInt(txtamount.getText());
            String descripcion = txtdescription.getText();

            if (bd.modify(codigo, nombre, cantidad, descripcion)) {
                txtlist.setText("modificacion realizada");
            } else {
                txtlist.setText("el codigo no existe");
            }
        } catch (Exception ex) {
            txtlist.setText("introduzca un numero valido:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnmodifyActionPerformed

    private void btncleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncleanActionPerformed
        try {
            txtlist.setText("");
            txtcode.setText("");
            txtname.setText("");
            txtamount.setText("");
            txtdescription.setText("");
        } catch (Exception ex) {
            txtlist.setText("no se puede limpiar:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btncleanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclean;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btninsert;
    private javax.swing.JButton btnmodify;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnshow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblamount;
    private javax.swing.JLabel lblcode;
    private javax.swing.JLabel lbldescription;
    private javax.swing.JLabel lblname;
    private javax.swing.JLabel lblproductslist;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtcode;
    private javax.swing.JTextField txtdescription;
    private javax.swing.JTextArea txtlist;
    private javax.swing.JTextField txtname;
    // End of variables declaration//GEN-END:variables

}