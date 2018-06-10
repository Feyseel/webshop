package nl.hu.V2IAC.webshop.resources;

import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Product;
import nl.hu.V2IAC.webshop.persistence.ProductDAO;

public class ProductService {

	private ProductDAO productDAO = new ProductDAO();

	
	public ArrayList<Product> getAllProducten() {
		return productDAO.getAllProducten();
	}

	public Product getProductById(int id) {
		return productDAO.getProductById(id);
	}

	public void insertProduct(Product product) {
		productDAO.insertProduct(product);
	}

	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}

	public void deleteProduct(int id) {
		productDAO.deleteProduct(id);
	}
}
