/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.ComicCollectorSystem.models;

/**
 *
 * @author Home
 */
public class Usuario {
    private String rut;
    private String nombre; 
    private String apellidoPaterno; 
    private String apellidoMaterno;
    private String mail;
    private int teléfono;    

    public Usuario (String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String mail, int teléfono) {
        if (!validarRut(rut)) {
            throw new IllegalArgumentException("RUT no válido");
        }
        
        this.rut = rut;
        this.nombre = nombre; 
        this.apellidoPaterno = apellidoPaterno; 
        this.apellidoMaterno = apellidoMaterno;
        this.mail = mail;
        this.teléfono = teléfono;
    }
    
    private boolean validarRut(String rut) {
        if (rut == null || rut.isEmpty()) {
            return false;
        } else {  
        return rut.length() == 9; 
        } 
    }
    
    public boolean registrarUsuario() {       
        if (validarRut(rut)) { 
            System.out.println("\n Usuario registrado correctamente: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno);
            return true;
        } 
        return false;     
    }
     
    public void mostrarDatos() {
        System.out.println("\n Datos del usuario");
        System.out.println("RUT: " + this.rut);
        System.out.println("Nombre completo: " + nombre + "" + apellidoPaterno + "" + apellidoMaterno);
        System.out.println("Mail: " + this.mail);
        System.out.println("Teléfono: " + this.teléfono); 
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut; 
    } 
    
}
