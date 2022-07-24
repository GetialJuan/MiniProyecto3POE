package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Laboratorio N.3: tercer miniproyecto. Archivo: Contacto.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 16-07-2022, Fecha última modificación: 23-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
 */

public class Contacto implements Serializable {
    
    private String fechaDeNacimiento;
    private String iD;
    private String nombres;
    private String apellidos;
    private String estamento;
    private final ArrayList<Map<String, String>> direcciones;
    private final ArrayList<Map<String, String>> telefonos;
    
    public Contacto(String fechaDeNacimiento, String iD, String nombres, 
            String apellidos, String estamento, 
            ArrayList<Map<String, String>> direcciones, 
            ArrayList<Map<String, String>> telefonos) {
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.iD = iD;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estamento = estamento;
        this.direcciones = direcciones;
        this.telefonos = telefonos;
    }
    
    public void agregarDireccion(String direccion,String barrio,String ciudad){
        Map<String,String> dir = new HashMap<>();
        dir.put("direccion", direccion);
        dir.put("barrio", barrio);
        dir.put("ciudad", ciudad);
        
        direcciones.add(dir);
    }
    
    public void agregarTelefono(String numero, String tipo){
        Map<String,String> telefono = new HashMap<>();
        telefono.put("numero", numero);
        telefono.put("tipo", tipo);
        
        telefonos.add(telefono);
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEstamento(String estamento) {
        this.estamento = estamento;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEstamento() {
        return estamento;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public String getiD() {
        return iD;
    }

    public ArrayList<Map<String, String>> getDirecciones() {
        return direcciones;
    }

    public ArrayList<Map<String, String>> getTelefonos() {
        return telefonos;
    }
    
    public void eliminarDireccion(int dirEliminar){
        try{
            this.direcciones.remove(dirEliminar);
        }catch (IndexOutOfBoundsException e){
            System.out.println("no hay direcciones para eliminar");
        }
        
    }
    
    public void eliminarTelefono(int telEliminar){
        try{
            this.telefonos.remove(telEliminar);
        }catch (IndexOutOfBoundsException e){
            System.out.println("No hay telefonos para eliminar");
        }
    }
}