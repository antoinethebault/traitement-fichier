package gestionProduits;

/**
 * @author antoinethebault
 *Allergene d'un produit
 */
public class Allergene {
	/**libelle : String*/
	private String libelle;

	/**Constructor
	 * @param libelle
	 */
	public Allergene(String libelle) {
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
	
	/**
	 *toString : affiche le libelle de l'allergene
	 */
	public String toString() {
		return libelle;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Allergene) {
			Allergene allergene = (Allergene) obj;
			if (libelle.equals(allergene.libelle))
				return true;
		}
		return false;
	}
}
