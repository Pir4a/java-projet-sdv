package com.boutique.thes.service;

import com.boutique.thes.model.Produit;
import com.boutique.thes.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> obtenirTousLesProduits() {
        return produitRepository.findAll();
    }

    public List<Produit> obtenirTousLesProduitsTries(String field, String direction) {
        org.springframework.data.domain.Sort.Direction dir = direction.equalsIgnoreCase("desc") ? 
            org.springframework.data.domain.Sort.Direction.DESC : org.springframework.data.domain.Sort.Direction.ASC;
        return produitRepository.findAll(org.springframework.data.domain.Sort.by(dir, field));
    }

    public List<Produit> rechercherProduits(String keyword) {
        if (keyword != null) {
            return produitRepository.findByNomContainingIgnoreCase(keyword);
        }
        return produitRepository.findAll();
    }

    public List<Produit> filtrerParType(String typeThe) {
        if (typeThe != null && !typeThe.isEmpty()) {
            return produitRepository.findByTypeThe(typeThe);
        }
        return produitRepository.findAll();
    }

    public org.springframework.data.domain.Page<Produit> obtenirProduitsPaginés(org.springframework.data.domain.Pageable pageable) {
        return produitRepository.findAll(pageable);
    }

    public void genererCsv(java.io.PrintWriter writer) {
        List<Produit> produits = produitRepository.findAll();
        try (org.apache.commons.csv.CSVPrinter csvPrinter = new org.apache.commons.csv.CSVPrinter(writer, 
                org.apache.commons.csv.CSVFormat.DEFAULT.withHeader("ID", "Nom", "Type", "Origine", "Prix", "Stock", "Date Reception"))) {
            for (Produit produit : produits) {
                csvPrinter.printRecord(produit.getId(), produit.getNom(), produit.getTypeThe(), 
                        produit.getOrigine(), produit.getPrix(), produit.getQuantiteStock(), produit.getDateReception());
            }
        } catch (java.io.IOException e) {
            throw new RuntimeException("Erreur lors de la génération du CSV", e);
        }
    }

    public void sauvegarderProduit(Produit produit) {
        produitRepository.save(produit);
    }

    public Produit obtenirProduitParId(Long id) {
        Optional<Produit> produit = produitRepository.findById(id);
        if (produit.isPresent()) {
            return produit.get();
        } else {
            throw new RuntimeException("Produit non trouvé pour l'id :: " + id);
        }
    }

    public void supprimerProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
