# Boutique de Thés - Spring Boot Application - DEDU STEPHANE

Une application complète de gestion de boutique de thés, développée avec **Spring Boot** et **Thymeleaf**.

## 🚀 Fonctionnalités

Cette application permet de gérer un inventaire de produits (thés) avec les fonctionnalités suivantes :

*   **CRUD Complet** : Ajouter, Modifier, Supprimer et Lister les thés.
*   **Recherche** : Rechercher des produits par nom.
*   **Filtrage** : Filtrer les produits par type de thé (Vert, Noir, Blanc, etc.).
*   **Tri** : Trier la liste par n'importe quelle colonne (Prix, Stock, Nom, etc.).
*   **Pagination** : Navigation fluide à travers les pages de produits.
*   **Export CSV** : Téléchargement du catalogue complet au format CSV.
*   **Interface Responsive** : Design moderne utilisant **Bootstrap 5**.

## 🛠 Technologies Utilisées

*   **Java 17**
*   **Spring Boot 3.2.x** (Web, Data JPA, DevTools)
*   **Thymeleaf** (Moteur de template)
*   **MySQL 8** (Base de données)
*   **Docker & Docker Compose** (Conteneurisation)
*   **Maven** (Gestion de dépendances)
*   **Bootstrap 5** (Frontend)

## 📋 Prérequis

*   **Docker** et **Docker Compose** doivent être installés sur votre machine.
*   Aucune installation locale de Java ou Maven n'est requise (tout est géré par Docker).

## ⚡ Installation et Lancement

1.  **Cloner le projet** (ou télécharger les sources) :
    ```bash
    git clone <votre-depot>
    cd "projet java"
    ```

2.  **Lancer l'application avec Docker** :
    ```bash
    docker compose up -d --build
    ```
    *Cette commande va compiler l'application Java, construire l'image Docker, et démarrer la base de données MySQL et le serveur Web.*

3.  **Accéder à l'application** :
    Ouvrez votre navigateur et allez sur :
    👉 **[http://localhost:8081](http://localhost:8081)**

4.  **Arrêter l'application** :
    ```bash
    docker compose down
    ```

## 📂 Structure du Projet

```
.
├── src
│   ├── main
│   │   ├── java/com/boutique/thes
│   │   │   ├── controller      # Contrôleurs Web (ProduitController)
│   │   │   ├── model           # Entités JPA (Produit)
│   │   │   ├── repository      # Interfaces d'accès aux données (ProduitRepository)
│   │   │   └── service         # Logique métier (ProduitService)
│   │   └── resources
│   │       ├── templates       # Vues HTML Thymeleaf (index.html, formulaire.html)
│   │       └── application.properties # Configuration Spring
├── Dockerfile                  # Configuration de l'image Docker (Build multi-stage)
├── docker-compose.yml          # Orchestration des conteneurs (App + MySQL)
└── pom.xml                     # Dépendances Maven
```

## 🔍 Utilisation

### Gestion des produits
*   Cliquez sur **"Ajouter un thé"** pour créer un nouveau produit.
*   Utilisez les boutons **Modifier** (jaune) ou **Supprimer** (rouge) dans le tableau pour gérer les produits existants.

### Recherche et Filtres
*   Utilisez la barre de recherche en haut pour trouver un thé par son nom.
*   Utilisez le menu déroulant pour filtrer uniquement les thés d'un certain type.

### Exportation
*   Cliquez sur le bouton vert **"Export CSV"** pour télécharger la liste actuelle des produits.

## 🛡 Configuration Docker

L'application écoute sur le port **8081** pour éviter les conflits fréquents sur le port 8080.
La configuration de la base de données est définie dans `docker-compose.yml` et injectée via des variables d'environnement.

---
*Développé dans le cadre du projet scolaire Java Spring Boot.*
