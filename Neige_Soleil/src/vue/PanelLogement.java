package vue;

import java.awt.Color;
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

import controleur.Appartement;
import controleur.Controleur;
import controleur.Maison;
import controleur.Tableau;
import controleur.User;


public class PanelLogement extends PanelPrincipal implements ActionListener{
	
	private User unUser;
	private JTextField txtSuperficieM = new JTextField();
	private JTextField txtAdresseM = new JTextField();
	private JTextField txtCPM = new JTextField();
	private JTextField txtVilleM = new JTextField();
	
	private JTextField txtSuperficieA = new JTextField();
	private JTextField txtAdresseA = new JTextField();
	private JTextField txtCPA = new JTextField();
	private JTextField txtNumpalierA = new JTextField();
	private JTextField txtNumbatA = new JTextField();
	private JTextField txtEtageA = new JTextField();
	private JTextField txtVilleA = new JTextField();
	
	private JButton btAnnulerA = new JButton("Annuler"); 
	private JButton btEnregistrerA = new JButton("Enregistrer");
	private JButton btAnnulerM = new JButton("Annuler"); 
	private JButton btEnregistrerM = new JButton("Enregistrer");
	
	private JPanel panelFormM = new JPanel();
	private JPanel panelFormA = new JPanel();
	
	private JTable tableAppartements;
	private JTable tableMaisons;
	private JScrollPane uneScrollA;
	private JScrollPane uneScrollM;
	private Tableau unTableauM;
	private Tableau unTableauA;
	
	private JPanel panelFiltreA = new JPanel(); 
	private JTextField txtFiltreA = new JTextField(); 
	private JButton btFiltrerA = new JButton("Filtrer"); 
	
	private JPanel panelFiltreM = new JPanel(); 
	private JTextField txtFiltreM = new JTextField(); 
	private JButton btFiltrerM = new JButton("Filtrer"); 
	
	private JLabel nbMaisons = new JLabel();
	private int nbA = 0, nbM = 0;
	
