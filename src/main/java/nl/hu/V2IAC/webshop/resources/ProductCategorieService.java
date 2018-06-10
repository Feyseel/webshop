package nl.hu.V2IAC.webshop.resources;

import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.ProductCategorie;
import nl.hu.V2IAC.webshop.persistence.ProductCategorieDAO;

public class ProductCategorieService {

	private ProductCategorieDAO productCategorieDAO = new ProductCategorieDAO();

	public ArrayList<ProductCategorie> getAllProductCategorieen() {
		return productCategorieDAO.getAllProductCategorieen();
	}

	public ProductCategorie getProductCategorieById(int productId, int categorieId) {
		return productCategorieDAO.getProductCategorieById(productId, categorieId);
	}

	public void insertProductCategorie(ProductCategorie productCategorie) {
		productCategorieDAO.insertProductCategorie(productCategorie);
	}

	public void deleteProductCategorie(int productId, int categorieId) {
		productCategorieDAO.deleteProductCategorie(productId, categorieId);
	}
}
