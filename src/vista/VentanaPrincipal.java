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
    
    private JTextField txtDireccion;
    private JTextField txtBarrio;
    private JTextField txtCiudad;
    
    private JTextField txtTelefono;
    private JTextField txtTipo;
    
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
        setSize(600, 600);
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
        
        //jtextfields
        txtNombres = new JTextField("Ingrese los nombres");
        txtNombres.setBounds(430, 10, 150, 20);
        
        txtApellidos = new JTextField("Ingrese los apellidos");
        txtApellidos.setBounds(430, 50, 150, 20);
        
        txtID = new JTextField("Ingrese el iD");
        txtID.setBounds(430, 80, 150, 20);
        
        //contPrincipal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        
        contPrincipal.add(txtNombres);
        contPrincipal.add(txtApellidos);
        contPrincipal.add(txtID);
    }
    
}
