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
 * Laboratorio N.3: tercer miniproyecto. Archivo: Directorio.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 16-07-2022, Fecha última modificación: 17-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
 */

public class Directorio {
    private final ArrayList<Contacto> directorio;
    private final File flDirectorio;
    private File backup;

    public Directorio(File txtDirectorio) {
        directorio = new ArrayList<>();
        flDirectorio = txtDirectorio;
        backup = new File(new File("").getAbsolutePath().
                concat("\\src\\directoriotelefonico\\backup.dat"));
        
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
    }
    
    public void agregarContacto(Contacto contacto){
        directorio.add(contacto);
        try {
            FileOutputStream fos = new FileOutputStream(flDirectorio,true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contacto);
            
            fos.close();
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("no se encontro el archivo");
        }catch (IOException ex){
            System.out.println("no se encontro el archivo");
        }
    }
    
    public ArrayList<Contacto> getContactos(){
        return directorio;
    }
    
    public void establecerDirectorioPersistente(){
        this.directorio.clear();
        importarInformacion(flDirectorio);
    }
    
    public void exportarInformacion(){
        File dirTxt = new File(
        new File("").getAbsolutePath().
                concat("\\src\\directoriotelefonico\\contactos.txt"));
        //Se añaden  los datos al txt
        
        try {
            FileWriter fw = new FileWriter(dirTxt, false);
            PrintWriter pw = new PrintWriter(fw);
            
            pw.println("iD,nombres,apellidos,fechaDeNacimiento,estamento,"
                    + "telefonos,direcciones");
            for(Contacto c : directorio){
                String strTels = "";
                String strDirs = "";
                ArrayList<Map<String, String>> tels = c.getTelefonos();
                for(Map<String, String> tel : tels){
                    strTels += tel;
                }
                ArrayList<Map<String, String>> dirs = c.getDirecciones();
                for(Map<String, String> dir : dirs){
                    strDirs += dir;
                }
                
                pw.println(c.getiD()+","+c.getNombres()+","+c.getApellidos()+","
                +c.getFechaDeNacimiento()+","+c.getEstamento()+","+strTels+","
                +strDirs);
            }
            
            fw.close();
            pw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getDirectorioVisual(String estamento){
        String dir = "";
        for(Contacto ctt : directorio){
            if(ctt != null){
                if(estamento.equals("todos") || estamento.equals(ctt.getEstamento())){
                    dir += ctt.getNombres()+" "+ctt.getApellidos()+"\t";

                    ArrayList<Map<String, String>> tels = ctt.getTelefonos();

                    for(Map<String, String> tel : tels){
                        dir += "("+tel.get("tipo")+")"+tel.get("numero")+"|";
                    }
                    dir += "\n";
                }
            }
            
        }
        return dir;
    }
    
    public void crearBackup(){
        try {
            backup = new File(
            new File("").getAbsolutePath().
                    concat("\\src\\directoriotelefonico\\backup.dat"));
            FileOutputStream fos = new FileOutputStream(backup,false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            for(Contacto c : directorio){
                oos.writeObject(c);
            }
            
            fos.close();
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("no se encontro el archivo");
        }catch (IOException ex){
            System.out.println("no se encontro el archivo");
        }
    }
    
    public void restaurarInformacion(){
        directorio.clear();
        importarInformacion(backup);
        try {
            FileOutputStream fos = new FileOutputStream(flDirectorio,false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            for(Contacto c : directorio){
                oos.writeObject(c);
            }
            
            fos.close();
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("no se encontro el archivo");
        }catch (IOException ex){
            System.out.println("no se encontro el archivo");
        }
    }
    
    private void importarInformacion(File f){
        try {
            FileInputStream fis = new FileInputStream(f);
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
