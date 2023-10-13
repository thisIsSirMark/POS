package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable {

	protected Connection dataBaseConnection;
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/point_on_sale_db";
	private static final String USER = "root";
	private static final String PASS = "";

	public DatabaseConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		dataBaseConnection = DriverManager.getConnection(URL, USER, PASS);

	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {

		return dataBaseConnection;

	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
