package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Habitation;
import controleur.Controleur;
import controleur.Habitation;
import controleur.Tableau;
import controleur.User;

public class PanelHabitation extends PanelPrincipal implements ActionListener{
	
	private JTextField txtType = new JTextField();
	private JTextField txtEtat = new JTextField();	
	private JTextField txtNbpersonne = new JTextField();
	private JTextField txtNbjours = new JTextField();
	private JTextField txtFiltre = new JTextField();
	
	private User unUser;
	
	private JPanel panelForm = new JPanel();
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JTable tableHabitations;
	private JScrollPane uneScroll;
	private Tableau unTableau;
	
	private JPanel panelFiltre = new JPanel();
	private JPanel panelReserver = new JPanel();

	private JLabel nbHabitations = new JLabel();
	private int nb = 0;
	
	public PanelHabitation(User unUser) {
		super("Liste de nos logements");
		
		this.unUser = unUser;
		int txtIduser = this.unUser.getIduser();
		this.panelFiltre.setBounds(360, 80, 460, 30);
		this.panelFiltre.setBackground(Color.gray); 
		this.panelFiltre.setLayout(new GridLayout(1,3));
		this.panelFiltre.add(new JLabel("Filtrer par :")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		
		this.panelForm.setBounds(20, 90, 250, 250);
		this.panelForm.setBackground(Color.gray); 
		this.panelForm.setLayout(new GridLayout(3,2));
		this.panelForm.add(new JLabel("Nombre de personne :")); 
		this.panelForm.add(this.txtNbpersonne ); 
		this.panelForm.add(new JLabel("Nombre de jours :")); 
		this.panelForm.add(this.txtNbjours); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		//on ajoute le formulaire à la fenetre 
		this.add(this.panelForm);
		
		String entetes [] = {"ID Habitation", "Type d'habitation","Etat de disponibilité"};
		this.unTableau= new Tableau (this.obtenirDonnees(""), entetes);
		this.tableHabitations = new JTable(this.unTableau) ; 
		this.uneScroll = new JScrollPane(this.tableHabitations);
		this.uneScroll.setBounds(360, 130, 460, 200);
		this.add(this.uneScroll);
		
		//interdire l'ordre des colonnes 
		this.tableHabitations.getTableHeader().setReorderingAllowed(false);
		
		this.nbHabitations.setBounds(400, 380, 250, 20);
		this.add(this.nbHabitations); 
		this.nb = this.unTableau.getRowCount(); 
		this.nbHabitations.setText("Nombre total d'habitations : " + nb);
		
		this.btFiltrer.addActionListener(this);
		
		this.tableHabitations.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne, idhabitation, iduser;
				float superficie, prix;
				String type;
				if (e.getClickCount() >= 2) {
					
					int reponse = JOptionPane.showConfirmDialog(null,  "Voulez-vous réserver ce logement",
							"Réserver logement", JOptionPane.YES_NO_OPTION);
					if (reponse == 0) {
									//supprimer dans la base 
									numLigne = tableHabitations.getSelectedRow();
									idhabitation = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
									type = unTableau.getValueAt(numLigne, 1).toString();
									superficie = Controleur.getSuperficie(idhabitation, type);
									prix = superficie * 2 * Integer.parseInt(txtNbpersonne.getText()) * Integer.parseInt(txtNbjours.getText());
									iduser = txtIduser;
									Controleur.addReservation(prix, txtNbpersonne.getText(), iduser, idhabitation, txtNbjours.getText());
						}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
			
		});
	}
	
	public Object [][] obtenirDonnees (String filtre){
		//transformer l'ArrayList en une matrice [][] 
		ArrayList<Habitation> lesHabitations = Controleur.selectAllHabitations(filtre); 
		Object [][] matrice = new Object [lesHabitations.size()][3];
		int i = 0; 
		for (Habitation uneHabitation : lesHabitations) {
			matrice [i][0] = uneHabitation.getIdhabitation(); 
			matrice [i][1] = uneHabitation.getType();
			matrice [i][2] = uneHabitation.getEtat(); 
			i++;
		}
		return matrice; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			
			//recuperation des données de la bdd avec le filtre 
			Object matrice[][] = this.obtenirDonnees(filtre);
			
			 //actualisation de l'affichage 
			this.unTableau.setDonnees(matrice);
		}
	}
}
