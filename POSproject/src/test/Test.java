package test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import employee.Employee;
import employee.EmployeeDatabaseConnectionCRUD;
import employee.EmployeeRequest;
import employee.ValidationResult;

public class Test {

	public static void main(String[] args) {

//		employee("Rick", "Grimes", "Rick", "TheWalkingDead4", "9876543210", LocalDate.of(1973, 9, 14));
//				employee("Mark", "Lanuzo", "Markyyy", "Test1234567890", "9876543210", LocalDate.of(2000, 2, 24))));
		System.out.println(testSignUp(testEmployee("Rickoo", "Grimes", "Rickies", "TheWalkingDead4", "9876543210",
				LocalDate.of(1973, 9, 14))));

		try {
			Employee emp = new Employee();

			emp.setUsername("Ricko");

			EmployeeDatabaseConnectionCRUD v = new EmployeeDatabaseConnectionCRUD();
			String getFirstname = v.getEmployeeByUsername(emp).getFirstname();
			v.close();

			System.out.println(getFirstname);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ValidationResult testSignUp(Employee employee) {
		return EmployeeRequest.registerNewEmployee(employee) ? ValidationResult.SUCCESS
				: ValidationResult.USERNAME_OR_PASSWORD_IS_INVALID;
	}

	public static ValidationResult testlLogIn(Employee employee) {

		return EmployeeRequest.login(employee) ? ValidationResult.SUCCESS
				: ValidationResult.USERNAME_OR_PASSWORD_IS_INVALID;
	}

	public static Employee testEmployee(String fname, String lname, String username, String password, String pNumber,
			LocalDate dob) {
		Employee emp = null;
		try {
			emp = new Employee(fname, lname, username, password, pNumber, dob);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emp;
	}

	public static Optional<Employee> testGetByUserName(Employee employee) throws ClassNotFoundException, SQLException {

		EmployeeDatabaseConnectionCRUD empDB = new EmployeeDatabaseConnectionCRUD();

		empDB.getByNameOrTitle(employee);

		empDB.close();

		return Optional.ofNullable(employee);
	}

}
