package controleur;

public class Reservation {
	private int idreservation, nb_personne, nb_jours, iduser, idhabitation;
	private float prix;
	public Reservation(int idreservation, int nb_personne, int nb_jours, int iduser, int idhabitation, float prix) {
		super();
		this.idreservation = idreservation;
		this.nb_personne = nb_personne;
		this.nb_jours = nb_jours;
		this.iduser = iduser;
		this.idhabitation = idhabitation;
		this.prix = prix;
	}
	public Reservation() {
		super();
		this.idreservation = 0;
		this.nb_personne = 0;
		this.nb_jours = 0;
		this.iduser = 0;
		this.idhabitation = 0;
		this.prix = 0;
	}
	public int getIdreservation() {
		return idreservation;
	}
	public void setIdreservation(int idreservation) {
		this.idreservation = idreservation;
	}
	public int getNb_personne() {
		return nb_personne;
	}
	public void setNb_personne(int nb_personne) {
		this.nb_personne = nb_personne;
	}
	public int getNb_jours() {
		return nb_personne;
	}
	public void setNb_jours(int nb_jours) {
		this.nb_jours = nb_jours;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public int getIdhabitation() {
		return idhabitation;
	}
	public void setIdhabitation(int idhabitation) {
		this.idhabitation = idhabitation;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	
}
