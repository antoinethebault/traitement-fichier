package application;

import java.util.Scanner;

import gestionProduits.Stock;

/**
 * @author antoinethebault
 *ApplicationOpenFoodFacts : application permettant de faire des recherches ou afficher des elements
 */
public class ApplicationOpenFoodFacts {
	public static void main (String[] args){
		Services services = new Services();
		Stock stock = services.loadStock();
		Scanner sc = new Scanner(System.in);
		int saisie=0;
		while (saisie!=9) {
			services.afficherMenu();
			saisie=sc.nextInt();
			switch(saisie) {
			case 1:
				services.produitsMarque(stock);
				break;
			case 2:
				services.produitsCategorie(stock);
				break;
			case 3:
				services.produitsMarqueCategorie(stock);
				break;
			case 4:
				services.allergenes(stock);
				break;
			case 5:
				services.additifs(stock);
				break;
			}
		}
		sc.close();
	}
}