	public PanelLogement(User unUser) {
		super("Vos logements");
		
		this.unUser = unUser;
		this.panelFiltreA.setBounds(180, 0, 300, 30);
		this.panelFiltreA.setBackground(Color.gray); 
		this.panelFiltreA.setLayout(new GridLayout(1,3));
		this.panelFiltreA.add(new JLabel("Filtrer par :")); 
		this.panelFiltreA.add(this.txtFiltreA); 
		this.panelFiltreA.add(this.btFiltrerA);
		this.add(this.panelFiltreA);
		
		this.panelFormM.setBounds(50, 30, 250, 200);
		this.panelFormM.setBackground(Color.gray);
		this.panelFormM.setLayout(new GridLayout(5,2));
		this.panelFormM.add(new JLabel("Adresse Maison :")); 
		this.panelFormM.add(this.txtAdresseM); 
		this.panelFormM.add(new JLabel("Superficie maison :")); 
		this.panelFormM.add(this.txtSuperficieM); 
		this.panelFormM.add(new JLabel("Code_postal maison :")); 
		this.panelFormM.add(this.txtCPM);
		this.panelFormM.add(new JLabel("Ville maison :")); 
		this.panelFormM.add(this.txtVilleM);
		this.panelFormM.add(this.btAnnulerM); 
		this.panelFormM.add(this.btEnregistrerM);
		//on ajoute le formulaire à la fenetre 
		this.add(this.panelFormM);
		
		this.panelFormA.setBounds(370, 30, 250, 200);
		this.panelFormA.setBackground(Color.gray);
		this.panelFormA.setLayout(new GridLayout(8,2));
		this.panelFormA.add(new JLabel("Adresse appartement :")); 
		this.panelFormA.add(this.txtAdresseA); 
		this.panelFormA.add(new JLabel("Superficie appartement :")); 
		this.panelFormA.add(this.txtSuperficieA); 
		this.panelFormA.add(new JLabel("Code_postal appartement :")); 
		this.panelFormA.add(this.txtCPA);
		this.panelFormA.add(new JLabel("Ville appartement :")); 
		this.panelFormA.add(this.txtVilleA);
		this.panelFormA.add(new JLabel("Numero palier appartement :")); 
		this.panelFormA.add(this.txtNumpalierA);
		this.panelFormA.add(new JLabel("Numero etage appartement :")); 
		this.panelFormA.add(this.txtEtageA);
		this.panelFormA.add(new JLabel("Numero batiment appartement :")); 
		this.panelFormA.add(this.txtNumbatA);
		this.panelFormA.add(this.btAnnulerA); 
		this.panelFormA.add(this.btEnregistrerA);
		//on ajoute le formulaire à la fenetre 
		this.add(this.panelFormA);
		
		String entetesA [] = {"ID Appartement", "Etage","Num Palier", "Num Batiment", "Adresse", "CP", "Ville", "Superficie"};
		this.unTableauA= new Tableau (this.obtenirDonneesA("", unUser), entetesA);
		this.tableAppartements = new JTable(this.unTableauA) ; 
		this.uneScrollA = new JScrollPane(this.tableAppartements);
		this.uneScrollA.setBounds(370, 250, 460, 200);
		this.add(this.uneScrollA);
		
		String entetesM [] = {"ID Maison", "Adresse","Superficie", "Ville", "Code_Postal"};
		this.unTableauM = new Tableau (this.obtenirDonneesM("", unUser), entetesM);
		this.tableMaisons = new JTable(this.unTableauM) ; 
		this.uneScrollM = new JScrollPane(this.tableMaisons);
		this.uneScrollM.setBounds(20, 250, 250, 100);
		this.add(this.uneScrollM);
		
		this.tableMaisons.getTableHeader().setReorderingAllowed(false);
		this.tableAppartements.getTableHeader().setReorderingAllowed(false);
		
		this.nbMaisons.setBounds(20, 400, 250, 60);
		this.add(this.nbMaisons); 
		this.nbM = this.unTableauM.getRowCount(); 
		this.nbA = this.unTableauA.getRowCount();
		this.nbMaisons.setText(nbM + " maison(s) et " + nbA + " appartement(s)");
		
		this.tableMaisons.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne, idmaison ; 
				if (e.getClickCount() >=2 ) {
					numLigne = tableMaisons.getSelectedRow(); 
					idmaison = Integer.parseInt(unTableauM.getValueAt(numLigne, 0).toString()); 
					int reponse = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer la maison",
							"Suppression Maison", JOptionPane.YES_NO_OPTION);
					if (reponse == 0) {
									//supprimer dans la base 
									Controleur.deleteMaison(idmaison);
									//actualiser l'affichage 
									unTableauM.supprimerLigne(numLigne);
									
					} 
				}
			}
			});
		this.tableAppartements.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne, idappartement ; 
				if (e.getClickCount() >=2 ) {
					numLigne = tableAppartements.getSelectedRow(); 
					idappartement = Integer.parseInt(unTableauA.getValueAt(numLigne, 0).toString()); 
					int reponse = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer la maison",
							"Suppression Appartement", JOptionPane.YES_NO_OPTION);
					if (reponse == 0) {
									//supprimer dans la base 
									Controleur.deleteAppartement(idappartement);
									//actualiser l'affichage 
									unTableauA.supprimerLigne(numLigne);
					} 
				}
			}
			});
		this.btFiltrerM.addActionListener(this);
		this.btFiltrerA.addActionListener(this);
		this.btAnnulerA.addActionListener(this);
		this.btEnregistrerA.addActionListener(this);
		this.btAnnulerM.addActionListener(this);
		this.btEnregistrerM.addActionListener(this);
	}
	public Object [][] obtenirDonneesA (String filtre, User unUser){
		//transformer l'ArrayList en une matrice [][] 
		ArrayList<Appartement> lesAppartements = Controleur.selectAllAppartements(filtre, unUser); 
		Object [][] matrice = new Object [lesAppartements.size()][8];
		int i = 0; 
		for (Appartement unAppartement : lesAppartements) {
			matrice [i][0] = unAppartement.getIdappartement(); 
			matrice [i][1] = unAppartement.getEtage();
			matrice [i][2] = unAppartement.getNump(); 
			matrice [i][3] = unAppartement.getNumb(); 
			matrice [i][4] = unAppartement.getAdresse(); 
			matrice [i][5] = unAppartement.getVille(); 
			matrice [i][6] = unAppartement.getCp(); 
			matrice [i][7] = unAppartement.getSuperficie(); 
			i++;
		}
		return matrice; 
	}
	public Object [][] obtenirDonneesM (String filtre, User unUser){
		//transformer l'ArrayList en une matrice [][] 
		ArrayList<Maison> lesMaisons = Controleur.selectAllMaisons(filtre, unUser); 
		Object [][] matrice = new Object [lesMaisons.size()][5];
		int i = 0; 
		for (Maison uneMaison : lesMaisons) {
			matrice [i][0] = uneMaison.getIdmaison(); 
			matrice [i][1] = uneMaison.getAdresse();
			matrice [i][2] = uneMaison.getSuperficie(); 
			matrice [i][3] = uneMaison.getCp(); 
			matrice [i][4] = uneMaison.getVille();
			i++;
		}
		return matrice; 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btFiltrerA) {
			 String filtre = this.txtFiltreA.getText(); 
			 
			 //recuperation des données de la bdd avec le filtre 
			 Object matriceA[][] = this.obtenirDonneesA(filtre, unUser);
			 Object matriceM[][] = this.obtenirDonneesM(filtre, unUser);
			 //actualisation de l'affichage 
			 this.unTableauA.setDonnees(matriceA);
			 this.unTableauM.setDonnees(matriceM);
		}else if (e.getSource() == this.btEnregistrerA && this.btEnregistrerA.getText().equals("Enregistrer")) {
			String adresseA = this.txtAdresseA.getText();
			float superficieA = Integer.parseInt(this.txtSuperficieA.getText());
			String villeA = this.txtVilleA.getText();
			int etageA = Integer.parseInt(this.txtEtageA.getText());
			int palierA = Integer.parseInt(this.txtNumpalierA.getText());
			int batimentA = Integer.parseInt(this.txtNumbatA.getText());
			int cpA = Integer.parseInt(this.txtCPA.getText());
			
			Appartement unAppartement = new Appartement(0, etageA, palierA, batimentA, cpA, this.unUser.getIduser(), adresseA, villeA, superficieA);
			
			Controleur.insertAppartement (unAppartement);
		}else if (e.getSource() == this.btEnregistrerM && this.btEnregistrerM.getText().equals("Enregistrer")) {
			String adresseM = this.txtAdresseM.getText();
			float superficieM = Integer.parseInt(this.txtSuperficieM.getText());
			String villeM = this.txtVilleM.getText();
			int cpM = Integer.parseInt(this.txtCPM.getText());
			
			Maison uneMaison = new Maison(0, cpM, this.unUser.getIduser(), adresseM, superficieM, villeM);
			
			Controleur.insertMaison (uneMaison);
		}
	}
}