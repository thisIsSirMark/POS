package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable {

	protected Connection dataBaseConnection;
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/pos_db";
	private static final String USER = "root";
	private static final String PASS = "";

	public DatabaseConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		dataBaseConnection = DriverManager.getConnection(URL, USER, PASS);
	}

	public void close() throws SQLException {
		dataBaseConnection.close();
	}

}