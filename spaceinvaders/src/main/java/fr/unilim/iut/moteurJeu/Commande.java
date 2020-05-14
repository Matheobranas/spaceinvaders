package fr.unilim.iut.moteurJeu;

public class Commande {

	public boolean gauche;
	public boolean droite;
	public boolean tir;

	public Commande()
	{
		
	}
	
	public Commande(Commande commandeACopier)
	{
		this.gauche=commandeACopier.gauche;
		this.droite=commandeACopier.droite;		
	}
	
}
