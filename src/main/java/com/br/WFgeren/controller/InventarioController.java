package com.br.WFgeren.controller;

import com.br.WFgeren.model.Inventario;
import com.br.WFgeren.service.InventarioService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    private final InventarioService inventarioService;
    public InventarioController(InventarioService inventarioService){
        this.inventarioService = inventarioService;
    }

    @GetMapping("/{id}")
    public Optional<Inventario> todosOsItensDoInventario(@PathVariable int id){
        return inventarioService.buscarInventarioId(id);
    }
    @PutMapping("/{id}")
    public Inventario atualizarInventario(@PathVariable int id,@RequestBody Inventario inventarioAtualizado){
        return inventarioService.atualizarInventario(id,inventarioAtualizado);
    }
}