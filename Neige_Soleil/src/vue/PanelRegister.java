package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Controleur;
import controleur.IrisEvent;
import controleur.Proprietaire;

public class PanelRegister extends JFrame implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCP = new JTextField();
	private JTextField txtVille = new JTextField();
	private JTextField txtTel = new JTextField();
	private JTextField txtEmail = new JTextField(""); 
	private JPasswordField txtMdp = new JPasswordField(""); 
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JComboBox<String> txtStatut = new  JComboBox<String> ();
	private JTextField txtClient = new JTextField();
	private JTextField txtProprietaire = new JTextField();
	
	private JPanel panelForm = new JPanel(); 
	
	public PanelRegister() {
		
		this.setTitle("Création de compte Neige et Soleil");
		this.setResizable(false);
		this.setBounds(100, 100, 600, 600);
		this.getContentPane().setBackground(Color.gray);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panelForm.setBounds(20, 80, 250, 200);
		this.panelForm.setBackground(Color.gray); 
		this.panelForm.setLayout(new GridLayout(10,2));
		this.panelForm.add(new JLabel("Nom : "));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Prénom : "));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Email : "));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("MDP :")); 
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(new JLabel("Adresse : "));
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("Code postal : "));
		this.panelForm.add(this.txtCP);
		this.panelForm.add(new JLabel("Ville : "));
		this.panelForm.add(this.txtVille);
		this.panelForm.add(new JLabel("Téléphone : "));
		this.panelForm.add(this.txtTel);
		this.panelForm.add(new JLabel("Statut"));
		this.panelForm.add(this.txtStatut);
		this.txtStatut.addItem("Client");
		this.txtStatut.addItem("proprietaire");
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.txtNom.addKeyListener(this);
		this.txtPrenom.addKeyListener(this);
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		this.txtAdresse.addKeyListener(this);
		this.txtCP.addKeyListener(this);
		this.txtVille.addKeyListener(this);
		this.txtTel.addKeyListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
			this.txtAdresse.setText("");
			this.txtCP.setText("");
			this.txtVille.setText("");
			this.txtTel.setText("");
		}else if (e.getSource() == this.btEnregistrer) {
			this.traitement();
		}
	}
	public void traitement () {
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String email = this.txtEmail.getText(); 
		String mdp = new String (this.txtMdp.getPassword()); 
		String adresse = this.txtAdresse.getText();
		String cp = this.txtCP.getText();
		String ville = this.txtVille.getText();
		String tel = this.txtTel.getText();
		String role = this.txtStatut.getSelectedItem().toString();
		
		if (role.equals("Client")) {
			Client unClient = new Client(nom, prenom, email, mdp, adresse, cp, ville, tel);
			Controleur.insertUser(unClient);
			IrisEvent.rendreVisibleVueInscription(false);
			IrisEvent.rendreVisibleVueConnexion(true);
		} else {
			Proprietaire unProprietaire = new Proprietaire(nom, prenom, email, mdp, adresse, cp, ville, tel);
			Controleur.insertProprietaire(unProprietaire);
			IrisEvent.rendreVisibleVueInscription(false);
			IrisEvent.rendreVisibleVueConnexion(true);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.traitement (); 
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
