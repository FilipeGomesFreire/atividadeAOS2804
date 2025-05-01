package com.funny.autismo_app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Ex: Leve, Moderado, Severo

    @OneToMany(mappedBy = "diagnostico")
    private List<Crianca> criancas;

    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Crianca> getCriancas() {
        return criancas;
    }

    public void setCriancas(List<Crianca> criancas) {
        this.criancas = criancas;
    }

    
}
