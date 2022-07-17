/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import modelo.Contacto;
import modelo.Directorio;

/**
 *
 * @author Juan
 */
public class VentanaDirectorio extends JFrame{
    private Directorio directorio;
    
    //pnls (de botones)
    private JPanel pnlBotonesSuperiores;
    private JPanel pnlBotonesInferiores;
    //jButons
    private JButton btnProfesores;
    private JButton btnEstudiantes;
    private JButton btnEmpleados;
    private JButton btnTodos;
    
    private JButton btnAgregarContacto;
    private JButton btnEditarContacto;
    private JButton btnConfiguracion;
    
    //jtextpane(directorio visual)
    private JTextPane tpDirectorio;
    private JScrollPane spDirectorio;
    
    //contendeor principal
    private Container contPrincipal;
    
    public VentanaDirectorio(){
        iniciarComponentes();
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Directorio");
        setResizable(false);
        //this.directorio = directorio;
    }
    
    private void iniciarComponentes(){
        
        //Se estblece el directorio persistente
        File fl = new File(new File("").getAbsolutePath().
                concat("\\src\\directoriotelefonico\\contactos.dat"));
        directorio = new Directorio(fl);
        
        //btns
        btnProfesores = new JButton("Profesores");
        btnEstudiantes = new JButton("Estudiantes");
        btnEmpleados = new JButton("Empleados");
        btnTodos = new JButton("Todos");
        
        btnAgregarContacto = new JButton("NuevoContacto");
        btnConfiguracion = new JButton("Configuracion");
        btnEditarContacto = new JButton("EditarContacto");
        
        //jpanels
        pnlBotonesSuperiores = new JPanel(new GridLayout(1,4));
        pnlBotonesSuperiores.setBounds(0, 0, 485, 50);
        
        pnlBotonesSuperiores.add(btnProfesores);
        pnlBotonesSuperiores.add(btnEstudiantes);
        pnlBotonesSuperiores.add(btnEmpleados);
        pnlBotonesSuperiores.add(btnTodos);
        
        pnlBotonesInferiores = new JPanel(new GridLayout(1,3));
        pnlBotonesInferiores.setBounds(0, 410, 485, 50);
        
        pnlBotonesInferiores.add(btnEditarContacto);
        pnlBotonesInferiores.add(btnAgregarContacto);
        pnlBotonesInferiores.add(btnConfiguracion);
        
        //directrio
        tpDirectorio = new JTextPane();
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(attribs, "Tahoma");
        StyleConstants.setFontSize(attribs, 18);
        tpDirectorio.setParagraphAttributes(attribs, true);
        
        tpDirectorio.setText(getDirectorioVisual());
        
        spDirectorio = new JScrollPane(tpDirectorio);
        spDirectorio.setBounds(5, 60, 475, 350);
        //contPrincipal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        
        contPrincipal.add(pnlBotonesSuperiores);
        contPrincipal.add(spDirectorio);
        contPrincipal.add(pnlBotonesInferiores);
        
        //listeners
        btnAgregarContacto.addMouseListener(new ManejadorDeEventos());
    }
    
    private String getDirectorioVisual(){
        String dir = "";
        
        ArrayList<Contacto> contactos = directorio.getContactos();
        
        for(Contacto ctt : contactos){
            dir += ctt.getNombres()+" "+ctt.getApellidos()+"\t";
            
            ArrayList<Map<String, String>> tels = ctt.getTelefonos();
            
            for(Map<String, String> tel : tels){
                dir += "("+tel.get("tipo")+")"+tel.get("numero")+"|";
            }
            dir += "\n";
        }
        
        return dir;
    }
    
    private class ManejadorDeEventos extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource() == btnAgregarContacto){
                dispose();
                VentanaAgregarContacto ventanaAgregarContacto = 
                        new VentanaAgregarContacto(directorio);
            }
        }

    }
}
