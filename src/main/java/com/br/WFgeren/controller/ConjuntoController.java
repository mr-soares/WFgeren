package com.br.WFgeren.controller;

import com.br.WFgeren.model.Conjunto;
import com.br.WFgeren.service.ConjuntoService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conjunto")
public class ConjuntoController {

    private final ConjuntoService conjuntoService;

    public ConjuntoController(ConjuntoService conjuntoService){
        this.conjuntoService = conjuntoService;
    }


    @GetMapping
    public List<Conjunto> buscarConjuntos(@RequestParam(required = false) String nome){
        if (nome != null && !nome.isEmpty()) {
            return conjuntoService.buscarConjuntoPeloNome(nome)
                    .map(List::of)
                    .orElse(List.of());
        }
        return conjuntoService.todosOsConjuntos();
    }

    @PostMapping
    public Conjunto criarConjunto(@Valid @RequestBody Conjunto novoConjunto){
        return conjuntoService.salvarConjunto(novoConjunto);
    }

    @PutMapping("/{id}")
    public Conjunto atualizarConjunto(@PathVariable int id, @Valid @RequestBody Conjunto conjuntoAtualizado){
        return conjuntoService.atualizarConjunto(id,conjuntoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarConjunto(@PathVariable int id){
        conjuntoService.deletarConjunto(id);
    }
}