package controleur;

public class User {
	private int iduser;
	private String nom, prenom, email, mdp, role, nbreservation;
	public User(int iduser, String nom, String prenom, String email, String mdp, String role, String nbreservation) {
		super();
		this.iduser = iduser;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.role = role;
		this.nbreservation = nbreservation;
	}
	public User(String nom, String prenom, String email, String mdp, String role, String nbreservation) {
		super();
		this.iduser = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.role = role;
		this.nbreservation = nbreservation;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getNbreservation() {
		return nbreservation;
	}
	public void setNbreservation(String nbreservation) {
		this.nbreservation = nbreservation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
}
