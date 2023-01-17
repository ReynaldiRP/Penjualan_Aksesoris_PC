/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UAS;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akbar
 */
public class Barang extends javax.swing.JInternalFrame {

    /**
     * Creates new form Barang
     */
    
    Connection conn;
    Statement cmd;
    ResultSet rs;
    DefaultTableModel tmBarang;
    String SQL;
    
    Statement cmd1;
    ResultSet rs1;
    
    public Barang() {
        initComponents();
        
        loadIDBarang();
    }
    
    private void loadIDBarang(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM Barang";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDBarang.removeAllItems();
            cbIDBarang.addItem("Pilih ID Barang");
            while(rs.next()){
                String cid = rs.getString("id_Barang");
                cbIDBarang.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public void showTabel() {
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT id_Barang, jenis_Barang, nama_Barang, ukuran_Barang, "
                    + "Harga_Jual, Harga_Beli, Stok FROM Barang ORDER BY id_Barang ASC");
            
            //Setting kolom dari DefaultTableModel
            tmBarang = new DefaultTableModel(new String[] {"ID BARANG", "JENIS", "NAMA", "UKURAN", "HARGA JUAL", "HARGA BELI", "STOK"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmBarang.addRow(new Object[] {rs.getString("id_Barang"), rs.getString("jenis_Barang"), rs.getString("nama_Barang"), 
                        rs.getString("ukuran_Barang"), rs.getInt("Harga_Jual"), rs.getInt("Harga_Beli"), rs.getInt("Stok")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbBarang.setDefaultEditor(Object.class, null);
            
            tbBarang.setModel(tmBarang);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private String cekBarang(int angka) {
        String Barang = null;
        try {
            switch(angka) {
                case 1:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT jenis_Barang FROM Barang WHERE ID_Barang = '" + cbIDBarang.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            Barang = rs1.getString("jenis_Barang");
                        }
                    }
                break;
                
                case 2:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT nama_Barang "
                            + "FROM Barang WHERE ID_Barang = '" + cbIDBarang.getSelectedItem().toString() + "'");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            Barang = rs1.getString("nama_Barang");
                        }
                    }
                break;
                
                case 3:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT ukuran_Barang FROM Barang WHERE ID_Barang = '" + cbIDBarang.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            Barang = rs1.getString("ukuran_Barang");
                        }
                    }
                break;
                
                case 4:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT Harga_Beli FROM Barang WHERE ID_Barang = '" + cbIDBarang.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            Barang = rs1.getString("Harga_Beli");
                        }
                    }
                break;
                
