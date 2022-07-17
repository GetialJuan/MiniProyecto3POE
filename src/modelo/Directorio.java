/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.VentanaDirectorio;

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
        
        establecerDirectorioPersistente();
        /*
        try {
            fwDirectorio = new FileWriter(flDirectorio,true);
        } catch (IOException ex) {
            Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public void agregarContacto(String fechaDeNacimiento, String iD, 
            String nombres, String apellidos, String estamento, 
            ArrayList<Map<String,String>> direcciones, 
            ArrayList<Map<String,String>> telefonos)
    {
        
        Contacto nuevoContacto = new Contacto(fechaDeNacimiento, iD, nombres, 
                apellidos, estamento, direcciones, telefonos);
        
        directorio.add(nuevoContacto);
        
        try {
            FileOutputStream fos = new FileOutputStream(flDirectorio,true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(nuevoContacto);
            
            fos.close();
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("no se encontro el archivo");
        }catch (IOException ex){
            System.out.println("no se encontro el archivo");
        }
        
        //Se añaden  los datos al txt
            /*
            PrintWriter pwDirectorio = new PrintWriter(fwDirectorio);
            pwDirectorio.println();
            pwDirectorio.print(iD+","+nombres+","+apellidos+","+fechaDeNacimiento
            +","+estamento+",");
            for(Map<String,String> e : telefonos){
            pwDirectorio.print(e);
            }
            pwDirectorio.print(",");
            for(Map<String,String> e : direcciones){
            pwDirectorio.print(e);
            }
            resetearFileWriter();*/
    }
    
    public void agregarContacto(Contacto contacto){
        directorio.add(contacto);
    }
    
    public ArrayList<Contacto> getContactos(){
        return directorio;
    }
    
    private void establecerDirectorioPersistente(){
        try {
            FileInputStream fis = new FileInputStream(flDirectorio);
            ObjectInputStream ois = null;
            
            while(fis.available() > 0){
                ois = new ObjectInputStream(fis);
                this.directorio.add((Contacto)ois.readObject());
            }
            
            fis.close();
            if(ois != null){
                ois.close();
            }
            
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            Logger.getLogger(VentanaDirectorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            
        }
    }
    
    /*
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
    }*/
    
}
