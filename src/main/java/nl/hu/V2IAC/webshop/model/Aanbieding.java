package nl.hu.V2IAC.webshop.model;

import java.sql.Date;

public class Aanbieding {

	private int id;
	private Date vanDatum;
	private Date totDatum;
	private double nieuwPrijs;
	private Product product;

	public Aanbieding() {
	}

	public Aanbieding(double nieuwPrijs, Date vanDatum, Date totDatum) {
		this.nieuwPrijs = nieuwPrijs;
		this.vanDatum = vanDatum;
		this.totDatum = totDatum;
	}

	public Aanbieding(Date vanDatum, Date totDatum, double nieuwPrijs, Product product) {
		this.vanDatum = vanDatum;
		this.totDatum = totDatum;
		this.nieuwPrijs = nieuwPrijs;
		this.product = product;
	}

	public Aanbieding(int id, Date vanDatum, Date totDatum, double nieuwPrijs, Product product) {
		super();
		this.id = id;
		this.vanDatum = vanDatum;
		this.totDatum = totDatum;
		this.nieuwPrijs = nieuwPrijs;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getVanDatum() {
		return vanDatum;
	}

	public void setVanDatum(Date vanDatum) {
		this.vanDatum = vanDatum;
	}

	public Date getTotDatum() {
		return totDatum;
	}

	public void setTotDatum(Date totDatum) {
		this.totDatum = totDatum;
	}

	public double getNieuwPrijs() {
		return nieuwPrijs;
	}

	public void setNieuwPrijs(double nieuwPrijs) {
		this.nieuwPrijs = nieuwPrijs;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	};
}
