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
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import modelo.Contacto;

/**
 * Laboratorio N.3: tercer miniproyecto. Archivo: VentanaEditarContacto.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 20-07-2022, Fecha última modificación: 21-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
 */

public class VentanaEditarContacto extends JFrame {
    private final Directorio directorio;
    private final Contacto contactoAEditar;
    
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
    
    private JTextField txtID, dia, mes, año, barrio, ciudad, direccion;
    
    //JcomboBox
    private JComboBox<String> cbTipoNumero;
    
    //JCOmboBox(estamento)
    private JComboBox cbEstamento, numeros, direcciones;
    
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
    private JButton btnCerrar, btnAyuda, btnEditN, btnEditD, btnCanN, btnCanD;
    
    private JButton btnEliminarTelefono;
    private JButton btnEliminarDireccion;
    private JButton btnEliminarContacto;

    private boolean agregado, retorno, edicion;
    private int x,y;
    private String tels;
    private boolean dir, tel;
    
    //contenedorPrincipal
    private Container contPrincipal;
    
    public VentanaEditarContacto(Directorio directorio, Contacto cualConcto){
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
        edicion = false;
        dir = true;
        tel = true;

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
        direccion = new JTextField();
        mensajes.add("direccion");
        cajas.add(direccion);
        direccion.setBounds(33, 195, 150, 25);
        
        barrio = new JTextField();
        mensajes.add("barrio");
        cajas.add(barrio);
        barrio.setBounds(226, 195, 150, 25);

        ciudad = new JTextField();
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
        dia = new JTextField();
        mensajes.add("dia");
        cajas.add(dia);
        dia.setBounds(33, 350, 150, 25);
        
        mes = new JTextField();
        mensajes.add("mes");
        cajas.add(mes);
        mes.setBounds(226, 350, 150, 25);
        
        año = new JTextField();
        mensajes.add("año");
        cajas.add(año);
        año.setBounds(415, 350, 150, 25);

        for(JTextField xCaja : cajas){
            crearCajas(xCaja, mensajes.get(cajas.indexOf(xCaja)));
        }
        
        txtNombres.setText(contactoAEditar.getNombres());
        txtApellidos.setText(contactoAEditar.getApellidos());
        txtID.setText(contactoAEditar.getiD());
        
        ArrayList<Map<String, String>> temporalDir = contactoAEditar.getDirecciones();
        direccion.setVisible(false);
        direccion.setText("");
        direcciones = new JComboBox();
        for(Map<String,String> dirsArray : temporalDir){
            direcciones.addItem(dirsArray.get("direccion"));
        }
        barrio.setText("");
        ciudad.setText("");
        direcciones.addItem("Añadir una direccion nueva");
        direcciones.setBounds(33, 195, 150, 25);
        direcciones.addMouseListener(new ManejadorDeEventos());
 
        ArrayList<Map<String, String>> temporalNum = contactoAEditar.getTelefonos();
        txtNumero.setVisible(false);
        numeros = new JComboBox();
        for(Map<String,String> telsArray : temporalNum){
            numeros.addItem(telsArray.get("numero"));
        }
        cbTipoNumero.setSelectedItem(temporalNum.get(0).get("tipo"));
        numeros.addItem(" Añadir un numero nuevo");
        numeros.setBounds(33, 270, 150, 25);
        numeros.addMouseListener(new ManejadorDeEventos());
        
        numeros.addActionListener((ActionEvent n)-> {
                if(numeros.getSelectedIndex() == numeros.getItemCount() - 1){
                    btnEditN.setVisible(false);
                    btnAgregarTelefono.setVisible(true);
                    btnEliminarTelefono.setVisible(false);
                    cbTipoNumero.setEnabled(true);
                    cbTipoNumero.setSelectedIndex(0);
                    numeros.setVisible(false);
                    txtNumero.setVisible(true);
                    txtNumero.setText("");
                    if(!temporalNum.isEmpty()){
                        btnCanN.setVisible(true);
                    } else {
                        btnCanN.setVisible(false);
                    }
                } else {
                    for(Map<String,String> telsArray : temporalNum){
                        if(numeros.getSelectedItem().equals(telsArray.get("numero"))){
                            cbTipoNumero.setSelectedItem(telsArray.get("tipo"));
                        }
                    }
                }
            }
        );
        
        direcciones.addActionListener((ActionEvent n)-> {
                if(direcciones.getSelectedIndex() == direcciones.getItemCount() - 1){
                    btnEditD.setVisible(false);
                    btnAgregarDireccion.setVisible(true);
                    btnEliminarDireccion.setVisible(false);
                    barrio.setEditable(true);
                    ciudad.setEditable(true);
                    barrio.setText("");
                    ciudad.setText("");
                    direccion.setVisible(true);
                    direccion.setText("");
                    direcciones.setVisible(false);
                    if(!temporalDir.isEmpty()){
                        btnCanD.setVisible(true);
                    } else {
                        btnCanD.setVisible(false);
                    }
                } else {
                    for(Map<String,String> dirsArray : temporalDir){
                        if(direcciones.getSelectedItem().equals(dirsArray.get("direccion"))){
                            barrio.setText(dirsArray.get("barrio"));
                            ciudad.setText(dirsArray.get("ciudad"));
                        }
                    }
                }
            }
        );
        
        cbEstamento.setSelectedItem(contactoAEditar.getEstamento()); 
        
        if(!contactoAEditar.getFechaDeNacimiento().equals("//")){
            String[] fechaNac = contactoAEditar.getFechaDeNacimiento().split("/");
        
            dia.setText(fechaNac[0]);
            mes.setText(fechaNac[1]);
            año.setText(fechaNac[2]);  
        }

        
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
        btnEditN = new JButton();
        btnEditD = new JButton();
        btnCanN = new JButton();
        btnCanD = new JButton();
        
        btnCanN.setVisible(false);
        btnCanD.setVisible(false);

        botones.add(btnAyuda); botones.add(btnAgregarDireccion); botones.add(btnAgregarTelefono); 
        botones.add(btnAgregarContacto); botones.add(btnCerrar); botones.add(btnEliminarDireccion);
        botones.add(btnEliminarTelefono); botones.add(btnEliminarContacto); botones.add(btnEditN);
        botones.add(btnEditD); botones.add(btnCanN); botones.add(btnCanD);

        for(JButton xButton : botones){
            crearBotones(xButton, botones.indexOf(xButton));
        }
        
        btnAyuda.setBounds(347, 17, 33, 33);
        btnAgregarDireccion.setBounds(0, 150, 30, 70);
        btnEditD.setBounds(0, 150, 30, 70);
        btnAgregarTelefono.setBounds(0, 230, 30, 70);
        btnEditN.setBounds(0, 230, 30, 70);
        btnAgregarContacto.setBounds(400, 11, 130, 45);
        btnCerrar.setBounds(550, 17, 33, 33);
        btnEliminarDireccion.setBounds(570, 150, 30, 70);
        btnEliminarTelefono.setBounds(190, 230, 30, 70);
        btnEliminarContacto.setBounds(305, 17, 33, 33);
        btnCanN.setBounds(190, 230, 30, 70);
        btnCanD.setBounds(570, 150, 30, 70);
        
        lblAyuda = new JLabel(new ImageIcon(getClass().getResource("/imagenes/ayudaC.png")));
        lblAyuda.setBounds(0,0,600,400);
        lblAyuda.setVisible(false);
        lblAyuda.addMouseListener(new ManejadorDeEventos());
        
        mensajes.removeAll(mensajes);
        mensajes.add("Ingrese una direccion válida");
        mensajes.add("Ingrese un barrio válido");
        mensajes.add("Ingrese una ciudad válida");
        
        btnAgregarDireccion.setVisible(false);
        btnAgregarTelefono.setVisible(false);
        
        barrio.setEditable(false);
        ciudad.setEditable(false);
        cbTipoNumero.setEnabled(false);
        
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
        contPrincipal.add(btnEditN);
        contPrincipal.add(btnEditD);
        contPrincipal.add(btnCanN);
        contPrincipal.add(btnCanD);
        contPrincipal.add(numeros);
        contPrincipal.add(direcciones);

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
        String dirB = obtenerDireccion(index);
        xButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btn"+dirB+".png"))); 
        xButton.setAutoscrolls(true);
        xButton.setBorder(null);
        xButton.setBorderPainted(false);
        xButton.setContentAreaFilled(false);
        xButton.setFocusPainted(false);
        xButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btn"+dirB+"p.png"))); 
        xButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btn"+dirB+"r.png"))); 
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
            case 8 -> { return "edin"; }
            case 9 -> { return "edid"; }
            case 10 -> { return "cann"; }
            case 11 -> { return "cand"; }
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
    
    private boolean validString(String entrada){
        char[] ch = entrada.toCharArray();
        for(char c : ch){
            if(Character.isDigit(c)) return false;
        }
        return true;
    }
    
    private boolean validNumber(String entrada){
        char[] ch = entrada.toCharArray();
        for(char c : ch){
            if(Character.isLetter(c)) return false;
        }
        return true;
    }

    private boolean validDate(String entrada, int max, int min){
        if(validNumber(entrada)){
            if(Integer.valueOf(entrada) >= max || Integer.valueOf(entrada) < min)
                return false;
        } else {
            return false;
        }
        return true;
    }
    
    private void reformarCBN(){
        
    }

    private class ManejadorDeEventos extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource() == btnAgregarContacto){
                try{
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

                    //se cambian aquellos valores que se hayan modificado
                    if(!"".equals(txtNombres.getText()) && validString(txtNombres.getText())){
                        contactoAEditar.setNombres(txtNombres.getText());
                    } else {
                        throw new RuntimeException("Ingrese un nombre válido");
                    }
                    
                    if(!"".equals(txtApellidos.getText()) && validString(txtApellidos.getText())){
                        contactoAEditar.setApellidos(txtApellidos.getText());
                    } else if(!"".equals(txtApellidos.getText()) && !validString(txtApellidos.getText())){
                        throw new RuntimeException("Ingrese un apellido válido");
                    }
                    
                    if(!"estamento".equals((String)cbEstamento.getSelectedItem())){
                        contactoAEditar.setEstamento(cbEstamento.getSelectedItem()+"");
                    }
                    
                    if(dir == false){
                        int indexAux = 0;
                        for(JTextField x: listTxtDireccion){
                            if(x.getText().equals("")){                    
                                throw new RuntimeException(mensajes.get(indexAux));
                            } else {
                                indexAux++;
                            }
                        }
                        contactoAEditar.agregarDireccion(direccion.getText(), barrio.getText(), ciudad.getText());
                    } else {
                        int indexAux = 0;
                        for(JTextField x: listTxtDireccion){
                            if(x.getText().equals("")){                    
                                indexAux++;
                            } 
                        }
                        if(indexAux < 3 && indexAux > 0 && barrio.isEditable()){
                            throw new RuntimeException("Complete la direccion");
                        } else {
                            contactoAEditar.agregarDireccion(direccion.getText(), barrio.getText(), ciudad.getText());
                        }
                    }
                   
                    if(tel == false){
                        if(txtNumero.getText().equals("") || !validNumber(txtNumero.getText())){
                          throw new RuntimeException("Ingrese un numero válido");
                        } else {
                            contactoAEditar.agregarTelefono(txtNumero.getText(), (String) cbTipoNumero.getSelectedItem());
                        }
                    } else {
                        if(!txtNumero.getText().equals("") && !validNumber(txtNumero.getText())){
                          throw new RuntimeException("Ingrese un numero válido");
                        } else if(!txtNumero.getText().equals("") && validNumber(txtNumero.getText())){
                            contactoAEditar.agregarTelefono(txtNumero.getText(), (String) cbTipoNumero.getSelectedItem());
                        }
                    }
                    
                    if(!txtID.getText().equals("")){
                        if(!validNumber(txtID.getText())){
                          throw new RuntimeException("Ingrese un ID válido");
                        } else {
                            contactoAEditar.setiD(txtID.getText());
                        }
                    }
                    
                    String cumpleAux;
                    boolean d = false, m = false, a = false;
                    
                    if(!dia.getText().equals("")){
                        if(!validDate(dia.getText(), 32, 1)){
                          throw new RuntimeException("Ingrese un día válido");
                        } else {
                            d = true;
                        }
                    }
                    
                    if(!mes.getText().equals("")){
                        if(!validDate(mes.getText(), 13, 1)){
                          throw new RuntimeException("Ingrese un mes válido");
                        } else {
                            m = true;
                        }
                    }
                    if(!año.getText().equals("")){
                        if(!validDate(año.getText(), 2023, 1923)){
                          throw new RuntimeException("Ingrese un año válido");
                        } else {
                            a = true;
                        }
                    }
                    
                    if(d && m && a) {
                        cumpleAux = dia.getText()+"/"+mes.getText()+"/"+año.getText();
                        contactoAEditar.setFechaDeNacimiento(cumpleAux);
                    }
                    
                    listAuxDir.clear();
                    listAuxTel.clear();

                    directorio.actualizarInformacion();
                    dispose();
                    agregado = true;
                    retorno = true;
                } catch(RuntimeException F){
                    JOptionPane.showMessageDialog(rootPane, F.getMessage(), "Informacion", HEIGHT);
                }
                Contacto aux = contactoAEditar;
                directorio.eliminarPorContacto(contactoAEditar);
                directorio.agregarContacto(aux);
            } 

