package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Aanbieding;
import nl.hu.V2IAC.webshop.model.Product;

public class AanbiedingDAO extends BaseDAO {

	private ArrayList<Aanbieding> selectAanbiedingen(String query) {
		ArrayList<Aanbieding> aanbieding = new ArrayList<Aanbieding>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				Date vanDatum = dbResultSet.getDate("van_datum");
				Date totDatum = dbResultSet.getDate("tot_datum");
				double nieuwPrijs = dbResultSet.getDouble("nieuw_prijs");
				Product productId = new Product(dbResultSet.getInt("product_id"));

				Aanbieding c = new Aanbieding(id, vanDatum, totDatum, nieuwPrijs, productId);
				aanbieding.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return aanbieding;
	}

	// Haalt alle aanbiedingen uit de database
	public ArrayList<Aanbieding> getAllAanbiedingen() {
		return selectAanbiedingen("SELECT * FROM aanbieding");
	}

	// Haalt aanbieding uit de database met een id
	public Aanbieding getAanbiedingById(int id) {
		Aanbieding c = new Aanbieding();
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM aanbieding WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setVanDatum(rs.getDate("van_datum"));
				c.setTotDatum(rs.getDate("tot_datum"));
				c.setNieuwPrijs(rs.getDouble("nieuw_prijs"));
				Product productId = new Product((rs.getInt("product_id")));
				c.setProduct(productId);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}

	public void insertAanbieding(Aanbieding aanbieding) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO aanbieding(van_datum, tot_datum, nieuw_prijs, product_id) VALUES(to_date(?,'YYYY-MM-DD'),to_date(?,'YYYY-MM-DD'),?,?)");
			stmt.setDate(1, aanbieding.getVanDatum());
			stmt.setDate(2, aanbieding.getTotDatum());
			stmt.setDouble(3, aanbieding.getNieuwPrijs());
			stmt.setInt(4, aanbieding.getProduct().getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void updateAanbieding(Aanbieding aanbieding) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE aanbieding SET van_datum = to_date(?,'YYYY-MM-DD'), tot_datum = to_date(?,'YYYY-MM-DD'), nieuw_prijs = ?, product_id = ? WHERE id = ?");
			stmt.setDate(1, aanbieding.getVanDatum());
			stmt.setDate(2, aanbieding.getTotDatum());
			stmt.setDouble(3, aanbieding.getNieuwPrijs());
			stmt.setInt(4, aanbieding.getProduct().getId());
			stmt.setInt(5, aanbieding.getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void deleteAanbieding(int id) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM aanbieding WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
