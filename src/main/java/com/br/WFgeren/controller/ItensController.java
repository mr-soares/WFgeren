package com.br.WFgeren.controller;

import com.br.WFgeren.model.Itens;
import com.br.WFgeren.service.ItensService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Necessário para o @Valid

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens")
public class ItensController {

    public final ItensService itensService;

    public ItensController(ItensService itensService){
        this.itensService = itensService;
    }

    @GetMapping
    public List<Itens> buscarTodosItens(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        if (nome != null && !nome.isEmpty()) {
            return itensService.buscarPorNome(nome)
                    .map(List::of)
                    .orElse(List.of());
        }
        return itensService.listarTodos();
    }

    @PostMapping
    public Itens criarNovoItem(@Valid @RequestBody Itens novoItem){
        return  itensService.salvar(novoItem);
    }

    @PutMapping("/{id}")
    public Itens atualizarItem(@PathVariable int id, @Valid @RequestBody Itens itemAtualizado){
        return itensService.atualizarItem(id,itemAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarItem(@PathVariable int id){
        itensService.deletar(id);
    }
}