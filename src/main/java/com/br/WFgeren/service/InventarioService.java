package com.br.WFgeren.service;

import com.br.WFgeren.model.Inventario;
import com.br.WFgeren.repository.InventarioRepository;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository){
        this.inventarioRepository = inventarioRepository;
    }
    public void excluirInventario(int id){
        if (!inventarioRepository.existsById(id)){
            throw new RuntimeException("Esse inventário não existe");
        }
        inventarioRepository.deleteById(id);
    }
    public Inventario atualizarInventario(int id,Inventario inventarioAtualizado){
        Inventario inventario = inventarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Esse inventário não existe!"));
        inventario.setConjuntos(inventarioAtualizado.getConjuntos());
        inventarioRepository.save(inventario);
        return inventario;
    }
}