package nl.hu.V2IAC.webshop.resources;

import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Bestelregel;
import nl.hu.V2IAC.webshop.persistence.BestelregelDAO;

public class BestelregelService {

	private BestelregelDAO bestelregelDAO = new BestelregelDAO();

	public ArrayList<Bestelregel> getAllBestelregels() {
		return bestelregelDAO.getAllBestelregels();
	}

	public Bestelregel getBestelregelById(int id) {
		return bestelregelDAO.getBestelregelById(id);
	}

	public void insertBestelregel(Bestelregel bestelregel) {
		bestelregelDAO.insertBestelregel(bestelregel);
	}

	public void updateBestelregel(Bestelregel bestelregel) {
		bestelregelDAO.updateBestelregel(bestelregel);
	}

	public void deleteBestelregel(int id) {
		bestelregelDAO.deleteBestelregel(id);
	}
}
