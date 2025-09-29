package com.br.WFgeren.repository;

import com.br.WFgeren.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    boolean existsByNome(String nome);

    Optional<Categoria> findByNome(String nome);
}