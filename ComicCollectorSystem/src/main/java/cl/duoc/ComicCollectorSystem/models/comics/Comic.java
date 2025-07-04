/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.ComicCollectorSystem.models.comics;

import cl.duoc.ComicCollectorSystem.exceptions.NoNombreException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author Home
 */
public class Comic implements Comparable<Comic> {
    private String isbn;
    private String titulo;
    private String autor;
    private int anio; 
 
    public Comic(String isbn, String titulo, String autor, int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    } 

    public String getIsbn() {
        return isbn;
    } 
 
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
 
    public int getAnio() {
        return anio;
    }
    
    public String mostrarComic() {
        return isbn + " | " + titulo + " | " + autor + " | " + anio;
    }

    public static Comic buscarComic(List<Comic> comics, String nombre) throws NoNombreException {
        if (comics == null || comics.isEmpty()) {
            throw new NoNombreException("Por favor ingrese un nombre válido");
        }            
        
        List<Comic> añadidos = new ArrayList<>();
        for (Comic comic : comics) {
            if (comic.getTitulo().equalsIgnoreCase(nombre)) {
                return comic;
            }
        }
        throw new NoNombreException ("El libro " + nombre + " no se encuentra");
    }    

    public static List<Comic> Prestamo(List<Comic> listaComics, List<Comic> prestamo, String retiro) throws NoNombreException {
        if (retiro == null || retiro.isEmpty()) {
            throw new NoNombreException("Por favor ingrese un nombre válido");
        }
        Comic encontrado = null;
        for (Comic comic : listaComics) {
            if (comic.getTitulo().equalsIgnoreCase(retiro)) {
                encontrado = comic;
                break;
            }
        }
        if (encontrado != null) {
            listaComics.remove(encontrado);
            prestamo.add(encontrado);
        } else {
            throw new NoNombreException("El libro " + retiro + " no se encuentra");
        }
        return prestamo;
    }

    public static List<Comic> Devolucion(List<Comic> comics, List<Comic> prestamo, String devolver) throws NoNombreException {
    if (devolver == null || devolver.isEmpty()) {
        throw new NoNombreException("Por favor ingrese un nombre válido");
    }

        Comic devuelto = null;
  
        for (Comic comic : prestamo) {
            if (comic.getTitulo().equalsIgnoreCase(devolver)) {
            devuelto = comic;
            break;
            }
        }
 
        if (devuelto != null) {
            prestamo.remove(devuelto);
            comics.add(devuelto);
        } else {
            throw new NoNombreException("El libro " + devolver + " no se encuentra");
        }

        return comics;
    } 
    
    public static HashSet<Comic> Catalogo(List<Comic> comics) {
        HashSet<String> nombresUnicos = new HashSet<>();
        HashSet<Comic> comicsUnicos = new HashSet<>();
        for (Comic comic : comics) {
            if (nombresUnicos.add(comic.getTitulo().toLowerCase())) {
                comicsUnicos.add(comic);
            } 
        }
        return comicsUnicos;
    }    
    
    public static List<Comic> cargarComicsDesdeCSV() {
        List<Comic> comics = new ArrayList<>();
        try (
            FileReader reader = new FileReader("comics.csv");
            CSVReader csvReader = new CSVReader(reader);
        ) {
            System.out.println(new java.io.File(".").getAbsolutePath());
            
            String[] nextLine;
            csvReader.readNext(); // skip header
            
            while ((nextLine = csvReader.readNext()) != null) {
                String isbn = nextLine[0];
                String titulo = nextLine[1];
                String autor = nextLine[2];
                int anio = Integer.parseInt(nextLine[3]);

                Comic comic = new Comic(isbn, titulo, autor, anio); 
                comics.add(comic);
            }
        } catch (Exception e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
        return comics;
    }

    public static void guardarComicsEnCSV(List<Comic> comics) {
        try (
            FileWriter writer = new FileWriter("comics.csv");
            CSVWriter csvWriter = new CSVWriter(writer);
        ) {
            String[] header = {"isbn", "titulo", "autor", "año"};
            csvWriter.writeNext(header);

            for (Comic comic : comics) {
            String[] data = {
                comic.getIsbn(),
                comic.getTitulo(),
                comic.getAutor(),
                String.valueOf(comic.getAnio())
            };
            csvWriter.writeNext(data);
            }
        } catch (IOException e) {
        System.out.println("Error de guardado: " + e.getMessage());
        }
    }
    
    @Override
    public int compareTo(Comic otro) {
        return this.titulo.compareToIgnoreCase(otro.titulo);
    }
  
}
