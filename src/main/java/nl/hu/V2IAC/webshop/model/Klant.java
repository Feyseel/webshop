package nl.hu.V2IAC.webshop.model;

public class Klant {

	private int id;
	private String voornaam;
	private String achternaam;
	private String afbeelding;
	private Adres woonAdres;

	public Klant() {
	}

	public Klant(int id) {
		this.id = id;
	}

	public Klant(int id, String voornaam, String achternaam, Adres woonAdres) {
		this.id = id;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.woonAdres = woonAdres;
	}

	public Klant(String voornaam, String achternaam, String afbeelding, Adres woonAdres) {
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.afbeelding = afbeelding;
		this.woonAdres = woonAdres;
	}

	public Klant(int id, String voornaam, String achternaam, String afbeelding, Adres woonAdres) {
		super();
		this.id = id;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.afbeelding = afbeelding;
		this.woonAdres = woonAdres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}

	public Adres getWoonAdres() {
		return woonAdres;
	}

	public void setWoonAdres(Adres woonAdres) {
		this.woonAdres = woonAdres;
	}

}
