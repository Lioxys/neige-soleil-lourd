package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.IrisEvent;
import controleur.User;

public class VueGenerale extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelMenu = new JPanel();
	private JButton btProfil = new JButton("Profil");
	private JButton btNosHabitations = new JButton("Nos habitations");
	private JButton btVosLogements = new JButton("Vos logements");
	private JButton btVosReservations = new JButton("Vos réservations");
	private JButton btDeconnecter = new JButton("Déconnecter"); 
	
	private PanelProfil unPanelProfil;
	private PanelLogement unPanelLogement;
	private PanelHabitation unPanelHabitation;
	private PanelReservation unPanelReservation;
	private User unUser;
	
	public VueGenerale(User unUser) {
		
		unPanelProfil = new PanelProfil(unUser);
		unPanelLogement = new PanelLogement(unUser);
		unPanelHabitation = new PanelHabitation(unUser);
		unPanelReservation = new PanelReservation(unUser);
		
		this.unUser = unUser;
		this.setTitle("Application Neige et Soleil");
		this.setResizable(false);
		this.setBounds(100, 100, 900, 600);
		this.getContentPane().setBackground(Color.gray);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panelMenu.setBounds(50, 10, 800, 30);
		this.panelMenu.setBackground(Color.gray);
		this.panelMenu.setLayout(new GridLayout(1, 5));
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btNosHabitations);
		if (this.unUser.getRole().equals("proprietaire")) {
			this.panelMenu.add(this.btVosLogements);
		}
		this.panelMenu.add(this.btVosReservations); 
		this.panelMenu.add(this.btDeconnecter);
		this.add(this.panelMenu); 
		
		this.btProfil.addActionListener(this);
		this.btNosHabitations.addActionListener(this);
		this.btVosLogements.addActionListener(this);
		this.btVosReservations.addActionListener(this);
		this.btDeconnecter.addActionListener(this);
		
		this.add(this.unPanelProfil);
		this.add(this.unPanelHabitation);
		if (this.unUser.getRole().equals("proprietaire")) {
			this.add(this.unPanelLogement);
		}
		this.add(this.unPanelReservation);
		
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btDeconnecter) {
			 IrisEvent.rendreVisibleVueGenerale(false, null);
			 IrisEvent.rendreVisibleVueConnexion(true);
		 }else if (e.getSource() == this.btProfil) {
			 this.afficherPanel(1);
		 }else if (e.getSource() == this.btNosHabitations) {
			 this.afficherPanel(2);
		 }else if (e.getSource() == this.btVosLogements) {
			 this.afficherPanel(3);
		 }else if (e.getSource() == this.btVosReservations) {
			 this.afficherPanel(4);
		 }
	}
	public void afficherPanel (int choix) {
		this.unPanelProfil.setVisible(false);
		this.unPanelHabitation.setVisible(false);
		this.unPanelLogement.setVisible(false);
		this.unPanelReservation.setVisible(false);
		switch (choix) {
		case 1 : this.unPanelProfil.setVisible(true);break;
		case 2 : this.unPanelHabitation.setVisible(true);break;
		case 3 : this.unPanelLogement.setVisible(true); break;
		case 4 : this.unPanelReservation.setVisible(true);break;
		}
	}
}

	