package fr.unilim.iut.moteurJeu;

public interface Jeu {

	public void evoluer(Commande commandeUser);
	
	public boolean etreFini();
}
