/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cl.duoc.ComicCollectorSystem;

import cl.duoc.ComicCollectorSystem.exceptions.NoNombreException;
import cl.duoc.ComicCollectorSystem.exceptions.NoUsuarioException;
import cl.duoc.ComicCollectorSystem.managers.Manager;
import cl.duoc.ComicCollectorSystem.models.cuentas.Usuario;
import java.util.Scanner;

/**
 * 
 * @author Home  
 */

public class ComicCollectorSystem { 
    public static Scanner sc = new Scanner(System.in);  
    public static Manager manager; 
    public static Usuario usuario;
  
    public static void main(String[] args) throws NoNombreException, NoUsuarioException {
        System.out.println("Menú de Biblioteca Duoc");
        
        Menu menu = new Menu (sc, manager, usuario);
        menu.mostrarMenu(); 
    }      
} 
  