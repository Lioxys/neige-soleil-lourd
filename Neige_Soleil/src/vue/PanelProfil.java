package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.User;

public class PanelProfil extends PanelPrincipal implements ActionListener
{
	
	private User unUser;
	private JTextArea txtInfos = new JTextArea(); 
	private JButton btModifier = new JButton("Modifier"); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtEmail = new JTextField(); 
	private JTextField txtMdp = new JTextField(); 
	private JTextField txtRole = new JTextField(); 
	private JTextField txtNbreservation = new JTextField();
	
	private JPanel panelForm = new JPanel (); 
	
	public PanelProfil (User unUser) {
		super ("Gestion des infos du profil");
		
		this.unUser =unUser; 
		//placement de la textearea 
		this.txtInfos.setBounds(60, 50, 250, 250);
		this.txtInfos.setText(
				"\n ________ INFOS Profil ________"
				+ "\n\n Nom user    : "+this.unUser.getNom()
				+ "\n\n Prénom user : "+this.unUser.getPrenom()
				+ "\n\n Email user : "+this.unUser.getEmail()
				+ "\n\n Mdp user : "+this.unUser.getMdp()
				+ "\n\n Rôle user : "+this.unUser.getRole()
				+ "\n\n Nombre réservations user : "+this.unUser.getNbreservation()
				+ "\n\n ____________________________"); 
		this.add(this.txtInfos);
		this.txtInfos.setBackground(Color.gray); 
		
		//placement du bouton modifier 
		this.btModifier.setBounds(60, 305, 200, 40);
		this.add(this.btModifier);
		
		//placement du formulaire de modification 
		this.panelForm.setBounds(350, 50, 400, 300);
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setLayout(new GridLayout(6,2));
		this.panelForm.add(new JLabel("Nom User : "));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Prenom User : "));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Email User : "));
		this.panelForm.add(this.txtEmail);
		
		this.panelForm.add(new JLabel("Mdp User : "));
		this.panelForm.add(this.txtMdp); 
		
		this.panelForm.add(new JLabel(""));
		this.panelForm.add(new JLabel(""));
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		this.panelForm.setVisible(false);
		
		//rendre les boutons ecoutables 
		this.btModifier.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btModifier) {
			this.panelForm.setVisible(true);
			this.txtNom.setText(this.unUser.getNom());
			this.txtPrenom.setText(this.unUser.getPrenom());
			this.txtEmail.setText(this.unUser.getEmail());
			this.txtMdp.setText(this.unUser.getMdp());
		}
		else if (e.getSource() == this.btAnnuler) {
			this.panelForm.setVisible(false);
		}
		else if (e.getSource() == this.btEnregistrer) {
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText(); 
			String email = this.txtEmail.getText();
			String mdp = this.txtMdp.getText();
			
			//instanciation d'un user 
			unUser.setNom(nom);
			unUser.setPrenom(prenom);
			unUser.setEmail(email);
			unUser.setMdp(mdp);
			//modification dans la base de données 
			Controleur.updateUser (unUser); 
			//actualisation de l'affichage 
			JOptionPane.showMessageDialog(this, "Modification effectuée");
			
			this.txtInfos.setText(
					"\n ________ INFOS Profil ________"
					+ "\n\n Nom user    : "+this.unUser.getNom()
					+ "\n\n Prénom user : "+this.unUser.getPrenom()
					+ "\n\n Email user : "+this.unUser.getEmail()
					+ "\n\n Mdp user : "+this.unUser.getMdp()
					+ "\n\n Rôle  user : "+this.unUser.getRole()
					+ "\n\n Nombre réservation  user : "+this.unUser.getNbreservation()
					+ "\n\n ____________________________"); 
			this.panelForm.setVisible(false);
			
		}
	}
}







