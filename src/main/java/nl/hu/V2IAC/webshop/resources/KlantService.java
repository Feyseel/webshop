package nl.hu.V2IAC.webshop.resources;

import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Klant;
import nl.hu.V2IAC.webshop.persistence.KlantDAO;

public class KlantService {

	private KlantDAO klantDAO = new KlantDAO();

	public ArrayList<Klant> getAllKlanten() {
		return klantDAO.getAllKlanten();
	}

	public Klant getKlantById(int id) {
		return klantDAO.getKlantById(id);
	}

	public void insertKlant(Klant klant) {
		klantDAO.insertKlant(klant);
	}

	public void updateKlant(Klant klant) {
		klantDAO.updateKlant(klant);
	}

	public void deleteKlant(int id) {
		klantDAO.deleteKlant(id);
	}
}
