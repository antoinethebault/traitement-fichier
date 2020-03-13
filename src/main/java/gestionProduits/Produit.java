package gestionProduits;

import java.util.ArrayList;

public class Produit {
	private String nom;
	private char scoreNutritionnel;
	private Categorie categorie;
	private Marque marque;
	private int energie;
	private int sucres;
	private int sels;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Allergene> allergenes;
	private ArrayList<Additif> additifs;
	/**Constructor
	 * @param nom
	 * @param categorie
	 * @param marque
	 * @param scoreNutritionnel
	 */
	public Produit(String nom, char scoreNutritionnel, Categorie categorie, Marque marque) {
		super();
		this.setNom(nom);
		this.categorie = categorie;
		this.marque = marque;
		this.scoreNutritionnel = scoreNutritionnel;
	}
	/**Getter
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	/**Getter
	 * @return the marque
	 */
	public Marque getMarque() {
		return marque;
	}
	/**Getter
	 * @return the scoreNutritionnel
	 */
	public char getScoreNutritionnel() {
		return scoreNutritionnel;
	}
	/**Getter
	 * @return the energie
	 */
	public int getEnergie() {
		return energie;
	}
	/**Getter
	 * @return the sucres
	 */
	public int getSucres() {
		return sucres;
	}
	/**Getter
	 * @return the sels
	 */
	public int getSels() {
		return sels;
	}
	/**Getter
	 * @return the ingredients
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	/**Getter
	 * @return the allergenes
	 */
	public ArrayList<Allergene> getAllergenes() {
		return allergenes;
	}
	/**Getter
	 * @return the additifs
	 */
	public ArrayList<Additif> getAdditifs() {
		return additifs;
	}
	/**Setter
	 * @param energie the energie to set
	 */
	public void setEnergie(int energie) {
		this.energie = energie;
	}
	/**Setter
	 * @param sucres the sucres to set
	 */
	public void setSucres(int sucres) {
		this.sucres = sucres;
	}
	/**Setter
	 * @param sels the sels to set
	 */
	public void setSels(int sels) {
		this.sels = sels;
	}
	/**Setter
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	/**Setter
	 * @param allergenes the allergenes to set
	 */
	public void setAllergenes(ArrayList<Allergene> allergenes) {
		this.allergenes = allergenes;
	}
	/**Setter
	 * @param additifs the additifs to set
	 */
	public void setAdditifs(ArrayList<Additif> additifs) {
		this.additifs = additifs;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String toString() {
		return nom+" -----	"+marque.getLibelle()+" ------ score nutritionnel : "+scoreNutritionnel;
	}
}
