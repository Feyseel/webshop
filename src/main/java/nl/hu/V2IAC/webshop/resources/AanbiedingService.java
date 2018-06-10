package nl.hu.V2IAC.webshop.resources;

import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Aanbieding;
import nl.hu.V2IAC.webshop.persistence.AanbiedingDAO;

public class AanbiedingService {
	private AanbiedingDAO aanbiedingDAO = new AanbiedingDAO();

	public ArrayList<Aanbieding> getAllAanbiedingen() {
		return aanbiedingDAO.getAllAanbiedingen();
	}

	public Aanbieding getAanbiedingById(int id) {
		return aanbiedingDAO.getAanbiedingById(id);
	}

	public void insertAanbieding(Aanbieding aanbieding) {
		aanbiedingDAO.insertAanbieding(aanbieding);
	}

	public void updateAanbieding(Aanbieding aanbieding) {
		aanbiedingDAO.updateAanbieding(aanbieding);
	}

	public void deleteAanbieding(int id) {
		aanbiedingDAO.deleteAanbieding(id);
	}
}
