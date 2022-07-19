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
import extras.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Laboratorio N.3: tercer miniproyecto. Archivo: VentanaAgregarContacto.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 16-07-2022, Fecha última modificación: 17-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
 */

public class VentanaAgregarContacto extends JFrame{
    private final Directorio directorio;
    
    //objetosAuxiliares
    private ArrayList<Map<String,String>> listAuxDir;
    private ArrayList<Map<String,String>> listAuxTel;
    private final List <JTextField> cajas;
    private final List <String> mensajes;
    
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
    private JLabel lblAyuda;

    //jButons
    private JButton btnAgregarDireccion;
    private JButton btnAgregarTelefono;
    private JButton btnAgregarContacto;
    private JButton btnCerrar, btnAyuda;

    private boolean agregado, retorno;
    private int x,y;
    
    //contenedorPrincipal
    private Container contPrincipal;
    
    public VentanaAgregarContacto(Directorio directorio){
        imagenFondo imagenFondo = new imagenFondo();
        setUndecorated(true);
        this.setContentPane(imagenFondo);
        this.mensajes = new ArrayList<>();
        this.cajas = new ArrayList<>();
        this.directorio = directorio;
        iniciarComponentes();
        setSize(600, 400);
        Shape forma = new RoundRectangle2D.Double(0,0,600,400,30,30);
        this.setShape(forma);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Directorio");
        setResizable(false);  
    }
    
