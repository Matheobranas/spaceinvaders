package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class Vaisseau extends Sprite {

        public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
            super(dimension, positionOrigine, vitesse);
        }

        public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
        	 if (this.longueur()< dimensionMissile.longueur()){
				   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
			 }
        	Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);
    		return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
    	}

		private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
			int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
    		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

    		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
    		Position positionOrigineMissile = new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
			return positionOrigineMissile;
		}
        
        public int hauteur() {
    		return this.dimension.hauteur();
    	}

    	public int longueur() {
    		return this.dimension.longueur();
    	}
}