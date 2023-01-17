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

/**
 *
 * @author akbar
 */
public class Penjualan extends javax.swing.JInternalFrame {

    /**
     * Creates new form Penjualan
     */
    
    Connection conn;
    Statement cmd;
    ResultSet rs;
    DefaultTableModel tmTransaksi;
    DefaultTableModel tmDetail;
    String SQL;

    String SQL1;
    Statement cmd1;
    ResultSet rs1;
    
    String SQL2;
    Statement cmd2;
    ResultSet rs2;
    
    public Penjualan() {
        initComponents();
        
        loadNamaPegawai();
        loadNamaPelanggan();
    }
    
    private void loadNamaPegawai(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM pegawai";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbNamaPegawai.removeAllItems();
            cbNamaPegawai.addItem("Pilih Nama Pegawai");
            cbNamaPegawai1.removeAllItems();
            cbNamaPegawai1.addItem("Pilih Nama Pegawai");
            while(rs.next()){
                String cid = rs.getString("Nama_Pegawai");
                cbNamaPegawai.addItem(cid);
                cbNamaPegawai1.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void loadNamaPelanggan(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM Pelanggan";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbNamaPelanggan.removeAllItems();
            cbNamaPelanggan.addItem("Pilih Nama Pelanggan");
            cbNamaPelanggan1.removeAllItems();
            cbNamaPelanggan1.addItem("Pilih Nama Pelanggan");
            while(rs.next()){
                String cid = rs.getString("Nama_Pelanggan");
                cbNamaPelanggan.addItem(cid);
                cbNamaPelanggan1.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void showTabel() {
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT id_transaksi, nama_Pegawai, Nama_Pelanggan, "
                    + "DATE_FORMAT(tanggal_transaksi, '%d %M %Y') as Tanggal_Transaksi FROM Transaksi ORDER BY id_transaksi ASC");
            
            //Setting kolom dari DefaultTableModel
            tmTransaksi = new DefaultTableModel(new String[] {"ID TRANSAKSI", "NAMA PEGAWAI", "NAMA PELANGGAN", "TANGGAL"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmTransaksi.addRow(new Object[] {rs.getString("id_transaksi"), rs.getString("nama_Pegawai"), rs.getString("Nama_Pelanggan"), 
                        rs.getString("Tanggal_Transaksi")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbTransaksi.setDefaultEditor(Object.class, null);
            
            tbTransaksi.setModel(tmTransaksi);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT id_transaksi, jenis_Barang, Nama_Barang, ukuran_Barang, "
                    + "Kuantitas, Total_Harga FROM Detail_Transaksi ORDER BY id_transaksi ASC");
            
            //Setting kolom dari DefaultTableModel
            tmDetail = new DefaultTableModel(new String[] {"ID TRANSAKSI", "JENIS BARANG", "NAMA BARANG", "UKURAN BARANG", "KUANTITAS", "TOTAL HARGA"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmDetail.addRow(new Object[] {rs.getString("id_transaksi"), rs.getString("jenis_Barang"), rs.getString("Nama_Barang"), 
                        rs.getString("ukuran_Barang"), rs.getInt("Kuantitas"), rs.getInt("Total_Harga")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbDetail.setDefaultEditor(Object.class, null);
            
            tbDetail.setModel(tmDetail);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void clear() {
        txIDTransaksi.setText("ID Transaksi");
        cbNamaPegawai.setSelectedIndex(0);
        cbNamaPelanggan.setSelectedIndex(0);
        cbNamaBarang.removeAllItems();
        bgJenis.clearSelection();
        txJmlBarang.setText(null);
        txTotal.setText("Total Harga");
        txTanggal.setDate(null);
        
        cbNamaPegawai1.setSelectedIndex(0);
        cbNamaPelanggan1.setSelectedIndex(0);
        cbNamaBarang1.removeAllItems();
        bgJenis1.clearSelection();
        txJmlBarang1.setText(null);
        txTanggal1.setDate(null);

        clearUkuran();
        diskon.setText("diskon");
    }
    
    private void clearUkuran() {
        cbUkuranBarang.removeAllItems();
        cbUkuranBarang.addItem("Pilih Ukuran Barang");
        cbUkuranBarang.addItem("40%");
        cbUkuranBarang.addItem("60%");
        cbUkuranBarang.addItem("65%");
        cbUkuranBarang.addItem("75%");
        cbUkuranBarang.addItem("TKL");
        cbUkuranBarang.addItem("Full Size");
        
        cbUkuranBarang1.removeAllItems();
        cbUkuranBarang1.addItem("Pilih Ukuran Barang");
        cbUkuranBarang1.addItem("40%");
        cbUkuranBarang1.addItem("60%");
        cbUkuranBarang1.addItem("65%");
        cbUkuranBarang1.addItem("75%");
        cbUkuranBarang1.addItem("TKL");
        cbUkuranBarang1.addItem("Full Size");
    }
    
    private void jenisBarang(int pilih) {
        switch(pilih) {
            case 1:
                try {
                    SQL = "SELECT DISTINCT(Ukuran_Barang) FROM Barang WHERE Jenis_Barang = 'Mechanical'";
                    cmd = conn.createStatement();
                    rs = cmd.executeQuery(SQL);
                    cbUkuranBarang.removeAllItems();
                    cbUkuranBarang.addItem("Pilih Ukuran Barang");
                    cbUkuranBarang1.removeAllItems();
                    cbUkuranBarang1.addItem("Pilih Ukuran Barang");
                    while(rs.next()) {
                        cbUkuranBarang.addItem(rs.getString("Ukuran_Barang"));
                        cbUkuranBarang1.addItem(rs.getString("Ukuran_Barang"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            break;
            
            case 2:
                try {
                    SQL = "SELECT DISTINCT(Ukuran_Barang) FROM Barang WHERE Jenis_Barang = 'Membrane'";
                    cmd = conn.createStatement();
                    rs = cmd.executeQuery(SQL);
                    cbUkuranBarang.removeAllItems();
                    cbUkuranBarang.addItem("Pilih Ukuran Barang");
                    cbUkuranBarang1.removeAllItems();
                    cbUkuranBarang1.addItem("Pilih Ukuran Barang");
                    while(rs.next()) {
                        cbUkuranBarang.addItem(rs.getString("Ukuran_Barang"));
                        cbUkuranBarang1.addItem(rs.getString("Ukuran_Barang"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            break;
        }
    }
    
    private void ukuranBarang(int pilih) {
        switch(pilih) {
            case 1:
                try {
                    SQL = "SELECT * FROM Barang WHERE Jenis_Barang = 'Mechanical' AND Ukuran_Barang = '" + cbUkuranBarang.getSelectedItem().toString() + "'";
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery(SQL);
                    cbNamaBarang.removeAllItems();
                    while(rs1.next()) {
                        cbNamaBarang.addItem(rs1.getString("Nama_Barang"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            break;
            
            case 2:
                try {
                    SQL = "SELECT * FROM Barang WHERE Jenis_Barang = 'Membrane' AND Ukuran_Barang = '" + cbUkuranBarang.getSelectedItem().toString() + "'";
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery(SQL);
                    cbNamaBarang.removeAllItems();
                    while(rs1.next()) {
                        cbNamaBarang.addItem(rs1.getString("Nama_Barang"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            break;
            
            case 3:
                try {
                    SQL = "SELECT * FROM Barang WHERE Jenis_Barang = 'Mechanical' AND Ukuran_Barang = '" + cbUkuranBarang1.getSelectedItem().toString() + "'";
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery(SQL);
                    cbNamaBarang1.removeAllItems();
                    while(rs1.next()) {
                        cbNamaBarang1.addItem(rs1.getString("Nama_Barang"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            break;
            
            case 4:
                try {
                    SQL = "SELECT * FROM Barang WHERE Jenis_Barang = 'Membrane' AND Ukuran_Barang = '" + cbUkuranBarang1.getSelectedItem().toString() + "'";
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery(SQL);
                    cbNamaBarang1.removeAllItems();
                    while(rs1.next()) {
                        cbNamaBarang1.addItem(rs1.getString("Nama_Barang"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            break;
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

        bgJenis = new javax.swing.ButtonGroup();
        bgJenis1 = new javax.swing.ButtonGroup();
        tabPenjualan = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        labJenisBarang = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        rbMembrane = new javax.swing.JRadioButton();
        rbMecha = new javax.swing.JRadioButton();
        labIDTransaksi = new javax.swing.JLabel();
        txIDTransaksi = new javax.swing.JLabel();
        labNamaPegawai = new javax.swing.JLabel();
        cbNamaPegawai = new javax.swing.JComboBox<>();
        labNamaPelanggan = new javax.swing.JLabel();
        cbNamaPelanggan = new javax.swing.JComboBox<>();
        labNamaBarang = new javax.swing.JLabel();
        cbNamaBarang = new javax.swing.JComboBox<>();
        labJmlBarang = new javax.swing.JLabel();
        txJmlBarang = new javax.swing.JTextField();
        labTotal = new javax.swing.JLabel();
        txTotal = new javax.swing.JLabel();
        labUkuranBarang = new javax.swing.JLabel();
        cbUkuranBarang = new javax.swing.JComboBox<>();
        labTanggal = new javax.swing.JLabel();
        txTanggal = new com.toedter.calendar.JDateChooser();
        labTotal1 = new javax.swing.JLabel();
        diskon = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTransaksi = new javax.swing.JTable();
        txID = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCl = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbDetail = new javax.swing.JTable();
        txID1 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        btnCl1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnTambahData = new javax.swing.JButton();
        labJenisBarang1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnClear1 = new javax.swing.JButton();
        rbMembrane1 = new javax.swing.JRadioButton();
        rbMecha1 = new javax.swing.JRadioButton();
        labNamaPegawai1 = new javax.swing.JLabel();
        cbNamaPegawai1 = new javax.swing.JComboBox<>();
        labNamaPelanggan1 = new javax.swing.JLabel();
        cbNamaPelanggan1 = new javax.swing.JComboBox<>();
        labNamaBarang1 = new javax.swing.JLabel();
        cbNamaBarang1 = new javax.swing.JComboBox<>();
        labJmlBarang1 = new javax.swing.JLabel();
        txJmlBarang1 = new javax.swing.JTextField();
        labUkuranBarang1 = new javax.swing.JLabel();
        cbUkuranBarang1 = new javax.swing.JComboBox<>();
        labTanggal1 = new javax.swing.JLabel();
        txTanggal1 = new com.toedter.calendar.JDateChooser();

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

        tabPenjualan.setName("tabPenjualan"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnUpdate.setBackground(new java.awt.Color(255, 255, 0));
        btnUpdate.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setName("btnUpdate"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
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

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(251, 188, 5));
        jLabel7.setText("Transaksi Penjualan");

        btnClear.setBackground(new java.awt.Color(204, 204, 204));
        btnClear.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear.setText("Clear");
        btnClear.setName("btnClear"); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        rbMembrane.setBackground(new java.awt.Color(153, 153, 153));
        bgJenis.add(rbMembrane);
        rbMembrane.setText("Membrane");
        rbMembrane.setName("rbMembrane"); // NOI18N
        rbMembrane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMembraneActionPerformed(evt);
            }
        });

        rbMecha.setBackground(new java.awt.Color(153, 153, 153));
        bgJenis.add(rbMecha);
        rbMecha.setText("Mechanical");
        rbMecha.setName("rbMecha"); // NOI18N
        rbMecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMechaActionPerformed(evt);
            }
        });

        labIDTransaksi.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDTransaksi.setForeground(new java.awt.Color(8, 76, 77));
        labIDTransaksi.setText("ID Transaksi");

        txIDTransaksi.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txIDTransaksi.setForeground(new java.awt.Color(8, 76, 77));
        txIDTransaksi.setText("ID Transaksi");
        txIDTransaksi.setName("txIDTransaksi"); // NOI18N

        labNamaPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPegawai.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPegawai.setText("Nama Pegawai");

        cbNamaPegawai.setBackground(new java.awt.Color(66, 132, 244));
        cbNamaPegawai.setForeground(new java.awt.Color(255, 255, 255));
        cbNamaPegawai.setName("cbNamaPegawai"); // NOI18N

        labNamaPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPelanggan.setText("Nama Pelanggan");

        cbNamaPelanggan.setBackground(new java.awt.Color(66, 132, 244));
        cbNamaPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        cbNamaPelanggan.setName("cbNamaPelanggan"); // NOI18N

        labNamaBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang.setForeground(new java.awt.Color(8, 76, 77));
        labNamaBarang.setText("Nama Barang");

        cbNamaBarang.setBackground(new java.awt.Color(66, 132, 244));
        cbNamaBarang.setForeground(new java.awt.Color(255, 255, 255));
        cbNamaBarang.setName("cbNamaBarang"); // NOI18N

        labJmlBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJmlBarang.setForeground(new java.awt.Color(8, 76, 77));
        labJmlBarang.setText("Jumlah Barang");

        txJmlBarang.setBackground(new java.awt.Color(66, 132, 244));
        txJmlBarang.setForeground(new java.awt.Color(255, 255, 255));
        txJmlBarang.setName("txJmlBarang"); // NOI18N

        labTotal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTotal.setForeground(new java.awt.Color(8, 76, 77));
        labTotal.setText("Total Harga");

        txTotal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txTotal.setForeground(new java.awt.Color(8, 76, 77));
        txTotal.setText("Total Harga");
        txTotal.setName("txTotal"); // NOI18N

        labUkuranBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labUkuranBarang.setForeground(new java.awt.Color(8, 76, 77));
        labUkuranBarang.setText("Ukuran Barang");

        cbUkuranBarang.setBackground(new java.awt.Color(66, 132, 244));
        cbUkuranBarang.setForeground(new java.awt.Color(255, 255, 255));
        cbUkuranBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Ukuran Barang", "40%", "60%", "65%", "75%", "TKL", "Full Size" }));
        cbUkuranBarang.setName("cbUkuranBarang"); // NOI18N
        cbUkuranBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUkuranBarangActionPerformed(evt);
            }
        });

        labTanggal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTanggal.setForeground(new java.awt.Color(8, 76, 77));
        labTanggal.setText("Tanggal");

        txTanggal.setBackground(new java.awt.Color(66, 132, 244));
        txTanggal.setForeground(new java.awt.Color(255, 255, 255));
        txTanggal.setDateFormatString("dd-MM-yyyy");
        txTanggal.setName("txTanggal"); // NOI18N

        labTotal1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTotal1.setForeground(new java.awt.Color(8, 76, 77));
        labTotal1.setText("Diskon");

        diskon.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        diskon.setForeground(new java.awt.Color(8, 76, 77));
        diskon.setText("Diskon");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNamaBarang)
                            .addComponent(labUkuranBarang)
                            .addComponent(labNamaPelanggan)
                            .addComponent(labJenisBarang)
                            .addComponent(labTotal))
                        .addGap(139, 139, 139)
                        .addComponent(rbMembrane, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(rbMecha, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labIDTransaksi)
                                    .addComponent(labNamaPegawai))
                                .addGap(148, 148, 148)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txIDTransaksi)
                                    .addComponent(cbNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbUkuranBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labJmlBarang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txTotal)
                                    .addComponent(txJmlBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labTanggal)
                    .addComponent(labTotal1))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(diskon)
                    .addComponent(txTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(195, 195, 195))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labIDTransaksi)
                            .addComponent(txIDTransaksi))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNamaPegawai)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(labTanggal))
                    .addComponent(txTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNamaPelanggan))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labJenisBarang)
                            .addComponent(rbMembrane)
                            .addComponent(rbMecha))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUkuranBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labUkuranBarang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNamaBarang))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnUpdate)
                        .addGap(47, 47, 47)
                        .addComponent(btnDelete)
                        .addGap(44, 44, 44)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txJmlBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labJmlBarang))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTotal)
                    .addComponent(txTotal)
                    .addComponent(labTotal1)
                    .addComponent(diskon))
                .addGap(35, 35, 35))
        );

        tabPenjualan.addTab("Master", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tbTransaksi.setForeground(new java.awt.Color(43, 123, 123));
        tbTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbTransaksi.setName("tbTransaksi"); // NOI18N
        tbTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransaksiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbTransaksi);

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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1278, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 63, Short.MAX_VALUE)))
        );

        tabPenjualan.addTab("Tabel Transaksi", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tbDetail.setForeground(new java.awt.Color(43, 123, 123));
        tbDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDetail.setName("tbDetail"); // NOI18N
        tbDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetailMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbDetail);

        txID1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txID1.setName("txID1"); // NOI18N

        btnSearch1.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnSearch1.setForeground(new java.awt.Color(8, 76, 77));
        btnSearch1.setText("Search");
        btnSearch1.setName("btnSearch1"); // NOI18N
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        btnCl1.setBackground(new java.awt.Color(255, 255, 255));
        btnCl1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnCl1.setForeground(new java.awt.Color(8, 76, 77));
        btnCl1.setText("Clear");
        btnCl1.setName("btnCl1"); // NOI18N
        btnCl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCl1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(txID1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCl1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(416, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1278, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(564, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txID1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCl1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 63, Short.MAX_VALUE)))
        );

        tabPenjualan.addTab("Tabel Transaksi Barang", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnTambahData.setBackground(new java.awt.Color(0, 255, 0));
        btnTambahData.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnTambahData.setText("Tambah Data");
        btnTambahData.setName("btnTambahData"); // NOI18N
        btnTambahData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahDataActionPerformed(evt);
            }
        });

        labJenisBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJenisBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labJenisBarang1.setText("Jenis Keyboard");

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(251, 188, 5));
        jLabel8.setText("Transaksi Penjualan");

        btnClear1.setBackground(new java.awt.Color(204, 204, 204));
        btnClear1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear1.setText("Clear");
        btnClear1.setName("btnClear1"); // NOI18N
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        rbMembrane1.setBackground(new java.awt.Color(153, 153, 153));
        bgJenis1.add(rbMembrane1);
        rbMembrane1.setText("Membrane");
        rbMembrane1.setName("rbMembrane1"); // NOI18N
        rbMembrane1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMembrane1ActionPerformed(evt);
            }
        });

        rbMecha1.setBackground(new java.awt.Color(153, 153, 153));
        bgJenis1.add(rbMecha1);
        rbMecha1.setText("Mechanical");
        rbMecha1.setName("rbMecha1"); // NOI18N
        rbMecha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMecha1ActionPerformed(evt);
            }
        });

        labNamaPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPegawai1.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPegawai1.setText("Nama Pegawai");

        cbNamaPegawai1.setBackground(new java.awt.Color(66, 132, 244));
        cbNamaPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        cbNamaPegawai1.setName("cbNamaPegawai1"); // NOI18N

        labNamaPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan1.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPelanggan1.setText("Nama Pelanggan");

        cbNamaPelanggan1.setBackground(new java.awt.Color(66, 132, 244));
        cbNamaPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        cbNamaPelanggan1.setName("cbNamaPelanggan1"); // NOI18N

        labNamaBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labNamaBarang1.setText("Nama Barang");

        cbNamaBarang1.setBackground(new java.awt.Color(66, 132, 244));
        cbNamaBarang1.setForeground(new java.awt.Color(255, 255, 255));
        cbNamaBarang1.setName("cbNamaBarang1"); // NOI18N

        labJmlBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJmlBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labJmlBarang1.setText("Jumlah Barang");

        txJmlBarang1.setBackground(new java.awt.Color(66, 132, 244));
        txJmlBarang1.setForeground(new java.awt.Color(255, 255, 255));
        txJmlBarang1.setName("txJmlBarang1"); // NOI18N

        labUkuranBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labUkuranBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labUkuranBarang1.setText("Ukuran Barang");

        cbUkuranBarang1.setBackground(new java.awt.Color(66, 132, 244));
        cbUkuranBarang1.setForeground(new java.awt.Color(255, 255, 255));
        cbUkuranBarang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Ukuran Barang", "40%", "60%", "65%", "75%", "TKL", "Full Size" }));
        cbUkuranBarang1.setName("cbUkuranBarang1"); // NOI18N
        cbUkuranBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUkuranBarang1ActionPerformed(evt);
            }
        });

        labTanggal1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTanggal1.setForeground(new java.awt.Color(8, 76, 77));
        labTanggal1.setText("Tanggal");

        txTanggal1.setBackground(new java.awt.Color(66, 132, 244));
        txTanggal1.setForeground(new java.awt.Color(255, 255, 255));
        txTanggal1.setDateFormatString("dd-MM-yyyy");
        txTanggal1.setName("txTanggal1"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(436, 436, 436)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labJmlBarang1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txJmlBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labNamaBarang1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbNamaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labNamaPelanggan1)
                                    .addComponent(labJenisBarang1))
                                .addGap(130, 130, 130)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rbMembrane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(rbMecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbNamaPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labNamaPegawai1)
                                    .addComponent(labTanggal1))
                                .addGap(148, 148, 148)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txTanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbNamaPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labUkuranBarang1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbUkuranBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(133, 133, 133)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClear1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTambahData))))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTambahData)
                        .addGap(41, 41, 41)
                        .addComponent(btnClear1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txTanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labTanggal1))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNamaPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNamaPegawai1))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNamaPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNamaPelanggan1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labJenisBarang1)
                            .addComponent(rbMembrane1)
                            .addComponent(rbMecha1))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labUkuranBarang1)
                            .addComponent(cbUkuranBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNamaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labNamaBarang1))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txJmlBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labJmlBarang1))
                        .addGap(71, 71, 71))))
        );

        tabPenjualan.addTab("Tambah Data", jPanel2);

        getContentPane().add(tabPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

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
            
            String formatDate = "yyyy-MM-dd";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = String.valueOf(df.format(txTanggal.getDate()));

            conn = Koneksi.getKoneksi();

            SQL = "UPDATE transaksi SET "
            + "Nama_Pegawai = '" + cbNamaPegawai.getSelectedItem().toString() + "',"
            + "Nama_Pelanggan = '" + cbNamaPelanggan.getSelectedItem().toString() + "',"
            + "Tanggal_Transaksi = '" + tanggal + "'"
            + "WHERE ID_Transaksi = '" + txIDTransaksi.getText() + "'";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);
            
            SQL = "UPDATE detail_transaksi SET "
            + "Jenis_barang = '" + jenis + "',"
            + "Nama_barang = '" + cbNamaBarang.getSelectedItem().toString() + "',"
            + "Ukuran_barang = '" + cbUkuranBarang.getSelectedItem().toString() + "',"
            + "Kuantitas = '" + txJmlBarang.getText() + "',"
            + "total_harga = totalHarga(nama_Barang, kuantitas)"
            + "WHERE ID_Transaksi = '" + txIDTransaksi.getText() + "'";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);

            JOptionPane.showMessageDialog(this, "Success");
            showTabel();
            loadNamaPegawai();
            loadNamaPelanggan();
            clear();

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
                    
                SQL = "DELETE FROM detail_transaksi WHERE ID_transaksi = '" + txIDTransaksi.getText() + "'";
                cmd = conn.createStatement();
                cmd.executeUpdate(SQL);

                SQL1 = "DELETE FROM transaksi WHERE ID_transaksi = '" + txIDTransaksi.getText() + "'";
                cmd1 = conn.createStatement();
                cmd1.executeUpdate(SQL1);

                JOptionPane.showMessageDialog(this, "Success");
                showTabel();
                loadNamaPegawai();
                loadNamaPelanggan();
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

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTambahDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDataActionPerformed
        // TODO add your handling code here:
        try {
            String jenis;
            if(rbMembrane1.isSelected()) {
                jenis = "Membrane";
            } else {
                jenis = "Mechanical";
            }
            
            String formatDate = "yyyy-MM-dd";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = df.format(txTanggal1.getDate());

            conn = Koneksi.getKoneksi();

            SQL = "INSERT INTO transaksi (id_Transaksi, nama_pegawai, nama_pelanggan, tanggal_transaksi) VALUES ("
            + "generateID('transaksi'),"
            + "'" + cbNamaPegawai1.getSelectedItem().toString() + "',"
            + "'" + cbNamaPelanggan1.getSelectedItem().toString() + "',"
            + "'" + tanggal + "')";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);
            
            SQL = "INSERT INTO detail_transaksi (id_Transaksi, jenis_Barang, nama_Barang, ukuran_Barang, kuantitas, total_harga) VALUES ("
            + "generateID('transaksi2'),"
            + "'" + jenis + "',"
            + "'" + cbNamaBarang1.getSelectedItem().toString() + "',"
            + "'" + cbUkuranBarang1.getSelectedItem().toString() + "',"
            + txJmlBarang1.getText() + ","
            + "totalHarga(nama_Barang, kuantitas))";
            cmd = conn.createStatement();
            cmd.executeUpdate(SQL);
            
