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
public class Pegawai extends javax.swing.JInternalFrame {

    /**
     * Creates new form Pegawai
     */
    
    Connection conn;
    Statement cmd;
    ResultSet rs;
    DefaultTableModel tmPegawai;
    String SQL;
    
    Statement cmd1;
    ResultSet rs1;
    
    public Pegawai() {
        initComponents();
        
        loadIDPegawai();
    }
    
    private void loadIDPegawai(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM pegawai";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDPegawai.removeAllItems();
            cbIDPegawai.addItem("Pilih ID Pegawai");
            while(rs.next()){
                String cid = rs.getString("id_pegawai");
                cbIDPegawai.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public void showTabel() {
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT id_pegawai, nama_pegawai, DATE_FORMAT(tanggal_lahir_pegawai, '%d %M %Y') as tanggal_lahir_pegawai, "
                    + "alamat_pegawai, telepon_pegawai, jk_pegawai FROM pegawai ORDER BY id_pegawai ASC");
            
            //Setting kolom dari DefaultTableModel
            tmPegawai = new DefaultTableModel(new String[] {"ID PEGAWAI", "NAMA", "TANGGAL LAHIR", "ALAMAT", "TELEPON", "JENIS KELAMIN"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmPegawai.addRow(new Object[] {rs.getString("id_pegawai"), rs.getString("nama_pegawai"), rs.getString("tanggal_lahir_pegawai"), 
                        rs.getString("alamat_pegawai"), rs.getString("telepon_pegawai"), rs.getString("jk_pegawai")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbPegawai.setDefaultEditor(Object.class, null);
            
            tbPegawai.setModel(tmPegawai);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private String cekPegawai(int angka) {
        String pegawai = null;
        try {
            switch(angka) {
                case 1:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT nama_pegawai FROM pegawai WHERE ID_Pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("nama_pegawai");
                        }
                    }
                break;
                
                case 2:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT DATE_FORMAT(tanggal_lahir_pegawai, '%d %b %Y') as tanggal_lahir_pegawai "
                            + "FROM pegawai WHERE ID_Pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "'");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("tanggal_lahir_pegawai");
                        }
                    }
                break;
                
                case 3:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT alamat_pegawai FROM pegawai WHERE ID_Pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("alamat_pegawai");
                        }
                    }
                break;
                
                case 4:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT telepon_pegawai FROM pegawai WHERE ID_Pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("telepon_pegawai");
                        }
                    }
                break;
                
                case 5:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT JK_PEGAWAI FROM pegawai WHERE ID_Pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "'");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("JK_PEGAWAI");
                        }
                    }
                break;
            }
            
            
        } catch (Exception e) {
            if("You have an error in your SQL syntax".equals(e.getLocalizedMessage())) {
                    System.out.println("");
                }
        }
        return pegawai;
    }
    
    private void clear() {
        cbIDPegawai.setSelectedIndex(0);
        txNamaPegawai.setText(null);
        txTglLahirPegawai.setDate(null);
        txAlamatPegawai.setText(null);
        txTeleponPegawai.setText(null);
        bgGender.clearSelection();
        
        txNamaPegawai1.setText(null);
        txTglLahirPegawai1.setDate(null);
        txAlamatPegawai1.setText(null);
        txTeleponPegawai1.setText(null);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        labNamaPegawai = new javax.swing.JLabel();
        labTglLahirPegawai = new javax.swing.JLabel();
        labIDPegawai = new javax.swing.JLabel();
        labAlamatPegawai = new javax.swing.JLabel();
        labTeleponPegawai = new javax.swing.JLabel();
        labJKPegawai = new javax.swing.JLabel();
        txNamaPegawai = new javax.swing.JTextField();
        txAlamatPegawai = new javax.swing.JTextField();
        txTeleponPegawai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbIDPegawai = new javax.swing.JComboBox<>();
        btnClear = new javax.swing.JButton();
        txTglLahirPegawai = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPegawai = new javax.swing.JTable();
        txID = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCl = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnClear1 = new javax.swing.JButton();
        btnTambahData = new javax.swing.JButton();
        labNamaPegawai1 = new javax.swing.JLabel();
        txNamaPegawai1 = new javax.swing.JTextField();
        labTglLahirPegawai1 = new javax.swing.JLabel();
        labAlamatPegawai1 = new javax.swing.JLabel();
        txAlamatPegawai1 = new javax.swing.JTextField();
        labTeleponPegawai1 = new javax.swing.JLabel();
        txTeleponPegawai1 = new javax.swing.JTextField();
        labJKPegawai1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rbLaki1 = new javax.swing.JRadioButton();
        rbPerempuan1 = new javax.swing.JRadioButton();
        txTglLahirPegawai1 = new com.toedter.calendar.JDateChooser();

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

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setName("tabPegawai"); // NOI18N

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

        labNamaPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPegawai.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPegawai.setText("Nama Pegawai");

        labTglLahirPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglLahirPegawai.setForeground(new java.awt.Color(8, 76, 77));
        labTglLahirPegawai.setText("Tanggal Lahir Pegawai");

        labIDPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDPegawai.setForeground(new java.awt.Color(8, 76, 77));
        labIDPegawai.setText("ID Pegawai");

        labAlamatPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labAlamatPegawai.setForeground(new java.awt.Color(8, 76, 77));
        labAlamatPegawai.setText("Alamat Pegawai");

        labTeleponPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTeleponPegawai.setForeground(new java.awt.Color(8, 76, 77));
        labTeleponPegawai.setText("Telepon Pegawai");

        labJKPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJKPegawai.setForeground(new java.awt.Color(8, 76, 77));
        labJKPegawai.setText("Jenis Kelamin");

        txNamaPegawai.setBackground(new java.awt.Color(66, 132, 244));
        txNamaPegawai.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPegawai.setName("txNamaPegawai"); // NOI18N

        txAlamatPegawai.setBackground(new java.awt.Color(66, 132, 244));
        txAlamatPegawai.setForeground(new java.awt.Color(255, 255, 255));
        txAlamatPegawai.setName("txAlamatPegawai"); // NOI18N

        txTeleponPegawai.setBackground(new java.awt.Color(66, 132, 244));
        txTeleponPegawai.setForeground(new java.awt.Color(255, 255, 255));
        txTeleponPegawai.setName("txTeleponPegawai"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(251, 188, 5));
        jLabel7.setText("Data Pegawai");

        cbIDPegawai.setBackground(new java.awt.Color(66, 132, 244));
        cbIDPegawai.setForeground(new java.awt.Color(255, 255, 255));
        cbIDPegawai.setName("cbIDPegawai"); // NOI18N
        cbIDPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPegawaiActionPerformed(evt);
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

        txTglLahirPegawai.setBackground(new java.awt.Color(66, 132, 244));
        txTglLahirPegawai.setForeground(new java.awt.Color(255, 255, 255));
        txTglLahirPegawai.setDateFormatString("dd-MM-yyyy");
        txTglLahirPegawai.setName("txTglLahirPegawai"); // NOI18N

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
                                .addComponent(labIDPegawai)
                                .addGap(179, 179, 179)
                                .addComponent(cbIDPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labNamaPegawai)
                                .addGap(145, 145, 145)
                                .addComponent(txNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(labTeleponPegawai)
                                            .addGap(126, 126, 126))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(labJKPegawai)
                                            .addGap(154, 154, 154)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(labAlamatPegawai)
                                        .addGap(134, 134, 134))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(labTglLahirPegawai)
                                        .addGap(76, 76, 76)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txTglLahirPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txAlamatPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbLaki)
                                        .addGap(27, 27, 27)
                                        .addComponent(rbPerempuan))
                                    .addComponent(txTeleponPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(133, 133, 133)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(jLabel7)))
                .addContainerGap(420, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDPegawai)
                    .addComponent(cbIDPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNamaPegawai)
                            .addComponent(txNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labTglLahirPegawai)
                            .addComponent(txTglLahirPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labAlamatPegawai)
                            .addComponent(txAlamatPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(labTeleponPegawai))
                    .addComponent(txTeleponPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLaki)
                    .addComponent(rbPerempuan)
                    .addComponent(labJKPegawai))
                .addGap(89, 89, 89))
        );

        jTabbedPane1.addTab("Master", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setName("tabTabel"); // NOI18N

        tbPegawai.setBackground(new java.awt.Color(255, 255, 255));
        tbPegawai.setForeground(new java.awt.Color(43, 123, 123));
        tbPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPegawai.setName("tbPegawai"); // NOI18N
        tbPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPegawaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPegawai);

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

        jTabbedPane1.addTab("Tabel", jPanel3);

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

        labNamaPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPegawai1.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPegawai1.setText("Nama Pegawai");

        txNamaPegawai1.setBackground(new java.awt.Color(66, 132, 244));
        txNamaPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPegawai1.setName("txNamaPegawai1"); // NOI18N

        labTglLahirPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglLahirPegawai1.setForeground(new java.awt.Color(8, 76, 77));
        labTglLahirPegawai1.setText("Tanggal Lahir Pegawai");

        labAlamatPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labAlamatPegawai1.setForeground(new java.awt.Color(8, 76, 77));
        labAlamatPegawai1.setText("Alamat Pegawai");

        txAlamatPegawai1.setBackground(new java.awt.Color(66, 132, 244));
        txAlamatPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        txAlamatPegawai1.setName("txAlamatPegawai1"); // NOI18N

        labTeleponPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTeleponPegawai1.setForeground(new java.awt.Color(8, 76, 77));
        labTeleponPegawai1.setText("Telepon Pegawai");

        txTeleponPegawai1.setBackground(new java.awt.Color(66, 132, 244));
        txTeleponPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        txTeleponPegawai1.setName("txTeleponPegawai1"); // NOI18N

        labJKPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJKPegawai1.setForeground(new java.awt.Color(8, 76, 77));
        labJKPegawai1.setText("Jenis Kelamin");

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(251, 188, 5));
        jLabel8.setText("Data Pegawai");

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

        txTglLahirPegawai1.setBackground(new java.awt.Color(66, 132, 244));
        txTglLahirPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        txTglLahirPegawai1.setDateFormatString("dd-MM-yyyy");
        txTglLahirPegawai1.setName("txTglLahirPegawai1"); // NOI18N

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
                                .addComponent(labNamaPegawai1)
                                .addGap(146, 146, 146)
                                .addComponent(txNamaPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(labTglLahirPegawai1)
                                .addGap(77, 77, 77)
                                .addComponent(txTglLahirPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labTeleponPegawai1)
                                    .addComponent(labJKPegawai1))
                                .addGap(127, 127, 127)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txTeleponPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rbLaki1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbPerempuan1))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(labAlamatPegawai1)
                                .addGap(135, 135, 135)
                                .addComponent(txAlamatPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTambahData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(497, 497, 497)
                        .addComponent(jLabel8)))
                .addContainerGap(402, Short.MAX_VALUE))
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
                        .addComponent(labNamaPegawai1))
                    .addComponent(txNamaPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labTglLahirPegawai1)
                            .addComponent(txTglLahirPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labAlamatPegawai1)
                            .addComponent(txAlamatPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTambahData)
                        .addGap(49, 49, 49)
                        .addComponent(btnClear1)))
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labTeleponPegawai1)
                    .addComponent(txTeleponPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labJKPegawai1)
                    .addComponent(rbLaki1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbPerempuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(146, 146, 146))
        );

        jTabbedPane1.addTab("Tambah Data", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        showTabel();
    }//GEN-LAST:event_formInternalFrameOpened
  
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
            String tanggal = String.valueOf(df.format(txTglLahirPegawai.getDate()));

            SQL = "UPDATE pegawai SET "
                    + "nama_pegawai = '" + txNamaPegawai.getText() + "',"
                    + "tanggal_lahir_pegawai = '" + tanggal + "',"
                    + "alamat_pegawai = '" + txAlamatPegawai.getText() + "',"
                    + "telepon_pegawai = '" + txTeleponPegawai.getText() + "',"
                    + "jk_pegawai = '" + gender + "'"
                    + "WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "'";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);

            JOptionPane.showMessageDialog(this, "Success");
            showTabel();
            loadIDPegawai();

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

                    SQL = "DELETE FROM pegawai WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "'";
                    cmd = conn.createStatement();
                    cmd.executeUpdate(SQL);

                    JOptionPane.showMessageDialog(this, "Success");
                    showTabel();
                    loadIDPegawai();
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

    private void cbIDPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPegawaiActionPerformed
        // TODO add your handling code here:
        txNamaPegawai.setText(cekPegawai(1));
        try {
            String date = cekPegawai(2);
            Date date2 = new SimpleDateFormat("dd MMMM yyyy").parse(date);
            txTglLahirPegawai.setDate(date2);
        } catch (Exception e) {
            if("java.lang.NullPointerException".equals(e.getLocalizedMessage())) {
                System.out.println("");
            }
        }
        txAlamatPegawai.setText(cekPegawai(3));
        txTeleponPegawai.setText(cekPegawai(4));
        if("L".equals(cekPegawai(5))) {
            rbLaki.setSelected(true);
        } else if("P".equals(cekPegawai(5))) {
            rbPerempuan.setSelected(true);
        }
    }//GEN-LAST:event_cbIDPegawaiActionPerformed

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
            String tanggal = df.format(txTglLahirPegawai1.getDate());

            SQL = "INSERT INTO pegawai (id_Pegawai, nama_pegawai, tanggal_lahir_pegawai, alamat_pegawai, telepon_pegawai, jk_pegawai) VALUES ("
            + "generateID('pegawai'),"
            + "'" + txNamaPegawai1.getText() + "',"
            + "'" + tanggal + "',"
            + "'" + txAlamatPegawai1.getText() + "',"
            + "'" + txTeleponPegawai1.getText() + "',"
            + "'" + gender + "')";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);
            JOptionPane.showMessageDialog(this, "Success");

            clear();
            showTabel();
            loadIDPegawai();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahDataActionPerformed

    private void tbPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPegawaiMouseClicked
        // TODO add your handling code here:
        int index = tbPegawai.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {

            cbIDPegawai.setSelectedItem(tbPegawai.getValueAt(index, 0).toString());
            txNamaPegawai.setText(tbPegawai.getValueAt(index, 1).toString());
            try {
                String date = tbPegawai.getValueAt(index, 2).toString();
                Date date2 = new SimpleDateFormat("dd MMMM yyyy").parse(date);
                txTglLahirPegawai.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            txAlamatPegawai.setText(tbPegawai.getValueAt(index, 3).toString());
            txTeleponPegawai.setText(tbPegawai.getValueAt(index, 4).toString());
            if(tbPegawai.getValueAt(index, 5).toString().equals("L")) {
                rbLaki.setSelected(true);
            } else {
                rbPerempuan.setSelected(true);
            }
        }
    }//GEN-LAST:event_tbPegawaiMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();

            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT * FROM pegawai WHERE id_Pegawai = '" + txID.getText() + "'");

            //Setting kolom dari DefaultTableModel
            tmPegawai = new DefaultTableModel(new String[] {"ID PEGAWAI", "NAMA", "TANGGAL LAHIR", "ALAMAT", "TELEPON", "JENIS KELAMIN"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmPegawai.addRow(new Object[] {rs.getString("id_pegawai"), rs.getString("nama_pegawai"), rs.getString("tanggal_lahir_pegawai"), 
                        rs.getString("alamat_pegawai"), rs.getString("telepon_pegawai"), rs.getString("jk_pegawai")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
        tbPegawai.setDefaultEditor(Object.class, null);
        tbPegawai.setModel(tmPegawai);

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
    private javax.swing.JComboBox<String> cbIDPegawai;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labAlamatPegawai;
    private javax.swing.JLabel labAlamatPegawai1;
    private javax.swing.JLabel labIDPegawai;
    private javax.swing.JLabel labJKPegawai;
    private javax.swing.JLabel labJKPegawai1;
    private javax.swing.JLabel labNamaPegawai;
    private javax.swing.JLabel labNamaPegawai1;
    private javax.swing.JLabel labTeleponPegawai;
    private javax.swing.JLabel labTeleponPegawai1;
    private javax.swing.JLabel labTglLahirPegawai;
    private javax.swing.JLabel labTglLahirPegawai1;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbLaki1;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JRadioButton rbPerempuan1;
    private javax.swing.JTable tbPegawai;
    private javax.swing.JTextField txAlamatPegawai;
    private javax.swing.JTextField txAlamatPegawai1;
    private javax.swing.JTextField txID;
    private javax.swing.JTextField txNamaPegawai;
    private javax.swing.JTextField txNamaPegawai1;
    private javax.swing.JTextField txTeleponPegawai;
    private javax.swing.JTextField txTeleponPegawai1;
    private com.toedter.calendar.JDateChooser txTglLahirPegawai;
    private com.toedter.calendar.JDateChooser txTglLahirPegawai1;
    // End of variables declaration//GEN-END:variables
}
