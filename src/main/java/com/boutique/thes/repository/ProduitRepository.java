package com.boutique.thes.repository;

import com.boutique.thes.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByNomContainingIgnoreCase(String nom);
    List<Produit> findByTypeThe(String typeThe);
}
