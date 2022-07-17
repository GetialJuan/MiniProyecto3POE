/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import modelo.Directorio;

/**
 *
 * @author Juan
 */
public class VentanaConfiguracion extends JFrame{
    
    private Directorio directorio;
    
    //btns
    private JButton btnCrearBackup;
    private JButton btnRestaurarInformacion;
    private JButton btnExportarInformacion;
    private JButton btnBorrarInformacion;
    
    //contendeor principal
    private Container contPrincipal;
    
    public VentanaConfiguracion(Directorio directorio){
        this.directorio = directorio;
        iniciarComponentes();
        setSize(300, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Configuracion");
        setResizable(false);
    }
    
    private void iniciarComponentes(){
        
        //btns
        btnCrearBackup = new JButton("Crear Backup");
        btnCrearBackup.setBounds(65, 20, 150, 25);
        
        btnExportarInformacion = new JButton("Exportar Archivo");
        btnExportarInformacion.setBounds(65, 55, 150, 25);
        
        btnBorrarInformacion = new JButton("Borrar Informacion");
        btnBorrarInformacion.setBounds(65, 90, 150, 25);
        
        btnRestaurarInformacion = new JButton("Restaurar Informacion");
        btnRestaurarInformacion.setBounds(54, 125, 170, 25);
        
        //Contenedor Principal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        
        contPrincipal.add(btnCrearBackup);
        contPrincipal.add(btnExportarInformacion);
        contPrincipal.add(btnRestaurarInformacion);
        contPrincipal.add(btnBorrarInformacion);
        
        
    }
}
