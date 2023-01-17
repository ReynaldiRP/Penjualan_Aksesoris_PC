/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UAS;

import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

/**
 *
 * @author akbar
 */
public class Pelanggan extends javax.swing.JInternalFrame {

    /**
     * Creates new form Pelanggan
     */
    
    Connection conn;
    Statement cmd;
    ResultSet rs;
    DefaultTableModel tmPelanggan;
    String SQL;
    
    Statement cmd1;
    ResultSet rs1;
    
    public Pelanggan() {
        initComponents();
        
        loadIDPelanggan();
    }
    
    private void loadIDPelanggan(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM Pelanggan";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDPelanggan.removeAllItems();
            cbIDPelanggan.addItem("Pilih ID Pelanggan");
            while(rs.next()){
                String cid = rs.getString("id_Pelanggan");
                cbIDPelanggan.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public void showTabel() {
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT id_Pelanggan, nama_Pelanggan, DATE_FORMAT(tanggal_lahir_Pelanggan, '%d %M %Y') as tanggal_lahir_Pelanggan, "
                    + "alamat_Pelanggan, telepon_Pelanggan, jk_Pelanggan FROM Pelanggan ORDER BY id_Pelanggan ASC");
            
            //Setting kolom dari DefaultTableModel
            tmPelanggan = new DefaultTableModel(new String[] {"ID PELANGGAN", "NAMA", "TANGGAL LAHIR", "ALAMAT", "TELEPON", "JENIS KELAMIN"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmPelanggan.addRow(new Object[] {rs.getString("id_Pelanggan"), rs.getString("nama_Pelanggan"), rs.getString("tanggal_lahir_Pelanggan"), 
                        rs.getString("alamat_Pelanggan"), rs.getString("telepon_Pelanggan"), rs.getString("jk_Pelanggan")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbPelanggan.setDefaultEditor(Object.class, null);
            
            tbPelanggan.setModel(tmPelanggan);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private String cekPelanggan(int angka) {
        String Pelanggan = null;
        try {
            switch(angka) {
                case 1:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT nama_Pelanggan FROM Pelanggan WHERE ID_Pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            Pelanggan = rs1.getString("nama_Pelanggan");
                        }
                    }
                break;
                
                case 2:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT DATE_FORMAT(tanggal_lahir_Pelanggan, '%d %b %Y') as tanggal_lahir_Pelanggan "
                            + "FROM Pelanggan WHERE ID_Pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "'");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            Pelanggan = rs1.getString("tanggal_lahir_Pelanggan");
                        }
                    }
                break;
                
                case 3:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT alamat_Pelanggan FROM Pelanggan WHERE ID_Pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            Pelanggan = rs1.getString("alamat_Pelanggan");
                        }
                    }
                break;
                
                case 4:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT telepon_Pelanggan FROM Pelanggan WHERE ID_Pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            Pelanggan = rs1.getString("telepon_Pelanggan");
                        }
                    }
                break;
                
                case 5:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT JK_Pelanggan FROM Pelanggan WHERE ID_Pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "'");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            Pelanggan = rs1.getString("JK_Pelanggan");
                        }
                    }
                break;
            }
            
            
        } catch (Exception e) {
            if("You have an error in your SQL syntax".equals(e.getLocalizedMessage())) {
                    System.out.println("");
                }
        }
        return Pelanggan;
    }
    
    private void clear() {
        cbIDPelanggan.setSelectedIndex(0);
        txNamaPelanggan.setText(null);
        txTglLahirPelanggan.setDate(null);
        txAlamatPelanggan.setText(null);
        txTeleponPelanggan.setText(null);
        bgGender.clearSelection();
        
        txNamaPelanggan1.setText(null);
        txTglLahirPelanggan1.setDate(null);
        txAlamatPelanggan1.setText(null);
        txTeleponPelanggan1.setText(null);
        bgGender1.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgGender = new javax.swing.ButtonGroup();
        bgGender1 = new javax.swing.ButtonGroup();
        tabPelanggan = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        labNamaPelanggan = new javax.swing.JLabel();
        labTglLahirPelanggan = new javax.swing.JLabel();
        labIDPelanggan = new javax.swing.JLabel();
        labAlamatPelanggan = new javax.swing.JLabel();
        labTeleponPelanggan = new javax.swing.JLabel();
        labJKPelanggan = new javax.swing.JLabel();
        txNamaPelanggan = new javax.swing.JTextField();
        txAlamatPelanggan = new javax.swing.JTextField();
        txTeleponPelanggan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbIDPelanggan = new javax.swing.JComboBox<>();
        btnClear = new javax.swing.JButton();
        txTglLahirPelanggan = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPelanggan = new javax.swing.JTable();
        txID = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCl = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnClear1 = new javax.swing.JButton();
        btnTambahData = new javax.swing.JButton();
        labNamaPelanggan1 = new javax.swing.JLabel();
        txNamaPelanggan1 = new javax.swing.JTextField();
        labTglLahirPelanggan1 = new javax.swing.JLabel();
        labAlamatPelanggan1 = new javax.swing.JLabel();
        txAlamatPelanggan1 = new javax.swing.JTextField();
        labTeleponPelanggan1 = new javax.swing.JLabel();
        txTeleponPelanggan1 = new javax.swing.JTextField();
        labJKPelanggan1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rbLaki1 = new javax.swing.JRadioButton();
        rbPerempuan1 = new javax.swing.JRadioButton();
        txTglLahirPelanggan1 = new com.toedter.calendar.JDateChooser();

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

        tabPelanggan.setName("tabPelanggan"); // NOI18N

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

        rbLaki.setBackground(new java.awt.Color(153, 153, 153));
        bgGender.add(rbLaki);
        rbLaki.setForeground(new java.awt.Color(0, 0, 0));
        rbLaki.setText("Laki-Laki");
        rbLaki.setName("rbLaki"); // NOI18N

        rbPerempuan.setBackground(new java.awt.Color(153, 153, 153));
        bgGender.add(rbPerempuan);
        rbPerempuan.setForeground(new java.awt.Color(0, 0, 0));
        rbPerempuan.setText("Perempuan");
        rbPerempuan.setName("rbPerempuan"); // NOI18N

        labNamaPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPelanggan.setText("Nama Pelanggan");

        labTglLahirPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglLahirPelanggan.setForeground(new java.awt.Color(8, 76, 77));
        labTglLahirPelanggan.setText("Tanggal Lahir Pelanggan");

        labIDPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDPelanggan.setForeground(new java.awt.Color(8, 76, 77));
        labIDPelanggan.setText("ID Pelanggan");

        labAlamatPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labAlamatPelanggan.setForeground(new java.awt.Color(8, 76, 77));
        labAlamatPelanggan.setText("Alamat Pelanggan");

        labTeleponPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTeleponPelanggan.setForeground(new java.awt.Color(8, 76, 77));
        labTeleponPelanggan.setText("Telepon Pelanggan");

        labJKPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJKPelanggan.setForeground(new java.awt.Color(8, 76, 77));
        labJKPelanggan.setText("Jenis Kelamin");

        txNamaPelanggan.setBackground(new java.awt.Color(66, 132, 244));
        txNamaPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPelanggan.setName("txNamaPelanggan"); // NOI18N

        txAlamatPelanggan.setBackground(new java.awt.Color(66, 132, 244));
        txAlamatPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txAlamatPelanggan.setName("txAlamatPelanggan"); // NOI18N

        txTeleponPelanggan.setBackground(new java.awt.Color(66, 132, 244));
        txTeleponPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txTeleponPelanggan.setName("txTeleponPelanggan"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(251, 188, 5));
        jLabel7.setText("Data Pelanggan");

        cbIDPelanggan.setBackground(new java.awt.Color(66, 132, 244));
        cbIDPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        cbIDPelanggan.setName("cbIDPelanggan"); // NOI18N
        cbIDPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPelangganActionPerformed(evt);
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

        txTglLahirPelanggan.setBackground(new java.awt.Color(66, 132, 244));
        txTglLahirPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txTglLahirPelanggan.setDateFormatString("dd-MM-yyyy");
        txTglLahirPelanggan.setName("txTglLahirPelanggan"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labIDPelanggan)
                                .addGap(179, 179, 179)
                                .addComponent(cbIDPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labNamaPelanggan)
                                .addGap(145, 145, 145)
                                .addComponent(txNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(labTeleponPelanggan)
                                            .addGap(126, 126, 126))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(labJKPelanggan)
                                            .addGap(154, 154, 154)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(labAlamatPelanggan)
                                        .addGap(134, 134, 134))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(labTglLahirPelanggan)
                                        .addGap(76, 76, 76)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txTglLahirPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txAlamatPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbLaki)
                                        .addGap(27, 27, 27)
                                        .addComponent(rbPerempuan))
                                    .addComponent(txTeleponPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(133, 133, 133)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(483, 483, 483)
                        .addComponent(jLabel7)))
                .addContainerGap(402, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDPelanggan)
                    .addComponent(cbIDPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNamaPelanggan)
                            .addComponent(txNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labTglLahirPelanggan)
                            .addComponent(txTglLahirPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labAlamatPelanggan)
                            .addComponent(txAlamatPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnUpdate)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(btnClear))
                            .addComponent(btnDelete))))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labTeleponPelanggan))
                    .addComponent(txTeleponPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLaki)
                    .addComponent(rbPerempuan)
                    .addComponent(labJKPelanggan))
                .addGap(89, 89, 89))
        );

        tabPelanggan.addTab("Master", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setName("tabTabel"); // NOI18N

        tbPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        tbPelanggan.setForeground(new java.awt.Color(43, 123, 123));
        tbPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPelangganMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPelanggan);

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1278, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 63, Short.MAX_VALUE)))
        );

        tabPelanggan.addTab("Tabel", jPanel3);

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

        labNamaPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan1.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPelanggan1.setText("Nama Pelanggan");

        txNamaPelanggan1.setBackground(new java.awt.Color(66, 132, 244));
        txNamaPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPelanggan1.setName("txNamaPelanggan1"); // NOI18N

        labTglLahirPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglLahirPelanggan1.setForeground(new java.awt.Color(8, 76, 77));
        labTglLahirPelanggan1.setText("Tanggal Lahir Pelanggan");

        labAlamatPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labAlamatPelanggan1.setForeground(new java.awt.Color(8, 76, 77));
        labAlamatPelanggan1.setText("Alamat Pelanggan");

        txAlamatPelanggan1.setBackground(new java.awt.Color(66, 132, 244));
        txAlamatPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        txAlamatPelanggan1.setName("txAlamatPelanggan1"); // NOI18N

        labTeleponPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTeleponPelanggan1.setForeground(new java.awt.Color(8, 76, 77));
        labTeleponPelanggan1.setText("Telepon Pelanggan");

        txTeleponPelanggan1.setBackground(new java.awt.Color(66, 132, 244));
        txTeleponPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        txTeleponPelanggan1.setName("txTeleponPelanggan1"); // NOI18N

        labJKPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJKPelanggan1.setForeground(new java.awt.Color(8, 76, 77));
        labJKPelanggan1.setText("Jenis Kelamin");

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(251, 188, 5));
        jLabel8.setText("Data Pelanggan");

        rbLaki1.setBackground(new java.awt.Color(153, 153, 153));
        bgGender1.add(rbLaki1);
        rbLaki1.setForeground(new java.awt.Color(0, 0, 0));
        rbLaki1.setText("Laki-Laki");
        rbLaki1.setName("rbLaki1"); // NOI18N

        rbPerempuan1.setBackground(new java.awt.Color(153, 153, 153));
        bgGender1.add(rbPerempuan1);
        rbPerempuan1.setForeground(new java.awt.Color(0, 0, 0));
        rbPerempuan1.setText("Perempuan");
        rbPerempuan1.setName("rbPerempuan1"); // NOI18N

        txTglLahirPelanggan1.setBackground(new java.awt.Color(66, 132, 244));
        txTglLahirPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        txTglLahirPelanggan1.setDateFormatString("dd-MM-yyyy");
        txTglLahirPelanggan1.setName("txTglLahirPelanggan1"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labNamaPelanggan1)
                                .addGap(146, 146, 146)
                                .addComponent(txNamaPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(labTglLahirPelanggan1)
                                .addGap(77, 77, 77)
                                .addComponent(txTglLahirPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labTeleponPelanggan1)
                                    .addComponent(labJKPelanggan1))
                                .addGap(127, 127, 127)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txTeleponPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rbLaki1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbPerempuan1))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(labAlamatPelanggan1)
                                .addGap(135, 135, 135)
                                .addComponent(txAlamatPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTambahData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(468, 468, 468)
                        .addComponent(jLabel8)))
                .addContainerGap(384, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(labNamaPelanggan1))
                    .addComponent(txNamaPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labTglLahirPelanggan1)
                            .addComponent(txTglLahirPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labAlamatPelanggan1)
                            .addComponent(txAlamatPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTambahData)
                        .addGap(49, 49, 49)
                        .addComponent(btnClear1)))
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labTeleponPelanggan1)
                    .addComponent(txTeleponPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labJKPelanggan1)
                    .addComponent(rbLaki1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbPerempuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(146, 146, 146))
        );

        tabPelanggan.addTab("Tambah Data", jPanel2);

        getContentPane().add(tabPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            String gender;
            if(rbLaki.isSelected()) {
                gender = "L";
            } else {
                gender = "P";
            }

            String formatDate = "yyyy-MM-dd";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = String.valueOf(df.format(txTglLahirPelanggan.getDate()));

            SQL = "UPDATE Pelanggan SET "
            + "nama_Pelanggan = '" + txNamaPelanggan.getText() + "',"
            + "tanggal_lahir_Pelanggan = '" + tanggal + "',"
            + "alamat_Pelanggan = '" + txAlamatPelanggan.getText() + "',"
            + "telepon_Pelanggan = '" + txTeleponPelanggan.getText() + "',"
            + "jk_Pelanggan = '" + gender + "'"
            + "WHERE id_Pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "'";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);

            JOptionPane.showMessageDialog(this, "Success");
            showTabel();
            loadIDPelanggan();

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

                SQL = "DELETE FROM Pelanggan WHERE id_Pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "'";
                cmd = conn.createStatement();
                cmd.executeUpdate(SQL);

                JOptionPane.showMessageDialog(this, "Success");
                showTabel();
                loadIDPelanggan();
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

    private void cbIDPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPelangganActionPerformed
        // TODO add your handling code here:
        txNamaPelanggan.setText(cekPelanggan(1));
        try {
            String date = cekPelanggan(2);
            Date date2 = new SimpleDateFormat("dd MMMM yyyy").parse(date);
            txTglLahirPelanggan.setDate(date2);
        } catch (Exception e) {
            if("java.lang.NullPointerException".equals(e.getLocalizedMessage())) {
                System.out.println("");
            }
        }
        txAlamatPelanggan.setText(cekPelanggan(3));
        txTeleponPelanggan.setText(cekPelanggan(4));
        if("L".equals(cekPelanggan(5))) {
            rbLaki.setSelected(true);
        } else if("P".equals(cekPelanggan(5))) {
            rbPerempuan.setSelected(true);
        }
    }//GEN-LAST:event_cbIDPelangganActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnTambahDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDataActionPerformed
        // TODO add your handling code here:
        try {
            String gender;
            if(rbLaki1.isSelected()) {
                gender = "L";
            } else {
                gender = "P";
            }

            conn = Koneksi.getKoneksi();

            String formatDate = "yyyy-MM-dd";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = df.format(txTglLahirPelanggan1.getDate());

            SQL = "INSERT INTO Pelanggan (id_Pelanggan, nama_Pelanggan, tanggal_lahir_Pelanggan, alamat_Pelanggan, telepon_Pelanggan, jk_Pelanggan) VALUES ("
            + "generateID('pelanggan'),"
            + "'" + txNamaPelanggan1.getText() + "',"
            + "'" + tanggal + "',"
            + "'" + txAlamatPelanggan1.getText() + "',"
            + "'" + txTeleponPelanggan1.getText() + "',"
            + "'" + gender + "')";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);
            JOptionPane.showMessageDialog(this, "Success");

            clear();
            showTabel();
            loadIDPelanggan();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahDataActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        showTabel();
    }//GEN-LAST:event_formInternalFrameOpened

    private void tbPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPelangganMouseClicked
        // TODO add your handling code here:
        int index = tbPelanggan.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {

            cbIDPelanggan.setSelectedItem(tbPelanggan.getValueAt(index, 0).toString());
            txNamaPelanggan.setText(tbPelanggan.getValueAt(index, 1).toString());
            try {
                String date = tbPelanggan.getValueAt(index, 2).toString();
                Date date2 = new SimpleDateFormat("dd MMMM yyyy").parse(date);
                txTglLahirPelanggan.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            txAlamatPelanggan.setText(tbPelanggan.getValueAt(index, 3).toString());
            txTeleponPelanggan.setText(tbPelanggan.getValueAt(index, 4).toString());

            if(tbPelanggan.getValueAt(index, 5).toString().equals("L")) {
                rbLaki.setSelected(true);
            } else {
                rbPerempuan.setSelected(true);
            }
        }
    }//GEN-LAST:event_tbPelangganMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();

            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT * FROM pelanggan WHERE id_Pelanggan = '" + txID.getText() + "'");

            //Setting kolom dari DefaultTableModel
            tmPelanggan = new DefaultTableModel(new String[] {"ID PELANGGAN", "NAMA", "TANGGAL LAHIR", "ALAMAT", "TELEPON", "JENIS KELAMIN"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmPelanggan.addRow(new Object[] {rs.getString("id_Pelanggan"), rs.getString("nama_Pelanggan"), rs.getString("tanggal_lahir_Pelanggan"), 
                        rs.getString("alamat_Pelanggan"), rs.getString("telepon_Pelanggan"), rs.getString("jk_Pelanggan")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
        tbPelanggan.setDefaultEditor(Object.class, null);
        tbPelanggan.setModel(tmPelanggan);

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
    private javax.swing.ButtonGroup bgGender;
    private javax.swing.ButtonGroup bgGender1;
    private javax.swing.JButton btnCl;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTambahData;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbIDPelanggan;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labAlamatPelanggan;
    private javax.swing.JLabel labAlamatPelanggan1;
    private javax.swing.JLabel labIDPelanggan;
    private javax.swing.JLabel labJKPelanggan;
    private javax.swing.JLabel labJKPelanggan1;
    private javax.swing.JLabel labNamaPelanggan;
    private javax.swing.JLabel labNamaPelanggan1;
    private javax.swing.JLabel labTeleponPelanggan;
    private javax.swing.JLabel labTeleponPelanggan1;
    private javax.swing.JLabel labTglLahirPelanggan;
    private javax.swing.JLabel labTglLahirPelanggan1;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbLaki1;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JRadioButton rbPerempuan1;
    private javax.swing.JTabbedPane tabPelanggan;
    private javax.swing.JTable tbPelanggan;
    private javax.swing.JTextField txAlamatPelanggan;
    private javax.swing.JTextField txAlamatPelanggan1;
    private javax.swing.JTextField txID;
    private javax.swing.JTextField txNamaPelanggan;
    private javax.swing.JTextField txNamaPelanggan1;
    private javax.swing.JTextField txTeleponPelanggan;
    private javax.swing.JTextField txTeleponPelanggan1;
    private com.toedter.calendar.JDateChooser txTglLahirPelanggan;
    private com.toedter.calendar.JDateChooser txTglLahirPelanggan1;
    // End of variables declaration//GEN-END:variables
}
