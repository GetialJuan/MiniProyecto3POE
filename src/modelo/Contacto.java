/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author invitado
 */
public class Contacto {
    
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
}
