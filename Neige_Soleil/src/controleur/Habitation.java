package controleur;

public class Habitation {
	private int idhabitation;
	private String type, etat;
	public Habitation(int idhabitation, String type, String etat) {
		super();
		this.idhabitation = idhabitation;
		this.type = type;
		this.etat = etat;
	}
	public Habitation(String type, String etat) {
		super();
		this.idhabitation = 0;
		this.type = type;
		this.etat = etat;
	}
	public int getIdhabitation() {
		return idhabitation;
	}
	public void setIdhabitation(int idhabitation) {
		this.idhabitation = idhabitation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
}
