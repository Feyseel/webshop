package nl.hu.V2IAC.webshop.model;

import java.util.ArrayList;

public class Categorie {

	private int id;
	private String naam;
	private String omschrijving;
	private String afbeelding;
	private Product product;
	private ArrayList<Product> productList;

	public Categorie() {
	}

	public Categorie(int id) {
		this.id = id;
	}

	public Categorie(String naam, String omschrijving, String afbeelding) {
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.afbeelding = afbeelding;
	}

	public Categorie(int id, String naam, String omschrijving, String afbeelding) {
		this.id = id;
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.afbeelding = afbeelding;
	}

	public Categorie(String naam, String omschrijving, Product product) {
		super();
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.product = product;
	}

	public Categorie(String naam, String omschrijving, String afbeelding, ArrayList<Product> productList) {
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.productList = productList;
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

	public String getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ArrayList<Product> product() {
		return this.productList;
	}

	public void addProduct(Product product) {
		this.productList.add(product);
	}
}
