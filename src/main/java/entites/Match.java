package entites;

import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable{

	private static final long serialVersionUID = 1L;
	int identifiant;
	Joueur joueur1;
	Joueur joueur2;
	Arbitre arbitre;
	Court court;
	Date dateMatch;
	int nbsetsjoueur1;
	int nbsetsjoueur2;

	public int getNbsetsjoueur1() {
		return nbsetsjoueur1;
	}


	public int getNbsetsjoueur2() {
		return nbsetsjoueur2;
	}


	public void setNbsetsjoueur2(int nbsetsjoueur2) {
		this.nbsetsjoueur2 = nbsetsjoueur2;
	}


	public void setNbsetsjoueur1(int nbsetjoueur1) {
		this.nbsetsjoueur1 = nbsetjoueur1;
	}


	public Match() {
		
		this.identifiant = 0;
		this.joueur1 = null;
		this.joueur2 = null;
		this.arbitre = null;
		this.court = null;
		this.dateMatch = null;
		this.nbsetsjoueur1 = 0;
		this.nbsetsjoueur2 = 0;
	}
	
/*
	public Match(Joueur joueur1, Joueur joueur2, Arbitre arbitre, Court court, Date dateMatch) {
		this.identifiant = 0;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.arbitre = arbitre;
		this.court = court;
		this.dateMatch = dateMatch;
	}
*/

	public int getIdentifiant() {
		System.out.println("id: " +identifiant);
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		
		System.out.println("Setid: " +identifiant);
		this.identifiant = identifiant;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public Arbitre getArbitre() {
		return arbitre;
	}

	public void setArbitre(Arbitre arbitre) {
		this.arbitre = arbitre;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Date getDateMatch() {
		return dateMatch;
	}

	public void setDateMatch(Date dateMatch) {
		this.dateMatch = dateMatch;
	}

	
	
}
