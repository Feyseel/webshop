package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Bestelling;
import nl.hu.V2IAC.webshop.model.Bestelregel;
import nl.hu.V2IAC.webshop.model.Product;

public class BestelregelDAO extends BaseDAO {

	private ArrayList<Bestelregel> selectBestelregels(String query) {
		ArrayList<Bestelregel> bestelregel = new ArrayList<Bestelregel>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				int aantal = dbResultSet.getInt("van_datum");
				double subtotaal = dbResultSet.getDouble("aantal");
				Product productId = new Product(dbResultSet.getInt("product_id"));
				Bestelling bestellingId = new Bestelling(dbResultSet.getInt("bestelling_id"));

				Bestelregel c = new Bestelregel(id, aantal, subtotaal, productId, bestellingId);
				bestelregel.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return bestelregel;
	}

	// Haalt alle aanbiedingen uit de database
	public ArrayList<Bestelregel> getAllBestelregels() {
		return selectBestelregels("SELECT * FROM bestelregel");
	}

	// Haalt aanbieding uit de database met een id
	public Bestelregel getBestelregelById(int id) {
		Bestelregel c = new Bestelregel();
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM bestelregel WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setAantal(rs.getInt("aantal"));
				c.setSubtotaal(rs.getDouble("subtotaal"));
				Product productId = new Product(rs.getInt("product_id"));
				c.setProduct(productId);
				Bestelling bestellingId = new Bestelling(rs.getInt("bestelling_id"));
				c.setBestelling(bestellingId);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}

	public void insertBestelregel(Bestelregel bestelregel) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO bestelregel(aantal, subtotaal, product_id, bestelling_id) VALUES(?,?,?,?)",
					new String[] { "id" });
			stmt.setInt(1, bestelregel.getAantal());
			stmt.setDouble(2, bestelregel.getSubtotaal());
			stmt.setInt(3, bestelregel.getProduct().getId());
			stmt.setInt(4, bestelregel.getBestelling().getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void updateBestelregel(Bestelregel bestelregel) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE bestelregel SET aantal = ?, subtotaal = ?, product_id = ?, bestelling_id = ? WHERE id = ?");
			stmt.setInt(1, bestelregel.getAantal());
			stmt.setDouble(2, bestelregel.getSubtotaal());
			stmt.setInt(3, bestelregel.getProduct().getId());
			stmt.setInt(4, bestelregel.getBestelling().getId());
			stmt.setInt(5, bestelregel.getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void deleteBestelregel(int id) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM bestelregel WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
