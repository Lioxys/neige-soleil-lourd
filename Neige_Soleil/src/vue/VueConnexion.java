package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.IrisEvent;
import controleur.User;

public class VueConnexion extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelForm = new JPanel();
	private JTextField txtEmail = new JTextField();
	private JTextField txtMdp = new JTextField();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JButton btSinscrire = new JButton("S'inscrire");
	
	public VueConnexion() {
		this.setTitle("Application Neige et Soleil");
		this.setResizable(false);
		this.setBounds(100, 100, 600, 300);
		this.getContentPane().setBackground(Color.gray);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//placement du logo 
		ImageIcon uneImage = new ImageIcon("src/images/logo.png"); 
		JLabel leLogo = new JLabel(uneImage); 
		leLogo.setBounds(20, 20,240, 240);
		this.add(leLogo); 
		
		//construction du panel form 
		this.panelForm.setBounds(300, 70, 240, 150);
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setLayout(new GridLayout(4,2)); //3lignes - 2 colonnes 
		this.panelForm.add(new JLabel("Email : ")); 
		this.panelForm.add(this.txtEmail); 
		this.panelForm.add(new JLabel("Mdp :")); 
		this.panelForm.add(this.txtMdp); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btSeConnecter); 
		this.panelForm.add(this.btSinscrire);
		this.add(this.panelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		this.btSinscrire.addActionListener(this);
		
		this.setVisible(true);
	}
		public void actionPerformed(ActionEvent e) {
			 if (e.getSource() == this.btAnnuler) {
				 this.txtEmail.setText("");
				 this.txtMdp.setText("");
			 }else if (e.getSource() == this.btSeConnecter) {
				this.traitement ();
			 }else if (e.getSource() == this.btSinscrire) {
				 IrisEvent.rendreVisibleVueConnexion(false);
				 IrisEvent.rendreVisibleVueInscription(true);
			 }
		}

		public void traitement () {
			 String email = this.txtEmail.getText(); 
			 String mdp = this.txtMdp.getText();
			 
			 //on vérifie la sécurité des données 
			 
			 //on vérifie dans la base de données 
			 User unUser = Controleur.selectWhereUser(email, mdp); 
			 if (unUser == null) {
				 JOptionPane.showMessageDialog(this,
						 "Veuillez vérifier vos identifiants");
			 }else {
				 JOptionPane.showMessageDialog(this,
						 "Bienvenue "+unUser.getNom()
						 +"  "+unUser.getPrenom()); 
				 //on ouvre la vue générale 
				 IrisEvent.rendreVisibleVueConnexion(false);
				 IrisEvent.rendreVisibleVueGenerale(true, unUser);
			 }
		}
	}
