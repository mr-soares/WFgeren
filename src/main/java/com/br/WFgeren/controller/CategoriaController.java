package com.br.WFgeren.controller;

import com.br.WFgeren.model.Categoria;
import com.br.WFgeren.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Api Categoria  Validado
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    //criar, editar, excluir, procurar todos e procurar pelo nome

    @GetMapping
    public List<Categoria> todasCategorias(){
        return categoriaService.todasCategorias();
    }
    @DeleteMapping("/{id}")
    public void DeletarCategoria(@PathVariable int id){
        categoriaService.apagarCategoria(id);
    }
    @GetMapping("/{nome}")
    public Optional<Categoria> buscarCategoriaPorNome(@PathVariable String nome){
        return categoriaService.buscarCategoriaPorNome(nome);
    }
    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria novaCategoria){
        return categoriaService.criarCategoria(novaCategoria);
    }
    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable int id, @RequestBody Categoria categoriaAtualizada){
        return categoriaService.editarCategoria(id, categoriaAtualizada);
    }
}