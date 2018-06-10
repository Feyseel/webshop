package nl.hu.V2IAC.webshop.model;

public class Adres {

	private int id;
	private String land;
	private String stad;
	private String straat;
	private int huisnummer;
	private String toevoeging;
	private String postcode;

	public Adres() {
	}
	
	public Adres(int id) {
		this.id = id;
	}

	public Adres(String land, String stad, String straat, int huisnummer, String toevoeging, String postcode) {
		this.land = land;
		this.stad = stad;
		this.straat = straat;
		this.huisnummer = huisnummer;
		this.toevoeging = toevoeging;
		this.postcode = postcode;
	}

	public Adres(int id, String land, String stad, String straat, int huisnummer, String toevoeging, String postcode) {
		super();
		this.id = id;
		this.land = land;
		this.stad = stad;
		this.straat = straat;
		this.huisnummer = huisnummer;
		this.toevoeging = toevoeging;
		this.postcode = postcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getStad() {
		return stad;
	}

	public void setStad(String stad) {
		this.stad = stad;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public int getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(int huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getToevoeging() {
		return toevoeging;
	}

	public void setToevoeging(String toevoeging) {
		this.toevoeging = toevoeging;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
}
