/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.ComicCollectorSystem.managers;

import cl.duoc.ComicCollectorSystem.exceptions.NoUsuarioException;
import cl.duoc.ComicCollectorSystem.models.Usuario;
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
     
    public Manager() {   
        this.usuarios = new HashMap<>(); 
    } 
    
    public static Manager getInstancia() {  
        if (instancia == null) {
            instancia = new Manager(); 
        }
        return instancia;
    } 
    
    public boolean agregarUsuario (Usuario usuario) {
        if (usuarios.containsKey(usuario.getRut())) {
            return false;
        }
        usuarios.put(usuario.getRut(), usuario);
        return true;
    }
     
    public Usuario obtenerUsuario(String rut) {
        return usuarios.get(rut);
    }
    
    public Usuario buscarUsuario(String rut) throws NoUsuarioException {
        if (usuarios.containsKey(rut)) {
            return usuarios.get(rut);
        }
        throw new NoUsuarioException("Usuario con rut " + rut + " no encontrado");
    }
    
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
}