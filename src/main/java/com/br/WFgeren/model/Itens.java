package com.br.WFgeren.model;

import jakarta.persistence.*;

@Entity
@Table(name = "itens")
public class Itens {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(unique = true,nullable = false)
    private String nome;
    @Column(nullable = false)
    private int dukat;
    @Column(nullable = false)
    private int platina;
    @Column(nullable = false)
    private int quantidade;
    @ManyToOne
    @JoinColumn(name = "conjunto_id", nullable = true)
    private Conjunto conjunto;

    public Itens(){}
    public Itens(String nome, int dukat,int platina, int quantidade){
        this.nome = nome;
        this.dukat = dukat;
        this.platina = platina;
        this.quantidade = quantidade;
    }

    public Conjunto getConjunto() {
        return conjunto;
    }

    public void setConjunto(Conjunto conjunto) {
        this.conjunto = conjunto;
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

    public int getDukat() {
        return dukat;
    }

    public void setDukat(int dukat) {
        this.dukat = dukat;
    }

    public int getPlatina() {
        return platina;
    }

    public void setPlatina(int platina) {
        this.platina = platina;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}