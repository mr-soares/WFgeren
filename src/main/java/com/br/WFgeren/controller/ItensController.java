package com.br.WFgeren.controller;

import com.br.WFgeren.model.Itens;
import com.br.WFgeren.service.ItensService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//validado apis
@RestController
@RequestMapping("/api/itens")
public class ItensController {

    public final ItensService itensService;

    public ItensController(ItensService itensService){
        this.itensService = itensService;
    }

    //criar, editar, excluir, buscar todos e buscar pelo nome

    @GetMapping
    public List<Itens> buscarTodosItens(){
        return itensService.listarTodos();
    }
    @DeleteMapping("/{id}")
    public void deletarItem(@PathVariable int id){
         itensService.deletar(id);
    }
    @GetMapping("/{nome}")
    public Optional<Itens> buscarItemPeloNome(@PathVariable String nome){
        return itensService.buscarPorNome(nome);
    }
    @PostMapping
    public Itens criarNovoItem(@RequestBody Itens novoItem){
        return  itensService.salvar(novoItem);
    }
    @PutMapping("/{id}")
    public Itens atualizarItem(@PathVariable int id,@RequestBody Itens itemAtualizado){
        return itensService.atualizarItem(id,itemAtualizado);
    }
}