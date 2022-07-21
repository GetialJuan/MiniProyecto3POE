
package vista;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import modelo.Directorio;
import java.util.List;
import java.util.ArrayList;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.NORMAL;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Contacto;

/**
 * Laboratorio N.3: tercer miniproyecto. Archivo: VentanaDirectorio.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 16-07-2022, Fecha última modificación: 21-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
 */

public class VentanaDirectorio extends JFrame{
    private Directorio directorio;
    
    //pnls (de botones)
    private JPanel pnlBotonesSuperiores;;

    //jButons
    private JButton btnProfesores, btnEstudiantes, btnEmpleados, btnTodos, btnAgregarContacto, 
                    btnEditarContacto,btnConfiguracion, btnAyuda, btnMinimizar, btnCerrar;

    private final List<JButton> botones = new ArrayList<>();
    
    //jtextpane(directorio visual)
    private JTextPane tpDirectorio;
    private JScrollPane spDirectorio;
    
    private JTable tDirectorio;
    private DefaultTableModel modeloTabla;
    
    //contendeor principal
    private Container contPrincipal;

    private int x, y;
    private Timer reOpen;   
    private JLabel lblAyuda, lblAbajo;

    private JComboBox comboBox;
    
    public VentanaDirectorio(){
        imagenFondo imagenFondo = new imagenFondo();
        setUndecorated(true);
        this.setContentPane(imagenFondo);
        Shape forma = new RoundRectangle2D.Double(0,0,520,520,30,30);
        this.setShape(forma);
        iniciarComponentes();
        setSize(520, 520);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Directorio");
    }
    
    private void iniciarComponentes(){
     
        //Se estblece el directorio persistente
        File fl = new File(new File("").getAbsolutePath().
                concat("\\src\\directoriotelefonico\\contactos.dat"));
        directorio = new Directorio(fl);
        
        //jpanels
        pnlBotonesSuperiores = new JPanel(new GridLayout(1,5));
        pnlBotonesSuperiores.setBounds(13, 74, 496, 50);

        btnProfesores = new JButton();
        btnEstudiantes = new JButton();
        btnEmpleados = new JButton();
        btnTodos = new JButton();
        btnAyuda = new JButton();
        btnMinimizar = new JButton();
        btnCerrar = new JButton();
        btnAgregarContacto = new JButton();
        btnConfiguracion = new JButton();
        btnEditarContacto = new JButton("");
        
        botones.add(btnProfesores); botones.add(btnEstudiantes); botones.add(btnEmpleados); botones.add(btnTodos);
        botones.add(btnAyuda); botones.add(btnMinimizar); botones.add(btnCerrar); botones.add(btnAgregarContacto);
        botones.add(btnConfiguracion); botones.add(btnEditarContacto);

        for(JButton xButton : botones){
            crearBotones(xButton, botones.indexOf(xButton));
        } 

        pnlBotonesSuperiores.add(btnProfesores);
        pnlBotonesSuperiores.add(btnEstudiantes);
        pnlBotonesSuperiores.add(btnEmpleados);
        pnlBotonesSuperiores.add(btnTodos);

        pnlBotonesSuperiores.setBorder(null);

        JLabel lblLinea = new JLabel();
        lblLinea.setIcon(new ImageIcon(getClass().getResource("/botones/linea.png")));
        lblLinea.setBounds(13,74,1,50);

        lblAbajo = new JLabel();
        lblLinea.setIcon(new ImageIcon(getClass().getResource("/botones/lblAbajo.png")));
        lblLinea.setBounds(0,497,520,23);

        btnAyuda.setBounds(348,21,46,46);
        btnMinimizar.setBounds(401,21,46,46); 
        btnCerrar.setBounds(454,21,46,46); 
        btnAgregarContacto.setBounds(121,402,92,92);
        btnConfiguracion.setBounds(221,402,92,92);
        btnEditarContacto.setBounds(321,402,92,92);
        
        //directrio
        /*
        tpDirectorio = new JTextPane();
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontFamily(attribs, "Tahoma");
        StyleConstants.setFontSize(attribs, 18);
        tpDirectorio.setParagraphAttributes(attribs, true);
        tpDirectorio.setBorder(null);
        tpDirectorio.setEditable(false);
        tpDirectorio.setText(directorio.getDirectorioVisual("todos"));
        */
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("#");
        modeloTabla.addColumn("Nombres");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Numeros");
        tDirectorio = new JTable(){
        @Override
        public boolean isCellEditable(int rowIndex, int vColIndex) {
            return vColIndex == 3;
        }};

        tDirectorio.setBorder(BorderFactory.createEmptyBorder());
        tDirectorio.setModel(modeloTabla);

        establecerTabla("todos");
        mostrarCB();

        spDirectorio = new JScrollPane(tDirectorio);
        spDirectorio.setBounds(14, 125, 492, 380);
        spDirectorio.setBorder(BorderFactory.createEmptyBorder());

        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);

