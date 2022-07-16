/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Container;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import modelo.Directorio;

/**
 *
 * @author invitado
 */
public class VentanaPrincipal extends JFrame{
    private String rutaAbsoluta;
    private Directorio directorio;
    
    //Jtextfields
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtDirecciones;
    private JTextField txtTelefonos;
    private JTextField txtID;
    
    //JLabels
    private JLabel lblNombres;
    private JLabel lblApellidos;
    private JLabel lblDirecciones;
    private JLabel lblTelefonos;
    private JLabel lblID;
    
    //contenedorPrincipal
    private Container contPrincipal;
    
    public VentanaPrincipal(){
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Directorio");
        setResizable(false);
        
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        rutaAbsoluta = new File("").getAbsolutePath();
        directorio = new Directorio(new File(rutaAbsoluta.
                concat("\\src\\directoriotelefonico\\contactos.txt")));
        
        //contPrincipal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
    }
    
}
