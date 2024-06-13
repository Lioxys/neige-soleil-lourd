package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Habitation;
import controleur.Maison;
import controleur.Proprietaire;
import controleur.Reservation;
import controleur.Appartement;
import controleur.Client;
import controleur.Habitation;
import controleur.User;

public class Modele {
	private static BDD uneBDD = new BDD("root", "", "localhost", "neige_soleil_jv_24");
	// private static BDD uneBDD = new BDD("ludkas", "ludkas", "35.169.205.60:13392", "neige_soleil_jv_24");
	
	public static void insertClient(Client unClient) {
		String requete="insert into client values(null,'"
				+unClient.getNom()+"','"
				+unClient.getPrenom()+"','"
				+unClient.getEmail()+"','"
				+unClient.getMdp()+"','"
				+unClient.getAdresse()+"','"
				+unClient.getCp()+"','"
				+unClient.getVille()+"','"
				+unClient.getTel()+"');";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			
			unStat.execute(requete);
			
			unStat.close();
			uneBDD.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + exp);
		}
		
	}
	public static User selectWhereUser (String email, String mdp) {
		User unUser = null;
		String requete = "select * from user where email='"+email+"' and mdp='"+mdp+"' ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unUser = new User(unResultat.getInt("iduser"), unResultat.getString("nom"),
						unResultat.getString("prenom"), unResultat.getString("email"), unResultat.getString("mdp"), unResultat.getString("role"),
						unResultat.getString("nbreservation"));
			}
			unStat.close();
			uneBDD.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unUser;
	}
	public static void updateUser(User unUser) {
		// TODO Auto-generated method stub
		 String requete="update user set nom='"
				 +unUser.getNom()+"',prenom = '"
				 +unUser.getPrenom()+"', email = '"
				 +unUser.getEmail()+"', mdp = '"
				 +unUser.getMdp()+ "' where iduser = "
				 +unUser.getIduser() +";";
		 try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement(); 
				
				unStat.execute(requete);
				
				unStat.close();
				uneBDD.seDeconnecter();	
			 }
			 catch (SQLException exp)
				{
					System.out.println("Erreur de requete : " + exp );
				}	
			 
		 }
	public static ArrayList<Habitation> selectAllHabitations(String filtre) {
		// TODO Auto-generated method stub
		String requete="";
		if (filtre.equals("")) {
			requete = "select * from habitation ; ";
		}else {
			requete = "select * from habitation where type like '%"
					+ filtre +"%' or etat like '%" + filtre + "%' ;";
		}
		ArrayList<Habitation> lesHabitations = new ArrayList<Habitation>();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete); 
			while (desRes.next()) {
				Habitation uneHabitation = new Habitation (
						desRes.getInt("idhabitation"), desRes.getString("type"), 
						desRes.getString("etat")
						);
				lesHabitations.add(uneHabitation);
			}
			unStat.close();
			uneBDD.seDeconnecter();	
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + exp );
		}	
		return lesHabitations;
	}
	
	public static void deleteMaison(int idmaison) {
		// TODO Auto-generated method stub
		String requete = "delete from maison where idhabitation = "+idmaison+";";
		 try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement(); 
				unStat.execute(requete);
				unStat.close();
				uneBDD.seDeconnecter();	
			 }
		 catch (SQLException exp)
			{
				System.out.println("Erreur de requete : " + exp );
			}	
	}
	public static ArrayList<Appartement> selectAllAppartements(String filtre, User unUser) {
		// TODO Auto-generated method stub
		String requete="";
		if (filtre.equals("")) {
			requete = "select * from appartement where idproprietaire = " + unUser.getIduser() + "; ";
		}else {
			requete = "select * from appartement where adresse like '%"
					+ filtre +"%' or code_postal like '%" + filtre + "%' or ville like '%" + filtre + "%' "
					+ "or superficie like '%" + filtre + "%' ;";
		}
		ArrayList<Appartement> lesAppartements = new ArrayList<Appartement>();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete); 
			while (desRes.next()) {
				Appartement unAppartement = new Appartement (
						desRes.getInt("idhabitation"), desRes.getInt("etage"), desRes.getInt("num_palier"), desRes.getInt("num_batiment"),
						desRes.getInt("code_postal"), desRes.getInt("idproprietaire"), desRes.getString("adresse"), desRes.getString("ville"), desRes.getFloat("superficie")
						);
				lesAppartements.add(unAppartement);
			}
			unStat.close();
			uneBDD.seDeconnecter();	
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + exp );
		}	
		return lesAppartements;
	}
	public static ArrayList<Maison> selectAllMaisons(String filtre, User unUser) {
		// TODO Auto-generated method stub
		String requete="";
		if (filtre.equals("")) {
			requete = "select * from Maison where idproprietaire = " + unUser.getIduser() + "; ";
		}else {
			requete = "select * from Maison where adresse like '%"
					+ filtre +"%' or code_postal like '%" + filtre + "%' or ville like '%" + filtre + "%' ;";
		}
		ArrayList<Maison> lesMaisons = new ArrayList<Maison>();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete); 
			while (desRes.next()) {
				Maison uneMaison = new Maison (
						desRes.getInt("idhabitation"), desRes.getInt("code_postal"), desRes.getInt("idproprietaire"), desRes.getString("adresse"), desRes.getFloat("superficie"), desRes.getString("ville")
						);
				lesMaisons.add(uneMaison);
			}
			unStat.close();
			uneBDD.seDeconnecter();	
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + exp );
		}	
		return lesMaisons;
	}
	public static void addReservation(float prix, String nbpersonne, int iduser, int idhabitation, String nbjours) {
		// TODO Auto-generated method stub
		String requete = "insert into reservation values(null," +prix+","+nbpersonne+","+iduser+","+idhabitation+","+nbjours+");"; 
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeconnecter();	
		 }
	 catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + exp );
		}	
	}
	public static ArrayList<Reservation> selectAllReservations(String filtre, User unUser) {
		// TODO Auto-generated method stub
		String requete="";
		if (filtre.equals("")) {
			requete = "select * from reservation where iduser = " + unUser.getIduser() + "; ";
		}else {
			requete = "select * from reservation where prix like '%"
					+ filtre +"%' or nb_personne like '%" + filtre + "%' or idhabitation like '%" + filtre + "%' ;";
		}
		ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete); 
			while (desRes.next()) {
				Reservation uneReservation = new Reservation (
						desRes.getInt("idreservation"), desRes.getInt("nb_personne"), desRes.getInt("nb_jours"), desRes.getInt("iduser"), desRes.getInt("idhabitation"), desRes.getFloat("prix")
						);
				lesReservations.add(uneReservation);
			}
			unStat.close();
			uneBDD.seDeconnecter();	
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}	
		return lesReservations;
	}
	public static float getSuperficie(int idhabitation, String type) {
		// TODO Auto-generated method stub
		float superficie = 0;
		String requete = "select superficie from " + type + " where idhabitation = " + idhabitation + ";";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				superficie = unResultat.getFloat("superficie");
			}
			unStat.close();
			uneBDD.seDeconnecter();
		}catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
		return superficie;
	}
	public static void deleteAppartement(int idappartement) {
		// TODO Auto-generated method stub
		String requete = "delete from appartement where idhabitation = "+idappartement+";";
		 try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement(); 
				unStat.execute(requete);
				unStat.close();
				uneBDD.seDeconnecter();	
			 }
		 catch (SQLException exp)
			{
				System.out.println("Erreur de requete : " + requete );
			}
	}
	public static void insertProprietaire(Proprietaire unProprietaire) {
		// TODO Auto-generated method stub
		String requete="insert into proprietaire values(null,'"
				+unProprietaire.getNom()+"','"
				+unProprietaire.getPrenom()+"','"
				+unProprietaire.getAdresse()+"','"
				+unProprietaire.getCp()+"','"
				+unProprietaire.getVille()+"','"
				+unProprietaire.getTel()+"',0,'"
				+unProprietaire.getMdp()+"','"
				+unProprietaire.getEmail()+"');";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			
			unStat.execute(requete);
			
			unStat.close();
			uneBDD.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " + exp);
		}
	}
	public static void insertAppartement(Appartement unAppartement) {
		// TODO Auto-generated method stub
		String requete ="insert into appartement values(null, "
				 +unAppartement.getEtage()+","
				 +unAppartement.getNump()+","
				 +unAppartement.getNumb()+",'"
				 +unAppartement.getAdresse()+"',"
				 +unAppartement.getCp()+",'"
				 +unAppartement.getVille()+"',"
				 +unAppartement.getSuperficie()+","
				 +unAppartement.getIdproprietaire()+");";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeconnecter();	
		 }
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}	
	}
	public static void insertMaison(Maison uneMaison) {
		// TODO Auto-generated method stub
		String requete ="insert into maison values(null, "
				 +uneMaison.getSuperficie()+",'"
				 +uneMaison.getAdresse()+"',"
				 +uneMaison.getCp()+","
				 +uneMaison.getIdproprietaire()+",'"
				 +uneMaison.getVille()+"');";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeconnecter();	
		 }
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}	
	}
		 
}
