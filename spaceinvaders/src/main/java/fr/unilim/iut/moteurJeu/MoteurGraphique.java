package fr.unilim.iut.moteurJeu;

public class MoteurGraphique {

	private Jeu jeu;

	private InterfaceGraphique gui;

	private DessinJeu dessin;

	public MoteurGraphique(Jeu pJeu, DessinJeu pAffiche) {
		this.jeu = pJeu;
		this.dessin = pAffiche;
	}

	public void lancerJeu(int width, int height) throws InterruptedException {

		this.gui = new InterfaceGraphique(this.dessin,width,height);
		Controleur controle = this.gui.getControleur();

		while (!this.jeu.etreFini()) {
			Commande c = controle.getCommande();
			this.jeu.evoluer(c);
			this.gui.dessiner();
			Thread.sleep(100);
		}
	}

}
