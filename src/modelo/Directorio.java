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

/**
 *
 * @author invitado
 */
public class Directorio {
    private ArrayList<Contacto> directorio;
    private PrintWriter pwDirectorio;

    public Directorio(File txtDirectorio) {
        directorio = new ArrayList<>();
        try {
            pwDirectorio = new PrintWriter(new FileWriter(txtDirectorio,true));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //Se ecribe la primera linea en el archivo
        pwDirectorio.println("id,nombres,apellidos,fechaDeNacimiento,estamento,"
                + "telefonos,direcciones");
        
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
        pwDirectorio.print(iD+","+nombres+","+apellidos+","+fechaDeNacimiento
                +","+estamento+",");
        for(Map<String,String> e : telefonos){
            pwDirectorio.print(e);
        }
        pwDirectorio.print(",");
        for(Map<String,String> e : direcciones){
            pwDirectorio.print(e);
        }
        pwDirectorio.println();
    }
    
    
}
