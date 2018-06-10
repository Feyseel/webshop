package nl.hu.V2IAC.webshop.model;

import java.util.ArrayList;

public class Bestelling {

	private int id;
	private Adres afleverAdres;
	private Account account;
	private Klant klant;
	private ArrayList<Bestelregel> bestelregelArray = new ArrayList<Bestelregel>();

	public Bestelling() {
	}

	public Bestelling(int id) {
		this.id = id;
	}

	public Bestelling(Adres afleverAdres, Account account) {
		this.afleverAdres = afleverAdres;
		this.account = account;
	}

	public Bestelling(int id, Adres afleverAdres, Account account) {
		this.id = id;
		this.afleverAdres = afleverAdres;
		this.account = account;
	}

	public Bestelling(int id, Adres afleverAdres, Account account, ArrayList<Bestelregel> bestelregelArray) {
		super();
		this.id = id;
		this.afleverAdres = afleverAdres;
		this.account = account;
		this.bestelregelArray = bestelregelArray;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adres getAfleverAdres() {
		return afleverAdres;
	}

	public void setAfleverAdres(Adres afleverAdres) {
		this.afleverAdres = afleverAdres;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Klant getKlant() {
		return this.klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public ArrayList<Bestelregel> getBestelregelArray() {
		return this.bestelregelArray;
	}

	public void addBestelregel(Bestelregel bestelregel) {
		this.bestelregelArray.add(bestelregel);
	}
}
