package vista;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
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
        btnCrearBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnbac.png"))); 
        btnCrearBackup.setAutoscrolls(true);
        btnCrearBackup.setBorder(null);
        btnCrearBackup.setBorderPainted(false);
        btnCrearBackup.setContentAreaFilled(false);
        btnCrearBackup.setFocusPainted(false);
        btnCrearBackup.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnbacp.png"))); 
        btnCrearBackup.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnbacr.png"))); 
        btnCrearBackup.setBounds(11,38,278,61);
        
        btnExportarInformacion = new JButton();
        btnExportarInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnExp.png"))); 
        btnExportarInformacion.setAutoscrolls(true);
        btnExportarInformacion.setBorder(null);
        btnExportarInformacion.setBorderPainted(false);
        btnExportarInformacion.setContentAreaFilled(false);
        btnExportarInformacion.setFocusPainted(false);
        btnExportarInformacion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnExpp.png"))); 
        btnExportarInformacion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnExpr.png"))); 
        btnExportarInformacion.setBounds(11,101,278,61);
        
        btnBorrarInformacion = new JButton();
        btnBorrarInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnBor.png"))); 
        btnBorrarInformacion.setAutoscrolls(true);
        btnBorrarInformacion.setBorder(null);
        btnBorrarInformacion.setBorderPainted(false);
        btnBorrarInformacion.setContentAreaFilled(false);
        btnBorrarInformacion.setFocusPainted(false);
        btnBorrarInformacion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnBorp.png"))); 
        btnBorrarInformacion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnBorr.png"))); 
        btnBorrarInformacion.setBounds(10,230,280,61);
        
        btnRestaurarInformacion = new JButton();
        btnRestaurarInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnRes.png"))); 
        btnRestaurarInformacion.setAutoscrolls(true);
        btnRestaurarInformacion.setBorder(null);
        btnRestaurarInformacion.setBorderPainted(false);
        btnRestaurarInformacion.setContentAreaFilled(false);
        btnRestaurarInformacion.setFocusPainted(false);
        btnRestaurarInformacion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnResp.png"))); 
        btnRestaurarInformacion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnResr.png"))); 
        btnRestaurarInformacion.setBounds(10,164,280,64);
        
        //Contenedor Principal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);

        btnCerrar = new JButton();
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnCer2.png"))); 
        btnCerrar.setAutoscrolls(true);
        btnCerrar.setBorder(null);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnCerp2.png"))); 
        btnCerrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/btnCerr2.png"))); 
        btnCerrar.setBounds(260,12,23,23);

        lblAbajo = new JLabel();
        lblAbajo.setIcon(new ImageIcon(getClass().getResource("/botones/btnAbajo.png")));
        lblAbajo.setBounds(0,282,300,18);
        
        contPrincipal.add(btnCrearBackup);
        contPrincipal.add(btnExportarInformacion);
        contPrincipal.add(btnRestaurarInformacion);
        contPrincipal.add(lblAbajo);
        contPrincipal.add(btnBorrarInformacion);

        contPrincipal.add(btnCerrar);
        
        //listeners
        btnBorrarInformacion.addMouseListener(new ManejadorDeEventos());
        btnCrearBackup.addMouseListener(new ManejadorDeEventos());
        btnExportarInformacion.addMouseListener(new ManejadorDeEventos());
        btnRestaurarInformacion.addMouseListener(new ManejadorDeEventos());
        btnCerrar.addMouseListener(new ManejadorDeEventos());
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
