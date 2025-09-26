package com.br.WFgeren.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.WFgeren.model.Itens;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Integer> {
    Optional<Itens> findByNome(String nome);

    List<Itens> findByConjuntoId(Integer conjuntoId);

    List<Itens> findByQuantidadeLessThan(int quantidade);

    List<Itens> findByDukatBetween(int min, int max);

    List<Itens> findByPlatinaBetween(int min, int max);
}