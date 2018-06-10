package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Aanbieding;
import nl.hu.V2IAC.webshop.model.Product;

public class ProductDAO extends BaseDAO {

	private ArrayList<Product> selectProducten(String query) {
		ArrayList<Product> product = new ArrayList<Product>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				String naam = dbResultSet.getString("naam");
				String omschrijving = dbResultSet.getString("omschrijving");
				double prijs = dbResultSet.getDouble("prijs");
				String afbeelding = dbResultSet.getString("afbeelding");
				boolean isAanbieding = false;
				if (dbResultSet.getDate("van_datum") != null) {
					isAanbieding = true;
				}
				Aanbieding aanbieding = new Aanbieding(dbResultSet.getDouble("nieuw_prijs"),
						dbResultSet.getDate("van_datum"), dbResultSet.getDate("tot_datum"));

				Product c = new Product(id, naam, omschrijving, prijs, afbeelding, isAanbieding, aanbieding);
				product.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return product;
	}

	// Haalt alle producten uit de database
	public ArrayList<Product> getAllProducten() {
		return selectProducten(
				"SELECT p.id, p.naam, p.omschrijving, p.prijs, p.afbeelding, a.nieuw_prijs, a.van_datum, a.tot_datum\r\n"
						+ "FROM product p LEFT OUTER JOIN aanbieding a ON p.id = a.product_id;");
	}

	// Haalt product uit de database met een id
	public Product getProductById(int id) {
		Product c = new Product();
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT p.id, p.naam, p.omschrijving, p.prijs, p.afbeelding, a.nieuw_prijs, a.van_datum, a.tot_datum\r\n"
							+ "FROM product p LEFT OUTER JOIN aanbieding a ON p.id = a.product_id WHERE p.id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setNaam(rs.getString("naam"));
				c.setOmschrijving(rs.getString("omschrijving"));
				c.setPrijs(rs.getDouble("prijs"));
				c.setAfbeelding(rs.getString("afbeelding"));
				if (rs.getDate("van_datum") != null) {
					c.setAanbieding(true);
				} else {
					c.setAanbieding(false);
				}
				Aanbieding aanbieding = new Aanbieding(rs.getDouble("nieuw_prijs"), rs.getDate("van_datum"),
						rs.getDate("tot_datum"));
				c.setAanbieding(aanbieding);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}

	public void insertProduct(Product product) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO product(naam, omschrijving, prijs, afbeelding) VALUES(?,?,?,?)");
			stmt.setString(1, product.getNaam());
			stmt.setString(2, product.getOmschrijving());
			stmt.setDouble(3, product.getPrijs());
			stmt.setString(4, product.getAfbeelding());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void updateProduct(Product product) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE product SET naam = ?, omschrijving = ?, prijs = ?, afbeelding = ? WHERE id = ?");
			stmt.setString(1, product.getNaam());
			stmt.setString(2, product.getOmschrijving());
			stmt.setDouble(3, product.getPrijs());
			stmt.setString(4, product.getAfbeelding());
			stmt.setInt(5, product.getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void deleteProduct(int id) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM bestelregel WHERE product_id = ?;"
					+ "DELETE FROM product_categorie WHERE product_id = ?;"
					+ "DELETE FROM aanbieding WHERE product_id = ?;" + "DELETE FROM product WHERE id = ?;");
			stmt.setInt(1, id);
			stmt.setInt(2, id);
			stmt.setInt(3, id);
			stmt.setInt(4, id);
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
