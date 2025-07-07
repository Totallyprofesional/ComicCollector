/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cl.duoc.ComicCollectorSystem;

import static cl.duoc.ComicCollectorSystem.ComicCollectorSystem.manager;
import cl.duoc.ComicCollectorSystem.exceptions.NoNombreException;
import cl.duoc.ComicCollectorSystem.exceptions.NoUsuarioException;
import cl.duoc.ComicCollectorSystem.managers.Manager;
import cl.duoc.ComicCollectorSystem.models.comics.Comic;
import cl.duoc.ComicCollectorSystem.models.cuentas.Usuario;
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
     private Scanner sc = new Scanner(System.in);
    private Manager manager; 
    private Usuario usuario;
    private List<Comic> comics = new ArrayList<>();
    private List<String> prestamo = new ArrayList<>();

    public Menu(Manager manager, Usuario usuario) {
        this.manager = manager;
        this.usuario = usuario;
    }
    
    public void mostrarMenu() throws NoNombreException, NoUsuarioException {
        int opcion = 0;
        
        do {  
            System.out.println("\n Menu Comic Collector");
            System.out.println("");          
            System.out.println("1) Registrar usuario");            
            System.out.println("2) Ingreso de usuario");  
            System.out.println("3) Lista de usuarios"); 
            System.out.println("4) Lista de comics");
            System.out.println("5) Mostrar comics por nombre único");
            System.out.println("6) Mostrar comics por orden");
            System.out.println("7) Guardar cambios");
            System.out.println("8) Exportar reporte a TXT");
            System.out.println("9) Salir");
            
            opcion = sc.nextInt();
            sc.nextLine();
                 
                switch (opcion) {
                    case 1: 
                        RegistrarUsuario(sc);
                        break;
                    case 2:
                        IngresoUsuario(sc);
                        break;
                    case 3:
                        ListaUsuarios(sc);
                        break;
                    case 4:
                        ListaComics(sc);
                        break;
                    case 5:
                        NombresUnicos(sc);
                        break;  
                    case 6:
                        NombresOrdenados(sc);
                        break;  
                    case 7:
                        GuardarCambios();
                        break;  
                    case 8:
                        ExportarReporteTxt();
                        break;      
                    case 9:
                        System.out.println("Fin del programa");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Por favor ingrese una opción");
                        break;    
                }                           
        } while (opcion != 9);        
    }
     
    private void RegistrarUsuario (Scanner sc) throws NoUsuarioException { 
        System.out.println("\n Registro de usuario");
        System.out.print("Ingrese rut (con guión y puntos): "); 
        String rut = sc.nextLine(); 
        
        manager = new Manager();
        if (manager.obtenerUsuario(rut) != null) {
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
               
                if (nuevoUsuario.registrarUsuario() && manager.agregarUsuario (nuevoUsuario)) {         
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
        
        Usuario UsuarioActual = manager.buscarUsuario(rut); 
        
        if (UsuarioActual != null) { 
            System.out.println("Perfil de usuario:"); 
            usuario.mostrarDatos();
        } else {
            System.out.println("RUT no encontrado"); 
        }      
    }
          
    private void ListaUsuarios(Scanner sc) { 
        System.out.println("Lista de usuarios:");
        Manager manager = new Manager();
        manager.mostrarUsuarios();    
        System.out.println("Presione enter para continuar...");
        sc.nextLine();
    } 
    
    private void ListaComics(Scanner sc) {
    System.out.println("\n Lista de Comics:");

    List<Comic> comics = Comic.cargarComicsDesdeCSV();

    if (comics.isEmpty()) {
        System.out.println("No hay comics disponibles.");
    } else {
        for (Comic comic : comics) {
            System.out.println(comic.mostrarComic());
        }
    }

    System.out.println("Presione enter para continuar...");
    sc.nextLine();
}
    
    private void NombresUnicos(Scanner sc) { 
        System.out.println("\n Lista de comics con nombre unico:");
    
        HashSet<Comic> comicsUnicos = Comic.Catalogo(comics); 
    
        for (Comic comic : comicsUnicos) {
            System.out.println(comic.mostrarComic());
        }

        System.out.println("Presione enter para continuar...");
        sc.nextLine();
    }
    
    private void NombresOrdenados(Scanner sc) {
        System.out.println("\n Comics por orden");
        TreeSet<Comic> comicsOrdenados = new TreeSet<>(comics);
        for (Comic comic : comicsOrdenados) { 
            System.out.println(comic.mostrarComic());  
        } 
         
        System.out.println("Presione enter para continuar...");
        sc.nextLine();
    }
    
    private void GuardarCambios() {
        try {
            Comic.guardarComicsEnCSV(comics);
            System.out.println("Cambios guardados satisfactoriamente");
        } catch (Exception e) {
            System.out.println("Error al guardar los cambios: " + e.getMessage());
        }  
    } 
    
    public void ExportarReporteTxt() {
      String fecha = java.time.LocalDate.now().toString();
      String rutaArchivo = "reporte_comics_" + fecha + ".txt";
      try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(rutaArchivo))) {
        writer.write("Reporte de Comics");
        writer.newLine();
        if (comics.isEmpty()) {
          writer.write("No hay comics disponibles.");
          writer.newLine();
        } else { 
          for (Comic comic : comics) {
            writer.write(comic.getIsbn() + " - " + comic.getTitulo() + " - " + comic.getAutor() + " - " + comic.getAnio());
            writer.newLine();
          }
          writer.write("Total de comics: " + comics.size());
          writer.newLine();
          writer.write("Fecha de generación del reporte: " + fecha);
        }
        System.out.println("Reporte exportado exitosamente a: " + rutaArchivo);
      } catch (Exception e) {
        System.err.println("Error al exportar el reporte: " + e.getMessage());
      }
    }  
    
}
