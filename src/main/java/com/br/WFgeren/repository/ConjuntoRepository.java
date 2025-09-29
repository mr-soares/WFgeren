package com.br.WFgeren.repository;

import com.br.WFgeren.model.Conjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConjuntoRepository extends JpaRepository<Conjunto, Integer> {
    boolean existsByNome(String nome);

    Optional<Conjunto> findByNome(String nome);
}