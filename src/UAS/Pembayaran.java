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
public class Pembayaran extends javax.swing.JInternalFrame {

    /**
     * Creates new form Pembayaran
     */
    
    Connection conn;
    Statement cmd;
    ResultSet rs;
    DefaultTableModel tmPembayaran;
    String SQL;
    
    Statement cmd1;
    ResultSet rs1;
    
    public Pembayaran() {
        initComponents();
        
        loadIDTransaksi();
    }
    
    private void loadIDTransaksi(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM Transaksi";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDTransaksi.removeAllItems();
            cbIDTransaksi.addItem("Pilih ID Transaksi");
            cbIDTransaksi1.removeAllItems();
            cbIDTransaksi1.addItem("Pilih ID Transaksi");
            while(rs.next()){
                String cid = rs.getString("ID_Transaksi");
                cbIDTransaksi.addItem(cid);
                cbIDTransaksi1.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void showTabel() {
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT * FROM Pembayaran ORDER BY ID_Pembayaran ASC");
            
            //Setting kolom dari DefaultTableModel
            tmPembayaran = new DefaultTableModel(new String[] {"ID PEMBAYARAN", "ID TRANSAKSI", "NAMA PELANGGAN", "NAMA BARANG", 
                "JUMLAH", "TOTAL HARGA", "UANG PEMBAYARAN", "KEMBALIAN"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmPembayaran.addRow(new Object[] {rs.getString("ID_Pembayaran"), rs.getString("id_transaksi"), rs.getString("Nama_Pelanggan"), rs.getString("Nama_Barang"), 
                        rs.getInt("Kuantitas"), rs.getInt("Total_Harga"), rs.getInt("Uang_Pembayaran"), rs.getInt("Kembalian")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbPembayaran.setDefaultEditor(Object.class, null);
            
            tbPembayaran.setModel(tmPembayaran);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void clear() {
        txIDPembayaran.setText("ID Pembayaran");
        cbIDTransaksi.setSelectedIndex(0);
        txPelanggan.setText("Nama Pelanggan");
        txBarang.setText("Nama Barang");
        txJml.setText("Jumlah Barang");
        txTotal.setText("Total Harga");
        txBayar.setText(null);
        txKembalian.setText("Kembalian");
        
        cbIDTransaksi1.setSelectedIndex(0);
        txPelanggan1.setText("Nama Pelanggan");
        txBarang1.setText("Nama Barang");
        txJml1.setText("Jumlah Barang");
        txTotal1.setText("Total Harga");
        txBayar1.setText(null);
    }
    
    private String cekPembayaran(int angka) {
        String pembayaran = null;
        try {
            
            switch(angka) {
                
                case 1:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT nama_pelanggan FROM Transaksi WHERE ID_Transaksi = '" + cbIDTransaksi.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pembayaran = rs1.getString("nama_pelanggan");
                        }
                    }
                break;
                
                case 2:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT nama_barang FROM Detail_Transaksi WHERE ID_Transaksi = '" + cbIDTransaksi.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pembayaran = rs1.getString("nama_barang");
                        }
                    }
                break;
                
                case 3:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT kuantitas FROM Detail_Transaksi WHERE ID_Transaksi = '" + cbIDTransaksi.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pembayaran = rs1.getString("kuantitas");
                        }
                    }
                break;
                
                case 4:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT total_harga FROM Detail_Transaksi WHERE ID_Transaksi = '" + cbIDTransaksi.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pembayaran = rs1.getString("total_harga");
                        }
                    }
                break;
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pembayaran;
    }
    
    private String cekPembayaran1(int angka) {
        String pembayaran = null;
        try {
            
            switch(angka) {
                
                case 1:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT nama_pelanggan FROM Transaksi WHERE ID_Transaksi = '" + cbIDTransaksi1.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pembayaran = rs1.getString("nama_pelanggan");
                        }
                    }
                break;
                
                case 2:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT nama_barang FROM Detail_Transaksi WHERE ID_Transaksi = '" + cbIDTransaksi1.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pembayaran = rs1.getString("nama_barang");
                        }
                    }
                break;
                
