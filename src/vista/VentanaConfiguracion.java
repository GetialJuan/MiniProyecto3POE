package vista;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Directorio;

/**
 * Laboratorio N.3: tercer miniproyecto. Archivo: VentanaConfiguracion.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 17-07-2022, Fecha última modificación: 18-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
 */

public class VentanaConfiguracion extends JFrame{

    private final Directorio directorio;
    private JButton btnCrearBackup, btnRestaurarInformacion, btnExportarInformacion, btnBorrarInformacion, btnCerrar;
    private JLabel lblAbajo;
    public boolean closed;
    private Container contPrincipal;
    private List<JButton> botones = new ArrayList<>();
    
    public VentanaConfiguracion(Directorio directorio){
        imagenFondo imagenFondo = new imagenFondo();
        this.directorio = directorio;
        setUndecorated(true);
        this.setContentPane(imagenFondo);
        Shape forma = new RoundRectangle2D.Double(0,0,300,300,30,30);
        this.setShape(forma);
        iniciarComponentes();
        setSize(300, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Configuracion");
        setResizable(false);
    }
    
    private void iniciarComponentes(){
        closed = false;

        //btns
        btnCrearBackup = new JButton();
        btnExportarInformacion = new JButton();
        btnBorrarInformacion = new JButton();
        btnRestaurarInformacion = new JButton();
        btnCerrar = new JButton();

        botones.add(btnCrearBackup); botones.add(btnExportarInformacion); botones.add(btnBorrarInformacion);
        botones.add(btnRestaurarInformacion); botones.add(btnCerrar);

        for(JButton xButton : botones){
            crearBotones(xButton, botones.indexOf(xButton));
        }
 
        btnCrearBackup.setBounds(11,38,278,61); 
        btnExportarInformacion.setBounds(11,101,278,61); 
        btnBorrarInformacion.setBounds(10,230,280,61); 
        btnRestaurarInformacion.setBounds(10,164,280,64);
        btnCerrar.setBounds(260,12,23,23);
        
        //Contenedor Principal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
 
        lblAbajo = new JLabel();
        lblAbajo.setIcon(new ImageIcon(getClass().getResource("/botones/btnAbajo.png")));
        lblAbajo.setBounds(0,282,300,18);
        
        contPrincipal.add(btnCrearBackup);
        contPrincipal.add(btnExportarInformacion);
        contPrincipal.add(btnRestaurarInformacion);
        contPrincipal.add(lblAbajo);
        contPrincipal.add(btnBorrarInformacion);
        contPrincipal.add(btnCerrar);
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
            case 0 -> { return "bac"; }
            case 1 -> { return "Exp"; }
            case 2 -> { return "Bor"; }
            case 3 -> { return "Res"; }
            case 4 -> { return "Cer2"; }
        }
        return "";
    }
    
    private class ManejadorDeEventos extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource() == btnBorrarInformacion){
                directorio.borrarInformacion();
                JOptionPane.showMessageDialog(null, "Se ha borrado la "
                        + "informacion");
            }
            else if(e.getSource() == btnCrearBackup){
                directorio.crearBackup();
                JOptionPane.showMessageDialog(null, "Se ha creado un nuevo "
                        + "backup");
            }
            else if(e.getSource() == btnExportarInformacion){
                directorio.exportarInformacion();
                JOptionPane.showMessageDialog(null, "Se ha creado el archivo "
                        + "contactos.txt");
            }
            else if(e.getSource() == btnRestaurarInformacion){
                directorio.restaurarInformacion();
                JOptionPane.showMessageDialog(null, "Se restauro la "
                        + "informacion del ultimo Backup");
            }
            else if(e.getSource() == btnCerrar){
                dispose();
                closed = true;
            }
        }
    }

    public boolean isClosed(){
        return closed;
    }

    class imagenFondo extends JPanel{
        private Image fondo;
        @Override
        public void paint(Graphics g) {
            fondo = new ImageIcon(getClass().getResource("/imagenes/config.png")).getImage();
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
