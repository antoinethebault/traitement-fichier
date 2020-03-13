package gestionProduits;

/**
 * @author antoinethebault
 *Marque d'un produit
 */
public class Marque {
	/**libelle : String*/
	private String libelle;

	/**Constructor
	 * @param libelle
	 */
	public Marque(String libelle) {
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