            else if(e.getSource() == btnAgregarDireccion){
                ArrayList<Map<String,String>> dirs = contactoAEditar.getDirecciones();
                if(!dirs.isEmpty()){
                    for(Map<String,String> map : dirs){
                        if(map.get("direccion").equals(direccion.getText()) && map.get("ciudad").equals(ciudad.getText())){
                            throw new RuntimeException("Esta dirección ya existe");
                        } 
                    }
                }
                try{
                    int indexAux = 0;
                    for(JTextField x: listTxtDireccion){
                        if(x.getText().equals("")){                    
                            throw new RuntimeException(mensajes.get(indexAux));
                        } else {
                            indexAux++;
                        }
                    }

                    Map<String,String> dirAux = new HashMap<>();

                    dirAux.put("direccion", direccion.getText());
                    dirAux.put("barrio", barrio.getText());
                    dirAux.put("ciudad", ciudad.getText());

                    listAuxDir.add(dirAux);
                    
                    if(edicion){
                        contactoAEditar.eliminarDireccion(direcciones.getSelectedIndex());
                        JOptionPane.showMessageDialog(rootPane, "Se editó esa dirección", "Información", HEIGHT);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Se añadió esa dirección", "Información", HEIGHT);
                    }
                    contactoAEditar.agregarDireccion(direccion.getText(), barrio.getText(), ciudad.getText());
                    dir = true;
                    edicion = false;
                    crearCajas(direccion, "direccion");
                    
                    if(!dirs.isEmpty()){
                        ArrayList<String> items = new ArrayList();
                        
                        for(Map<String,String> x: dirs){
                            items.add(x.get("direccion"));
                        }
                        
                        direcciones.removeAllItems();
                        for(String x : items){
                            direcciones.addItem(x);
                        }
                        direcciones.addItem("Añadir una dirección nueva");
                    } else {
                        direcciones.addItem(direccion.getText());
                        direcciones.addItem("Añadir una dirección nueva");
                    }
                    
                    direcciones.setVisible(true);
                    direccion.setVisible(false);
                    btnEditD.setVisible(true);
                    btnEliminarDireccion.setVisible(true);
                    btnAgregarDireccion.setVisible(false);
                    barrio.setEditable(false);
                    ciudad.setEditable(false);
                    SwingUtilities.updateComponentTreeUI(contPrincipal);
                    
                } catch (RuntimeException E){
                    JOptionPane.showMessageDialog(rootPane, E.getMessage(), "Informacion", HEIGHT);
                }
            }
            else if(e.getSource() == btnAgregarTelefono){
                ArrayList<Map<String,String>> tels = contactoAEditar.getTelefonos();
                if(!tels.isEmpty()){
                    for(Map<String,String> map : tels){
                        if(map.get("numero").equals(txtNumero.getText()) && cbTipoNumero.getSelectedItem().equals(map.get("tipo"))){
                            throw new RuntimeException("Este numero ya existe");
                        } 
                    }
                }
                try{
                    if(txtNumero.getText().equals("") || !validNumber(txtNumero.getText())){
                          throw new RuntimeException("Ingrese un numero válido");
                    }

                    Map<String,String> telAux = new HashMap<>();

                    telAux.put("numero", txtNumero.getText());
                    telAux.put("tipo", cbTipoNumero.getSelectedItem()+"");

                    listAuxTel.add(telAux);
                    if(edicion){
                        contactoAEditar.eliminarTelefono(numeros.getSelectedIndex());
                        JOptionPane.showMessageDialog(rootPane, "Se editó ese número", "Información", HEIGHT);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Se añadió ese número", "Información", HEIGHT);
                    }
                    contactoAEditar.agregarTelefono(txtNumero.getText(), (String) cbTipoNumero.getSelectedItem());
                    tel = true;
                    edicion = false;
                    crearCajas(txtNumero, "numero");
                    
                    if(!tels.isEmpty()){
                        ArrayList<String> items = new ArrayList();
                        
                        for(Map<String,String> x: tels){
                            items.add(x.get("numero"));
                        }
                        
                        numeros.removeAllItems();
                        for(String x : items){
                            numeros.addItem(x);
                        }
                        numeros.addItem("Añadir un numero nuevo");
                    } else {
                        numeros.addItem(txtNumero.getText());
                        numeros.addItem("Añadir un numero nuevo");
                    }
                    
                    numeros.setVisible(true);
                    txtNumero.setVisible(false);
                    btnEditN.setVisible(true);
                    btnEliminarTelefono.setVisible(true);
                    btnAgregarTelefono.setVisible(false);
                    cbTipoNumero.setEnabled(false);
                    SwingUtilities.updateComponentTreeUI(contPrincipal);
                    
                } catch (RuntimeException E){
                    JOptionPane.showMessageDialog(rootPane, E.getMessage(), "Informacion", HEIGHT);
                }
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
                ArrayList<Contacto> contactos = directorio.getContactos("todos");
                int tempo = contactos.indexOf(contactoAEditar);
                directorio.eliminarContacto(tempo);
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
                int dirEliminar = direcciones.getSelectedIndex();
                contactoAEditar.eliminarDireccion(dirEliminar);
                ArrayList<Map<String,String>> dirs = contactoAEditar.getDirecciones();
                if(dirs.isEmpty()){
                    btnEliminarDireccion.setVisible(false);
                    direccion.setVisible(true);
                    direcciones.setVisible(false);
                    btnEditD.setVisible(false);
                    btnCanD.setVisible(false);
                    btnAgregarDireccion.setVisible(true);
                    direccion.setText("");
                    barrio.setText("");
                    ciudad.setText("");
                    barrio.setEditable(true);
                    ciudad.setEditable(true);
                    dir = false;
                    direcciones.removeAllItems();
                } else {
                    direcciones.removeItem(direcciones.getSelectedItem());
                    direcciones.setSelectedIndex(0);
                    for(Map<String,String> op : dirs){
                        if(op.get("direccion").equals(direcciones.getSelectedItem())){
                            barrio.setText(op.get("barrio"));
                            ciudad.setText(op.get("ciudad"));
                        }
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Se eliminó esa dirección", "Información", HEIGHT);
                directorio.actualizarInformacion();
            }
            else if(e.getSource() == btnEliminarTelefono){
                int telEliminar = numeros.getSelectedIndex();
                contactoAEditar.eliminarTelefono(telEliminar);
                ArrayList<Map<String,String>> tels = contactoAEditar.getTelefonos();
                if(tels.isEmpty()){
                    btnEliminarTelefono.setVisible(false);
                    txtNumero.setVisible(true);
                    numeros.setVisible(false);
                    btnEditN.setVisible(false);
                    btnCanN.setVisible(false);
                    btnAgregarTelefono.setVisible(true);
                    txtNumero.setText("");
                    cbTipoNumero.setSelectedItem("movil"); 
                    tel = false;
                    numeros.removeAllItems();
                } else {
                    numeros.removeItem(numeros.getSelectedItem());
                    numeros.setSelectedIndex(0);
                    for(Map<String,String> op : tels){
                        if(op.get("numero").equals(numeros.getSelectedItem())){
                            cbTipoNumero.setSelectedItem(op.get("tipo"));
                        }
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Se eliminó ese número", "Información", HEIGHT);
                directorio.actualizarInformacion();
            }
            else if(e.getSource() == btnCanN){
                btnEditN.setVisible(true);
                btnAgregarTelefono.setVisible(false);
                btnEliminarTelefono.setVisible(true);
                cbTipoNumero.setEnabled(false);
                cbTipoNumero.setSelectedIndex(0);
                numeros.setVisible(true);
                txtNumero.setVisible(false);
                txtNumero.setText("");
                btnCanN.setVisible(false);
                numeros.setSelectedIndex(0);
                edicion = false;
                SwingUtilities.updateComponentTreeUI(contPrincipal);
            }
            else if(e.getSource() == btnEditN){
                btnAgregarTelefono.setVisible(true);
                btnEliminarTelefono.setVisible(false);
                cbTipoNumero.setEnabled(true);
                numeros.setVisible(false);
                txtNumero.setVisible(true);
                btnCanN.setVisible(true);
                edicion = true;
                txtNumero.setText((String) numeros.getSelectedItem());
                SwingUtilities.updateComponentTreeUI(contPrincipal);
            }
            else if(e.getSource() == btnCanD){
                btnEditD.setVisible(true);
                btnAgregarDireccion.setVisible(false);
                btnEliminarDireccion.setVisible(true);
                barrio.setEditable(false);
                barrio.setText("");
                ciudad.setEditable(false);
                barrio.setText("");
                direcciones.setVisible(true);
                direccion.setVisible(false);
                direccion.setText("");
                btnCanD.setVisible(false);
                direcciones.setSelectedIndex(0);
                edicion = false;
                SwingUtilities.updateComponentTreeUI(contPrincipal);
            }
            else if(e.getSource() == btnEditD){
                btnAgregarDireccion.setVisible(true);
                btnEliminarDireccion.setVisible(false);
                barrio.setEditable(true);
                ciudad.setEditable(true);
                direcciones.setVisible(false);
                direccion.setVisible(true);
                btnCanD.setVisible(true);
                edicion = true;
                direccion.setText((String) direcciones.getSelectedItem());
                ArrayList<Map<String,String>> temporalDir = contactoAEditar.getDirecciones();
                for(Map<String,String> dirsArray : temporalDir){
                        if(direcciones.getSelectedItem().equals(dirsArray.get("direccion"))){
                            barrio.setText(dirsArray.get("barrio"));
                            ciudad.setText(dirsArray.get("ciudad"));
                        }
                }
                SwingUtilities.updateComponentTreeUI(contPrincipal);
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