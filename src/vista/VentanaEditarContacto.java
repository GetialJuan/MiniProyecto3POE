package vista;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.JFrame;

/**
 * Laboratorio N.3: tercer miniproyecto. Archivo: VentanaEditarContacto.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 20-07-2022, Fecha última modificación: 21-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
 */

public class VentanaEditarContacto extends JFrame {
    private final Directorio directorio;
    private final int contactoAEditar;
    
    //objetosAuxiliares
    private ArrayList<Map<String,String>> listAuxDir;
    private ArrayList<Map<String,String>> listAuxTel;
    private final List <JTextField> cajas;
    private final List <String> mensajes;
    
    //Jtextfields
    private JTextField txtNombres;
    private JTextField txtApellidos;
    
    private ArrayList<JTextField> listTxtDireccion;
    private final List<JButton> botones = new ArrayList<>();
    
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
    
    private JButton btnEliminarTelefono;
    private JButton btnEliminarDireccion;
    private JButton btnEliminarContacto;

    private boolean agregado, retorno;
    private int x,y;
    
    //contenedorPrincipal
    private Container contPrincipal;
    
    public VentanaEditarContacto(Directorio directorio, int cualConcto){
        contactoAEditar = cualConcto;
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
        String[] estamentos = {"estamento","empleado","estudiante","profesor"};
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
        
        txtNombres.setText(directorio.getContacto(contactoAEditar).getNombres());
        txtApellidos.setText(directorio.getContacto(contactoAEditar).getApellidos());
        txtID.setText(directorio.getContacto(contactoAEditar).getiD());

        listTxtFechaDeNacimiento = new ArrayList<>();
        listTxtFechaDeNacimiento.add(dia);
        listTxtFechaDeNacimiento.add(mes);
        listTxtFechaDeNacimiento.add(año);

        btnAyuda = new JButton();
        btnAgregarDireccion = new JButton();
        btnAgregarTelefono = new JButton();
        btnAgregarContacto = new JButton();
        btnCerrar = new JButton();
        btnEliminarDireccion = new JButton();
        btnEliminarTelefono = new JButton();
        btnEliminarContacto = new JButton();

        botones.add(btnAyuda); botones.add(btnAgregarDireccion); botones.add(btnAgregarTelefono); 
        botones.add(btnAgregarContacto); botones.add(btnCerrar); botones.add(btnEliminarDireccion);
        botones.add(btnEliminarTelefono); botones.add(btnEliminarContacto);

        for(JButton xButton : botones){
            crearBotones(xButton, botones.indexOf(xButton));
        }
        
        btnAyuda.setBounds(347, 17, 33, 33);
        btnAgregarDireccion.setBounds(570, 150, 30, 70);
        btnAgregarTelefono.setBounds(0, 230, 30, 70);
        btnAgregarContacto.setBounds(400, 11, 130, 45);
        btnCerrar.setBounds(550, 17, 33, 33);
        btnEliminarDireccion.setBounds(380, 150, 30, 70);
        btnEliminarTelefono.setBounds(190, 230, 30, 70);
        btnEliminarContacto.setBounds(305, 17, 33, 33);
        
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
        contPrincipal.add(btnEliminarContacto);
        contPrincipal.add(btnEliminarDireccion);
        contPrincipal.add(btnEliminarTelefono);

        contPrincipal.addMouseListener(new EventosJFrame());
        contPrincipal.addMouseMotionListener(new EventosJFrame());
    }

    public boolean fueAgregado(){
        return agregado;
    }
    
    public boolean huboRetorno(){
        return retorno;
    }

    private void crearBotones(JButton xButton, int index){
        String dir = obtenerDireccion(index);
        xButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btn"+dir+".png"))); 
        xButton.setAutoscrolls(true);
        xButton.setBorder(null);
        xButton.setBorderPainted(false);
        xButton.setContentAreaFilled(false);
        xButton.setFocusPainted(false);
        xButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btn"+dir+"p.png"))); 
        xButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btn"+dir+"r.png"))); 
        xButton.addMouseListener(new ManejadorDeEventos());
    }

    private String obtenerDireccion(int index){
        switch(index){
            case 0 -> { return "Pre2"; }
            case 1 -> { return "dir"; }
            case 2 -> { return "num"; }
            case 3 -> { return "Fin"; }
            case 4 -> { return "Cer3"; }
            case 5 -> { return "qdir"; }
            case 6 -> { return "qnum"; }
            case 7 -> { return "del"; }
        }
        return "";
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
                    if("".equals(listTxtFechaDeNacimiento.get(i).getText())){
                        break;
                    }
                    fechaDeNacimiento += listTxtFechaDeNacimiento.get(i).getText();
                    if(i<2){
                        fechaDeNacimiento += "/";
                    }
                }
                
                //se cambian aquello valores que se hayan modificado
                
                if(!"".equals(txtNombres.getText())){
                    directorio.getContacto(contactoAEditar).
                            setNombres(txtNombres.getText());
                }
                if(!"".equals(txtApellidos.getText())){
                    directorio.getContacto(contactoAEditar).
                            setApellidos(txtApellidos.getText());
                }
                if(!"".equals(txtID.getText())){
                    directorio.getContacto(contactoAEditar).
                            setiD(txtID.getText());
                }
                if(!"estamento".equals((String)cbEstamento.getSelectedItem())){
                    directorio.getContacto(contactoAEditar).setEstamento(cbEstamento.getSelectedItem()+"");
                }
                if(!"".equals(fechaDeNacimiento)){
                    directorio.getContacto(contactoAEditar).setFechaDeNacimiento(fechaDeNacimiento);
                }
                for(Map<String,String> dir : listAuxDir){
                    directorio.getContacto(contactoAEditar).
                            agregarDireccion(dir.get("direccion"), 
                                    dir.get("barrio"), dir.get("ciudad"));
                }
                for(Map<String,String> tel : listAuxTel){
                    directorio.getContacto(contactoAEditar).
                            agregarTelefono(tel.get("numero"), tel.get("tipo"));
                }
                listAuxDir.clear();
                listAuxTel.clear();
                
                
                directorio.actualizarInformacion();
                dispose();
                agregado = true;
                retorno = true;
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
            else if(e.getSource() == btnEliminarContacto){
                directorio.eliminarContacto(contactoAEditar);
                directorio.actualizarInformacion();
                int exit = JOptionPane.showOptionDialog(null, "Se eliminó el contacto" , "Informacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "OK", "GENIAL" }, "NO");
                if (exit == JOptionPane.NO_OPTION)
                {
                    dispose();
                    retorno = true;
                }
                else if(exit == JOptionPane.YES_OPTION){
                    dispose();
                    retorno = true;
                }
            }
            else if(e.getSource() == btnEliminarDireccion){
                directorio.getContacto(contactoAEditar).eliminarDireccion();
            }
            else if(e.getSource() == btnEliminarTelefono){
                directorio.getContacto(contactoAEditar).eliminarTelefono();
            }
        }
        
    }

        class imagenFondo extends JPanel{
        private Image fondo;
        @Override
        public void paint(Graphics g) {
            fondo = new ImageIcon(getClass().getResource("/imagenes/contactoE.png")).getImage();
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
} //NO SE PUEDE OBTENER EL CONTACTO 0