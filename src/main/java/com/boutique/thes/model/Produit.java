package com.boutique.thes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    private String typeThe;

    private String origine;

    private Double prix;

    private Integer quantiteStock;

    @Column(length = 500)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateReception;

    public Produit() {
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getTypeThe() { return typeThe; }
    public void setTypeThe(String typeThe) { this.typeThe = typeThe; }

    public String getOrigine() { return origine; }
    public void setOrigine(String origine) { this.origine = origine; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public Integer getQuantiteStock() { return quantiteStock; }
    public void setQuantiteStock(Integer quantiteStock) { this.quantiteStock = quantiteStock; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDateReception() { return dateReception; }
    public void setDateReception(LocalDate dateReception) { this.dateReception = dateReception; }
}
