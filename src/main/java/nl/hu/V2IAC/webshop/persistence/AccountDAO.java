package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.V2IAC.webshop.model.Account;
import nl.hu.V2IAC.webshop.model.Adres;
import nl.hu.V2IAC.webshop.model.Klant;

public class AccountDAO extends BaseDAO {

	private ArrayList<Account> selectAccounts(String query) {
		ArrayList<Account> account = new ArrayList<Account>();

		try (Connection con = super.connect()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				String role = dbResultSet.getString("role");
				String username = dbResultSet.getString("username");
				Date openDatum = dbResultSet.getDate("open_datum");
				Boolean actief = dbResultSet.getBoolean("actief");
				Adres factuuradresId = new Adres(dbResultSet.getInt("factuuradres_id"));
				Klant klantId = new Klant(dbResultSet.getInt("klant_id"));

				Account c = new Account(id, role, username, openDatum, actief, factuuradresId, klantId);
				account.add(c);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return account;
	}

	// Haalt alle accounts uit de database
	public ArrayList<Account> getAllAccounts() {
		return selectAccounts("SELECT * FROM account");
	}

	// Haalt acount uit de database met een id
	public Account getAccountById(int id) {
		Account c = new Account();
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM account WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setRole(rs.getString("role"));
				c.setUsername(rs.getString("username"));
				c.setOpenDatum(rs.getDate("open_datum"));
				c.setActief(rs.getBoolean("actief"));
				Adres factuuradresId = new Adres(rs.getInt("factuuradres_id"));
				c.setFactuurAdres(factuuradresId);
				Klant klantId = new Klant(rs.getInt("klant_id"));
				c.setKlant(klantId);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return c;
	}

	public void insertAccount(Account account) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO account(role, username, password, open_datum, actief, factuuradres_id, klant_id) VALUES(?,?,?,?,?,?,?)");
			stmt.setString(1, account.getRole());
			stmt.setString(2, account.getUsername());
			stmt.setString(3, account.getPassword());
			stmt.setDate(4, account.getOpenDatum());
			stmt.setBoolean(5, account.isActief());
			stmt.setInt(6, account.getFactuurAdres().getId());
			stmt.setInt(7, account.getKlant().getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void updateAccount(Account account) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("UPDATE account SET password = ? WHERE id = ?");
			stmt.setString(1, account.getPassword());
			stmt.setInt(2, account.getId());
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void deleteAccount(int id) {
		try (Connection con = super.connect()) {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM account WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
