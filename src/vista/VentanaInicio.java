package vista;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Laboratorio N.3: tercer miniproyecto. Archivo: VentanaInicio.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 17-07-2022, Fecha última modificación: 18-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
 */

public class VentanaInicio extends JFrame{

    private final imagenFondo imagenFondo = new imagenFondo();
    private JLabel cargando;
    private ImageIcon icono;
    private JPanel jpanel;
    private Clip sonido;
    private Timer t;

    public VentanaInicio() throws MalformedURLException{
        setUndecorated(true);
        this.setContentPane(imagenFondo);
        Shape forma = new RoundRectangle2D.Double(0,0,300,400,30,30);
        setSize(300,400);
        this.setShape(forma);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
    }
    
    private void iniciarComponentes() throws MalformedURLException{
        URL url = getClass().getResource("/imagenes/arenaf.gif");
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        label.setBounds(110,240,icon.getIconWidth(),icon.getIconHeight());
        this.getContentPane().add(label);
        /*try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File("src\\Musica\\fondo.wav")));
            sonido.start();
            sonido.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("" + e);
        }*/
        t = new Timer(5000, null);
        t.setInitialDelay(5000);
        t.addActionListener((ActionEvent e) -> {
            this.dispose();
            VentanaDirectorio directorio = new VentanaDirectorio();
            t.stop();
        });
        t.start();
    }

    class imagenFondo extends JPanel{
        private Image fondo;
        @Override
        public void paint(Graphics g) {
            fondo = new ImageIcon(getClass().getResource("/imagenes/cargando.png")).getImage();
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}