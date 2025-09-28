package com.br.WFgeren.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(mappedBy = "inventario")
    @JsonBackReference
    private Usuario usuario;
    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conjunto> conjuntos = new ArrayList<>();

    public Inventario(){}
    public Inventario(Usuario usuario, List<Conjunto> conjuntos){
        this.usuario = usuario;
        this.conjuntos = conjuntos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Conjunto> getConjuntos() {
        return conjuntos;
    }

    public void setConjuntos(List<Conjunto> conjuntos) {
        this.conjuntos = conjuntos;
    }
}