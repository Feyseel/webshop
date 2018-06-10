package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Adres;

public class AdresDAO extends BaseDAO {

	private ArrayList<Adres> selectAdressen(String query) {
		ArrayList<Adres> adres = new ArrayList<Adres>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				String land = dbResultSet.getString("land");
				String stad = dbResultSet.getString("stad");
				String straat = dbResultSet.getString("straat");
				int huisnummer = dbResultSet.getInt("huisnummer");
				String toevoeging = dbResultSet.getString("toevoeging");
				String postcode = dbResultSet.getString("postcode");

				Adres c = new Adres(id, land, stad, straat, huisnummer, toevoeging, postcode);
				adres.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return adres;
	}

	// Haalt alle adressen uit de database
	public ArrayList<Adres> getAllAdressen() {
		return selectAdressen("SELECT * FROM adres");
	}

	// Haalt adres uit de database met een id
	public Adres getAdresById(int id) {
		Adres c = new Adres();
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM adres WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setLand(rs.getString("land"));
				c.setStad(rs.getString("stad"));
				c.setStraat(rs.getString("straat"));
				c.setHuisnummer(rs.getInt("huisnummer"));
				c.setToevoeging(rs.getString("toevoeging"));
				c.setPostcode(rs.getString("postcode"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}

	public Long insertAdres(Adres adres) {
		long id = 0;
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO adres(land, stad, straat, huisnummer, toevoeging, postcode) VALUES(?,?,?,?,?,?)",
					new String[] { "id" });
			stmt.setString(1, adres.getLand());
			stmt.setString(2, adres.getStad());
			stmt.setString(3, adres.getStraat());
			stmt.setInt(4, adres.getHuisnummer());
			stmt.setString(5, adres.getToevoeging());
			stmt.setString(6, adres.getPostcode());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = (rs.getLong("id"));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return id;
	}

	public void updateAdres(Adres adres) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE adres SET land = ?, stad = ?, straat = ?, huisnummer = ?, toevoeging = ?, postcode = ? WHERE id = ?");
			stmt.setString(1, adres.getLand());
			stmt.setString(2, adres.getStad());
			stmt.setString(3, adres.getStraat());
			stmt.setInt(4, adres.getHuisnummer());
			stmt.setString(5, adres.getToevoeging());
			stmt.setString(6, adres.getPostcode());
			stmt.setInt(7, adres.getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void deleteAdres(int id) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM adres WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}