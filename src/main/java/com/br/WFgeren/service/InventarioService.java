package com.br.WFgeren.service;

import com.br.WFgeren.model.Conjunto;
import com.br.WFgeren.model.Inventario;
import com.br.WFgeren.repository.ConjuntoRepository;
import com.br.WFgeren.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;
    private final ConjuntoRepository conjuntoRepository;

    public InventarioService(InventarioRepository inventarioRepository, ConjuntoRepository conjuntoRepository){
        this.inventarioRepository = inventarioRepository;
        this.conjuntoRepository = conjuntoRepository;
    }
    public void excluirInventario(int id){
        if (!inventarioRepository.existsById(id)){
            throw new RuntimeException("Esse inventário não existe");
        }
        inventarioRepository.deleteById(id);
    }
    public Inventario atualizarInventario(int id, Inventario inventarioAtualizado) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Esse inventário não existe!"));

        for (Conjunto conjuntoAtualizado : inventarioAtualizado.getConjuntos()) {
            Conjunto conjunto = conjuntoRepository.findById(conjuntoAtualizado.getId())
                    .orElseThrow(() -> new RuntimeException("Conjunto não encontrado"));
            inventario.getConjuntos().add(conjunto);
        }
        return inventarioRepository.save(inventario);
    }
    public Optional<Inventario> buscarInventarioId(int id){
        return inventarioRepository.findById(id);
    }
}