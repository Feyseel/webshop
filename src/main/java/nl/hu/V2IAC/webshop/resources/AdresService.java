package nl.hu.V2IAC.webshop.resources;

import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Adres;
import nl.hu.V2IAC.webshop.persistence.AdresDAO;

public class AdresService {

	private AdresDAO adresDAO = new AdresDAO();

	public ArrayList<Adres> getAllAdressen() {
		return adresDAO.getAllAdressen();
	}

	public Adres getAdresById(int id) {
		return adresDAO.getAdresById(id);
	}

	public void insertAdres(Adres adres) {
		adresDAO.insertAdres(adres);
	}

	public void updateAdres(Adres adres) {
		adresDAO.updateAdres(adres);
	}

	public void deleteAdres(int id) {
		adresDAO.deleteAdres(id);
	}
}