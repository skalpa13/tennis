package entites;

import java.io.Serializable;

public class Court implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int numero;
	private String nom;

	public Court() {
		this.numero = 0;
		this.nom = "";
	}
	
	public Court(String nom) {
		this.nom = nom;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String toString(){
		return this.getNom();
	}

}
