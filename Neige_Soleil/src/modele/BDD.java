package modele;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class BDD {
	
	private String user, mdp, serveur, bdd;
	private Connection maConnexion;
	public BDD(String user, String mdp, String serveur, String bdd) {
		super();
		this.user = user;
		this.mdp = mdp;
		this.serveur = serveur;
		this.bdd = bdd;
		this.maConnexion = null; 
	}
	
	public void chargerPilote() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Attention, absence de pilote JDBC.");
		}
	}
	public void seConnecter() {
		this.chargerPilote();
		String url ="jdbc:mysql://"+this.serveur+"/"+this.bdd;
		try {
			this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
		}
		catch (SQLException exp) {
			System.out.println("Erreur de connexion à : "+url);
			exp.printStackTrace();
		}
	}
	public void seDeconnecter() {
		try {
			if (this.maConnexion != null) {
				this.maConnexion.close();
			}
		}
		catch (SQLException exp) {
			System.out.println("Erreur de fermeture de la connexion");
		}
	}
	public Connection getMaConnexion() {
		return this.maConnexion;
	}
}
