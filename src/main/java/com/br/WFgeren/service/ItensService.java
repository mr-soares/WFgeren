package com.br.WFgeren.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.WFgeren.model.Itens;
import com.br.WFgeren.repository.ItensRepository;

@Service
public class ItensService {

    private final ItensRepository itensRepository;

    public ItensService(ItensRepository itensRepository) {
        this.itensRepository = itensRepository;
    }

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