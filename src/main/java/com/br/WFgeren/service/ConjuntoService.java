package com.br.WFgeren.service;

import com.br.WFgeren.Exception.ConjuntoExisteException;
import com.br.WFgeren.Exception.ItemNaoExisteException;
import com.br.WFgeren.model.Conjunto;
import com.br.WFgeren.model.Itens;
import com.br.WFgeren.repository.ConjuntoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConjuntoService {
    private final ConjuntoRepository conjuntoRepository;

    public ConjuntoService(ConjuntoRepository conjuntoRepository){
        this.conjuntoRepository = conjuntoRepository;
    }

    public List<Conjunto> todosOsConjuntos(){
        return conjuntoRepository.findAll();
    }
    public Conjunto salvarConjunto(Conjunto novoConjunto){
        if (conjuntoRepository.existsById(novoConjunto.getId())){
            throw new ConjuntoExisteException("Esse conjunto já existe!");
        }
        if (conjuntoRepository.existsByNome(novoConjunto.getNome())){
            throw new ConjuntoExisteException("Esse conjunto já existe!");
        }
        conjuntoRepository.save(novoConjunto);
        return novoConjunto;
    }

    public void deletarConjunto(int id){
        if (!conjuntoRepository.existsById(id)){
            throw new ConjuntoExisteException("Esse conjunto não existe!");
        }
        conjuntoRepository.deleteById(id);
    }
    public Conjunto atualizarConjunto(int id, Conjunto novoConjunto){
        Conjunto conjunto = conjuntoRepository.findById(id)
                .orElseThrow(() -> new ConjuntoExisteException("Conjunto não existe."));

        if (novoConjunto.getNome() != null && !novoConjunto.getNome().isBlank()) {
            conjunto.setNome(novoConjunto.getNome());
        }
        if (novoConjunto.getCategoria() != null){
            conjunto.setCategoria(novoConjunto.getCategoria());
        }
        conjunto.setValor(novoConjunto.getValor());
        if (novoConjunto.getItens() != null){
            conjunto.setItens(novoConjunto.getItens());
        }
        return conjuntoRepository.save(conjunto);
    }
    public Optional<Conjunto> buscarConjuntoPeloNome(String nome){
        return conjuntoRepository.findByNome(nome);
    }
}