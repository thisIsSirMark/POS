package employee;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import database.DatabaseConnection;

public class EmployeeDAOImplementation implements EmployeeDAO {

	@Override
	public int add(Employee employee) throws SQLException {

		String SQL = "INSERT INTO employee (username, user_password, first_name, last_name, "
				+ "date_of_birth, age, phone_number, is_active) VALUES (?,?,?,?,?,?,?,true)";

		int success = 0;

		try (DatabaseConnection con = new DatabaseConnection();
				PreparedStatement ps = con.getConnection().prepareStatement(SQL)) {

			ps.setString(1, employee.getUsername());
			ps.setString(2, employee.getUserPassword());
			ps.setString(3, employee.getFirstName());
			ps.setString(4, employee.getLastName());
			ps.setDate(5, employee.getDateOfBirth());
			ps.setInt(6, employee.getAge());
			ps.setString(7, employee.getPhoneNumber());

			success = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public int update(Employee employee) throws SQLException {

		String SQL = "UPDATE employee SET employee_id = ?, username = ?, user_password = ? WHERE employee_number = ?";

		int success = 0;

		try (DatabaseConnection con = new DatabaseConnection();
				PreparedStatement ps = con.getConnection().prepareStatement(SQL)) {

			ps.setString(1, employee.getEmployeeId());
			ps.setString(2, employee.getUsername());
			ps.setString(3, employee.getUserPassword());
			ps.setInt(4, employee.getEmployeeNumber());

			success = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public int delete(Employee employee) throws SQLException {

		String SQL = "UPDATE employee SET is_active = FALSE WHERE employee_id = ?";

		int success = 0;

		try (DatabaseConnection con = new DatabaseConnection();
				PreparedStatement ps = con.getConnection().prepareStatement(SQL)) {

			ps.setString(1, employee.getEmployeeId());

			success = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public List<Employee> getAll() throws ClassNotFoundException, Exception {

		String SQL = "SELECT username, user_password, first_name, last_name, date_of_birth, age, phone_number, is_on_shift "
				+ "FROM employee  WHERE is_active = true";
		List<Employee> employees = new ArrayList<>();
		try (DatabaseConnection connection = new DatabaseConnection();
				PreparedStatement statement = connection.getConnection().prepareStatement(SQL)) {

//		statement.setInt(1, limit);
//		statement.setInt(2, offset);

			if (statement.execute()) {
				try (ResultSet resultSet = statement.executeQuery()) {

					while (resultSet.next()) {
						Employee emp = new Employee();

						emp.setUsername(resultSet.getString("username"));
						emp.setFirstName(resultSet.getString("first_name"));
						emp.setLastName(resultSet.getString("last_name"));
						emp.setDateOfBirth(resultSet.getDate("date_of_birth"));
						emp.setAge(resultSet.getInt("age"));
						emp.setPhoneNumber(resultSet.getString("phone_number"));
						emp.setOnShift(resultSet.getBoolean("is_on_shift"));

						employees.add(emp);
					}
				}
			}
		}

		return employees;
	}

	@Override
	public List<Employee> getAll(Employee type) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getByName(String employee) throws SQLException {

		String SQL = "SELECT username, user_password, first_name, last_name, date_of_birth, age, phone_number, is_on_shift "
				+ "FROM employee  WHERE is_active = true AND username = ?";

		Employee emp = new Employee();

		try (DatabaseConnection connection = new DatabaseConnection();
				PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL)) {

			preparedStatement.setString(1, employee);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {

					emp.setUsername(resultSet.getString("username"));
					emp.setFirstName(resultSet.getString("first_name"));
					emp.setUserPassword(resultSet.getString("user_password"));
					emp.setLastName(resultSet.getString("last_name"));
					emp.setDateOfBirth(resultSet.getDate("date_of_birth"));
					emp.setAge(resultSet.getInt("age"));
					emp.setPhoneNumber(resultSet.getString("phone_number"));
					emp.setOnShift(resultSet.getBoolean("is_on_shift"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return emp;
	}

	@Override
	public Employee getById(String employeeID) throws SQLException {

		String SQL = "SELECT username, user_password, first_name, last_name, date_of_birth, age, phone_number, is_on_shift "
				+ "FROM employee  WHERE is_active = true AND e = ?";

		Employee emp = new Employee();

		try (DatabaseConnection connection = new DatabaseConnection();
				PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL)) {

			preparedStatement.setString(1, employeeID);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {

					emp.setUsername(resultSet.getString("username"));
					emp.setFirstName(resultSet.getString("first_name"));
					emp.setLastName(resultSet.getString("last_name"));
					emp.setDateOfBirth(resultSet.getDate("date_of_birth"));
					emp.setAge(resultSet.getInt("age"));
					emp.setPhoneNumber(resultSet.getString("phone_number"));
					emp.setOnShift(resultSet.getBoolean("is_on_shift"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return emp;
	}

	@Override
	public boolean isUsernameUnique(String username) throws NullPointerException {
		String SQL = "SELECT first_name, last_name FROM employee WHERE username LIKE ?";

		boolean result = true;

		try (DatabaseConnection connection = new DatabaseConnection();
				PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL)) {

			preparedStatement.setString(1, username);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {
//					System.out.println(resultSet.getString("first_name"));
//					System.out.println(resultSet.getString("last_name"));
					result = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;
	}

	@Override
	public EmployeeValidationResult signUp(Employee employee) throws NullPointerException {

		EmployeeDAOImplementation empDAO = new EmployeeDAOImplementation();

		int numberOfAffectedRows = 0;

		try {

			String pass = employee.getUserPassword();
			System.out.println(pass);
			HashGenerator hash = new HashGenerator();

			hash.setInputPassword(pass);

			employee.setUserPassword(hash.generatePassword());

			numberOfAffectedRows = empDAO.add(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return numberOfAffectedRows > 0 ? EmployeeValidationResult.SUCCESS : EmployeeValidationResult.SIGN_UP_FAILED;
	}

	@Override
	public EmployeeValidationResult signIn(Employee employee) throws Exception {

		HashGenerator hash = new HashGenerator();

		hash.setInputPassword(employee.getUserPassword());

		Employee emp = new EmployeeDAOImplementation().getByName(employee.getUsername());

		hash.setSQLPassword(emp.getUserPassword());

		String[] sqlPass = emp.getUserPassword().split(":");

		hash.setIteration(Integer.valueOf(sqlPass[0]));

		hash.setSalt(sqlPass[1]);

		String generatedPass = hash.generatePassword();

		return hash.compareSQLPassword(generatedPass) ? EmployeeValidationResult.SUCCESS
				: EmployeeValidationResult.USERNAME_OR_PASSWORD_IS_INCORRECT;

	}

	public class HashGenerator {

		private String inputPassword;
		private String sqlPassword;
		private byte[] salt;
		private int iteration = 1000;

		public void setInputPassword(String inputPassword) {
			this.inputPassword = inputPassword;
		}

		public void setSQLPassword(String sqlPassword) {
			this.sqlPassword = sqlPassword;
		}

		public void setSalt(String salt) {
			this.salt = toByte(salt);
		}

		public void setIteration(int iteration) {
			this.iteration = iteration;
		}

		public String generatePassword() throws NoSuchAlgorithmException, InvalidKeySpecException {

			char[] pass = inputPassword.toCharArray();

			if (salt == null)
				salt = generateSalt();

			PBEKeySpec spec = new PBEKeySpec(pass, salt, iteration, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

			byte[] bytePass = skf.generateSecret(spec).getEncoded();

			return iteration + ":" + toHexString(salt) + ":" + toHexString(bytePass);
		}

		public boolean compareSQLPassword(String pass) {
			if (pass.equals(sqlPassword))
				return true;

			return false;
		}

		public byte[] generateSalt() throws NoSuchAlgorithmException {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

			byte[] b = new byte[16];

			sr.nextBytes(b);

			return b;
		}

		public String toHexString(byte[] b) {
			BigInteger bi = new BigInteger(1, b);
			String hex = bi.toString(16);

			int paddingLength = (b.length * 2) - hex.length();
			if (paddingLength > 0) {
				return String.format("%0" + paddingLength + "d", 0) + hex;
			} else {
				return hex;
			}
		}

		private byte[] toByte(String hex) {
			byte[] bytes = new byte[hex.length() / 2];

			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
			}

			return bytes;
		}

	}

}
