package UAS;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Window;
import java.awt.Dimension;
import org.fest.swing.fixture.FrameFixture;


public class LoginTest {
    FrameFixture win;
    public LoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
        
    @After
    public void tearDown() {
    }

    @Before
    public void setUp() {
        win = new FrameFixture(new Login());
        Login login = new Login();
        Dimension ukuranLogin = new Dimension(login.getSize());
        win.show(ukuranLogin);
    }
    
    @Test
    public void testLogin() {      
        System.out.println("=====Login Gagal=====");
        win.textBox("txUser").enterText("qwerty");
        win.textBox("txPass").enterText("12345");
        win.button("btnLogin").click();
        win.optionPane().requireMessage("Username atau Password Salah");
        win.optionPane().okButton().click();
        
               
        win.textBox("txUser").setText("");
        win.textBox("txPass").setText("");
        
        System.out.println("=====Login Sukses=====");
        win.textBox("txUser").enterText("admin");
        win.textBox("txPass").enterText("admin");
        win.button("btnLogin").click();
        win.optionPane().requireMessage("Login Berhasil");
        win.optionPane().okButton().click();
    }
    
}
