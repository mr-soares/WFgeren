package com.br.WFgeren.controller;

import com.br.WFgeren.model.Conjunto;
import com.br.WFgeren.service.ConjuntoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conjunto")
public class ConjuntoController {

    private final ConjuntoService conjuntoService;

    public ConjuntoController(ConjuntoService conjuntoService){
        this.conjuntoService = conjuntoService;
    }

    //criar, excluir, procurar todos, procurar um e editar

    @PostMapping
    public Conjunto criarConjunto(Conjunto novoConjunto){
        return conjuntoService.salvarConjunto(novoConjunto);
    }
    @DeleteMapping("/{id}")
    public void deletarConjunto(@PathVariable int id){
        conjuntoService.deletarConjunto(id);
    }
    @GetMapping
    public List<Conjunto> todosConjuntos(){
        return conjuntoService.todosOsConjuntos();
    }
    @GetMapping("/{nome}")
    public Optional<Conjunto> buscarConjuntoPeloNome(@PathVariable String nome){
        return conjuntoService.buscarConjuntoPeloNome(nome);
    }
    @PutMapping("/{id}")
    public Conjunto atualizarConjunto(@PathVariable int id, @RequestBody Conjunto conjuntoAtualizado){
        return conjuntoService.atualizarConjunto(id,conjuntoAtualizado);
    }
}