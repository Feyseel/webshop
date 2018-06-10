package nl.hu.V2IAC.webshop.resources;

import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Bestelling;
import nl.hu.V2IAC.webshop.persistence.BestellingDAO;

public class BestellingService {

	private BestellingDAO bestellingDAO = new BestellingDAO();

	public ArrayList<Bestelling> getAllBestellingen() {
		return bestellingDAO.getAllBestellingen();
	}

	public Bestelling getBestellingById(int id) {
		return bestellingDAO.getBestellingById(id);
	}

	public int insertBestelling(Bestelling bestelling) {
		return bestellingDAO.insertBestelling(bestelling);
	}

	public void updateBestelling(Bestelling bestelling) {
		bestellingDAO.updateBestelling(bestelling);
	}

	public void deleteBestelling(int id) {
		bestellingDAO.deleteBestelling(id);
	}
}
