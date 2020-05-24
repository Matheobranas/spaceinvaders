package fr.unilim.iut.spaceinvaders.model;

public class Collision {

public boolean detecterCollision(Missile missile, Envahisseur envahisseur) {

	return ((this.collisionAbscisseDroite(missile, envahisseur) || this.collisionAbscisseGauche(missile, envahisseur)) && (this.collisionOrdonneeEnvahisseur(missile, envahisseur) || this.collisionOrdonneeBasse(missile, envahisseur) || this.collisionOrdonneeHaute(missile, envahisseur)) );
}

private boolean collisionOrdonneeEnvahisseur(Missile missile, Envahisseur envahisseur) {

	return(envahisseur.ordonneeLaPlusHaute() <= missile.ordonneeLaPlusHaute() && envahisseur.ordonneeLaPlusBasse() >= missile.ordonneeLaPlusBasse());
}

private boolean collisionAbscisseGauche(Missile missile, Envahisseur envahisseur) {

	return(missile.abscisseLaPlusAGauche() >= envahisseur.abscisseLaPlusAGauche() && missile.abscisseLaPlusAGauche() <= envahisseur.abscisseLaPlusADroite());
}

private boolean collisionAbscisseDroite(Missile missile, Envahisseur envahisseur) {

	return(missile.abscisseLaPlusADroite() <= envahisseur.abscisseLaPlusADroite() && missile.abscisseLaPlusADroite() >= envahisseur.abscisseLaPlusAGauche());

}

private boolean collisionOrdonneeBasse(Missile missile, Envahisseur envahisseur) {

	return(missile.ordonneeLaPlusBasse() >= envahisseur.ordonneeLaPlusBasse() && missile.ordonneeLaPlusBasse() <= envahisseur.ordonneeLaPlusHaute());
}

private boolean collisionOrdonneeHaute(Missile missile, Envahisseur envahisseur) {

	return(missile.ordonneeLaPlusHaute() >= envahisseur.ordonneeLaPlusBasse() && missile.ordonneeLaPlusHaute() <= envahisseur.ordonneeLaPlusHaute());
}

}
