/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.ComicCollectorSystem.managers;

import cl.duoc.ComicCollectorSystem.exceptions.NoUsuarioException;
import cl.duoc.ComicCollectorSystem.models.cuentas.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Home
 */
public class Manager {
    private Map < String, Usuario > usuarios;
    private static Manager instancia;
    private int balance;
     
    public Manager() {    
        this.usuarios = new HashMap<>(); 
        this.balance = 0;
    }
    
     public void SumarBalance(int total) {
        this.balance += total; 
    }
    
    public static Manager getInstancia() {  
        if (instancia == null) {
            instancia = new Manager(); 
        }
        return instancia;
    } 
    
    public boolean agregarUsuario (Usuario usuario) {
        if (usuarios.containsKey(usuario.getRut())) {
            System.out.println("Ya existe un usuario con ese nombre");
            return false;
        }
        usuarios.put(usuario.getRut(), usuario); 
        System.out.println("Usuario" + usuario + "agregado con exito");
        return true;
    }
    
    public Usuario buscarUsuario(String rut) throws NoUsuarioException {
        if (usuarios.containsKey(rut)) {
            return usuarios.get(rut);
        }
        throw new NoUsuarioException("Usuario con rut " + rut + " no encontrado");
    }
    
    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario user : usuarios.values()) {
            System.out.println(user);
        }
    }
    
    public Usuario obtenerUsuario(String rut) {
        return usuarios.get(rut);
    }
    
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public int getBalance() {
        return balance;
    }
    
    
}