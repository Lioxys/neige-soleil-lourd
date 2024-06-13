package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Appartement;
import controleur.Controleur;
import controleur.Reservation;
import controleur.Tableau;
import controleur.User;

public class PanelReservation extends PanelPrincipal implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPrix = new JTextField();
	private JTextField txtNbpersonne = new JTextField();
	private JTextField txtIdhabitation = new JTextField();
	
	private JTable tableReservations;
	private JScrollPane uneScroll;
	private Tableau unTableau;
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel nbReservations = new JLabel();
	private int nb = 0;
	
	public PanelReservation(User unUser) {
		super("Gestion de vos réservations");
		
		this.panelFiltre.setBounds(360, 80, 460, 30);
		this.panelFiltre.setBackground(Color.gray); 
		this.panelFiltre.setLayout(new GridLayout(1,3));
		this.panelFiltre.add(new JLabel("Filtrer par :")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		
		String entetes [] = {"ID Reservation", "Prix de votre réservation","Nombre de personnes", "ID de l'habitation", "Nombre de jours"};
		this.unTableau= new Tableau (this.obtenirDonnees("", unUser), entetes);
		this.tableReservations = new JTable(this.unTableau) ; 
		this.uneScroll = new JScrollPane(this.tableReservations);
		this.uneScroll.setBounds(360, 130, 460, 200);
		this.add(this.uneScroll);
		
		this.tableReservations.getTableHeader().setReorderingAllowed(false);
		
		this.nbReservations.setBounds(400, 380, 250, 20);
		this.add(this.nbReservations); 
		this.nb = this.unTableau.getRowCount(); 
		this.nbReservations.setText("Nombre total de réservations : " + nb);
		
	}
	public Object [][] obtenirDonnees (String filtre, User unUser){
		//transformer l'ArrayList en une matrice [][] 
		ArrayList<Reservation> lesReservations = Controleur.selectAllReservations(filtre, unUser); 
		Object [][] matrice = new Object [lesReservations.size()][5];
		int i = 0; 
		for (Reservation uneReservation : lesReservations) {
			matrice [i][0] = uneReservation.getIdreservation(); 
			matrice [i][1] = uneReservation.getPrix(); 
			matrice [i][2] = uneReservation.getNb_personne();
			matrice [i][3] = uneReservation.getIdhabitation(); 
			matrice [i][4] = uneReservation.getNb_jours();
			i++;
		}
		return matrice; 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
