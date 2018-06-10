package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Account;
import nl.hu.V2IAC.webshop.model.Adres;
import nl.hu.V2IAC.webshop.model.Bestelling;
import nl.hu.V2IAC.webshop.model.Bestelregel;

public class BestellingDAO extends BaseDAO {

	private ArrayList<Bestelling> selectBestelling(String query) {
		ArrayList<Bestelling> bestelling = new ArrayList<Bestelling>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				Adres afleverAdresId = new Adres(dbResultSet.getInt("afleveradres_id"));
				Account accountId = new Account(dbResultSet.getInt("klant_id"));

				Bestelling c = new Bestelling(id, afleverAdresId, accountId);
				bestelling.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return bestelling;
	}

	// Haalt alle bestellingen uit de database
	public ArrayList<Bestelling> getAllBestellingen() {
		return selectBestelling("SELECT * FROM bestelling");
	}

	// Haalt bestelling uit de database met een id
	public Bestelling getBestellingById(int id) {
		Bestelling c = new Bestelling();
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM bestelling WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				Adres afleverAdresId = new Adres((rs.getInt("afleveradres_id")));
				c.setAfleverAdres(afleverAdresId);
				Account accountId = new Account((rs.getInt("klant_id")));
				c.setAccount(accountId);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}

	public int insertBestelling(Bestelling bestelling) {
		int bestellingId = 0;
		try (Connection con = super.connect()) {
			Adres adres = new Adres(bestelling.getKlant().getWoonAdres().getLand(),
					bestelling.getKlant().getWoonAdres().getStad(), bestelling.getKlant().getWoonAdres().getStraat(),
					bestelling.getKlant().getWoonAdres().getHuisnummer(),
					bestelling.getKlant().getWoonAdres().getToevoeging(),
					bestelling.getKlant().getWoonAdres().getPostcode());
			AdresDAO adresDAO = new AdresDAO();
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO bestelling(klant_id, afleveradres_id) VALUES(?,?)", new String[] { "id" });
			stmt.setInt(1, bestelling.getKlant().getId());
			stmt.setLong(2, adresDAO.insertAdres(adres));
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			Bestelling bestellingResponse = new Bestelling();
			if (rs.next()) {
				bestellingId = (int) rs.getLong("id");
				bestellingResponse.setId((int) (long) rs.getLong("id"));
			}
			BestelregelDAO bestelregelDAO = new BestelregelDAO();
			int i = 0;
			while (bestelling.getBestelregelArray().size() > i) {
				Bestelregel bestelregel = new Bestelregel(bestelling.getBestelregelArray().get(i).getAantal(),
						bestelling.getBestelregelArray().get(i).getSubtotaal(),
						bestelling.getBestelregelArray().get(i).getProduct(), bestellingResponse);
				bestelregelDAO.insertBestelregel(bestelregel);
				i++;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return bestellingId;
	}

	public void updateBestelling(Bestelling bestelling) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con
					.prepareStatement("UPDATE bestelling SET klant_id = ?, afleveradres_id = ? WHERE id = ?");
			stmt.setInt(1, bestelling.getAfleverAdres().getId());
			stmt.setInt(2, bestelling.getAccount().getId());
			stmt.setInt(3, bestelling.getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void deleteBestelling(int id) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM bestelling WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
