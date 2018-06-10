package nl.hu.V2IAC.webshop.resources;

import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Categorie;
import nl.hu.V2IAC.webshop.persistence.CategorieDAO;

public class CategorieService {

	private CategorieDAO categorieDAO = new CategorieDAO();

	
	public ArrayList<Categorie> getAllCategorieen() {
		return categorieDAO.getAllCategorieen();
	}
	
	public ArrayList<Categorie> getAllCategorieenById(int id) {
		return categorieDAO.getAllCategorieenById(id);
	}

	public void insertCategorie(Categorie categorie) {
		categorieDAO.insertCategorie(categorie);
	}

	public void updateCategorie(Categorie categorie) {
		categorieDAO.updateCategorie(categorie);
	}

	public void deleteCategorie(int id) {
		categorieDAO.deleteCategorie(id);
	}
}
