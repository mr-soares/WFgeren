package com.br.WFgeren.repository;

import com.br.WFgeren.model.Conjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConjuntoRepository extends JpaRepository<Conjunto, Integer> {
}
