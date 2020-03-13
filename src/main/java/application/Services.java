package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import gestionProduits.Additif;
import gestionProduits.Allergene;
import gestionProduits.Categorie;
import gestionProduits.Marque;
import gestionProduits.Produit;
import gestionProduits.Stock;

/**
 * @author antoinethebault
 *Services : gere differentes fonctions de l'application
 */
public class Services {
	/**
	 * afficherMenu : affiche le menu
	 */
	public void afficherMenu() {
		System.out.println("1. Afficher les meilleurs produits d'une marque");
		System.out.println("2. Afficher les meilleurs produits d'une categorie");
		System.out.println("3. Afficher les meilleurs produits par marque et categorie");
		System.out.println("4. Afficher les allergenes les plus courants et le nombre de produits dans lesquels ils apparaissent");
		System.out.println("5. Afficher les additifs les plus courants et le nombre de produits dans lesquels ils apparaissent");
		System.out.println("9. Sortir");
	}
	
	/**
	 * loadStock
	 * @return le stock de produits
	 */
	public Stock loadStock() {
		Stock stock = new Stock();
		String filePath = ClassLoader.getSystemClassLoader().getResource("open-food-facts.csv").getFile();
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("Impossible de trouver le fichier");
		}
		try {
			List<String> lignes = FileUtils.readLines(file, "UTF-8");
			lignes.remove(0);
			for (String ligne: lignes) {
				String[] tableau = ligne.split("\\|");
				String nom=tableau[0];
				char scoreNutritionnel=tableau[1].charAt(0);
				Categorie categorie = new Categorie(tableau[2]);
				Marque marque = new Marque(tableau[3]);
				Produit produit = new Produit(nom, scoreNutritionnel, categorie, marque);
				ArrayList<Allergene> allergenes = new ArrayList<Allergene>();
				tableau[30]=tableau[30].replaceAll("\\s", "");
				tableau[30]=tableau[30].replaceAll("\\[", "");
				tableau[30]=tableau[30].replaceAll("\\]", "");
				for (String allergene : tableau[30].split(",")) 
					allergenes.add(new Allergene(allergene.toLowerCase()));
				produit.setAllergenes(allergenes);
				ArrayList<Additif> additifs = new ArrayList<Additif>();
				tableau[31]=tableau[31].replaceAll("\\s", "");
				tableau[31]=tableau[31].replaceAll("\\[", "");
				tableau[31]=tableau[31].replaceAll("\\]", "");
				for (String additif : tableau[31].split(",")) 
					additifs.add(new Additif(additif.toLowerCase()));
				produit.setAdditifs(additifs);
				stock.ajouter(produit);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stock;
	}

	/**
	 * produitsMarque : renvoie les 10 meilleurs produits d'une marque
	 * @param stock
	 */
	public void produitsMarque(Stock stock) {
		ArrayList<Produit> produits = stock.getListe();
		System.out.println("Entrez la marque de votre choix : ");
		Scanner sc = new Scanner(System.in);
		String marque = sc.nextLine();
		ArrayList<Produit> produitsMarque = new ArrayList<Produit>();
		
		for (Produit produit : produits) {
			//System.out.println(produit.getMarque().getLibelle());
			if (produit.getMarque().getLibelle().equals(marque)) {
				produitsMarque.add(produit);
			}
		}
		
		Collections.sort(produitsMarque, new ScoreComparator());
		int compteur=0;
		Iterator<Produit> iterator = produitsMarque.iterator();
		while (iterator.hasNext() && compteur<10) {
			System.out.println(iterator.next().toString());
			compteur++;
		}
	}

	/**
	 * produitsCategorie : renvoie les 10 meilleurs produits d'une categorie
	 * @param stock
	 */
	public void produitsCategorie(Stock stock) {
		ArrayList<Produit> produits = stock.getListe();
		System.out.println("Entrez la categorie de votre choix : ");
		Scanner sc = new Scanner(System.in);
		String categorie = sc.nextLine();
		ArrayList<Produit> produitsCategorie = new ArrayList<Produit>();
		
		for (Produit produit : produits) {
			if (produit.getCategorie().getLibelle().equals(categorie)) {
				produitsCategorie.add(produit);
			}
		}
		
		Collections.sort(produitsCategorie, new ScoreComparator());
		int compteur=0;
		Iterator<Produit> iterator = produitsCategorie.iterator();
		while (iterator.hasNext() && compteur<10) {
			System.out.println(iterator.next().toString());
			compteur++;
		}
		
	}

	/**
	 * produitsMarqueCategorie : renvoie les 10 meilleurs produits d'une categorie et d'une marque
	 * @param stock
	 */
	public void produitsMarqueCategorie(Stock stock) {
		ArrayList<Produit> produits = stock.getListe();
		
		System.out.println("Entrez la categorie de votre choix : ");
		Scanner sc = new Scanner(System.in);
		String categorie = sc.nextLine();
		System.out.println("Entrez la marque de votre choix : ");
		String marque = sc.nextLine();
		
		ArrayList<Produit> produitsCategorieMarque = new ArrayList<Produit>();
		for (Produit produit : produits) {
			if (produit.getCategorie().getLibelle().equals(categorie) || produit.getMarque().getLibelle().equals(marque)) {
				produitsCategorieMarque.add(produit);
			}
		}
		
		Collections.sort(produitsCategorieMarque, new ScoreComparator());
		int compteur=0;
		Iterator<Produit> iterator = produitsCategorieMarque.iterator();
		while (iterator.hasNext() && compteur<10) {
			System.out.println(iterator.next().toString());
			compteur++;
		}
		
		
	}

	/**
	 * allergenes : affiche les allergenes les plus courants et le nombre de produits
	 * dans lesquels ils apparaissent
	 * @param stock
	 */
	public void allergenes(Stock stock) {
		ArrayList<Produit> produits = stock.getListe();
		//on construit une liste avec les differents allergenes et le nombre de chacun
		HashMap<String, Integer> allergenes = new HashMap<String, Integer>();
		for (Produit produit: produits) {
			ArrayList<Allergene> liste = produit.getAllergenes();
			for (Allergene allergene: liste) {
				if (!allergenes.containsKey(allergene.getLibelle()))
					allergenes.put(allergene.getLibelle(),1);
				else 
					allergenes.put(allergene.getLibelle(), allergenes.get(allergene.getLibelle())+1);
			}
		}
		//on trie les allergenes par les plus courants
		//https://www.java67.com/2015/01/how-to-sort-hashmap-in-java-based-on.html
		Set<Entry<String, Integer>> entries = allergenes.entrySet();
		List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries);
		Collections.sort(listOfEntries,new AllergenesAdditifsComparator());
		LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());
		for(Entry<String, Integer> entry : listOfEntries){ 
			sortedByValue.put(entry.getKey(), entry.getValue()); 
		}
		Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
		