                case 5:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT Harga_Jual FROM Barang WHERE ID_Barang = '" + cbIDBarang.getSelectedItem().toString() + "'");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            Barang = rs1.getString("Harga_Jual");
                        }
                    }
                break;
                
                case 6:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT Stok FROM Barang WHERE ID_Barang = '" + cbIDBarang.getSelectedItem().toString() + "'");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            Barang = rs1.getString("Stok");
                        }
                    }
                break;
            }
            
        } catch (Exception e) {
            if("You have an error in your SQL syntax".equals(e.getLocalizedMessage())) {
                    System.out.println("");
                }
        }
        return Barang;
    }
    
    private void clear() {
        cbIDBarang.setSelectedIndex(0);
        bgJenis.clearSelection();
        txNamaBarang.setText(null);
        cbUkuranBarang.setSelectedIndex(0);
        txHrgJual.setText(null);
        txHrgBeli.setText(null);
        txStokBarang.setText(null);
        
        bgJenis1.clearSelection();
        txNamaBarang1.setText(null);
        cbUkuranBarang1.setSelectedIndex(0);
        txHrgJual1.setText(null);
        txHrgBeli1.setText(null);
        txStokBarang1.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgJenis = new javax.swing.ButtonGroup();
        bgJenis1 = new javax.swing.ButtonGroup();
        tabBarang = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        labJenisBarang = new javax.swing.JLabel();
        labIDBarang = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbIDBarang = new javax.swing.JComboBox<>();
        btnClear = new javax.swing.JButton();
        labNamaBarang = new javax.swing.JLabel();
        txNamaBarang = new javax.swing.JTextField();
        labUkuranBarang = new javax.swing.JLabel();
        labHrgJual = new javax.swing.JLabel();
        txHrgJual = new javax.swing.JTextField();
        labHrgBeli = new javax.swing.JLabel();
        txHrgBeli = new javax.swing.JTextField();
        labStokBarang = new javax.swing.JLabel();
        txStokBarang = new javax.swing.JTextField();
        rbMembrane = new javax.swing.JRadioButton();
        rbMecha = new javax.swing.JRadioButton();
        cbUkuranBarang = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBarang = new javax.swing.JTable();
        txID = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCl = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnClear1 = new javax.swing.JButton();
        btnTambahData = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        labJenisBarang1 = new javax.swing.JLabel();
        rbMembrane1 = new javax.swing.JRadioButton();
        rbMecha1 = new javax.swing.JRadioButton();
        txNamaBarang1 = new javax.swing.JTextField();
        labNamaBarang1 = new javax.swing.JLabel();
        labUkuranBarang1 = new javax.swing.JLabel();
        cbUkuranBarang1 = new javax.swing.JComboBox<>();
        labHrgJual1 = new javax.swing.JLabel();
        txHrgJual1 = new javax.swing.JTextField();
        txHrgBeli1 = new javax.swing.JTextField();
        txStokBarang1 = new javax.swing.JTextField();
        labStokBarang1 = new javax.swing.JLabel();
        labHrgBeli1 = new javax.swing.JLabel();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabBarang.setName("tabBarang"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("tabMaster"); // NOI18N

        btnUpdate.setBackground(new java.awt.Color(255, 255, 0));
        btnUpdate.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate.setText("Update");
        btnUpdate.setName("btnUpdate"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Delete");
        btnDelete.setName("btnDelete"); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        labJenisBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJenisBarang.setForeground(new java.awt.Color(8, 76, 77));
        labJenisBarang.setText("Jenis Keyboard");

        labIDBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDBarang.setForeground(new java.awt.Color(8, 76, 77));
        labIDBarang.setText("ID Barang");

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(251, 188, 5));
        jLabel7.setText("Data Barang");

        cbIDBarang.setBackground(new java.awt.Color(66, 132, 244));
        cbIDBarang.setForeground(new java.awt.Color(255, 255, 255));
        cbIDBarang.setName("cbIDBarang"); // NOI18N
        cbIDBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDBarangActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(204, 204, 204));
        btnClear.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear.setForeground(new java.awt.Color(0, 0, 0));
        btnClear.setText("Clear");
        btnClear.setName("btnClear"); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        labNamaBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang.setForeground(new java.awt.Color(8, 76, 77));
        labNamaBarang.setText("Nama");

        txNamaBarang.setBackground(new java.awt.Color(66, 132, 244));
        txNamaBarang.setForeground(new java.awt.Color(255, 255, 255));
        txNamaBarang.setName("txNamaBarang"); // NOI18N

        labUkuranBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labUkuranBarang.setForeground(new java.awt.Color(8, 76, 77));
        labUkuranBarang.setText("Ukuran");

        labHrgJual.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labHrgJual.setForeground(new java.awt.Color(8, 76, 77));
        labHrgJual.setText("Harga Jual");

        txHrgJual.setBackground(new java.awt.Color(66, 132, 244));
        txHrgJual.setForeground(new java.awt.Color(255, 255, 255));
        txHrgJual.setName("txHrgJual"); // NOI18N

        labHrgBeli.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labHrgBeli.setForeground(new java.awt.Color(8, 76, 77));
        labHrgBeli.setText("Harga Beli");

        txHrgBeli.setBackground(new java.awt.Color(66, 132, 244));
        txHrgBeli.setForeground(new java.awt.Color(255, 255, 255));
        txHrgBeli.setName("txHrgBeli"); // NOI18N

        labStokBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labStokBarang.setForeground(new java.awt.Color(8, 76, 77));
        labStokBarang.setText("Stok");

        txStokBarang.setBackground(new java.awt.Color(66, 132, 244));
        txStokBarang.setForeground(new java.awt.Color(255, 255, 255));
        txStokBarang.setName("txStokBarang"); // NOI18N

        rbMembrane.setBackground(new java.awt.Color(153, 153, 153));
        bgJenis.add(rbMembrane);
        rbMembrane.setForeground(new java.awt.Color(0, 0, 0));
        rbMembrane.setText("Membrane");
        rbMembrane.setName("rbMembrane"); // NOI18N

        rbMecha.setBackground(new java.awt.Color(153, 153, 153));
        bgJenis.add(rbMecha);
        rbMecha.setForeground(new java.awt.Color(0, 0, 0));
        rbMecha.setText("Mechanical");
        rbMecha.setName("rbMecha"); // NOI18N

        cbUkuranBarang.setBackground(new java.awt.Color(66, 132, 244));
        cbUkuranBarang.setForeground(new java.awt.Color(255, 255, 255));
        cbUkuranBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Ukuran", "40%", "60%", "65%", "75%", "TKL", "Full Size" }));
        cbUkuranBarang.setName("cbUkuranBarang"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labIDBarang)
                                    .addComponent(labJenisBarang, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(145, 145, 145)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbIDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbMembrane, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rbMecha, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labNamaBarang)
                                    .addComponent(labUkuranBarang)
                                    .addComponent(labStokBarang)
                                    .addComponent(labHrgBeli)
                                    .addComponent(labHrgJual))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txHrgBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txHrgJual, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txNamaBarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbUkuranBarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(399, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDBarang)
                    .addComponent(cbIDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnUpdate)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(btnClear))
                            .addComponent(btnDelete)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labJenisBarang)
                            .addComponent(rbMembrane)
                            .addComponent(rbMecha))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNamaBarang))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labUkuranBarang)
                            .addComponent(cbUkuranBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(labHrgBeli)
                                .addGap(54, 54, 54)
                                .addComponent(labStokBarang))
                            .addComponent(labHrgJual)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txHrgJual, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(txHrgBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(txStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        tabBarang.addTab("Master", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setName("tabTabel"); // NOI18N

        tbBarang.setBackground(new java.awt.Color(255, 255, 255));
        tbBarang.setForeground(new java.awt.Color(43, 123, 123));
        tbBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbBarang.setName("tbBarang"); // NOI18N
        tbBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBarang);

        txID.setBackground(new java.awt.Color(255, 255, 255));
        txID.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txID.setName("txID"); // NOI18N

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(8, 76, 77));
        btnSearch.setText("Search");
        btnSearch.setName("btnSearch"); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnCl.setBackground(new java.awt.Color(255, 255, 255));
        btnCl.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnCl.setForeground(new java.awt.Color(8, 76, 77));
        btnCl.setText("Clear");
        btnCl.setName("btnCl"); // NOI18N
        btnCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(txID, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(416, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1278, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(564, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 63, Short.MAX_VALUE)))
        );

        tabBarang.addTab("Tabel", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setName("tabTambahData"); // NOI18N

        btnClear1.setBackground(new java.awt.Color(255, 0, 0));
        btnClear1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(0, 0, 0));
        btnClear1.setText("Clear");
        btnClear1.setName("btnClear1"); // NOI18N
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        btnTambahData.setBackground(new java.awt.Color(0, 255, 0));
        btnTambahData.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnTambahData.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahData.setText("Tambah Data");
        btnTambahData.setName("btnTambahData"); // NOI18N
        btnTambahData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahDataActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(251, 188, 5));
        jLabel8.setText("Data Barang");

        labJenisBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJenisBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labJenisBarang1.setText("Jenis Keyboard");

        rbMembrane1.setBackground(new java.awt.Color(153, 153, 153));
        bgJenis1.add(rbMembrane1);
        rbMembrane1.setForeground(new java.awt.Color(0, 0, 0));
        rbMembrane1.setText("Membrane");
        rbMembrane1.setName("rbMembrane1"); // NOI18N

        rbMecha1.setBackground(new java.awt.Color(153, 153, 153));
        bgJenis1.add(rbMecha1);
        rbMecha1.setForeground(new java.awt.Color(0, 0, 0));
        rbMecha1.setText("Mechanical");
        rbMecha1.setName("rbMecha1"); // NOI18N

        txNamaBarang1.setBackground(new java.awt.Color(66, 132, 244));
        txNamaBarang1.setForeground(new java.awt.Color(255, 255, 255));
        txNamaBarang1.setName("txNamaBarang1"); // NOI18N

        labNamaBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labNamaBarang1.setText("Nama");

        labUkuranBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labUkuranBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labUkuranBarang1.setText("Ukuran");

        cbUkuranBarang1.setBackground(new java.awt.Color(66, 132, 244));
        cbUkuranBarang1.setForeground(new java.awt.Color(255, 255, 255));
        cbUkuranBarang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Ukuran", "40%", "60%", "65%", "75%", "TKL", "Full Size" }));
        cbUkuranBarang1.setName("cbUkuranBarang1"); // NOI18N

        labHrgJual1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labHrgJual1.setForeground(new java.awt.Color(8, 76, 77));
        labHrgJual1.setText("Harga Jual");

        txHrgJual1.setBackground(new java.awt.Color(66, 132, 244));
        txHrgJual1.setForeground(new java.awt.Color(255, 255, 255));
        txHrgJual1.setName("txHrgJual1"); // NOI18N

        txHrgBeli1.setBackground(new java.awt.Color(66, 132, 244));
        txHrgBeli1.setForeground(new java.awt.Color(255, 255, 255));
        txHrgBeli1.setName("txHrgBeli1"); // NOI18N

        txStokBarang1.setBackground(new java.awt.Color(66, 132, 244));
        txStokBarang1.setForeground(new java.awt.Color(255, 255, 255));
        txStokBarang1.setName("txStokBarang1"); // NOI18N

        labStokBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labStokBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labStokBarang1.setText("Stok");

        labHrgBeli1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labHrgBeli1.setForeground(new java.awt.Color(8, 76, 77));
        labHrgBeli1.setText("Harga Beli");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(497, 497, 497)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labStokBarang1)
                                    .addComponent(labHrgBeli1)
                                    .addComponent(labHrgJual1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txStokBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txHrgBeli1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txHrgJual1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labNamaBarang1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txNamaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labJenisBarang1)
                                .addGap(159, 159, 159)
                                .addComponent(rbMembrane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(rbMecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labUkuranBarang1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbUkuranBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTambahData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(386, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labJenisBarang1)
                    .addComponent(rbMembrane1)
                    .addComponent(rbMecha1))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTambahData)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txNamaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNamaBarang1))
                        .addGap(15, 15, 15)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnClear1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUkuranBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labUkuranBarang1))))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(labHrgBeli1)
                        .addGap(54, 54, 54)
                        .addComponent(labStokBarang1))
                    .addComponent(labHrgJual1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txHrgJual1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(txHrgBeli1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(txStokBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(119, 119, 119))
        );

        tabBarang.addTab("Tambah Data", jPanel2);

        getContentPane().add(tabBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            String jenis;
            if(rbMembrane.isSelected()) {
                jenis = "Membrane";
            } else {
                jenis = "Mechanical";
            }
            
            conn = Koneksi.getKoneksi();

            SQL = "UPDATE Barang SET "
            + "jenis_Barang = '" + jenis + "',"
            + "nama_Barang = '" + txNamaBarang.getText() + "',"
            + "ukuran_Barang = '" + cbUkuranBarang.getSelectedItem().toString() + "',"
            + "harga_jual = '" + txHrgJual.getText() + "',"
            + "harga_beli = '" + txHrgBeli.getText() + "',"
            + "stok = '" + txStokBarang.getText() + "'"
            + "WHERE id_Barang = '" + cbIDBarang.getSelectedItem().toString() + "'";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);

            JOptionPane.showMessageDialog(this, "Success");
            showTabel();
            loadIDBarang();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            int jawab = JOptionPane.showConfirmDialog(this, "Silakan Konfirmasi?");

            switch(jawab){
                case JOptionPane.YES_OPTION:

                SQL = "DELETE FROM Barang WHERE id_Barang = '" + cbIDBarang.getSelectedItem().toString() + "'";
                cmd = conn.createStatement();
                cmd.executeUpdate(SQL);

                JOptionPane.showMessageDialog(this, "Success");
                showTabel();
                loadIDBarang();
                clear();
                break;

                case JOptionPane.NO_OPTION:
                break;

                case JOptionPane.CANCEL_OPTION:
                break;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbIDBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDBarangActionPerformed
        // TODO add your handling code here:
        if("Membrane".equals(cekBarang(1))) {
            rbMembrane.setSelected(true);
        } else if("Mechanical".equals(cekBarang(1))) {
            rbMecha.setSelected(true);
        }
        
        txNamaBarang.setText(cekBarang(2));
        cbUkuranBarang.setSelectedItem(cekBarang(3));
        txHrgJual.setText(cekBarang(4));
        txHrgBeli.setText(cekBarang(5));
        txStokBarang.setText(cekBarang(6));
    }//GEN-LAST:event_cbIDBarangActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tbBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBarangMouseClicked
        // TODO add your handling code here:
        int index = tbBarang.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {

            cbIDBarang.setSelectedItem(tbBarang.getValueAt(index, 0).toString());
            
            if(tbBarang.getValueAt(index, 1).toString().equals("Membrane")) {
                rbMembrane.setSelected(true);
            } else {
                rbMecha.setSelected(true);
            }
            
            txNamaBarang.setText(tbBarang.getValueAt(index, 2).toString());
            cbUkuranBarang.setSelectedItem(tbBarang.getValueAt(index, 3).toString());
            txHrgJual.setText(tbBarang.getValueAt(index, 4).toString());
            txHrgBeli.setText(tbBarang.getValueAt(index, 5).toString());
            txStokBarang.setText(tbBarang.getValueAt(index, 6).toString());
        }
    }//GEN-LAST:event_tbBarangMouseClicked

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnTambahDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDataActionPerformed
        // TODO add your handling code here:
        try {
            String jenis = null;
            if(rbMembrane1.isSelected()) {
                jenis = "Membrane";
            } else if(rbMecha1.isSelected()) {
                jenis = "Mechanical";
            }

            conn = Koneksi.getKoneksi();
            
            SQL = "INSERT INTO Barang (id_Barang, jenis_Barang, nama_Barang, ukuran_Barang, harga_jual, harga_beli, Stok) VALUES ("
            + "generateID('barang'),"
            + "'" + jenis + "',"
            + "'" + txNamaBarang1.getText() + "',"
            + "'" + cbUkuranBarang1.getSelectedItem().toString() + "',"
            + "'" + txHrgJual1.getText() + "',"
            + "'" + txHrgBeli1.getText() + "',"
            + "'" + txStokBarang1.getText() + "')";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);
            JOptionPane.showMessageDialog(this, "Success");

            clear();
            showTabel();
            loadIDBarang();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahDataActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        showTabel();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT * FROM barang WHERE id_Barang = '" + txID.getText() + "'");
            
            //Setting kolom dari DefaultTableModel
            tmBarang = new DefaultTableModel(new String[] {"ID BARANG", "JENIS", "nama", "UKURAN", "HARGA JUAL", "HARGA BELI", "STOK"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmBarang.addRow(new Object[] {rs.getString("id_Barang"), rs.getString("jenis_Barang"), rs.getString("nama_Barang"), 
                        rs.getString("ukuran_Barang"), rs.getInt("Harga_Beli"), rs.getInt("Harga_Jual"), rs.getInt("Stok")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            tbBarang.setDefaultEditor(Object.class, null);
            tbBarang.setModel(tmBarang);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClActionPerformed
        // TODO add your handling code here:
        txID.setText(null);
        showTabel();
    }//GEN-LAST:event_btnClActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgJenis;
    private javax.swing.ButtonGroup bgJenis1;
    private javax.swing.JButton btnCl;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTambahData;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbIDBarang;
    private javax.swing.JComboBox<String> cbUkuranBarang;
    private javax.swing.JComboBox<String> cbUkuranBarang1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labHrgBeli;
    private javax.swing.JLabel labHrgBeli1;
    private javax.swing.JLabel labHrgJual;
    private javax.swing.JLabel labHrgJual1;
    private javax.swing.JLabel labIDBarang;
    private javax.swing.JLabel labJenisBarang;
    private javax.swing.JLabel labJenisBarang1;
    private javax.swing.JLabel labNamaBarang;
    private javax.swing.JLabel labNamaBarang1;
    private javax.swing.JLabel labStokBarang;
    private javax.swing.JLabel labStokBarang1;
    private javax.swing.JLabel labUkuranBarang;
    private javax.swing.JLabel labUkuranBarang1;
    private javax.swing.JRadioButton rbMecha;
    private javax.swing.JRadioButton rbMecha1;
    private javax.swing.JRadioButton rbMembrane;
    private javax.swing.JRadioButton rbMembrane1;
    private javax.swing.JTabbedPane tabBarang;
    private javax.swing.JTable tbBarang;
    private javax.swing.JTextField txHrgBeli;
    private javax.swing.JTextField txHrgBeli1;
    private javax.swing.JTextField txHrgJual;
    private javax.swing.JTextField txHrgJual1;
    private javax.swing.JTextField txID;
    private javax.swing.JTextField txNamaBarang;
    private javax.swing.JTextField txNamaBarang1;
    private javax.swing.JTextField txStokBarang;
    private javax.swing.JTextField txStokBarang1;
    // End of variables declaration//GEN-END:variables
}
