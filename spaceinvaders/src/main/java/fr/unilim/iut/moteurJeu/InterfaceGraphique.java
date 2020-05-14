package fr.unilim.iut.moteurJeu;

import javax.swing.JFrame;

public class InterfaceGraphique  {

	private PanelDessin panel;

	private Controleur controleur;

	public InterfaceGraphique(DessinJeu afficheurUtil,int x,int y)
	{
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panel=new PanelDessin(x, y,afficheurUtil);
		f.setContentPane(this.panel);
		
		Controleur controlleurGraph=new Controleur();
		this.controleur=controlleurGraph;
		this.panel.addKeyListener(controlleurGraph);	
		
		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}

	public Controleur getControleur() {
		return controleur;
	}

	public void dessiner() {
		this.panel.dessinerJeu();	
	}
	
}
