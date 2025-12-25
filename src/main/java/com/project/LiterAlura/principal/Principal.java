package com.project.LiterAlura.principal;

import com.project.LiterAlura.model.*;
import com.project.LiterAlura.repository.AutorRepository;
import com.project.LiterAlura.repository.LibroRepository;
import com.project.LiterAlura.service.ConsumoApi;
import com.project.LiterAlura.service.ConvierteDatos;
import org.springframework.stereotype.Service;

import java.util.List;
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
        int opcion = -1;

        while (opcion != 0) {

            System.out.println("\n---------------------------------------");
            System.out.println("""
                \n1- Buscar libro por titulo
                2- Listar libros registrados
                3- listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0 - salir
                """);
            System.out.println("---------------------------------------\n");


            System.out.print("Elija una opción: ");
            String input = scanner.nextLine();

            if (!input.matches("\\d+")) {
                System.out.println("\n---------------------------------------\n");

                System.out.println("Ingrese solo números");
                continue;
            }

            opcion = Integer.parseInt(input);

            switch (opcion) {
                case 1 -> consultaLibro();
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivosEnUnAño();
                case 5 -> librosPorIdioma();
                case 0 -> System.out.println("\n---------------------------------------\n\nSaliendo...\n\n---------------------------------------\n");
                default -> System.out.println("\n---------------------------------------\n\nOpción no disponible");
            }
        }
    }

    private void listarAutoresVivosEnUnAño() {
        System.out.println("\n---------------------------------------\n");
        System.out.println("Ingrese el año");
        System.out.println("\n---------------------------------------\n");
        System.out.print("Año: ");
        Integer anio = scanner.nextInt();
        scanner.nextLine();

        List<Autor> fallecidosDespues = autorRepository.findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThan(anio, anio);

        List<Autor> vivos = autorRepository.findByFechaNacimientoLessThanEqualAndFechaFallecimientoIsNull(anio);

        fallecidosDespues.addAll(vivos);

        if (fallecidosDespues.isEmpty()) {
            System.out.println("\n---------------------------------------\n");
            System.out.println("No se encontraron autores en ese año");
            System.out.println("\n---------------------------------------");
        } else {
            fallecidosDespues.forEach(a -> {
                System.out.println("\nAutor: " + a.getNombre());
                System.out.println("Nacimiento: " + a.getFechaNacimiento());
                System.out.println("Fallecimiento: " + a.getFechaFallecimiento());
                System.out.println("\n---------------------------------------");
            });
        }
    }

    private void librosPorIdioma() {
        System.out.println("---------------------------------------\n");
        System.out.print("Escriba el idioma (por ejemplo: en, es): \n\n");
        System.out.println("---------------------------------------\n");
        System.out.print("Idioma: " );
        String idioma = scanner.nextLine();
        Long cantidad = libroRepository.countByIdioma(idioma);
        System.out.println("\n---------------------------------------\n");
        System.out.println("Cantidad de libros en el idioma " + idioma + ": " + cantidad);
        System.out.println("\n---------------------------------------");

    }

    private void listarAutores() {
        System.out.println("\n---------------------------------------\n");
        System.out.println("Lista de autores registrados");
        System.out.println("\n---------------------------------------\n");
        autorRepository.findAll().forEach(a -> {
            System.out.println("Escritor: " + a.getNombre());
            System.out.println("Nacimiento: " + a.getFechaNacimiento());
            System.out.println("Fallecimiento: " + a.getFechaFallecimiento());
            System.out.println("\n---------------------------------------\n");
        });
    }

    private void listarLibros() {
        System.out.println("\n---------------------------------------");
        System.out.println("\nLista de libros buscados\n");
        System.out.println("---------------------------------------\n");
        libroRepository.findAll().forEach(l -> {
                System.out.println("\nLibro: " + l.getTitulo());
                System.out.println("Autor: " + l.getAutor().getNombre());
                System.out.println("Idioma: " + l.getIdioma());
                System.out.println("Descargas: " + l.getDescargas());
                System.out.println("\n---------------------------------------");
        });
    }

    private void consultaLibro() {
        System.out.println("\n---------------------------------------");
        System.out.println("\nIngrese el nombre del libro\n");
        System.out.println("---------------------------------------\n");
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

        System.out.println(datosLibro);

        System.out.println("\n---------------------------------------");
        System.out.println("\nLibro guardado correctamente");

    }
}

