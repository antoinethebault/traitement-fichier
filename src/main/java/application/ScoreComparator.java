package application;

import java.util.Comparator;

import gestionProduits.Produit;

public class ScoreComparator implements Comparator<Produit> {

	public int compare(Produit o1, Produit o2) {
		return Character.compare(o1.getScoreNutritionnel(),o2.getScoreNutritionnel());
	}

}
