package controleur;

public class Maison {
	private int idmaison, cp, idproprietaire;
	private String adresse, ville;
	private float superficie;
	public Maison(int idmaison, int cp, int idproprietaire, String adresse, float superficie, String ville) {
		super();
		this.idmaison = idmaison;
		this.cp = cp;
		this.idproprietaire = idproprietaire;
		this.adresse = adresse;
		this.superficie = superficie;
		this.ville = ville;
	}
	public Maison(String adresse) {
		super();
		this.idmaison = 0;
		this.cp = 0;
		this.idproprietaire = 0;
		this.adresse = adresse;
		this.superficie = 0;
		this.ville = ville;
	}
	public int getIdmaison() {
		return idmaison;
	}
	public void setIdmaison(int idmaison) {
		this.idmaison = idmaison;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public int getIdproprietaire() {
		return idproprietaire;
	}
	public void setIdproprietaire(int idproprietaire) {
		this.idproprietaire = idproprietaire;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public float getSuperficie() {
		return superficie;
	}
	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
}
