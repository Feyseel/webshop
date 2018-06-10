package nl.hu.V2IAC.webshop.model;

import java.sql.Date;

public class Account {

	private int id;
	private String role;
	private String username;
	private String password;
	private Date openDatum;
	private boolean isActief;
	private Adres factuurAdres;
	private Klant klant;

	public Account() {
	}
	
	public Account(int id) {
		this.id = id;
	}
	
	public Account(int id, String password) {
		this.id = id;
		this.password = password;
	}

	public Account(String role, String username, String password, Date openDatum, boolean isActief, Adres factuurAdres,
			Klant klant) {
		this.role = role;
		this.username = username;
		this.password = password;
		this.openDatum = openDatum;
		this.isActief = isActief;
		this.factuurAdres = factuurAdres;
		this.klant = klant;
	}

	public Account(int id, String role, String username, Date openDatum, boolean isActief, Adres factuurAdres,
			Klant klant) {
		this.id = id;
		this.role = role;
		this.username = username;
		this.openDatum = openDatum;
		this.isActief = isActief;
		this.factuurAdres = factuurAdres;
		this.klant = klant;
	}

	public Account(int id, String role, String username, String password, Date openDatum, boolean isActief,
			Adres factuurAdres, Klant klant) {
		super();
		this.id = id;
		this.role = role;
		this.username = username;
		this.password = password;
		this.openDatum = openDatum;
		this.isActief = isActief;
		this.factuurAdres = factuurAdres;
		this.klant = klant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getOpenDatum() {
		return openDatum;
	}

	public void setOpenDatum(Date openDatum) {
		this.openDatum = openDatum;
	}

	public boolean isActief() {
		return isActief;
	}

	public void setActief(boolean isActief) {
		this.isActief = isActief;
	}

	public Adres getFactuurAdres() {
		return factuurAdres;
	}

	public void setFactuurAdres(Adres factuurAdres) {
		this.factuurAdres = factuurAdres;
	}

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

}
