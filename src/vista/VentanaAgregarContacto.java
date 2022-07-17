/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Directorio;

/**
 *
 * @author invitado
 */
public class VentanaAgregarContacto extends JFrame{
    private Directorio directorio;
    
    //objetosAuxiliares
    private ArrayList<Map<String,String>> listAuxDir;
    private ArrayList<Map<String,String>> listAuxTel;
    
    //Jtextfields
    private JTextField txtNombres;
    private JTextField txtApellidos;
    
    private ArrayList<JTextField> listTxtDireccion;
    
    private ArrayList<JTextField> listTxtNumero;
    private JTextField txtNumero;
    
    private ArrayList<JTextField> listTxtFechaDeNacimiento;
    
    private JTextField txtID;
    
    //JcomboBox
    private JComboBox<String> cbTipoNumero;
    
    //JCOmboBox(estamento)
    private JComboBox<String> cbEstamento;
    
    //JLabels
    private JLabel lblNombres;
    private JLabel lblApellidos;
    private JLabel lblDirecciones;
    private JLabel lblTelefonos;
    private JLabel lblID;
    
    //jButons
    private JButton btnAgregarDireccion;
    private JButton btnAgregarTelefono;
    private JButton btnAgregarContacto;
    private JButton btnVerDirectorio;
    
    //contenedorPrincipal
    private Container contPrincipal;
    
    public VentanaAgregarContacto(Directorio directorio){
        this.directorio = directorio;
        iniciarComponentes();
        setSize(600, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Directorio");
        setResizable(false);
        
        
    }
    
    private void iniciarComponentes(){
        
        //obejtos aux
        listAuxDir = new ArrayList<>();
        listAuxTel = new ArrayList<>();
        
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
        txtNumero = new JTextField("numero");
        txtNumero.setBounds(10, 190, 150, 25);
        
        String[] tiposDeNumero = {"movil", "casa", "oficina"};
        cbTipoNumero = new JComboBox<>(tiposDeNumero);
        cbTipoNumero.setBounds(170, 190, 150, 25);
        
        //FechaDeNacimiento(lista)
        JTextField dia = new JTextField("dia");
        dia.setBounds(10, 230, 150, 25);
        
        JTextField mes = new JTextField("mes");
        mes.setBounds(170, 230, 150, 25);
        
        JTextField año = new JTextField("año");
        año.setBounds(330, 230, 150, 25);
        
        listTxtFechaDeNacimiento = new ArrayList<>();
        listTxtFechaDeNacimiento.add(dia);
        listTxtFechaDeNacimiento.add(mes);
        listTxtFechaDeNacimiento.add(año);
        
        //btns
        btnAgregarDireccion = new JButton("+");
        btnAgregarDireccion.setBounds(490, 150, 50, 25);
        
        btnAgregarTelefono = new JButton("+");
        btnAgregarTelefono.setBounds(330, 190, 50, 25);
        
        btnAgregarContacto = new JButton("Agregar Contacto");
        btnAgregarContacto.setBounds(430, 270, 140, 30);
        
        btnVerDirectorio = new JButton("Ver Directorio");
        btnVerDirectorio.setBounds(10, 270, 140, 30);
        
        //contPrincipal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        
        contPrincipal.add(txtNombres);
        contPrincipal.add(txtApellidos);
        contPrincipal.add(txtID);
        contPrincipal.add(cbEstamento);
        contPrincipal.add(txtNumero);
        contPrincipal.add(cbTipoNumero);
        for(JTextField txt : listTxtDireccion){
            contPrincipal.add(txt);
        }
        for(JTextField txt : listTxtFechaDeNacimiento){
            contPrincipal.add(txt);
        }
        contPrincipal.add(btnAgregarContacto);
        contPrincipal.add(btnAgregarDireccion);
        contPrincipal.add(btnAgregarTelefono);
        contPrincipal.add(btnVerDirectorio);
        
        //listeners
        btnAgregarContacto.addMouseListener(new ManejadorDeEventos());
        btnAgregarDireccion.addMouseListener(new ManejadorDeEventos());
        btnAgregarTelefono.addMouseListener(new ManejadorDeEventos());
        btnVerDirectorio.addMouseListener(new ManejadorDeEventos());
        
    }
    
    private class ManejadorDeEventos extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource() == btnAgregarContacto){
                String fechaDeNacimiento = "";
                for(int i = 0; i<3; i++){
                    fechaDeNacimiento += listTxtFechaDeNacimiento.get(i).getText();
                    if(i<2){
                        fechaDeNacimiento += "/";
                    }
                }
                directorio.agregarContacto(fechaDeNacimiento, txtID.getText(), 
                        txtNombres.getText(), txtApellidos.getText(), 
                        ""+cbEstamento.getSelectedItem(), listAuxDir, 
                        listAuxTel);
                
                listAuxDir.clear();
                listAuxTel.clear();
                
                JOptionPane.showMessageDialog(null, "Se agrego el contacto");
            }
            else if(e.getSource() == btnAgregarDireccion){
                String[] claves = {"direccion", "barrio", "ciudad"};
                Map<String,String> direccionAux = new HashMap<>();
                for(int i = 0; i<3; i++){
                    direccionAux.put(claves[i], listTxtDireccion.get(i).
                            getText());
                }
                
                listAuxDir.add(direccionAux);
            }
            else if(e.getSource() == btnAgregarTelefono){
                Map<String,String> telAux = new HashMap<>();
                
                telAux.put("numero", txtNumero.getText());
                telAux.put("tipo", cbTipoNumero.getSelectedItem()+"");
                
                listAuxTel.add(telAux);
            }
            else if(e.getSource() == btnVerDirectorio){
                dispose();
            }
        }
        
    }
}
