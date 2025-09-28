package com.br.WFgeren.repository;

import com.br.WFgeren.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByName(String name);

    Optional<Usuario> findByNameIgnoreCase(String name);
}