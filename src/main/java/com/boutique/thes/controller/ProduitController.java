package com.boutique.thes.controller;

import com.boutique.thes.model.Produit;
import com.boutique.thes.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProduitController {

    private final ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model, 
                               @org.springframework.web.bind.annotation.RequestParam(required = false) String keyword,
                               @org.springframework.web.bind.annotation.RequestParam(required = false) String type,
                               @org.springframework.web.bind.annotation.RequestParam(required = false) String sort,
                               @org.springframework.web.bind.annotation.RequestParam(required = false, defaultValue = "asc") String dir,
                               @org.springframework.web.bind.annotation.RequestParam(defaultValue = "0") int page,
                               @org.springframework.web.bind.annotation.RequestParam(defaultValue = "5") int size) {
        
        if (keyword != null) {
            model.addAttribute("produits", produitService.rechercherProduits(keyword));
            model.addAttribute("keyword", keyword);
            return "index"; // Search doesn't support pagination in this simple impl yet
        } else if (type != null && !type.isEmpty()) {
            model.addAttribute("produits", produitService.filtrerParType(type));
            model.addAttribute("type", type);
            return "index"; // Filter doesn't support pagination yet
        } else if (sort != null) {
             model.addAttribute("produits", produitService.obtenirTousLesProduitsTries(sort, dir));
             model.addAttribute("sortField", sort);
             model.addAttribute("sortDir", dir);
             model.addAttribute("reverseSortDir", dir.equals("asc") ? "desc" : "asc");
             return "index"; // Sort doesn't support pagination yet
        }
        
        org.springframework.data.domain.Page<Produit> pageProduits = produitService.obtenirProduitsPaginés(org.springframework.data.domain.PageRequest.of(page, size));
        model.addAttribute("produits", pageProduits.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageProduits.getTotalPages());
        model.addAttribute("totalItems", pageProduits.getTotalElements());
        return "index";
    }

    @GetMapping("/export")
    public void exportToCSV(jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException {
        response.setContentType("text/csv");
        java.text.DateFormat dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new java.util.Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=produits_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        produitService.genererCsv(response.getWriter());
    }

    @GetMapping("/nouveau")
    public String showNewProductForm(Model model) {
        Produit produit = new Produit();
        model.addAttribute("produit", produit);
        return "formulaire-produit";
    }

    @PostMapping("/enregistrer")
    public String saveProduct(@ModelAttribute("produit") Produit produit) {
        produitService.sauvegarderProduit(produit);
        return "redirect:/";
    }

    @GetMapping("/modifier/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Produit produit = produitService.obtenirProduitParId(id);
        model.addAttribute("produit", produit);
        return "formulaire-produit";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        produitService.supprimerProduit(id);
        return "redirect:/";
    }
}
