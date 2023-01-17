/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UAS;

import UAS.MainProject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Window;
import java.awt.Dimension;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.core.MouseButton;

/**
 *
 * @author akbar
 */
public class MainProjectTest {
    
    FrameFixture win;
    
    public MainProjectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        win = new FrameFixture(new MainProject());
        MainProject mp = new MainProject();
        Dimension ukuran = new Dimension(mp.getSize());
        win.show(ukuran);
    }
    
    @Test
    public void testPegawai() {
        System.out.println("=====Tambah Pegawai=====");
        win.button("btnPegawai").click();
        win.tabbedPane("tabPegawai").selectTab("Tambah Data");
        win.textBox("txNamaPegawai1").enterText("Alfa");
        win.robot.moveMouse(500, 473);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.robot.enterText("31-12-2001");
        win.textBox("txAlamatPegawai1").enterText("AAA");
        win.textBox("txTeleponPegawai1").enterText("1234");
        win.radioButton("rbLaki1").click();
        win.button("btnTambahData").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Search Pegawai=====");
        win.tabbedPane("tabPegawai").selectTab("Tabel");
        win.textBox("txID").enterText("A00006");
        win.button("btnSearch").click();
        win.button("btnCl").click();
        
        System.out.println("=====Update Pegawai=====");
        win.tabbedPane("tabPegawai").selectTab("Master");
        win.comboBox("cbIDPegawai").selectItem("A00006");
        win.textBox("txNamaPegawai").setText("");
        win.textBox("txNamaPegawai").enterText("Zulfa");
        win.textBox("txAlamatPegawai").setText("");
        win.textBox("txAlamatPegawai").enterText("DSZ");
        win.textBox("txTeleponPegawai").setText("");
        win.textBox("txTeleponPegawai").enterText("0826");
        win.radioButton("rbPerempuan").click();
        win.button("btnUpdate").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Clear Pegawai=====");
        win.button("btnClear").click();
        
        System.out.println("=====Delete Pegawai=====");
        win.comboBox().click().selectItem("A00006");
        win.button("btnDelete").click();
        win.optionPane().yesButton().click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
    }
    
    @After
    public void clean() {
        win.cleanUp();
    }
    
    @Test
    public void testPelanggan() {
        System.out.println("\n=====Tambah Pelanggan=====");
        win.button("btnPelanggan").click();
        win.tabbedPane("tabPelanggan").selectTab("Tambah Data");
        win.textBox("txNamaPelanggan1").enterText("Beta");
        win.robot.moveMouse(500, 473);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.robot.enterText("31-12-2001");
        win.textBox("txAlamatPelanggan1").enterText("BBB");
        win.textBox("txTeleponPelanggan1").enterText("4321");
        win.radioButton("rbPerempuan1").click();
        win.button("btnTambahData").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Search Pelanggan=====");
        win.tabbedPane("tabPelanggan").selectTab("Tabel");
        win.textBox("txID").enterText("B00006");
        win.button("btnSearch").click();
        win.button("btnCl").click();
        
        System.out.println("=====Update Pelanggan=====");
        win.tabbedPane("tabPelanggan").selectTab("Master");
        win.comboBox("cbIDPelanggan").selectItem("B00006");
        win.textBox("txNamaPelanggan").setText("");
        win.textBox("txNamaPelanggan").enterText("Zulfi");
        win.textBox("txAlamatPelanggan").setText("");
        win.textBox("txAlamatPelanggan").enterText("DSZ");
        win.textBox("txTeleponPelanggan").setText("");
        win.textBox("txTeleponPelanggan").enterText("0945");
        win.radioButton("rbLaki").click();
        win.button("btnUpdate").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Clear Pelanggan=====");
        win.button("btnClear").click();
        
        System.out.println("=====Delete Pelanggan=====");
        win.comboBox().click().selectItem("B00006");
        win.button("btnDelete").click();
        win.optionPane().yesButton().click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
    }
    
    @After
    public void clean1() {
        win.cleanUp();
    }
    
    @Test
    public void testBarang() {
        System.out.println("\n=====Tambah Barang=====");
        win.button("btnBarang").click();
        win.tabbedPane("tabBarang").selectTab("Tambah Data");
        win.radioButton("rbMecha1").click();
        win.tabbedPane("tabBarang").selectTab("Tambah Data");
        win.textBox("txNamaBarang1").enterText("Charlie");
        win.comboBox("cbUkuranBarang1").selectItem("60%");
        win.textBox("txHrgJual1").enterText("125000");
        win.textBox("txHrgBeli1").enterText("100000");
        win.textBox("txStokBarang1").enterText("10");
        win.button("btnTambahData").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Search Barang=====");
        win.tabbedPane("tabBarang").selectTab("Tabel");
        win.textBox("txID").enterText("C00006");
        win.button("btnSearch").click();
        win.button("btnCl").click();
        
        System.out.println("=====Update Barang=====");
        win.tabbedPane("tabBarang").selectTab("Master");
        win.comboBox("cbIDBarang").selectItem("C00006");
        win.textBox("txNamaBarang").setText("");
        win.textBox("txNamaBarang").enterText("Rexus Daiva");
        win.textBox("txHrgJual").setText("");
        win.textBox("txHrgJual").enterText("450000");
        win.textBox("txHrgBeli").setText("");
        win.textBox("txHrgBeli").enterText("300000");
        win.textBox("txStokBarang").setText("");
        win.textBox("txStokBarang").enterText("0");
        win.button("btnUpdate").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Clear Barang=====");
        win.button("btnClear").click();
        
        System.out.println("=====Delete Barang=====");
        win.comboBox("cbIDBarang").click().selectItem("C00006");
        win.button("btnDelete").click();
        win.optionPane().yesButton().click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
    }
    
    @After
    public void clean2() {
        win.cleanUp();
    }
    
    @Test
    public void testPenjualan() {
        System.out.println("\n=====Tambah Penjualan=====");
        win.button("btnPenjualan").click();
        win.tabbedPane("tabPenjualan").selectTab("Tambah Data");
        win.robot.moveMouse(500, 353);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.robot.enterText("01-01-2022");
        win.comboBox("cbNamaPegawai1").selectItem("Anggi");
        win.comboBox("cbNamaPelanggan1").selectItem("Delima");
        win.radioButton("rbMecha1").click();
        win.comboBox("cbUkuranBarang1").selectItem("60%");
        win.comboBox("cbNamaBarang1").selectItem("Venom");
        win.textBox("txJmlBarang1").enterText("1");
        win.button("btnTambahData").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Search Penjualan=====");
        win.tabbedPane("tabPenjualan").selectTab("Tabel Transaksi");
        win.textBox("txID").enterText("D00006");
        win.button("btnSearch").click();
        win.button("btnCl").click();
        
        System.out.println("=====Update Penjualan=====");
        win.robot.moveMouse(500, 353);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.tabbedPane("tabPenjualan").selectTab("Master");
        win.comboBox("cbNamaPelanggan").selectItem("Delima");
        win.textBox("txJmlBarang").setText("");
        win.textBox("txJmlBarang").enterText("3");
        win.button("btnUpdate").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Clear Penjualan=====");
        win.button("btnClear").click();
        
        System.out.println("=====Delete Penjualan=====");
        win.tabbedPane("tabPenjualan").selectTab("Tabel Transaksi");
        win.robot.moveMouse(500, 353);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.tabbedPane("tabPenjualan").selectTab("Master");
        win.button("btnDelete").click();
        win.optionPane().yesButton().click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
    }
    
    @After
    public void clean3() {
        win.cleanUp();
    }
    
    @Test
    public void testPembayaran() {
        System.out.println("\n=====Tambah Pembayaran=====");
        win.button("btnPembayaran").click();
        win.tabbedPane("tabPembayaran").selectTab("Tambah Data");
        win.comboBox("cbIDTransaksi1").selectItem("D00005");
        win.textBox("txBayar1").enterText("1000000");
        win.button("btnTambahData").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Search Pembayaran=====");
        win.tabbedPane("tabPembayaran").selectTab("Tabel");
        win.textBox("txID").enterText("E00005");
        win.button("btnSearch").click();
        win.button("btnCl").click();
        
        System.out.println("=====Update Pembayaran=====");
        win.robot.moveMouse(500, 353);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.tabbedPane("tabPembayaran").selectTab("Master");
        win.textBox("txBayar").setText("");
        win.textBox("txBayar").enterText("2000000");
        win.button("btnUpdate").click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
        
        System.out.println("=====Clear Pembayaran=====");
        win.button("btnClear").click();
        
        System.out.println("=====Delete Penjualan=====");
        win.tabbedPane("tabPembayaran").selectTab("Tabel");
        win.robot.moveMouse(500, 353);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.robot.pressMouse(MouseButton.LEFT_BUTTON);
        win.robot.releaseMouse(MouseButton.LEFT_BUTTON);
        win.tabbedPane("tabPembayaran").selectTab("Master");
        win.button("btnDelete").click();
        win.optionPane().yesButton().click();
        win.optionPane().requireMessage("Success");
        win.optionPane().okButton().click();
    }
}
