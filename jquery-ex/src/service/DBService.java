package service;

import java.sql.Connection;
import java.sql.DriverManager;

class DBService {
	public Connection getConnection() throws Exception {
		Connection conn = null;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/puzzle","root","java1234");
		return conn;
	}
}
