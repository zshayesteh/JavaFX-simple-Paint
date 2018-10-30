package javaFXpaintOO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonDBManager {
	Statement stmt;

	public PersonDBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1/paint-oo?user=root&password=";
			Connection conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("exception " + e);
		}
	}

	public boolean authentication(Person p) throws SQLException {// authenticating
																	// the
																	// person's
																	// identity
		ResultSet rs = stmt.executeQuery("select * from person where username='" + Person.getUsername()
				+ "' and password='" + p.getPassword() + "';");
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
