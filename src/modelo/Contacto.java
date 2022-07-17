package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Laboratorio N.3: tercer miniproyecto. Archivo: Contacto.java, Autores (Grupo 01 POE): 
 * Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
 * Juan Sebastian Getial Getial <getial.juan@correounivalle.edu.co>
 * Fecha creación: 16-07-2022, Fecha última modificación: 17-07-2022 
 * Docente: Luis Romo <luis.romo@correounivalle.edu.co>
 */

public class Contacto implements Serializable {
    
    private String fechaDeNacimiento;
    private String iD;
    private String nombres;
    private String apellidos;
    private String estamento;
    private ArrayList<Map<String, String>> direcciones;
    private ArrayList<Map<String, String>> telefonos;
    
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
    
    
    
    //estos metodos pueden ser utiles en el futuro
    /*
    public void agregarDireccion(String direccion,String barrio,String ciudad){
        Map<String,String> dir = new HashMap();
        dir.put("direccion", direccion);
        dir.put("barrio", barrio);
        dir.put("ciudad", ciudad);
        
        direcciones.add(dir);
    }
    
    
    public void agregarTelefono(String numero, String tipo){
        Map<String,String> telefono = new HashMap();
        telefono.put("numero", numero);
        telefono.put("tipo", tipo);
        
        telefonos.add(telefono);
    }*/

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEstamento() {
        return estamento;
    }

    public ArrayList<Map<String, String>> getTelefonos() {
        return telefonos;
    }
}