    private void iniciarComponentes(){
        agregado = false;
        retorno = false;

        //obejtos aux
        listAuxDir = new ArrayList<>();
        listAuxTel = new ArrayList<>();
        
        //jtextfields
        txtNombres = new JTextField();
        mensajes.add("Ingrese los nombres");
        cajas.add(txtNombres);
        txtNombres.setBounds(33, 105, 150, 25);
        
        txtApellidos = new JTextField();
        mensajes.add("Ingrese los apellidos");
        cajas.add(txtApellidos);
        txtApellidos.setBounds(226, 105, 150, 25);
        
        txtID = new JTextField();
        mensajes.add("Ingrese el iD");
        cajas.add(txtID);
        txtID.setBounds(415, 105, 150, 25);
        
        //estamento (JComboBOx)
        String[] estamentos = {"empleado","estudiante","profesor"};
        cbEstamento = new JComboBox<>(estamentos);
        cbEstamento.setBounds(415, 270, 150, 25);
        
        //direcciones(lista)
        JTextField direccion = new JTextField();
        mensajes.add("direccion");
        cajas.add(direccion);
        direccion.setBounds(33, 195, 150, 25);
        
        JTextField barrio = new JTextField();
        mensajes.add("barrio");
        cajas.add(barrio);
        barrio.setBounds(226, 195, 150, 25);

        JTextField ciudad = new JTextField();
        mensajes.add("ciudad");
        cajas.add(ciudad);
        ciudad.setBounds(415, 195, 150, 25);
        
        listTxtDireccion = new ArrayList<>();
        listTxtDireccion.add(direccion);
        listTxtDireccion.add(barrio);
        listTxtDireccion.add(ciudad);
        
        //telefono(lista)
        txtNumero = new JTextField();
        mensajes.add("numero");
        cajas.add(txtNumero);
        txtNumero.setBounds(33, 270, 150, 25);
        
        String[] tiposDeNumero = {"movil", "casa", "oficina"};
        cbTipoNumero = new JComboBox<>(tiposDeNumero);
        cbTipoNumero.setBounds(226, 270, 150, 25);
        
        //FechaDeNacimiento(lista)
        JTextField dia = new JTextField();
        mensajes.add("dia");
        cajas.add(dia);
        dia.setBounds(33, 350, 150, 25);
        
        JTextField mes = new JTextField();
        mensajes.add("mes");
        cajas.add(mes);
        mes.setBounds(226, 350, 150, 25);
        
        JTextField año = new JTextField();
        mensajes.add("año");
        cajas.add(año);
        año.setBounds(415, 350, 150, 25);

        for(JTextField xCaja : cajas){
            crearCajas(xCaja, mensajes.get(cajas.indexOf(xCaja)));
        }
        
        listTxtFechaDeNacimiento = new ArrayList<>();
        listTxtFechaDeNacimiento.add(dia);
        listTxtFechaDeNacimiento.add(mes);
        listTxtFechaDeNacimiento.add(año);
        
        //btns
        btnAyuda = new JButton();
        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnpre2.png"))); 
        btnAyuda.setAutoscrolls(true);
        btnAyuda.setBorder(null);
        btnAyuda.setBorderPainted(false);
        btnAyuda.setContentAreaFilled(false);
        btnAyuda.setFocusPainted(false);
        btnAyuda.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnpre2p.png"))); 
        btnAyuda.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnpre2r.png"))); 
        btnAyuda.addMouseListener(new ManejadorDeEventos());
        btnAyuda.setBounds(347, 17, 33, 33);

        btnAgregarDireccion = new JButton();
        btnAgregarDireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btndir.png"))); 
        btnAgregarDireccion.setAutoscrolls(true);
        btnAgregarDireccion.setBorder(null);
        btnAgregarDireccion.setBorderPainted(false);
        btnAgregarDireccion.setContentAreaFilled(false);
        btnAgregarDireccion.setFocusPainted(false);
        btnAgregarDireccion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btndirp.png"))); 
        btnAgregarDireccion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btndirr.png"))); 
        btnAgregarDireccion.addMouseListener(new ManejadorDeEventos());
        btnAgregarDireccion.setBounds(570, 150, 30, 70);
        
        btnAgregarTelefono = new JButton();
        btnAgregarTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnnum.png"))); 
        btnAgregarTelefono.setAutoscrolls(true);
        btnAgregarTelefono.setBorder(null);
        btnAgregarTelefono.setBorderPainted(false);
        btnAgregarTelefono.setContentAreaFilled(false);
        btnAgregarTelefono.setFocusPainted(false);
        btnAgregarTelefono.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnnump.png"))); 
        btnAgregarTelefono.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnnumr.png"))); 
        btnAgregarTelefono.addMouseListener(new ManejadorDeEventos());
        btnAgregarTelefono.setBounds(0, 230, 30, 70);
        
        btnAgregarContacto = new JButton();
        btnAgregarContacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnFin.png"))); 
        btnAgregarContacto.setAutoscrolls(true);
        btnAgregarContacto.setBorder(null);
        btnAgregarContacto.setBorderPainted(false);
        btnAgregarContacto.setContentAreaFilled(false);
        btnAgregarContacto.setFocusPainted(false);
        btnAgregarContacto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnFinp.png"))); 
        btnAgregarContacto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnFinr.png"))); 
        btnAgregarContacto.addMouseListener(new ManejadorDeEventos());
        btnAgregarContacto.setBounds(400, 11, 130, 45);
        
        btnCerrar = new JButton();
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnCer3.png"))); 
        btnCerrar.setAutoscrolls(true);
        btnCerrar.setBorder(null);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnCer3p.png"))); 
        btnCerrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnCer3r.png"))); 
        btnCerrar.addMouseListener(new ManejadorDeEventos());
        btnCerrar.setBounds(550, 17, 33, 33);

        lblAyuda = new JLabel(new ImageIcon(getClass().getResource("/imagenes/ayudaC.png")));
        lblAyuda.setBounds(0,0,600,400);
        lblAyuda.setVisible(false);
        lblAyuda.addMouseListener(new ManejadorDeEventos());
        
        //contPrincipal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
                contPrincipal.add(lblAyuda);
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
        contPrincipal.add(btnAyuda);
        contPrincipal.add(btnCerrar);

        contPrincipal.addMouseListener(new EventosJFrame());
        contPrincipal.addMouseMotionListener(new EventosJFrame());
    }

    public boolean fueAgregado(){
        return agregado;
    }
    
    public boolean huboRetorno(){
        return retorno;
    }

    private void crearCajas(JTextField xJT, String message){
        TextPrompt placeholder = new TextPrompt(message, xJT);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
    }

    private class EventosJFrame extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
                Point point = MouseInfo.getPointerInfo().getLocation();
                setLocation(point.x - x, point.y - y);
        }
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
                
                
                int exit = JOptionPane.showOptionDialog(null, "¿Desea Agregar otro?" , "Se agrego el contacto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "SI", "NO" }, "NO");
                if (exit == JOptionPane.NO_OPTION)
                {
                    dispose();
                    agregado = true;
                }
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
            else if(e.getSource() == btnCerrar){
                retorno = true;
                dispose();
            }
            else if(e.getSource() == btnAyuda){
                lblAyuda.setVisible(true);
            } 
            else if(e.getSource() == lblAyuda){
                lblAyuda.setVisible(false);
            }
        }
        
    }

        class imagenFondo extends JPanel{
        private Image fondo;
        @Override
        public void paint(Graphics g) {
            fondo = new ImageIcon(getClass().getResource("/imagenes/contactoC.png")).getImage();
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
