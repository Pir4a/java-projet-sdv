package com.boutique.thes.config;

import com.boutique.thes.model.Produit;
import com.boutique.thes.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(ProduitRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                System.out.println("Seeding database with initial tea products...");
                
                Produit p1 = createProduit("Sencha Premium", "Vert", "Japon", 15.90, 50, "Un thé vert japonais classique aux notes végétales et marines.", LocalDate.now().minusDays(10));
                Produit p2 = createProduit("Earl Grey Impérial", "Noir", "Inde", 12.50, 100, "Le grand classique parfumé à la bergamote.", LocalDate.now().minusDays(5));
                Produit p3 = createProduit("Pai Mu Tan", "Blanc", "Chine", 22.00, 30, "Un thé blanc délicat aux notes florales et boisées.", LocalDate.now().minusDays(20));
                Produit p4 = createProduit("Milky Oolong", "Oolong", "Chine", 18.50, 45, "Un Oolong aux arômes naturels de lait et de beurre.", LocalDate.now().minusDays(2));
                Produit p5 = createProduit("Darjeeling First Flush", "Noir", "Inde", 25.00, 20, "Le champagne des thés, récolte de printemps.", LocalDate.now().minusDays(15));
                Produit p6 = createProduit("Rooibos Vanille", "Rooibos", "Afrique du Sud", 11.00, 80, "Sans théine, parfait pour le soir.", LocalDate.now().minusDays(3));
                Produit p7 = createProduit("Matcha Cérémonie", "Vert", "Japon", 35.00, 15, "Poudre de thé vert impérial pour la cérémonie du thé.", LocalDate.now().minusDays(8));
                Produit p8 = createProduit("Gunpowder", "Vert", "Chine", 9.90, 150, "Thé vert roulé en perles, idéal pour le thé à la menthe.", LocalDate.now().minusDays(25));
                Produit p9 = createProduit("Lapsang Souchong", "Noir", "Chine", 14.00, 40, "Thé noir fumé au bois de pin.", LocalDate.now().minusDays(12));
                Produit p10 = createProduit("Jasmin Perles", "Vert", "Chine", 28.00, 25, "Thé vert parfumé aux fleurs de jasmin fraîches.", LocalDate.now().minusDays(18));
                Produit p11 = createProduit("Genmaicha", "Vert", "Japon", 13.50, 60, "Thé vert mélangé à des grains de riz grillés.", LocalDate.now().minusDays(7));
                Produit p12 = createProduit("Tisane Camomille", "Herbal", "Egypte", 8.50, 90, "Fleurs de camomille pour une infusion apaisante.", LocalDate.now().minusDays(30));
                Produit p13 = createProduit("Assam TGFOP", "Noir", "Inde", 11.80, 75, "Thé noir corsé, idéal pour le petit déjeuner.", LocalDate.now().minusDays(1));

                repository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13));
                
                System.out.println("Database seeded with " + repository.count() + " products.");
            } else {
                System.out.println("Database already contains data. Seeding skipped.");
            }
        };
    }

    private Produit createProduit(String nom, String type, String origine, double prix, int stock, String description, LocalDate date) {
        Produit p = new Produit();
        p.setNom(nom);
        p.setTypeThe(type);
        p.setOrigine(origine);
        p.setPrix(prix);
        p.setQuantiteStock(stock);
        p.setDescription(description);
        p.setDateReception(date);
        return p;
    }
}
