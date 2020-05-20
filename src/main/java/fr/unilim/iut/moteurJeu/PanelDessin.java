package fr.unilim.iut.moteurJeu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PanelDessin extends JPanel {

	private DessinJeu dessin;

	private BufferedImage imageSuivante;

	private BufferedImage imageEnCours;

	private int width, height;

	public PanelDessin(int x, int y, DessinJeu affiche) {
		super();
		this.setPreferredSize(new Dimension(x, y));
		this.width = x;
		this.height = y;
		this.dessin=affiche;

		this.imageSuivante = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		this.imageEnCours = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
	}

	public void dessinerJeu() {
		this.dessin.dessiner(this.imageSuivante);

		BufferedImage temp = this.imageEnCours;
		this.imageEnCours = this.imageSuivante;
		this.imageSuivante = temp;
		this.imageSuivante.getGraphics()
				.fillRect(0, 0, this.width, this.height);
		this.repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.imageEnCours, 0, 0, getWidth(), getHeight(), 0, 0,
				getWidth(), getHeight(), null);
	}

}
