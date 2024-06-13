package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {
	
	public static void insertUser(Client unClient) {
		 Modele.insertClient(unClient);
	}
	public static User selectWhereUser(String nom, String prenom) {
		return Modele.selectWhereUser(nom, prenom);
	}
	public static void updateUser(User unUser) {
		// TODO Auto-generated method stub
		Modele.updateUser(unUser);
	}
	public static ArrayList<Habitation> selectAllHabitations(String filtre) {
		// TODO Auto-generated method stub
		return Modele.selectAllHabitations (filtre);
	}
	public static void deleteMaison(int idmaison) {
		// TODO Auto-generated method stub
		Modele.deleteMaison(idmaison);
	}
	public static ArrayList<Appartement> selectAllAppartements(String filtre, User unUser) {
		// TODO Auto-generated method stub
		return Modele.selectAllAppartements (filtre, unUser);
	}
	public static ArrayList<Maison> selectAllMaisons(String filtre, User unUser) {
		// TODO Auto-generated method stub
		return Modele.selectAllMaisons (filtre, unUser);
	}
	public static void addReservation(float prix, String nbpersonne, int iduser, int idhabitation, String nbjours) {
		// TODO Auto-generated method stub
		Modele.addReservation(prix, nbpersonne, iduser, idhabitation, nbjours);
	}
	public static ArrayList<Reservation> selectAllReservations(String filtre, User unUser) {
		// TODO Auto-generated method stub
		return Modele.selectAllReservations (filtre, unUser);
	}
	public static float getSuperficie(int idhabitation, String type) {
		// TODO Auto-generated method stub
		return Modele.getSuperficie(idhabitation, type);
	}
	public static void deleteAppartement(int idappartement) {
		// TODO Auto-generated method stub
		Modele.deleteAppartement(idappartement);
	}
	public static void insertProprietaire(Proprietaire unProprietaire) {
		// TODO Auto-generated method stub
		Modele.insertProprietaire (unProprietaire);
	}
	public static void insertAppartement(Appartement unAppartement) {
		// TODO Auto-generated method stub
		Modele.insertAppartement(unAppartement);
	}
	public static void insertMaison(Maison uneMaison) {
		// TODO Auto-generated method stub
		Modele.insertMaison(uneMaison);
	}
}