		//on affiche les 10 allergenes les plus courants
		//for(Entry<String, Integer> mapping : entrySetSortedByValue){ 
		//	System.out.println(mapping.getKey() + " ==> " + mapping.getValue()); 
		//}
		Iterator<Entry<String,Integer>> iterator = entrySetSortedByValue.iterator();
		int compteur=0;
		while (compteur<10 && iterator.hasNext()) {
			Entry<String, Integer> mapping = iterator.next();
			System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
			compteur++;
		}

	}

	/**
	 * additifs : affiche les additifs les plus courants et le nombre de produits
	 * dans lesquels ils apparaissent
	 * @param stock
	 */
	public void additifs(Stock stock) {
		ArrayList<Produit> produits = stock.getListe();
		//on construit une liste avec les differents additifs et le nombre de chacun
		HashMap<String, Integer> additifs = new HashMap<String, Integer>();
		for (Produit produit: produits) {
			ArrayList<Additif> liste = produit.getAdditifs();
			for (Additif additif: liste) {
				if (!additifs.containsKey(additif.getLibelle()))
					additifs.put(additif.getLibelle(),1);
				else 
					additifs.put(additif.getLibelle(), additifs.get(additif.getLibelle())+1);
			}
		}
		//on trie les additifs par les plus courants
		//https://www.java67.com/2015/01/how-to-sort-hashmap-in-java-based-on.html
		Set<Entry<String, Integer>> entries = additifs.entrySet();
		List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries);
		Collections.sort(listOfEntries,new AllergenesAdditifsComparator());
		LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());
		for(Entry<String, Integer> entry : listOfEntries){ 
			sortedByValue.put(entry.getKey(), entry.getValue()); 
		}
		Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
		
		//on affiche les 10 additifs les plus courants
		//for(Entry<String, Integer> mapping : entrySetSortedByValue){ 
		//	System.out.println(mapping.getKey() + " ==> " + mapping.getValue()); 
		//}
		Iterator<Entry<String,Integer>> iterator = entrySetSortedByValue.iterator();
		int compteur=0;
		while (compteur<10 && iterator.hasNext()) {
			Entry<String, Integer> mapping = iterator.next();
			System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
			compteur++;
		}
		
	}
}
