package nl.hu.V2IAC.webshop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO {
	public List<Object> findRoleForUsernameAndPassword(String username, String password) {
		Integer id = null;
		Integer klantId = null;
		String role = null;
		boolean isActief = false;
		String message = null;
		String query = "SELECT id, klant_id, role, actief FROM account WHERE username = ? AND password = ?";

		try (Connection con = super.connect()) {

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
				klantId = rs.getInt("klant_id");
				role = rs.getString("role");
				isActief = rs.getBoolean("actief");
			}
			if (id == null) {
				message = "Geen account gevonden!";
			} else if (id != null && isActief == false) {
				message = "Account is niet actief!";
			} else {
				message = "ok";
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		List<Object> idAndRole = new ArrayList<Object>();
		idAndRole.add(0, id);
		idAndRole.add(1, role);
		idAndRole.add(2, message);
		idAndRole.add(3, klantId);
		return idAndRole;
	}
}