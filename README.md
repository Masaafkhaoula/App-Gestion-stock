# Application de Gestion de Stock en Java

Ce projet est une application console simple de gestion de stock, développée en **Java** sans Programmation Orientée Objet (POO), dans le cadre d’un TP.

## 🎯 Objectifs de l'application

L’application permet de :

- Ajouter un produit
- Modifier un produit existant
- Supprimer un produit
- Afficher la liste des produits
- Rechercher un produit par son nom
- Calculer la valeur totale du stock

## ⚙️ Caractéristiques techniques

- Utilisation exclusive de **tableaux parallèles**
- Pas de classes personnalisées (sans POO)
- Utilisation de **méthodes statiques** uniquement
- Application interactive en **console**
- Taille maximale du stock : 100 produits

## 🗂 Structure des données

Chaque produit est représenté par une position unique dans 4 tableaux parallèles :

- `int[] codesProduits`
- `String[] nomsProduits`
- `int[] quantites`
- `double[] prix`

## 🧪 Exemple d'exécution

```text
=== MENU GESTION DE STOCK ===
1. Ajouter un produit
2. Modifier un produit
3. Supprimer un produit
4. Afficher la liste des produits
5. Rechercher un produit par nom
6. Calculer la valeur totale du stock
0. Quitter
