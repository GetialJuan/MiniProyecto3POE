/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import modelo.Contacto;

/**
 *
 * @author Juan
 */
public class VentanaDirectorio extends JFrame{
    private ArrayList<Contacto> directorio;
    
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
        StyleConstants.setFontSize(attribs, 30);
        tpDirectorio.setParagraphAttributes(attribs, true);
        
        tpDirectorio.setText("Este juego pone \n hola");
        
        spDirectorio = new JScrollPane(tpDirectorio);
        spDirectorio.setBounds(5, 60, 475, 350);
        //contPrincipal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        
        contPrincipal.add(pnlBotonesSuperiores);
        contPrincipal.add(spDirectorio);
        contPrincipal.add(pnlBotonesInferiores);
    }
}
