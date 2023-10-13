package database;

import java.sql.SQLException;
import java.util.List;

import employee.Employee;

public abstract class DatabaseConnectionCRUD<T> extends DatabaseConnection {

	public DatabaseConnectionCRUD() throws ClassNotFoundException, SQLException {
		super();
	}

	public abstract int add(T object) throws SQLException;

	public abstract List<T> getAll() throws SQLException;

	public abstract Employee getByNameOrTitle(T object) throws SQLException;

	public abstract int update(T object) throws SQLException;

	public abstract int delete(T object) throws SQLException;

}
