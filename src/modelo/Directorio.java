/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author invitado
 */
public class Directorio {
    private ArrayList<Contacto> directorio;
    private FileWriter fwDirectorio;
    private File flDirectorio;

    public Directorio(File txtDirectorio) {
        directorio = new ArrayList<>();
        flDirectorio = txtDirectorio;
        try {
            fwDirectorio = new FileWriter(flDirectorio,true);
        } catch (IOException ex) {
            Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarContacto(String fechaDeNacimiento, String iD, 
            String nombres, String apellidos, String estamento, 
            ArrayList<Map<String,String>> direcciones, 
            ArrayList<Map<String,String>> telefonos)
    {
        
        Contacto nuevoContacto = new Contacto(fechaDeNacimiento, iD, nombres, 
                apellidos, estamento, direcciones, telefonos);
        
        directorio.add(nuevoContacto);
        
        //Se añaden  los datos al txt
        
        PrintWriter pwDirectorio = new PrintWriter(fwDirectorio);
        pwDirectorio.println();
        pwDirectorio.print(iD+","+nombres+","+apellidos+","+fechaDeNacimiento
                +","+estamento+",");
        System.out.println(pwDirectorio);
        for(Map<String,String> e : telefonos){
            pwDirectorio.print(e);
        }
        pwDirectorio.print(",");
        for(Map<String,String> e : direcciones){
            pwDirectorio.print(e);
        }
        resetearFileWriter();
    }
    
    private void resetearFileWriter(){
        try {
            if(fwDirectorio != null){
                fwDirectorio.close();
            }
            fwDirectorio = new FileWriter(flDirectorio,true);
            System.out.println("se reseto el fw");
        } catch (IOException ex) {
            Logger.getLogger(Directorio.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    
}
