package com.project.LiterAlura.principal;

import com.project.LiterAlura.model.*;
import com.project.LiterAlura.repository.AutorRepository;
import com.project.LiterAlura.repository.LibroRepository;
import com.project.LiterAlura.service.ConsumoApi;
import com.project.LiterAlura.service.ConvierteDatos;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Principal {
    private final ConsumoApi consumoApi;
    private final ConvierteDatos convierteDatos;
    private Scanner scanner = new Scanner(System.in);
    private String urlBase = ("https://gutendex.com/books/?search=");
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public Principal(ConsumoApi consumoApi, ConvierteDatos convierteDatos, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.consumoApi = consumoApi;
        this.convierteDatos = convierteDatos;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu() {
        int opción = -1;

        while (opción != 0) {

            System.out.println("""
                \n1- buscar libro por titulo
                2- Listar libros registrados
                3- listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0 - salir
                """);

            System.out.print("\nElija una opción: ");
            opción = scanner.nextInt();
            scanner.nextLine();

            switch (opción) {
                case 1 -> ConsultaLibro();
            }
        }
    }

    private void ConsultaLibro() {
        System.out.println("\nIngrese el nombre del libro\n");
        System.out.print("Libro: ");
        String titulo = scanner.nextLine();

        String json = consumoApi.obtenerDatos(urlBase + titulo.replace(" ", "+"));
        GutendexDatos datos = convierteDatos.convertir(json, GutendexDatos.class);
        DatosLibro datosLibro = datos.getResults().get(0);

        DatosAutor datosAutor = datosLibro.autor().get(0);

        Autor autor = autorRepository.findByNombre(datosAutor.nombre()).orElseGet(() -> autorRepository.save(new Autor(datosAutor)));

        Libro libro = new Libro(datosLibro);
        libro.setAutor(autor);
        libroRepository.save(libro);

        System.out.println("\nLibro guardado correctamente");

    }
}

