package nl.hu.V2IAC.webshop.model;

public class Product {

	private int id;
	private String naam;
	private String omschrijving;
	private double prijs;
	private String afbeelding;
	private boolean isAanbieding;
	private Aanbieding aanbieding;

	public Product() {
	}

	public Product(int id) {
		this.id = id;
	}

	public Product(String naam, String omschrijving, double prijs, String afbeelding) {
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.prijs = prijs;
		this.afbeelding = afbeelding;
	}

	public Product(int id, String naam, String omschrijving, double prijs, String afbeelding) {
		this.id = id;
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.prijs = prijs;
		this.afbeelding = afbeelding;
	}

	public Product(int id, String naam, String omschrijving, double prijs, String afbeelding, boolean isAanbieding,
			Aanbieding aanbieding) {
		super();
		this.id = id;
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.prijs = prijs;
		this.afbeelding = afbeelding;
		this.isAanbieding = isAanbieding;
		this.aanbieding = aanbieding;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public String getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}

	public boolean isAanbieding() {
		return this.isAanbieding;
	}

	public void setAanbieding(boolean isAanbieding) {
		this.isAanbieding = isAanbieding;
	}

	public Aanbieding getAanbieding() {
		return aanbieding;
	}

	public void setAanbieding(Aanbieding aanbieding) {
		this.aanbieding = aanbieding;
	}
}
