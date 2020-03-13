package gestionProduits;

/**
 * @author antoinethebault
 *Additif d'un produit
 */
public class Additif {
	/**libelle : String*/
	private String libelle;

	/**Constructor
	 * @param libelle
	 */
	public Additif(String libelle) {
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
