package com.br.WFgeren.service;

import com.br.WFgeren.model.Itens;
import com.br.WFgeren.repository.ItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItensService {

    @Autowired
    private ItensRepository itensRepository;

    public List<Itens> listarTodos() {
        return itensRepository.findAll();
    }

    public Optional<Itens> buscarPorId(int id) {
        return itensRepository.findById(id);
    }

    public Itens salvar(Itens item) {
        return itensRepository.save(item);
    }

    public void deletar(int id) {
        itensRepository.deleteById(id);
    }
}