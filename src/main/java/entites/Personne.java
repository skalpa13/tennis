package entites;

import java.io.Serializable;

public abstract class Personne implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String nom;
	String prenom;
	String nation;
	

	public Personne() {
		this.id=0;
		this.nom="";
		this.prenom="";
		this.nation="";;
	}
	
	public Personne(String nom,String prenom,String nomNation) {
		this.id=0;
		this.nom=nom;
		this.prenom=prenom;
		this.nation=nomNation;
	}


	public int getId() {
		System.out.println("getidpersonne: " +id);
		return id;
	}


	public void setId(int id) {
		System.out.println("setidpersonne: " +id);
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNation() {
		return nation;
	}


	public void setNation(String nationalite) {
		this.nation = new String(nationalite);
	}

}