//            SQL2 = "SELECT totalHarga(nama_Barang, kuantitas) as Total from dual";
//            cmd2 = conn.createStatement();
//            rs2 = cmd2.executeQuery(SQL2);
//            String total = rs2.getInt();
            JOptionPane.showMessageDialog(this, "Success");

            clear();
            showTabel();
            loadNamaPegawai();
            loadNamaPelanggan();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahDataActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        showTabel();
        loadNamaPegawai();
        loadNamaPelanggan();
    }//GEN-LAST:event_formInternalFrameOpened

    private void tbTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransaksiMouseClicked
        // TODO add your handling code here:
        int index = tbTransaksi.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            
            txIDTransaksi.setText(tbTransaksi.getValueAt(index, 0).toString());
            cbNamaPegawai.setSelectedItem(tbTransaksi.getValueAt(index, 1).toString());
            cbNamaPelanggan.setSelectedItem(tbTransaksi.getValueAt(index, 2).toString());
            try {
                String date = tbTransaksi.getValueAt(index, 3).toString();
                java.util.Date date2 = new SimpleDateFormat("dd MMMM yyyy").parse(date);
                txTanggal.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            if(tbDetail.getValueAt(index, 1).toString().equals("Mechanical")) {
                rbMecha.setSelected(true);
            } else {
                rbMembrane.setSelected(true);
            }
            cbNamaBarang.setSelectedItem(tbDetail.getValueAt(index, 2).toString());
            cbUkuranBarang.setSelectedItem(tbDetail.getValueAt(index, 3).toString());
            txJmlBarang.setText(tbDetail.getValueAt(index, 4).toString());
            txTotal.setText(tbDetail.getValueAt(index, 5).toString());
            if (Integer.parseInt(txTotal.getText())>200000){
                diskon.setText("20%");
            } else{
                diskon.setText("-");
            }
        }
    }//GEN-LAST:event_tbTransaksiMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();

            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT * FROM transaksi WHERE id_Transaksi = '" + txID.getText() + "'");

            //Setting kolom dari DefaultTableModel
            tmTransaksi = new DefaultTableModel(new String[] {"ID TRANSAKSI", "NAMA PEGAWAI", "NAMA PELANGGAN", "TANGGAL"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmTransaksi.addRow(new Object[] {rs.getString("id_transaksi"), rs.getString("nama_Pegawai"), rs.getString("Nama_Pelanggan"), 
                        rs.getString("tanggal_transaksi")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
        tbTransaksi.setDefaultEditor(Object.class, null);
        tbTransaksi.setModel(tmTransaksi);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClActionPerformed
        // TODO add your handling code here:
        txID.setText(null);
        showTabel();
    }//GEN-LAST:event_btnClActionPerformed

    private void tbDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetailMouseClicked
        // TODO add your handling code here:
        int index = tbTransaksi.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            
            txIDTransaksi.setText(tbTransaksi.getValueAt(index, 0).toString());
            cbNamaPegawai.setSelectedItem(tbTransaksi.getValueAt(index, 1).toString());
            cbNamaPelanggan.setSelectedItem(tbTransaksi.getValueAt(index, 2).toString());
            try {
                String date = tbTransaksi.getValueAt(index, 3).toString();
                java.util.Date date2 = new SimpleDateFormat("dd MMMM yyyy").parse(date);
                txTanggal.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            if(tbDetail.getValueAt(index, 1).toString().equals("Mechanical")) {
                rbMecha.setSelected(true);
            } else {
                rbMembrane.setSelected(true);
            }
            cbNamaBarang.setSelectedItem(tbDetail.getValueAt(index, 2).toString());
            cbUkuranBarang.setSelectedItem(tbDetail.getValueAt(index, 3).toString());
            txJmlBarang.setText(tbDetail.getValueAt(index, 4).toString());
            txTotal.setText(tbDetail.getValueAt(index, 5).toString());
            if (Integer.parseInt(txTotal.getText())>200000){
                diskon.setText("20%");
            } else {
                diskon.setText("-");
            }
        }
    }//GEN-LAST:event_tbDetailMouseClicked

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();

            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT * FROM detail_transaksi WHERE id_Transaksi = '" + txID1.getText() + "'");

            //Setting kolom dari DefaultTableModel
            tmDetail = new DefaultTableModel(new String[] {"ID TRANSAKSI", "JENIS BARANG", "NAMA BARANG", "UKURAN BARANG", "KUANTITAS", "TOTAL HARGA"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmDetail.addRow(new Object[] {rs.getString("id_transaksi"), rs.getString("jenis_Barang"), rs.getString("Nama_Barang"), 
                        rs.getString("ukuran_Barang"), rs.getInt("Kuantitas"), rs.getInt("Total_Harga")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            tbDetail.setDefaultEditor(Object.class, null);
            tbDetail.setModel(tmDetail);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnCl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCl1ActionPerformed
        // TODO add your handling code here:
        txID1.setText(null);
        showTabel();
    }//GEN-LAST:event_btnCl1ActionPerformed

    private void rbMechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMechaActionPerformed
        // TODO add your handling code here:
        jenisBarang(1);
    }//GEN-LAST:event_rbMechaActionPerformed

    private void rbMembraneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMembraneActionPerformed
        // TODO add your handling code here:
        jenisBarang(2);
    }//GEN-LAST:event_rbMembraneActionPerformed

    private void cbUkuranBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUkuranBarangActionPerformed
        // TODO add your handling code here:
        if(rbMecha.isSelected()) {
            ukuranBarang(1);
        } else if(rbMembrane.isSelected()) {
            ukuranBarang(2);
        }
    }//GEN-LAST:event_cbUkuranBarangActionPerformed

    private void rbMembrane1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMembrane1ActionPerformed
        // TODO add your handling code here:
        jenisBarang(2);
    }//GEN-LAST:event_rbMembrane1ActionPerformed

    private void rbMecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMecha1ActionPerformed
        // TODO add your handling code here:
        jenisBarang(1);
    }//GEN-LAST:event_rbMecha1ActionPerformed

    private void cbUkuranBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUkuranBarang1ActionPerformed
        // TODO add your handling code here:
        if(rbMecha1.isSelected()) {
            ukuranBarang(3);
        } else if(rbMembrane1.isSelected()) {
            ukuranBarang(4);
        }
    }//GEN-LAST:event_cbUkuranBarang1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgJenis;
    private javax.swing.ButtonGroup bgJenis1;
    private javax.swing.JButton btnCl;
    private javax.swing.JButton btnCl1;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnTambahData;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbNamaBarang;
    private javax.swing.JComboBox<String> cbNamaBarang1;
    private javax.swing.JComboBox<String> cbNamaPegawai;
    private javax.swing.JComboBox<String> cbNamaPegawai1;
    private javax.swing.JComboBox<String> cbNamaPelanggan;
    private javax.swing.JComboBox<String> cbNamaPelanggan1;
    private javax.swing.JComboBox<String> cbUkuranBarang;
    private javax.swing.JComboBox<String> cbUkuranBarang1;
    private javax.swing.JLabel diskon;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labIDTransaksi;
    private javax.swing.JLabel labJenisBarang;
    private javax.swing.JLabel labJenisBarang1;
    private javax.swing.JLabel labJmlBarang;
    private javax.swing.JLabel labJmlBarang1;
    private javax.swing.JLabel labNamaBarang;
    private javax.swing.JLabel labNamaBarang1;
    private javax.swing.JLabel labNamaPegawai;
    private javax.swing.JLabel labNamaPegawai1;
    private javax.swing.JLabel labNamaPelanggan;
    private javax.swing.JLabel labNamaPelanggan1;
    private javax.swing.JLabel labTanggal;
    private javax.swing.JLabel labTanggal1;
    private javax.swing.JLabel labTotal;
    private javax.swing.JLabel labTotal1;
    private javax.swing.JLabel labUkuranBarang;
    private javax.swing.JLabel labUkuranBarang1;
    private javax.swing.JRadioButton rbMecha;
    private javax.swing.JRadioButton rbMecha1;
    private javax.swing.JRadioButton rbMembrane;
    private javax.swing.JRadioButton rbMembrane1;
    private javax.swing.JTabbedPane tabPenjualan;
    private javax.swing.JTable tbDetail;
    private javax.swing.JTable tbTransaksi;
    private javax.swing.JTextField txID;
    private javax.swing.JTextField txID1;
    private javax.swing.JLabel txIDTransaksi;
    private javax.swing.JTextField txJmlBarang;
    private javax.swing.JTextField txJmlBarang1;
    private com.toedter.calendar.JDateChooser txTanggal;
    private com.toedter.calendar.JDateChooser txTanggal1;
    private javax.swing.JLabel txTotal;
    // End of variables declaration//GEN-END:variables
}