        lblAyuda = new JLabel(new ImageIcon(getClass().getResource("/imagenes/ayuda.png")));
        lblAyuda.setBounds(0,0,520,520);
        contPrincipal.add(lblAyuda);

        contPrincipal.add(btnAyuda);
        contPrincipal.add(btnMinimizar);
        contPrincipal.add(btnCerrar);
        contPrincipal.add(btnAgregarContacto);
        contPrincipal.add(btnConfiguracion);
        contPrincipal.add(btnEditarContacto);
        
        contPrincipal.add(lblLinea);
        contPrincipal.add(pnlBotonesSuperiores);

        contPrincipal.add(spDirectorio);
        
        contPrincipal.addMouseListener(new EventosJFrame());
        contPrincipal.addMouseMotionListener(new EventosJFrame());

        contPrincipal.add(lblAbajo);
        lblAyuda.setVisible(false);
        lblAyuda.addMouseListener(new ManejadorDeEventos());
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
            case 0 -> { return "Pro"; }
            case 1 -> { return "Est"; }
            case 2 -> { return "Emp"; }
            case 3 -> { return "Tod"; }
            case 4 -> { return "Pre"; }
            case 5 -> { return "Min"; }
            case 6 -> { return "Cer"; }
            case 7 -> { return "Agr"; }
            case 8 -> { return "Con"; }
            case 9 -> { return "Edi"; }
        }
        return "";
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
                VentanaAgregarContacto ventanaAgregarContacto = 
                        new VentanaAgregarContacto(directorio);
                setExtendedState(ICONIFIED);
                reOpen = new Timer(1000, null);
                reOpen.addActionListener((ActionEvent a) -> {
                    if(ventanaAgregarContacto.fueAgregado() == true){
                        directorio.establecerDirectorioPersistente();
                        limpiarTabla();
                        establecerTabla("todos");
                        mostrarCB();
                        setExtendedState(NORMAL);
                        btnAgregarContacto.setVisible(false); btnConfiguracion.setVisible(false);
                        btnAgregarContacto.setVisible(true); btnConfiguracion.setVisible(true);
                        reOpen.stop();                        
                    } else if(ventanaAgregarContacto.huboRetorno() == true){
                        setExtendedState(NORMAL);
                        reOpen.stop();
                    }
                });
                reOpen.start();
            }
            else if(e.getSource() == btnEditarContacto){
                if(tDirectorio.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Seleccione el contacto"
                            + " que desea editar primero");
                }
                else{
                    System.out.println(tDirectorio.getSelectedRow());
                    VentanaEditarContacto ventanaEditarContacto = 
                        new VentanaEditarContacto(directorio, tDirectorio.getSelectedRow());
                    setExtendedState(ICONIFIED);
                    reOpen = new Timer(1000, null);
                    reOpen.addActionListener((ActionEvent a) -> {
                        if(ventanaEditarContacto.huboRetorno() == true){
                            directorio.establecerDirectorioPersistente();
                            limpiarTabla();
                            establecerTabla("todos");
                            mostrarCB();    
                            setExtendedState(NORMAL);
                            btnAgregarContacto.setVisible(false); btnConfiguracion.setVisible(false);
                            btnAgregarContacto.setVisible(true); btnConfiguracion.setVisible(true);
                            reOpen.stop();                        
                        } 
                    });
                    reOpen.start();
                }
                
            }
            else if(e.getSource() == btnProfesores){
                limpiarTabla();
                establecerTabla("profesor");
                mostrarCB();
            }
            else if(e.getSource() == btnEstudiantes){
                limpiarTabla();
                establecerTabla("estudiante");
                mostrarCB();
            }
            else if(e.getSource() == btnEmpleados){
                limpiarTabla();
                establecerTabla("empleado");
                mostrarCB();
            }
            else if(e.getSource() == btnTodos){
                directorio.establecerDirectorioPersistente();
                limpiarTabla();
                establecerTabla("todos");
                mostrarCB();
                btnAgregarContacto.setVisible(false); btnConfiguracion.setVisible(false);
                btnAgregarContacto.setVisible(true); btnConfiguracion.setVisible(true);
            }
            else if(e.getSource() == btnConfiguracion){
                VentanaConfiguracion ventanaConfiguracion = new VentanaConfiguracion(directorio);
                setExtendedState(ICONIFIED);
                reOpen = new Timer(1000, null);
                reOpen.addActionListener((ActionEvent a) -> {
                    if(ventanaConfiguracion.isClosed() == true){
                        directorio.establecerDirectorioPersistente();
                        limpiarTabla();
                        establecerTabla("todos");
                        mostrarCB();
                        setExtendedState(NORMAL);
                        btnAgregarContacto.setVisible(false); btnConfiguracion.setVisible(false);
                        btnAgregarContacto.setVisible(true); btnConfiguracion.setVisible(true);
                        reOpen.stop();
                    }
                });
                reOpen.start();
            }             
            else if(e.getSource() == btnAyuda){
                lblAyuda.setVisible(true);
                spDirectorio.setVisible(false);
            }
            else if(e.getSource() == btnMinimizar){
                setExtendedState(ICONIFIED);
            }
            else if(e.getSource() == btnCerrar){
                dispose();
            }
            else if(e.getSource() == lblAyuda){
                lblAyuda.setVisible(false);
                spDirectorio.setVisible(true);
            }
        }
    }

    private void mostrarCB(){
        int aux = tDirectorio.getRowCount();
        if(aux > 0){
        for(int i=0; i<aux; i++){
            tDirectorio.editCellAt(i,3);
        }
        tDirectorio.editCellAt(0,0);
        }
    }

    class imagenFondo extends JPanel{
        private Image fondo;
        @Override
        public void paint(Graphics g) {
            fondo = new ImageIcon(getClass().getResource("/imagenes/directorio.png")).getImage();
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
    
    private void establecerTabla(String estamento){
        ArrayList<Contacto> contactos = directorio.getContactos(estamento);
        int n = 0;
        for(Contacto c : contactos){
            if(c != null){
                String nombres = c.getNombres();
                String apellidos = c.getApellidos();
                String numeros;

                comboBox = new JComboBox(){
                    @Override
                    public Object getSelectedItem()
                    {
                        Object selected = super.getSelectedItem();

                        if (selected == null)
                            selected = "(Presiona Aqui)";

                        return selected;
                    }};
                
                ArrayList<Map<String, String>> tels = c.getTelefonos();
                for(Map<String, String> tel : tels){
                    numeros = "("+tel.get("tipo")+") "+tel.get("numero");
                    comboBox.addItem(numeros);
                }

                Object[] contacto = {(int) n, (String) nombres, (String) apellidos, null};

                modeloTabla.addRow(contacto);
                tDirectorio.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
                n++;
            }
        }
    }
    
    private void limpiarTabla(){
        int filas = tDirectorio.getRowCount();
        for(int i = filas -1; i >= 0; i--){
            modeloTabla.removeRow(i);
        }
    }
}