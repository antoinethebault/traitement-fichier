package gestionProduits;

/**
 * @author antoinethebault
 *Categorie d'un produit
 */
public class Categorie {
	/**libelle : String*/
	private String libelle;

	/**Constructor
	 * @param libelle
	 */
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	/**Getter
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**Setter
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
