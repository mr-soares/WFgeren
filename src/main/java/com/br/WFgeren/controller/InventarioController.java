package com.br.WFgeren.controller;

import com.br.WFgeren.model.Inventario;
import com.br.WFgeren.service.InventarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    private final InventarioService inventarioService;
    public InventarioController(InventarioService inventarioService){
        this.inventarioService = inventarioService;
    }

    //excluir(OK) e atualizar

    @DeleteMapping("/{id}")
    public void deletarInventario(@PathVariable int id){
        inventarioService.excluirInventario(id);
    }

    @PutMapping("/{id}")
    public Inventario atualizarInventario(@PathVariable int id,@RequestBody Inventario inventarioAtualizado){
        return inventarioService.atualizarInventario(id,inventarioAtualizado);
    }
}