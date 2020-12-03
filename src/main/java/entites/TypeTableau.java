package entites;

import java.io.Serializable;

public class TypeTableau implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String nomTableau;
	private int nbSets;

	public TypeTableau() {
		this.id = 0;
		this.nomTableau = null;
		this.nbSets = 0;
	}

	
	public TypeTableau(int identifiant, String nomTableau, int nbSets) {
		this.id = identifiant;
		this.nomTableau = nomTableau;
		this.nbSets = nbSets;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int identifiant) {
		this.id = identifiant;
	}
	public String getNomTableau() {
		return nomTableau;
		//return null;
	}
	public void setNomTableau(String nomTableau) {
		this.nomTableau = nomTableau;
	}
	public int getNbSets() {
		return nbSets;
	}
	public void setNbSets(int nbSets) {
		this.nbSets = nbSets;
	}

}
