package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Categorie;
import nl.hu.V2IAC.webshop.model.Product;
import nl.hu.V2IAC.webshop.model.ProductCategorie;

public class ProductCategorieDAO extends BaseDAO {

	private ArrayList<ProductCategorie> selectProductCategorieen(String query) {
		ArrayList<ProductCategorie> productCategorie = new ArrayList<ProductCategorie>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				Product productId = new Product(dbResultSet.getInt("product_id"));
				Categorie categorieId = new Categorie(dbResultSet.getInt("categorie_id"));

				ProductCategorie c = new ProductCategorie(productId, categorieId);
				productCategorie.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return productCategorie;
	}

	// Haalt alle productcategorieen uit de database
	public ArrayList<ProductCategorie> getAllProductCategorieen() {
		return selectProductCategorieen("SELECT * FROM product_categorie");
	}

	// Haalt productcategorie uit de database met een id
	public ProductCategorie getProductCategorieById(int productId, int categorieId) {
		ProductCategorie c = new ProductCategorie();
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM product_categorie WHERE product_id=? AND categorie_id = ?");
			stmt.setInt(1, productId);
			stmt.setInt(2, categorieId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Product product_id = new Product(rs.getInt("product_id"));
				c.setProduct(product_id);
				Categorie categorie_id = new Categorie(rs.getInt("categorie_id"));
				c.setCategorie(categorie_id);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}

	public void insertProductCategorie(ProductCategorie productCategorie) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO product_categorie(product_id, categorie_id) VALUES(?,?)");
			stmt.setInt(1, productCategorie.getProduct().getId());
			stmt.setInt(2, productCategorie.getCategorie().getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void deleteProductCategorie(int productId, int categorieId) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con
					.prepareStatement("DELETE FROM product_categorie WHERE product_id = ? AND categorie_id = ?");
			stmt.setInt(1, productId);
			stmt.setInt(2, categorieId);
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
