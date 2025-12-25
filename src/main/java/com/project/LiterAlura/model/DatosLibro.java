package com.project.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Integer descargas) {

    @Override
    public String toString() {
        return
                "Titulo: " + titulo +
                "\nAutor: " + autor +
                "\nIdioma: " + idioma +
                "\nDescargas: " + descargas;
    }
}
