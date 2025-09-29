package com.br.WFgeren.service;

import java.util.List;
import java.util.Optional;

import com.br.WFgeren.Exception.ItemNaoExisteException;
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

    public Optional<Itens> buscarPorNome(String nome) {
        return itensRepository.findByNome(nome);
    }

    public Itens salvar(Itens item) {
        return itensRepository.save(item);
    }

    public void deletar(int id) {
        itensRepository.deleteById(id);
    }
    public Itens atualizarItem(int id,Itens itemAtualizado) {
        Itens item = itensRepository.findById(id)
                .orElseThrow(() -> new ItemNaoExisteException("Item não existe."));

        if (itemAtualizado.getNome() != null && !itemAtualizado.getNome().isBlank()) {
            item.setNome(itemAtualizado.getNome());
        }
        if (itemAtualizado.getConjunto() != null) {
            item.setConjunto(itemAtualizado.getConjunto());
        }
        if (itemAtualizado.getDukat() != 0) {
            item.setDukat(itemAtualizado.getDukat());
        }
        if (itemAtualizado.getPlatina() != 0) {
            item.setPlatina(itemAtualizado.getPlatina());
        }
        item.setQuantidade(itemAtualizado.getQuantidade());


        return itensRepository.save(item);
    }
}