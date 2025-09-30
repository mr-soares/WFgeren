package com.br.WFgeren.service;

import java.util.List;
import java.util.Optional;

import com.br.WFgeren.Exception.ItemNaoExisteException;
import com.br.WFgeren.model.Conjunto;
import com.br.WFgeren.model.Inventario;
import com.br.WFgeren.repository.ConjuntoRepository;
import org.springframework.stereotype.Service;

import com.br.WFgeren.model.Itens;
import com.br.WFgeren.repository.ItensRepository;

@Service
public class ItensService {

    private final ItensRepository itensRepository;
    private final ConjuntoRepository conjuntoRepository;

    public ItensService(ItensRepository itensRepository, ConjuntoRepository conjuntoRepository) {
        this.itensRepository = itensRepository;
        this.conjuntoRepository = conjuntoRepository;
    }

    public List<Itens> listarTodos() {
        return itensRepository.findAll();
    }

    public Optional<Itens> buscarPorNome(String nome) {
        return itensRepository.findByNome(nome);
    }

    public Itens salvar(Itens item) {
        if (item.getConjunto() != null) {
            Conjunto conjunto = item.getConjunto();

            if (conjunto.getId() != 0) { // Se tiver id, tenta buscar
                conjunto = conjuntoRepository.findById(conjunto.getId())
                        .orElseGet(() -> conjuntoRepository.save(new Conjunto()));
            } else { // Se não tiver id, cria novo conjunto
                conjunto = conjuntoRepository.save(new Conjunto());
            }

            item.setConjunto(conjunto);
        }

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