package org.example;

import java.util.Scanner;

public class GestionStock {

    static final int MAX_PRODUITS = 100;
    static int[] codesProduits = new int[MAX_PRODUITS];
    static String[] nomsProduits = new String[MAX_PRODUITS];
    static int[] quantites = new int[MAX_PRODUITS];
    static double[] prix = new double[MAX_PRODUITS];

    static int taille = 0;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quitter = false;
        while (!quitter) {
            printMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne
            switch (choix) {
                case 1:
                    menuAjouterProduit();
                    break;
                case 2:
                    menuModifierProduit();
                    break;
                case 3:
                    menuSupprimerProduit();
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    menuRechercherProduit();
                    break;
                case 6:
                    System.out.printf("Valeur totale du stock : %.2f DH%n", calculerValeurStock());
                    break;
                case 0:
                    quitter = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        }
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\n=== MENU GESTION DE STOCK ===");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit par nom");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    public static void menuAjouterProduit() {
        System.out.print("Code produit : ");
        int code = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom produit : ");
        String nom = scanner.nextLine();
        System.out.print("Quantité : ");
        int quantite = scanner.nextInt();
        System.out.print("Prix unitaire : ");
        double prixUnitaire = scanner.nextDouble();
        scanner.nextLine();

        ajouterProduit(code, nom, quantite, prixUnitaire);
    }

    public static void ajouterProduit(int code, String nom, int quantite, double prixUnitaire) {
        if (taille >= MAX_PRODUITS) {
            System.out.println("Stock plein, impossible d'ajouter un produit.");
            return;
        }
        for (int i = 0; i < taille; i++) {
            if (codesProduits[i] == code) {
                System.out.println("Erreur : Le code produit existe déjà.");
                return;
            }
        }
        codesProduits[taille] = code;
        nomsProduits[taille] = nom;
        quantites[taille] = quantite;
        prix[taille] = prixUnitaire;
        taille++;
        System.out.println("Produit ajouté avec succès.");
    }

    public static void menuModifierProduit() {
        System.out.print("Entrez le code du produit à modifier : ");
        int code = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nouveau nom : ");
        String nom = scanner.nextLine();
        System.out.print("Nouvelle quantité : ");
        int quantite = scanner.nextInt();
        System.out.print("Nouveau prix unitaire : ");
        double prixUnitaire = scanner.nextDouble();
        scanner.nextLine();

        modifierProduit(code, nom, quantite, prixUnitaire);
    }

    public static void modifierProduit(int code, String nouveauNom, int nouvelleQuantite, double nouveauPrix) {
        int index = -1;
        for (int i = 0; i < taille; i++) {
            if (codesProduits[i] == code) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Produit non trouvé.");
            return;
        }
        nomsProduits[index] = nouveauNom;
        quantites[index] = nouvelleQuantite;
        prix[index] = nouveauPrix;
        System.out.println("Produit modifié avec succès.");
    }

    public static void menuSupprimerProduit() {
        System.out.print("Entrez le code du produit à supprimer : ");
        int code = scanner.nextInt();
        scanner.nextLine();

        supprimerProduit(code);
    }

    public static void supprimerProduit(int code) {
        int index = -1;
        for (int i = 0; i < taille; i++) {
            if (codesProduits[i] == code) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Produit non trouvé.");
            return;
        }
        for (int i = index; i < taille - 1; i++) {
            codesProduits[i] = codesProduits[i + 1];
            nomsProduits[i] = nomsProduits[i + 1];
            quantites[i] = quantites[i + 1];
            prix[i] = prix[i + 1];
        }
        taille--;
        System.out.println("Produit supprimé avec succès.");
    }

    public static void afficherProduits() {
        if (taille == 0) {
            System.out.println("Aucun produit en stock.");
            return;
        }
        System.out.println("\nListe des produits en stock :");
        System.out.println("Code\tNom\tQuantité\tPrix unitaire");
        for (int i = 0; i < taille; i++) {
            System.out.printf("%d\t%s\t%d\t\t%.2f%n", codesProduits[i], nomsProduits[i], quantites[i], prix[i]);
        }
    }

    public static void menuRechercherProduit() {
        System.out.print("Entrez le nom du produit à rechercher : ");
        String nom = scanner.nextLine();
        rechercherProduit(nom);
    }

    public static void rechercherProduit(String nomRecherche) {
        boolean trouve = false;
        for (int i = 0; i < taille; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nomRecherche)) {
                System.out.println("Produit trouvé :");
                System.out.printf("Code: %d, Nom: %s, Quantité: %d, Prix: %.2f%n",
                        codesProduits[i], nomsProduits[i], quantites[i], prix[i]);
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun produit trouvé avec ce nom.");
        }
    }

    public static double calculerValeurStock() {
        double total = 0;
        for (int i = 0; i < taille; i++) {
            total += quantites[i] * prix[i];
        }
        return total;
    }
}
