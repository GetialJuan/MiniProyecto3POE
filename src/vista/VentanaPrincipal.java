/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Container;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JComboBox;
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
    
    private ArrayList<JTextField> listTxtDireccion;
    
    private ArrayList<JTextField> listTxtNumero;
    
    private JTextField txtID;
    
    //JCOmboBox(estamento)
    private JComboBox<String> cbEstamento;
    
    //JLabels
    private JLabel lblNombres;
    private JLabel lblApellidos;
    private JLabel lblDirecciones;
    private JLabel lblTelefonos;
    private JLabel lblID;
    
    //contenedorPrincipal
    private Container contPrincipal;
    
    public VentanaPrincipal(){
        iniciarComponentes();
        setSize(600, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Directorio");
        setResizable(false);
        
        
    }
    
    private void iniciarComponentes(){
        rutaAbsoluta = new File("").getAbsolutePath();
        directorio = new Directorio(new File(rutaAbsoluta.
                concat("\\src\\directoriotelefonico\\contactos.txt")));
        
        //jtextfields
        txtNombres = new JTextField("Ingrese los nombres");
        txtNombres.setBounds(430, 10, 150, 25);
        
        txtApellidos = new JTextField("Ingrese los apellidos");
        txtApellidos.setBounds(430, 50, 150, 25);
        
        txtID = new JTextField("Ingrese el iD");
        txtID.setBounds(430, 80, 150, 25);
        
        //estamento (JComboBOx)
        String[] estamentos = {"empleado","estudiante","profesor"};
        cbEstamento = new JComboBox<>(estamentos);
        cbEstamento.setBounds(430, 115, 150, 25);
        
        //direcciones(lista)
        JTextField direccion = new JTextField("direccion");
        direccion.setBounds(10, 150, 150, 25);
        
        JTextField barrio = new JTextField("barrio");
        barrio.setBounds(170, 150, 150, 25);

        JTextField ciudad = new JTextField("ciudad");
        ciudad.setBounds(330, 150, 150, 25);
        
        listTxtDireccion = new ArrayList<>();
        listTxtDireccion.add(direccion);
        listTxtDireccion.add(barrio);
        listTxtDireccion.add(ciudad);
        
        //telefono(lista)
        JTextField numero = new JTextField("numero");
        numero.setBounds(10, 190, 150, 25);
        
        JTextField tipoNumero = new JTextField("tipo");
        tipoNumero.setBounds(170, 190, 150, 25);
        
        listTxtNumero = new ArrayList<>();
        listTxtNumero.add(numero);
        listTxtNumero.add(tipoNumero);
        
        //contPrincipal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        
        contPrincipal.add(txtNombres);
        contPrincipal.add(txtApellidos);
        contPrincipal.add(txtID);
        contPrincipal.add(cbEstamento);
        for(JTextField txt : listTxtDireccion){
            contPrincipal.add(txt);
        }
        for(JTextField txt : listTxtNumero){
            contPrincipal.add(txt);
        }
    }
    
}
