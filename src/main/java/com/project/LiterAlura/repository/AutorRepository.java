package com.project.LiterAlura.repository;

import com.project.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);

    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThan(Integer nacimiento, Integer fallecimiento);

    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoIsNull(Integer nacimiento);


}
