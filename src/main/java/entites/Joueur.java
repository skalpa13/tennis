package entites;

public class Joueur extends Personne{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String sexe;
	int classement;

	public Joueur() {
		this.sexe="";
		this.classement=0;
	
	}

	public Joueur(String nomJoueur, String prenomJoueur, String sexeJoueur,	String nomNationJoueur) {
		super(nomJoueur,prenomJoueur,nomNationJoueur);
		this.sexe = sexeJoueur;
		this.classement=0;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public int getClassement() {
		return classement;
	}

	public void setClassement(int classement) {
		this.classement = classement;
	}
	
	public String toString(){
		return "Joueur : "+getPrenom()+" "+getNom();
	}

}
