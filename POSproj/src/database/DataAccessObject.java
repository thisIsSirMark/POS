package database;

import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject<T> {

	int add(T type) throws SQLException, ClassNotFoundException;

	int update(T type) throws SQLException;

	int delete(T type) throws SQLException;

	List<T> getAll() throws SQLException, ClassNotFoundException, Exception;

	List<T> getAll(T type) throws SQLException;

	T getByName(String name) throws SQLException;

	T getById(String id) throws SQLException;

}
