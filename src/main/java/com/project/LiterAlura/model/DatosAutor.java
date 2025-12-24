package com.project.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") Integer fechaNacimiento,
    @JsonAlias("death_year") Integer fechaFallecimiento) {

    @Override
    public String toString() {
        return nombre;
    }
}
