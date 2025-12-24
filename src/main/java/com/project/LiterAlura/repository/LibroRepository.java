package com.project.LiterAlura.repository;

import com.project.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
