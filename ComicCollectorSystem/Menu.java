/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import cl.duoc.ComicCollectorSystem.exceptions.NoNombreException;
import cl.duoc.ComicCollectorSystem.exceptions.NoUsuarioException;
import cl.duoc.ComicCollectorSystem.managers.Manager;
import cl.duoc.ComicCollectorSystem.models.Comic;
import cl.duoc.ComicCollectorSystem.models.Usuario;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author Home
 */
public class Menu {
    private Scanner sc;   
    private Manager Manager; 
    private Usuario usuario;
    private List<String> comics = new ArrayList<>();
    private List<String> prestamo = new ArrayList<>();

    public Menu(Scanner sc, Manager Manager, Usuario usuario) {
        this.sc = sc;
        this.Manager = Manager;
        this.usuario = usuario;
    }
    
    public void mostrarMenu() throws NoNombreException, NoUsuarioException {
        int opcion = 0;
        
        do { 
            System.out.println("\n Menu Comic Collector");
            System.out.println("");          
            System.out.println("1) Registrar usuario");            
            System.out.println("2) Prestamo de Titulos");
            System.out.println("3) Devolucion de libros");
            System.out.println("4) Mostrar comics por nombre único");
            System.out.println("5) Listar comics por orden");
            System.out.println("6. Guardar cambios");
            System.out.println("7. Exportar reporte a TXT");
            System.out.println("8) Salir");
            
            try {
                opcion = sc.nextInt();
                sc.nextLine();
                 
                switch (opcion) {
                    case 1: 
                        RegistrarUsuario(sc);
                        break;
                    case 2:
                        IngresoUsuario(sc);
                        RetiroComics(sc);
                        break;
                    case 3: 
                        IngresoUsuario(sc);
                        DevolverComics(sc);
                        break;  
                    case 4:
                        NombresUnicos(sc);
                        break;  
                    case 5:
                        NombresOrdenados(sc);
                        break;  
                    case 6:
                        GuardarCambios();
                        break;  
                    case 7:
                        ExportarReporteTxt();
                        break;      
                    case 8:
                        System.out.println("Fin del programa");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Por favor ingrese una opción");
                        break;    
                }           
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar una opción válida");
                sc.nextLine(); 
            }
             
        } while (opcion != 9);        
    }
    
    private void RegistrarUsuario (Scanner sc) throws NoUsuarioException { 
        System.out.println("\n Registro de usuario");
        System.out.print("Ingrese rut (con guión y puntos): "); 
        String rut = sc.nextLine(); 
        
        Manager = new Manager();
        if (Manager.obtenerUsuario(rut) != null) {
            System.out.println("Error: Ya existe un usuario con ese RUT");
            return;
        } 
        
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese apellido paterno: "); 
        String apellidoPaterno = sc.nextLine();
        System.out.print("Ingrese apellido materno: ");
        String apellidoMaterno = sc.nextLine();
        System.out.print("Ingrese mail: ");
        String mail = sc.nextLine(); 
        System.out.print("Ingrese teléfono: ");
        int teléfono = sc.nextInt();  
        
        try {        
            Usuario nuevoUsuario = new Usuario (rut, nombre, apellidoPaterno, apellidoMaterno, mail, teléfono);
               
                if (nuevoUsuario.registrarUsuario() && Manager.agregarUsuario (nuevoUsuario)) {         
                    usuario = nuevoUsuario;     
                    usuario.mostrarDatos();              
                }   
              
            } catch (IllegalArgumentException e) { 
                System.out.println("Error: " + e.getMessage());          
            }  
        
        System.out.println("Presione enter para continuar ...");
        sc.nextLine();
    }
    
    private void IngresoUsuario (Scanner sc) throws NoUsuarioException {
        System.out.print("Ingrese RUT del cliente: ");
        String rut = sc.nextLine();
        
        Usuario UsuarioActual = Manager.buscarUsuario(rut); 
        
        if (UsuarioActual != null) { 
            System.out.println("Perfil de usuario:"); 
            usuario.mostrarDatos();
        } else {
            System.out.println("RUT no encontrado"); 
        }      
    }
        
    private void RetiroComics (Scanner sc) throws NoNombreException {
        NombresOrdenados(sc);
         
        try { 
            System.out.println("\n Ingrese comic a retirar: ");         
            String retiro = sc.nextLine();
            Comic.Prestamo(comics, prestamo, retiro);   
            System.out.println("El comic" + retiro + " ha sido retirado"); 
            
        } catch (InputMismatchException e) {
            System.out.println("Nombre inválido");
        } 
        
        System.out.println("Presione enter para continuar ...");
        sc.nextLine();
    }
    
    private void DevolverComics (Scanner sc) throws NoNombreException {
        for (Comic prestamo: comics) {
        System.out.println(comics);
        }
         
        try { 
            System.out.println("\n Ingrese comic a devolver: ");         
            String devolver = sc.nextLine();
            Comic.Devolucion(comics, prestamo, devolver);
            System.out.println("El comic" + devolver + " ha sido devuelto"); 
            
        } catch (InputMismatchException e) {
            System.out.println("Nombre inválido");
        } 
        
        System.out.println("Presione enter para continuar ...");
        sc.nextLine();
    }
    
        private void NombresUnicos(Scanner sc) { 
        System.out.println("\n Lista comics por nombre único");
        HashSet<Comic> comicsUnicos = Comic.CatalogoComics();
        for (Comic comic : comicsUnicos) {
            System.out.println(Comic.mostrarComic());
        }
        
        System.out.println("Presione enter para continuar ...");
        sc.nextLine();
    }
    
    private void NombresOrdenados(Scanner sc) {
        System.out.println("\n Libros por orden");
        TreeSet<Comic> comicsOrdenados = new TreeSet<>(comics);
        for (Comic comic : comicsOrdenados) { 
            System.out.println(Comic.mostrarComic());
        }
         
        System.out.println("Presione enter para continuar ...");
        sc.nextLine();
    }
    
    private void GuardarCambios() {
        try {
            Comic.guardarComicsEnCSV(Comic);
            System.out.println("Cambios guardados satisfactoriamente");
        } catch (Exception e) {
            System.out.println("Error al guardar los cambios: " + e.getMessage());
        }
                
    }
    
    public void ExportarReporteTxt() {
      String fecha = java.time.LocalDate.now().toString();
      String rutaArchivo = "reporte_comics_" + fecha + ".txt";
      try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(rutaArchivo))) {
        writer.write("===== Reporte de Libros =====");
        writer.newLine();
        if (Comic.isEmpty()) {
          writer.write("No hay comics disponibles.");
          writer.newLine();
        } else {
          for (Comic comic : comics.values()) {
            writer.write(Comic.getIsbn() + " - " + Comic.getTitulo() + " - " + Comic.getAutor() + " - " + Comic.getAnioPublicacion());
            writer.newLine();
          }
          writer.write("Total de comics: " + Comic.size());
          writer.newLine();
          writer.write("Fecha de generación del reporte: " + fecha);
        }
        System.out.println("Reporte exportado exitosamente a: " + rutaArchivo);
      } catch (Exception e) {
        System.err.println("Error al exportar el reporte: " + e.getMessage());
      }
    }  
    
}