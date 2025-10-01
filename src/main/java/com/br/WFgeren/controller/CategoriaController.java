package com.br.WFgeren.controller;

import com.br.WFgeren.model.Categoria;
import com.br.WFgeren.service.CategoriaService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> buscarCategorias(@RequestParam(required = false) String nome){
        if (nome != null && !nome.isEmpty()) {
            return categoriaService.buscarCategoriaPorNome(nome)
                    .map(List::of)
                    .orElse(List.of());
        }
        return categoriaService.todasCategorias();
    }

    @PostMapping
    public Categoria criarCategoria(@Valid @RequestBody Categoria novaCategoria){
        return categoriaService.criarCategoria(novaCategoria);
    }

    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable int id, @Valid @RequestBody Categoria categoriaAtualizada){
        return categoriaService.editarCategoria(id, categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void DeletarCategoria(@PathVariable int id){
        categoriaService.apagarCategoria(id);
    }
}