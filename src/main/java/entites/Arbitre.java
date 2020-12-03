package entites;

public class Arbitre extends Personne {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int niveau;

	public Arbitre() {
		super();
		this.niveau=1;
	}
	
	public Arbitre(String nom, String prenom, String nation, int niveau){
		super(nom,prenom,nation);
		this.niveau= niveau;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	public String toString(){
		return getPrenom()+" "+getNom();
	}

	

}
