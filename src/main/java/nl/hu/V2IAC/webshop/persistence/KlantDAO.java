package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Adres;
import nl.hu.V2IAC.webshop.model.Klant;

public class KlantDAO extends BaseDAO {

	private ArrayList<Klant> selectKlanten(String query) {
		ArrayList<Klant> klant = new ArrayList<Klant>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				String voornaam = dbResultSet.getString("voornaam");
				String achternaam = dbResultSet.getString("achternaam");
				String afbeelding = dbResultSet.getString("afbeelding");
				Adres woonadresId = new Adres(dbResultSet.getInt("woonadres_id"));

				Klant c = new Klant(id, voornaam, achternaam, afbeelding, woonadresId);
				klant.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return klant;
	}

	// Haalt alle klanten uit de database
	public ArrayList<Klant> getAllKlanten() {
		return selectKlanten("SELECT * FROM klant");
	}

	// Haalt adres uit de database met een id
	public Klant getKlantById(int id) {
		Klant c = new Klant();
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"select k.id, k.voornaam, k.achternaam, k.afbeelding, ad.land, ad.stad, ad.straat, ad.huisnummer, ad.toevoeging, ad.postcode\n"
							+ "FROM klant k LEFT OUTER JOIN account a ON k.id = a.klant_id AND a.id = ? INNER JOIN adres ad ON ad.id = k.woonadres_id");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setVoornaam(rs.getString("voornaam"));
				c.setAchternaam(rs.getString("achternaam"));
				c.setAfbeelding(rs.getString("afbeelding"));
				Adres woonadresId = new Adres(rs.getString("land"), rs.getString("stad"), rs.getString("straat"),
						rs.getInt("huisnummer"), rs.getString("toevoeging"), rs.getString("postcode"));
				c.setWoonAdres(woonadresId);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}

	public void insertKlant(Klant klant) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO klant(voornaam, achternaam, afbeelding, woonadres_id) VALUES(?,?,?,?)");
			stmt.setString(1, klant.getVoornaam());
			stmt.setString(2, klant.getAchternaam());
			stmt.setString(3, klant.getAfbeelding());
			stmt.setInt(4, klant.getWoonAdres().getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void updateKlant(Klant klant) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"UPDATE klant SET voornaam = ?, achternaam = ?, afbeelding = ?, woonadres_id = ? WHERE id = ?");
			stmt.setString(1, klant.getVoornaam());
			stmt.setString(2, klant.getAchternaam());
			stmt.setString(3, klant.getAfbeelding());
			stmt.setInt(4, klant.getWoonAdres().getId());
			stmt.setInt(5, klant.getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void deleteKlant(int id) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM klant WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
