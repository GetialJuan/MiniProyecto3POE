/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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
    private Vector<Map<String, String>> direcciones;
    private Vector<Map<String, String>> telefonos;
    
    public Contacto(String fechaDeNacimiento, String iD, String nombres, 
            String apellidos, String estamento, 
            Vector<Map<String, String>> direcciones, 
            Vector<Map<String, String>> telefonos) {
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.iD = iD;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estamento = estamento;
        this.direcciones = direcciones;
        this.telefonos = telefonos;
    }
    
    public void agregarDireccion(String direccion,String barrio,String ciudad){
        Map<String,String> dir = new HashMap();
        dir.put("direccion", direccion);
        dir.put("barrio", barrio);
        dir.put("ciudad", ciudad);
        
        direcciones.add(dir);
    }
    //estos metodos pueden ser utiles en el futuro
    /*
    public void agregarTelefono(String numero, String tipo){
        Map<String,String> telefono = new HashMap();
        telefono.put("numero", numero);
        telefono.put("tipo", tipo);
        
        telefonos.add(telefono);
    }*/
}
