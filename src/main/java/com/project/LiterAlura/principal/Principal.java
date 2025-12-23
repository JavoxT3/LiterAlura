package com.project.LiterAlura.principal;

import com.project.LiterAlura.model.DatosLibro;
import com.project.LiterAlura.model.GutendexDatos;
import com.project.LiterAlura.service.ConsumoApi;
import com.project.LiterAlura.service.ConvierteDatos;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Principal {
    private final ConsumoApi consumoApi;
    private final ConvierteDatos convierteDatos;
    private Scanner scanner = new Scanner(System.in);
    public Principal(ConsumoApi consumoApi, ConvierteDatos convierteDatos) {
        this.consumoApi = consumoApi;
        this.convierteDatos = convierteDatos;
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
        }
    }
    public void ejecutar() {
        String json = consumoApi.obtenerDatos("https://gutendex.com/books/?search=pride");

        GutendexDatos datos = convierteDatos.convertir(json, GutendexDatos.class);

        DatosLibro libro = datos.getResults().get(0);
        System.out.println(libro.titulo());
    }

}
