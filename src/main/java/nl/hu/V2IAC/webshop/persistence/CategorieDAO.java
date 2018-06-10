package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Aanbieding;
import nl.hu.V2IAC.webshop.model.Categorie;
import nl.hu.V2IAC.webshop.model.Product;

public class CategorieDAO extends BaseDAO {

	private ArrayList<Categorie> selectCategorieen(String query) {
		ArrayList<Categorie> categorie = new ArrayList<Categorie>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				String naam = dbResultSet.getString("naam");
				String omschrijving = dbResultSet.getString("omschrijving");
				String afbeelding = dbResultSet.getString("afbeelding");

				Categorie c = new Categorie(id, naam, omschrijving, afbeelding);
				categorie.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return categorie;
	}

	public ArrayList<Categorie> getAllCategorieen() {
		return selectCategorieen("SELECT * FROM categorie");
	}

	private ArrayList<Categorie> selectCategorieenById(String query) {
		ArrayList<Categorie> categorie = new ArrayList<Categorie>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				String cNaam = dbResultSet.getString("c_naam");
				String cOmschrijving = dbResultSet.getString("c_omschrijving");
				int pId = dbResultSet.getInt("p_id");
				String pNaam = dbResultSet.getString("p_naam");
				String pOmschrijving = dbResultSet.getString("p_omschrijving");
				double pPrijs = dbResultSet.getDouble("p_prijs");
				String pAfbeelding = dbResultSet.getString("p_afbeelding");
				boolean isAanbieding = false;
				if (dbResultSet.getString("a_van_datum") != null) {
					isAanbieding = true;
				}
				double aNieuwPrijs = dbResultSet.getDouble("a_nieuw_prijs");
				Date aVanDatum = dbResultSet.getDate("a_van_datum");
				Date aTotDatum = dbResultSet.getDate("a_tot_datum");

				Aanbieding a = new Aanbieding(aNieuwPrijs, aVanDatum, aTotDatum);
				Product p = new Product(pId, pNaam, pOmschrijving, pPrijs, pAfbeelding, isAanbieding, a);
				Categorie c = new Categorie(cNaam, cOmschrijving, p);
				categorie.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return categorie;
	}

	public ArrayList<Categorie> getAllCategorieenById(int id) {
		return selectCategorieenById(
				"SELECT c.naam AS c_naam, c.omschrijving AS c_omschrijving, p.id AS p_id, p.naam AS p_naam, p.omschrijving AS p_omschrijving, p.prijs AS p_prijs, p.afbeelding AS p_afbeelding, a.nieuw_prijs AS a_nieuw_prijs, a.van_datum AS a_van_datum, a.tot_datum AS a_tot_datum\r\n"
						+ "FROM categorie c LEFT OUTER JOIN product_categorie p_c ON c.id = p_c.categorie_id  LEFT JOIN product p ON p.id = p_c.product_id LEFT JOIN aanbieding a ON p.id = a.product_id\r\n"
						+ "WHERE c.id = " + id + "");
	}

	public void insertCategorie(Categorie categorie) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO categorie(naam, omschrijving, afbeelding) VALUES(?,?,?)");
			stmt.setString(1, categorie.getNaam());
			stmt.setString(2, categorie.getOmschrijving());
			stmt.setString(3, categorie.getAfbeelding());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void updateCategorie(Categorie categorie) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con
					.prepareStatement("UPDATE categorie SET naam = ?, omschrijving = ?, afbeelding = ? WHERE id = ?");
			stmt.setString(1, categorie.getNaam());
			stmt.setString(2, categorie.getOmschrijving());
			stmt.setString(3, categorie.getAfbeelding());
			stmt.setInt(4, categorie.getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void deleteCategorie(int id) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM categorie WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
