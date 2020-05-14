package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.moteurJeu.Commande;
import fr.unilim.iut.moteurJeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu{
	 int longueur;
	 int hauteur;
	 Vaisseau vaisseau;
	 Missile missile;
	 
	 public class Constante {

		   public static final int ESPACEJEU_LONGUEUR = 150;
		   public static final int ESPACEJEU_HAUTEUR = 100;
		
		   public static final int VAISSEAU_LONGUEUR = 30;
		   public static final int VAISSEAU_HAUTEUR = 20;
		   public static final int VAISSEAU_VITESSE = 5;
	   }

	 public void initialiserJeu() {
			Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
			Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
			positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
		 }

	 @Override
     public void evoluer(Commande commandeUser) {
		
        if (commandeUser.gauche) {
            deplacerVaisseauVersLaGauche();
        }
		
       if (commandeUser.droite) {
	        deplacerVaisseauVersLaDroite();
       }

     }

 
    @Override
    public boolean etreFini() {
       return false; 
    }
	    public SpaceInvaders(int longueur, int hauteur) {
		   this.longueur = longueur;
		   this.hauteur = hauteur;
	   }
	 
	    public Vaisseau recupererVaisseau() {
			return this.vaisseau;
		}
	    
	    public Missile recupererMissile() {
			return this.missile;
		}
	    
	    
	    public void deplacerVaisseauVersLaDroite() {
			if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
				vaisseau.seDeplacerVersLaDroite();
				if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
					vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
				}
			}
		}
	    
	    public void deplacerVaisseauVersLaGauche() {
			if (0 < vaisseau.abscisseLaPlusAGauche())
				vaisseau.seDeplacerVersLaGauche();
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
			}
		}
		public boolean estDansEspaceJeu(int x, int y) {
			return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
		}
	    
	    @Override
		public String toString() {
			return recupererEspaceJeuDansChaineASCII();
		}

		public String recupererEspaceJeuDansChaineASCII() {
			StringBuilder espaceDeJeu = new StringBuilder();
			for (int y = 0; y < hauteur; y++) {
				for (int x = 0; x < longueur; x++) {
					espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
				}
				espaceDeJeu.append(fr.unilim.iut.spaceinvaders.Constante.MARQUE_FIN_LIGNE);
			}
			return espaceDeJeu.toString();
		}

		private char recupererMarqueDeLaPosition(int x, int y) {
			char marque;
			if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
				marque = fr.unilim.iut.spaceinvaders.Constante.MARQUE_VAISSEAU;
			else if (this.aUnMissileQuiOccupeLaPosition(x, y))
					marque = fr.unilim.iut.spaceinvaders.Constante.MARQUE_MISSILE;
			else marque = fr.unilim.iut.spaceinvaders.Constante.MARQUE_VIDE;
			return marque;
		}
		private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
	        return this.aUnmissile() && missile.occupeLaPosition(x, y);
	    }
		
		public boolean aUnmissile() {
			return missile!=null;
		}

		private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
			return this.aUnvaisseau() && vaisseau.occupeLaPosition(x, y);
		}

		public boolean aUnvaisseau() {
			return vaisseau!=null;
		}

		
		public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
			int x = position.abscisse();
			int y = position.ordonnee();
			
			if (!estDansEspaceJeu(x, y))
				throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

			int longueurVaisseau = dimension.longueur();
			int hauteurVaisseau = dimension.hauteur();
			
			if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
				throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
			if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
				throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

			vaisseau = new Vaisseau(dimension,position,vitesse);

		}

		 public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
				
			   if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
				   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
								
			   this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
	       }	
}
