package com.sean.workshop.security.ids;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjection {

	private Connection conn;

	private void init() throws SQLException {

		conn = DriverManager.getConnection("jdbc:h2:file:~/.h2/security;AUTO_SERVER=TRUE", "sa", "");

		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(
					"CREATE TABLE test(id int primary key, username varchar(32) not null, password varchar(36))");
			stmt.executeUpdate("INSERT INTO test VALUES(1, 'sean', 'foo123'), (2, 'admin', 'admin123')");
			conn.commit();

			try {
				inject(stmt);
				fix();

			} finally {
				cleanUp(stmt);
			}
		}
	}

	private void fix() throws SQLException {
		System.out.println("Need use prepared statment to aovid injection");
		String user = "sean";
		String pwd = "wrong_pwd";
		String injected_user = "sean' OR '1'='1";
		String sql = "SELECT * FROM  test WHERE username=? AND password=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, user);
			pstmt.setString(2, pwd);
			boolean found = false;
			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					System.out.println(rset.getString(2) + ", " + rset.getString(3));
					found = true;
				}
			}
			if (!found) {
				System.out.println("Expected..., target user not found.");
			}
		}

	}

	private void inject(Statement stmt) throws SQLException {
		System.out.println("Inject a condition to enumerate and bypass the user 'sean'");
		String user = "sean";
		String pwd = "wrong_pwd";
		String injected_user = "sean' OR '1'='1";
		String sql = "SELECT * FROM  test WHERE username='" + injected_user + "' AND password='" + pwd + "'";
		try (ResultSet rset = stmt.executeQuery(sql)) {
			while (rset.next()) {
				System.out.println(rset.getString(2) + ", " + rset.getString(3));
			}
		}
	}

	private void cleanUp(Statement stmt) throws SQLException {
		stmt.executeUpdate("DROP TABLE test");
		System.out.println("table test is dropped...");
	}

	private void close() {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] args) {
		SQLInjection t = new SQLInjection();
		try {
			t.init();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			t.close();
		}

	}

}
