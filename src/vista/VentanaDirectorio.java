package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
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
 * Laboratorio N.3: tercer miniproyecto. Archivo: VentanaDirectorio.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 16-07-2022, Fecha última modificación: 17-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
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
    
    private JButton btnActualizar;
    
    //jtextpane(directorio visual)
    private JTextPane tpDirectorio;
    private JScrollPane spDirectorio;
    
    //contendeor principal
    private Container contPrincipal;
    
    public VentanaDirectorio(){
        iniciarComponentes();
        setSize(520, 500);
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
        btnActualizar = new JButton("Actualizar");
        
        btnAgregarContacto = new JButton("NuevoContacto");
        btnConfiguracion = new JButton("Configuracion");
        btnEditarContacto = new JButton("EditarContacto");
        
        //jpanels
        pnlBotonesSuperiores = new JPanel(new GridLayout(1,5));
        pnlBotonesSuperiores.setBounds(0, 0, 505, 50);
        
        pnlBotonesSuperiores.add(btnProfesores);
        pnlBotonesSuperiores.add(btnEstudiantes);
        pnlBotonesSuperiores.add(btnEmpleados);
        pnlBotonesSuperiores.add(btnTodos);
        pnlBotonesSuperiores.add(btnActualizar);
        
        pnlBotonesInferiores = new JPanel(new GridLayout(1,3));
        pnlBotonesInferiores.setBounds(0, 410, 505, 50);
        
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
        
        tpDirectorio.setText(getDirectorioVisual("todos"));
        
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
        btnActualizar.addMouseListener(new ManejadorDeEventos());
        btnProfesores.addMouseListener(new ManejadorDeEventos());
        btnEstudiantes.addMouseListener(new ManejadorDeEventos());
        btnEmpleados.addMouseListener(new ManejadorDeEventos());
        btnTodos.addMouseListener(new ManejadorDeEventos());
        btnConfiguracion.addMouseListener(new ManejadorDeEventos());
    }
    
    private String getDirectorioVisual(String estamento){
        String dir = "";
        
        ArrayList<Contacto> contactos = directorio.getContactos();
        for(Contacto ctt : contactos){
            if(estamento.equals("todos") || estamento.equals(ctt.getEstamento())){
                dir += ctt.getNombres()+" "+ctt.getApellidos()+"\t";
            
                ArrayList<Map<String, String>> tels = ctt.getTelefonos();

                for(Map<String, String> tel : tels){
                    dir += "("+tel.get("tipo")+")"+tel.get("numero")+"|";
                }
                dir += "\n";
            }
            
        }
        return dir;
    }
    
    private class ManejadorDeEventos extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource() == btnAgregarContacto){
                VentanaAgregarContacto ventanaAgregarContacto = 
                        new VentanaAgregarContacto(directorio);
            }
            else if(e.getSource() == btnProfesores){
                tpDirectorio.setText(getDirectorioVisual("profesor"));
            }
            else if(e.getSource() == btnEstudiantes){
                tpDirectorio.setText(getDirectorioVisual("estudiante"));
            }
            else if(e.getSource() == btnEmpleados){
                tpDirectorio.setText(getDirectorioVisual("empleado"));
            }
            else if(e.getSource() == btnActualizar || e.getSource() == btnTodos){
                directorio.establecerDirectorioPersistente();
                tpDirectorio.setText(getDirectorioVisual("todos"));
            }
            else if(e.getSource() == btnConfiguracion){
                VentanaConfiguracion ventanaConfiguracion = 
                        new VentanaConfiguracion(directorio);
            }
        }
    }
}