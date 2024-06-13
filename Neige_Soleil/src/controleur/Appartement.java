package controleur;

public class Appartement {
	private int idappartement, etage, nump, numb, cp, idproprietaire;
	private String adresse, ville;
	private float superficie;
	public Appartement(int idappartement, int etage, int nump, int numb, int cp, int idproprietaire, String adresse, String ville,
			float superficie) {
		super();
		this.idappartement = idappartement;
		this.etage = etage;
		this.nump = nump;
		this.numb = numb;
		this.cp = cp;
		this.idproprietaire = idproprietaire;
		this.adresse = adresse;
		this.ville = ville;
		this.superficie = superficie;
	}
	public Appartement(String adresse, String ville) {
		super();
		this.idappartement = 0;
		this.etage = 0;
		this.nump = 0;
		this.numb = 0;
		this.cp = 0;
		this.idproprietaire = 0;
		this.adresse = adresse;
		this.ville = ville;
		this.superficie = 0;
	}
	public int getIdappartement() {
		return idappartement;
	}
	public void setIdappartement(int idappartement) {
		this.idappartement = idappartement;
	}
	public int getEtage() {
		return etage;
	}
	public void setEtage(int etage) {
		this.etage = etage;
	}
	public int getNump() {
		return nump;
	}
	public void setNump(int nump) {
		this.nump = nump;
	}
	public int getNumb() {
		return numb;
	}
	public void setNumb(int numb) {
		this.numb = numb;
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
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public float getSuperficie() {
		return superficie;
	}
	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}
	
}
