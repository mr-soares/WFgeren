package com.br.WFgeren.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "conjunto")
public class Conjunto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false,length = 255)
    private String nome;
    @OneToMany(mappedBy = "conjunto",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Itens> itens;
    @ManyToOne
    private Categoria categoria;
    @Column(nullable = false)
    private int valor;
    @ManyToOne()
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Conjunto(){}
    public Conjunto(String nome, List<Itens> itens, Categoria categoria, int valor){
        this.nome = nome;
        this.itens = itens;
        this.categoria = categoria;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Itens> getItens() {
        return itens;
    }

    public void setItens(List<Itens> itens) {
        this.itens = itens;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
