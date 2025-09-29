package com.br.WFgeren.service;

import com.br.WFgeren.model.Categoria;
import com.br.WFgeren.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> todasCategorias(){
        return categoriaRepository.findAll();
    }
    public void apagarCategoria(int id){
    if (!categoriaRepository.existsById(id)){
        throw new RuntimeException("Essa categoria não existe.");
    }
    categoriaRepository.deleteById(id);
    }
    public Optional<Categoria> buscarCategoriaPorNome(String nome){
        if (!categoriaRepository.existsByNome(nome)){
            throw new RuntimeException("Essa categoria não existe.");
        }
        return categoriaRepository.findByNome(nome);
    }
    public Categoria criarCategoria(Categoria novaCategoria){
        if (categoriaRepository.existsByNome(novaCategoria.getNome())){
            throw new RuntimeException("Essa categoria existe");
        }
        return categoriaRepository.save(novaCategoria);
    }

}