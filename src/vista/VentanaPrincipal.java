/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.File;
import javax.swing.JFrame;
import modelo.Directorio;

/**
 *
 * @author invitado
 */
public class VentanaPrincipal extends JFrame{
    private String rutaAbsoluta;
    private Directorio directorio;
    
    public VentanaPrincipal(){
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa");
        setResizable(false);
        
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        rutaAbsoluta = new File("").getAbsolutePath();
        directorio = new Directorio(new File(rutaAbsoluta.
                concat("\\src\\directoriotelefonico\\contactos.txt")));
    }
    
}