                case 3:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT kuantitas FROM Detail_Transaksi WHERE ID_Transaksi = '" + cbIDTransaksi1.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pembayaran = rs1.getString("kuantitas");
                        }
                    }
                break;
                
                case 4:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT total_harga FROM Detail_Transaksi WHERE ID_Transaksi = '" + cbIDTransaksi1.getSelectedItem().toString() + "'");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pembayaran = rs1.getString("total_harga");
                        }
                    }
                break;
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pembayaran;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPembayaran = new javax.swing.JTabbedPane();
        tabMaster = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        labIDTransaksi = new javax.swing.JLabel();
        labNamaPelanggan = new javax.swing.JLabel();
        labNamaBarang = new javax.swing.JLabel();
        labJmlBarang = new javax.swing.JLabel();
        labTotal = new javax.swing.JLabel();
        txTotal = new javax.swing.JLabel();
        labTotal1 = new javax.swing.JLabel();
        txBayar = new javax.swing.JTextField();
        labTotal2 = new javax.swing.JLabel();
        txKembalian = new javax.swing.JLabel();
        txPelanggan = new javax.swing.JLabel();
        txBarang = new javax.swing.JLabel();
        txJml = new javax.swing.JLabel();
        labIDPembayaran = new javax.swing.JLabel();
        cbIDTransaksi = new javax.swing.JComboBox<>();
        txIDPembayaran = new javax.swing.JLabel();
        tabTabel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPembayaran = new javax.swing.JTable();
        txID = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCl = new javax.swing.JButton();
        tabTambahData = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnClear1 = new javax.swing.JButton();
        labIDTransaksi1 = new javax.swing.JLabel();
        labNamaPelanggan1 = new javax.swing.JLabel();
        labNamaBarang1 = new javax.swing.JLabel();
        labJmlBarang1 = new javax.swing.JLabel();
        labTotal3 = new javax.swing.JLabel();
        txTotal1 = new javax.swing.JLabel();
        labTotal4 = new javax.swing.JLabel();
        txBayar1 = new javax.swing.JTextField();
        txPelanggan1 = new javax.swing.JLabel();
        txBarang1 = new javax.swing.JLabel();
        txJml1 = new javax.swing.JLabel();
        cbIDTransaksi1 = new javax.swing.JComboBox<>();
        btnTambahData = new javax.swing.JButton();

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

        tabPembayaran.setName("tabPembayaran"); // NOI18N

        tabMaster.setBackground(new java.awt.Color(255, 255, 255));
        tabMaster.setName("tabMaster"); // NOI18N

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

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(251, 188, 5));
        jLabel7.setText("Pembayaran");

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

        labIDTransaksi.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDTransaksi.setForeground(new java.awt.Color(8, 76, 77));
        labIDTransaksi.setText("ID Transaksi");

        labNamaPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPelanggan.setText("Nama Pelanggan");

        labNamaBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang.setForeground(new java.awt.Color(8, 76, 77));
        labNamaBarang.setText("Nama Barang");

        labJmlBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJmlBarang.setForeground(new java.awt.Color(8, 76, 77));
        labJmlBarang.setText("Jumlah Barang");

        labTotal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTotal.setForeground(new java.awt.Color(8, 76, 77));
        labTotal.setText("Total Harga");

        txTotal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txTotal.setForeground(new java.awt.Color(8, 76, 77));
        txTotal.setText("Total Harga");

        labTotal1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTotal1.setForeground(new java.awt.Color(8, 76, 77));
        labTotal1.setText("Uang Pembayaran");

        txBayar.setBackground(new java.awt.Color(66, 132, 244));
        txBayar.setForeground(new java.awt.Color(255, 255, 255));
        txBayar.setName("txBayar"); // NOI18N

        labTotal2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTotal2.setForeground(new java.awt.Color(8, 76, 77));
        labTotal2.setText("Kembalian");

        txKembalian.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txKembalian.setForeground(new java.awt.Color(8, 76, 77));
        txKembalian.setText("Kembalian");

        txPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txPelanggan.setForeground(new java.awt.Color(8, 76, 77));
        txPelanggan.setText("Nama Pelanggan");

        txBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txBarang.setForeground(new java.awt.Color(8, 76, 77));
        txBarang.setText("Nama Barang");

        txJml.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txJml.setForeground(new java.awt.Color(8, 76, 77));
        txJml.setText("Jumlah Barang");

        labIDPembayaran.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDPembayaran.setForeground(new java.awt.Color(8, 76, 77));
        labIDPembayaran.setText("ID Pembayaran");

        cbIDTransaksi.setBackground(new java.awt.Color(66, 132, 244));
        cbIDTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        cbIDTransaksi.setName("cbIDTransaksi"); // NOI18N
        cbIDTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDTransaksiActionPerformed(evt);
            }
        });

        txIDPembayaran.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txIDPembayaran.setForeground(new java.awt.Color(8, 76, 77));
        txIDPembayaran.setText("ID Pembayaran");
        txIDPembayaran.setName("txIDPembayaran"); // NOI18N

        javax.swing.GroupLayout tabMasterLayout = new javax.swing.GroupLayout(tabMaster);
        tabMaster.setLayout(tabMasterLayout);
        tabMasterLayout.setHorizontalGroup(
            tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMasterLayout.createSequentialGroup()
                .addGap(501, 501, 501)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tabMasterLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabMasterLayout.createSequentialGroup()
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labTotal2)
                            .addGroup(tabMasterLayout.createSequentialGroup()
                                .addGap(282, 282, 282)
                                .addComponent(txKembalian)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabMasterLayout.createSequentialGroup()
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labTotal1)
                            .addGroup(tabMasterLayout.createSequentialGroup()
                                .addGap(282, 282, 282)
                                .addComponent(txBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabMasterLayout.createSequentialGroup()
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tabMasterLayout.createSequentialGroup()
                                .addComponent(labNamaBarang)
                                .addGap(161, 161, 161)
                                .addComponent(txBarang))
                            .addComponent(labTotal)
                            .addGroup(tabMasterLayout.createSequentialGroup()
                                .addComponent(labJmlBarang)
                                .addGap(148, 148, 148)
                                .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txJml)
                                    .addComponent(txTotal)))
                            .addGroup(tabMasterLayout.createSequentialGroup()
                                .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labIDTransaksi)
                                    .addComponent(labNamaPelanggan)
                                    .addComponent(labIDPembayaran))
                                .addGap(130, 130, 130)
                                .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txPelanggan)
                                    .addComponent(cbIDTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txIDPembayaran))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(421, 421, 421))))
        );
        tabMasterLayout.setVerticalGroup(
            tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabMasterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabMasterLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate)
                        .addGap(47, 47, 47)
                        .addComponent(btnDelete)
                        .addGap(44, 44, 44)
                        .addComponent(btnClear)
                        .addGap(226, 226, 226))
                    .addGroup(tabMasterLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labIDPembayaran)
                            .addComponent(txIDPembayaran))
                        .addGap(33, 33, 33)
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labIDTransaksi)
                            .addComponent(cbIDTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labNamaPelanggan)
                            .addComponent(txPelanggan))
                        .addGap(42, 42, 42)
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labNamaBarang)
                            .addComponent(txBarang))
                        .addGap(45, 45, 45)
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labJmlBarang)
                            .addComponent(txJml))
                        .addGap(44, 44, 44)
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labTotal)
                            .addComponent(txTotal))
                        .addGap(38, 38, 38)
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labTotal1)
                            .addComponent(txBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(tabMasterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labTotal2)
                            .addComponent(txKembalian))
                        .addContainerGap(63, Short.MAX_VALUE))))
        );

        tabPembayaran.addTab("Master", tabMaster);

        tabTabel.setBackground(new java.awt.Color(255, 255, 255));
        tabTabel.setName("tabTabel"); // NOI18N

        tbPembayaran.setBackground(new java.awt.Color(255, 255, 255));
        tbPembayaran.setForeground(new java.awt.Color(43, 123, 123));
        tbPembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPembayaran.setName("tbPembayaran"); // NOI18N
        tbPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPembayaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPembayaran);

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

        javax.swing.GroupLayout tabTabelLayout = new javax.swing.GroupLayout(tabTabel);
        tabTabel.setLayout(tabTabelLayout);
        tabTabelLayout.setHorizontalGroup(
            tabTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabTabelLayout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(txID, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(416, Short.MAX_VALUE))
            .addGroup(tabTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1278, Short.MAX_VALUE))
        );
        tabTabelLayout.setVerticalGroup(
            tabTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabTabelLayout.createSequentialGroup()
                .addContainerGap(564, Short.MAX_VALUE)
                .addGroup(tabTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
            .addGroup(tabTabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabTabelLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 63, Short.MAX_VALUE)))
        );

        tabPembayaran.addTab("Tabel", tabTabel);

        tabTambahData.setBackground(new java.awt.Color(255, 255, 255));
        tabTambahData.setName("tabTambahData"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(251, 188, 5));
        jLabel8.setText("Pembayaran");

        btnClear1.setBackground(new java.awt.Color(204, 204, 204));
        btnClear1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(0, 0, 0));
        btnClear1.setText("Clear");
        btnClear1.setName("btnClear1"); // NOI18N
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        labIDTransaksi1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDTransaksi1.setForeground(new java.awt.Color(8, 76, 77));
        labIDTransaksi1.setText("ID Transaksi");

        labNamaPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan1.setForeground(new java.awt.Color(8, 76, 77));
        labNamaPelanggan1.setText("Nama Pelanggan");

        labNamaBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labNamaBarang1.setText("Nama Barang");

        labJmlBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJmlBarang1.setForeground(new java.awt.Color(8, 76, 77));
        labJmlBarang1.setText("Jumlah Barang");

        labTotal3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTotal3.setForeground(new java.awt.Color(8, 76, 77));
        labTotal3.setText("Total Harga");

        txTotal1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txTotal1.setForeground(new java.awt.Color(8, 76, 77));
        txTotal1.setText("Total Harga");

        labTotal4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTotal4.setForeground(new java.awt.Color(8, 76, 77));
        labTotal4.setText("Uang Pembayaran");

        txBayar1.setBackground(new java.awt.Color(66, 132, 244));
        txBayar1.setForeground(new java.awt.Color(255, 255, 255));
        txBayar1.setName("txBayar1"); // NOI18N

        txPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txPelanggan1.setForeground(new java.awt.Color(8, 76, 77));
        txPelanggan1.setText("Nama Pelanggan");

        txBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txBarang1.setForeground(new java.awt.Color(8, 76, 77));
        txBarang1.setText("Nama Barang");

        txJml1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txJml1.setForeground(new java.awt.Color(8, 76, 77));
        txJml1.setText("Jumlah Barang");

        cbIDTransaksi1.setBackground(new java.awt.Color(66, 132, 244));
        cbIDTransaksi1.setForeground(new java.awt.Color(255, 255, 255));
        cbIDTransaksi1.setName("cbIDTransaksi1"); // NOI18N
        cbIDTransaksi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDTransaksi1ActionPerformed(evt);
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

        javax.swing.GroupLayout tabTambahDataLayout = new javax.swing.GroupLayout(tabTambahData);
        tabTambahData.setLayout(tabTambahDataLayout);
        tabTambahDataLayout.setHorizontalGroup(
            tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabTambahDataLayout.createSequentialGroup()
                .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabTambahDataLayout.createSequentialGroup()
                        .addGap(501, 501, 501)
                        .addComponent(jLabel8))
                    .addGroup(tabTambahDataLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabTambahDataLayout.createSequentialGroup()
                                .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labIDTransaksi1)
                                    .addComponent(labNamaPelanggan1))
                                .addGap(130, 130, 130)
                                .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txPelanggan1)
                                    .addComponent(cbIDTransaksi1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labTotal4)
                            .addGroup(tabTambahDataLayout.createSequentialGroup()
                                .addComponent(labNamaBarang1)
                                .addGap(161, 161, 161)
                                .addComponent(txBarang1))
                            .addGroup(tabTambahDataLayout.createSequentialGroup()
                                .addComponent(labJmlBarang1)
                                .addGap(148, 148, 148)
                                .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txBayar1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(tabTambahDataLayout.createSequentialGroup()
                                        .addComponent(txJml1)
                                        .addGap(215, 215, 215)
                                        .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnTambahData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnClear1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(labTotal3)
                            .addGroup(tabTambahDataLayout.createSequentialGroup()
                                .addGap(282, 282, 282)
                                .addComponent(txTotal1)))))
                .addContainerGap(455, Short.MAX_VALUE))
        );
        tabTambahDataLayout.setVerticalGroup(
            tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabTambahDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDTransaksi1)
                    .addComponent(cbIDTransaksi1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNamaPelanggan1)
                    .addComponent(txPelanggan1))
                .addGap(42, 42, 42)
                .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabTambahDataLayout.createSequentialGroup()
                        .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labNamaBarang1)
                            .addComponent(txBarang1))
                        .addGap(45, 45, 45)
                        .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labJmlBarang1)
                            .addComponent(txJml1))
                        .addGap(42, 42, 42)
                        .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labTotal3)
                            .addComponent(txTotal1))
                        .addGap(36, 36, 36)
                        .addGroup(tabTambahDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labTotal4)
                            .addComponent(txBayar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tabTambahDataLayout.createSequentialGroup()
                        .addComponent(btnTambahData)
                        .addGap(30, 30, 30)
                        .addComponent(btnClear1)))
                .addGap(187, 187, 187))
        );

        tabPembayaran.addTab("Tambah Data", tabTambahData);

        getContentPane().add(tabPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPembayaranMouseClicked
        // TODO add your handling code here:
        int index = tbPembayaran.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {

            txIDPembayaran.setText(tbPembayaran.getValueAt(index, 0).toString());
            cbIDTransaksi.setSelectedItem(tbPembayaran.getValueAt(index, 1).toString());
            txPelanggan.setText(tbPembayaran.getValueAt(index, 2).toString());
            txBarang.setText(tbPembayaran.getValueAt(index, 3).toString());
            txJml.setText(tbPembayaran.getValueAt(index, 4).toString());
            txTotal.setText(tbPembayaran.getValueAt(index, 5).toString());
            txBayar.setText(tbPembayaran.getValueAt(index, 6).toString());
            txKembalian.setText(tbPembayaran.getValueAt(index, 7).toString());
        }
    }//GEN-LAST:event_tbPembayaranMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();

            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT * FROM Pembayaran WHERE ID_Pembayaran = '" + txID.getText() + "'");

            //Setting kolom dari DefaultTableModel
            tmPembayaran = new DefaultTableModel(new String[] {"ID PEMBAYARAN", "ID TRANSAKSI", "NAMA PELANGGAN", "NAMA BARANG", "JUMLAH", "TOTAL HARGA", "UANG PEMBAYARAN", "KEMBALIAN"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmPembayaran.addRow(new Object[] {rs.getString("ID_Pembayaran"), rs.getString("id_transaksi"), rs.getString("Nama_Pelanggan"), rs.getString("Nama_Barang"), 
                        rs.getInt("Kuantitas"), rs.getInt("Total_Harga"), rs.getInt("Uang_Pembayaran"), rs.getInt("Kembalian")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbPembayaran.setDefaultEditor(Object.class, null);
            
            tbPembayaran.setModel(tmPembayaran);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClActionPerformed
        // TODO add your handling code here:
        txID.setText(null);
        showTabel();
    }//GEN-LAST:event_btnClActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();
            
            int uang = Integer.parseInt(txBayar.getText());
            int total = Integer.parseInt(txTotal.getText());
            
            if(uang < total) {
                JOptionPane.showMessageDialog(this, "Uang tidak cukup");
            } else {
                SQL = "UPDATE Pembayaran SET "
                + "ID_Transaksi = '" + cbIDTransaksi.getSelectedItem().toString() + "',"
                + "Nama_Pelanggan = '" + txPelanggan.getText() + "',"
                + "Nama_Barang = '" + txBarang.getText() + "',"
                + "Kuantitas = '" + txJml.getText() + "',"
                + "Total_Harga = '" + txTotal.getText() + "',"
                + "Uang_Pembayaran = '" + txBayar.getText() + "',"
                + "Kembalian = uangKembalian(Uang_Pembayaran, Total_Harga)"
                + "WHERE ID_Pembayaran = '" + txIDPembayaran.getText() + "'";
                cmd = conn.createStatement();
                cmd.executeUpdate(SQL);

                JOptionPane.showMessageDialog(this, "Success");
                showTabel();
                clear();
            }

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

                SQL = "DELETE FROM Pembayaran WHERE ID_Pembayaran = '" + txIDPembayaran.getText() + "'";
                cmd = conn.createStatement();
                cmd.executeUpdate(SQL);

                JOptionPane.showMessageDialog(this, "Success");
                showTabel();
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

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnTambahDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDataActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();
            
            int uang = Integer.parseInt(txBayar1.getText());
            int total = Integer.parseInt(txTotal1.getText());
            
            if(uang < total) {
                JOptionPane.showMessageDialog(this, "Uang tidak cukup");
            } else {
                SQL = "INSERT INTO Pembayaran (id_pembayaran, id_Transaksi, nama_pelanggan, Nama_Barang, Kuantitas, Total_Harga, Uang_Pembayaran, Kembalian) VALUES ("
                + "generateID('pembayaran'),"
                + "'" + cbIDTransaksi1.getSelectedItem().toString() + "',"
                + "'" + txPelanggan1.getText() + "',"
                + "'" + txBarang1.getText() + "',"
                + txJml1.getText() + ","
                + txTotal1.getText() + ","
                + txBayar1.getText() + ","
                + "uangKembalian(Uang_Pembayaran, Total_Harga))";
                cmd = conn.createStatement();
                cmd.executeUpdate(SQL);

                JOptionPane.showMessageDialog(this, "Success");

                clear();
                showTabel();
            }            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahDataActionPerformed

    private void cbIDTransaksi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDTransaksi1ActionPerformed
        // TODO add your handling code here:
        if(cbIDTransaksi1.getSelectedIndex() == 0) {
            txPelanggan1.setText("Nama Pelanggan");
            txBarang1.setText("Nama Barang");
            txJml1.setText("Jumlah Barang");
            txTotal1.setText("Total Harga");
        } else {
            txPelanggan1.setText(cekPembayaran1(1));
            txBarang1.setText(cekPembayaran1(2));
            txJml1.setText(cekPembayaran1(3));
            txTotal1.setText(cekPembayaran1(4));
        }
    }//GEN-LAST:event_cbIDTransaksi1ActionPerformed

    private void cbIDTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDTransaksiActionPerformed
        // TODO add your handling code here:
        if(cbIDTransaksi.getSelectedIndex() == 0) {
            txPelanggan.setText("Nama Pelanggan");
            txBarang.setText("Nama Barang");
            txJml.setText("Jumlah Barang");
            txTotal.setText("Total Harga");
        } else {
            txPelanggan.setText(cekPembayaran(1));
            txBarang.setText(cekPembayaran(2));
            txJml.setText(cekPembayaran(3));
            txTotal.setText(cekPembayaran(4));
        }
    }//GEN-LAST:event_cbIDTransaksiActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        showTabel();
        loadIDTransaksi();
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCl;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTambahData;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbIDTransaksi;
    private javax.swing.JComboBox<String> cbIDTransaksi1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labIDPembayaran;
    private javax.swing.JLabel labIDTransaksi;
    private javax.swing.JLabel labIDTransaksi1;
    private javax.swing.JLabel labJmlBarang;
    private javax.swing.JLabel labJmlBarang1;
    private javax.swing.JLabel labNamaBarang;
    private javax.swing.JLabel labNamaBarang1;
    private javax.swing.JLabel labNamaPelanggan;
    private javax.swing.JLabel labNamaPelanggan1;
    private javax.swing.JLabel labTotal;
    private javax.swing.JLabel labTotal1;
    private javax.swing.JLabel labTotal2;
    private javax.swing.JLabel labTotal3;
    private javax.swing.JLabel labTotal4;
    private javax.swing.JPanel tabMaster;
    private javax.swing.JTabbedPane tabPembayaran;
    private javax.swing.JPanel tabTabel;
    private javax.swing.JPanel tabTambahData;
    private javax.swing.JTable tbPembayaran;
    private javax.swing.JLabel txBarang;
    private javax.swing.JLabel txBarang1;
    private javax.swing.JTextField txBayar;
    private javax.swing.JTextField txBayar1;
    private javax.swing.JTextField txID;
    private javax.swing.JLabel txIDPembayaran;
    private javax.swing.JLabel txJml;
    private javax.swing.JLabel txJml1;
    private javax.swing.JLabel txKembalian;
    private javax.swing.JLabel txPelanggan;
    private javax.swing.JLabel txPelanggan1;
    private javax.swing.JLabel txTotal;
    private javax.swing.JLabel txTotal1;
    // End of variables declaration//GEN-END:variables
}
