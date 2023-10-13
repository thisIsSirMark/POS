package employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.DatabaseConnectionCRUD;

public class EmployeeDatabaseConnectionCRUD extends DatabaseConnectionCRUD<Employee> {

	public EmployeeDatabaseConnectionCRUD() throws ClassNotFoundException, SQLException {
		super();

	}

	@Override
	public int add(Employee employee) throws SQLException {
		String sql = "INSERT INTO employee "
				+ "(first_name, last_name, username, user_password, phone_number, date_of_birth, age, is_active)"
				+ " VALUES " + "(?,?,?,?,?,?,?, true)";
//				;sqlUpdate = "UPDATE employee" + "SET employee_id = ?" + "WHERE ?;"

		PreparedStatement statement = dataBaseConnection.prepareStatement(sql);
		try {

			statement.setString(1, employee.getFirstname());
			statement.setString(2, employee.getLastname());
			statement.setString(3, employee.getUsername());
			statement.setString(4, employee.getPassword());
			statement.setString(5, employee.getPhoneNumber());
			statement.setDate(6, employee.getDob());
			statement.setInt(7, employee.getAge());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return statement.executeUpdate();
	}

	@Override
	public List<Employee> getAll() throws SQLException {
		return null;
	}

	@Override
	public Employee getByNameOrTitle(Employee employee) throws SQLException {

		String sql = "SELECt * FROM employee WHERE is_active = true AND username = ?";

		PreparedStatement statement = dataBaseConnection.prepareStatement(sql);

		try {

			statement.setString(1, employee.getUsername());
		} catch (Exception e) {
			// TODO: handle exception
		}

		ResultSet result = statement.executeQuery();

		Employee emp = null;

		if (result.next()) {

			while (result.next()) {
				emp = new Employee();
				try {
					emp.setUsername(result.getString("username"));
					emp.setPassword(result.getString("user_password"));

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		return emp;
	}

	@Override
	public int update(Employee object) throws SQLException {
		return 0;
	}

	@Override
	public int delete(Employee object) throws SQLException {
		return 0;
	}

	public Employee getEmployeeByUsername(Employee employee) {

		String sql = "SELECT * FROM employee WHERE is_active = true AND username = ?";

		Employee emp = new Employee();

		try {

			PreparedStatement statement = dataBaseConnection.prepareStatement(sql);

			statement.setString(1, employee.getUsername());

			ResultSet result = statement.executeQuery();

			if (result.next())
				;

			emp.setFirstname(result.getString("first_name"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emp;

	}

}
