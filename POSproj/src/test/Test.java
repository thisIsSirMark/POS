package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import employee.Employee;
import employee.EmployeeDAOImplementation;
import product.Product;
import product.ProductDAOImplementation;

public class Test {

	public static void main(String[] args) {

		System.exit(0);

		List<Product> prodList = new ArrayList<>(List.of(new Product("Pastil", 10.0, 100),
				new Product("Asukal", 5.0, 100), new Product("Kwek-kwek", 3.0, 50)));

		prodList.stream().forEach(prod -> addProdTest(prod));
		getAllProdTest();

	}

	public static void getAllProdTest() {
		ProductDAOImplementation getAllProd = new ProductDAOImplementation();

		try {
			getAllProd.getAll().stream().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getByIdProdTest(int id) {
		ProductDAOImplementation getById = new ProductDAOImplementation();

		try {
			System.out.println(getById.getById(String.valueOf(id)).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getByTitleTest(String title) {
		ProductDAOImplementation getTitle = new ProductDAOImplementation();
		try {
			System.out.println(getTitle.getByName(title).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int addProdTest(Product prod) {
		ProductDAOImplementation addProd = new ProductDAOImplementation();

		int success = 0;
		try {
			success = addProd.add(prod);

			System.out.println(success);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;

	}

	public static void updateProdTest(Product prod) {
		ProductDAOImplementation updateProd = new ProductDAOImplementation();
		try {
			System.out.println(updateProd.update(prod) > 0 ? "Success" : "Failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deleteProdTest(Product prod) {
		ProductDAOImplementation deleteProd = new ProductDAOImplementation();
		try {
			System.out.println(deleteProd.delete(prod) > 0 ? "Success" : "Failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void signInTest(Employee employee) {
		EmployeeDAOImplementation signInTest = new EmployeeDAOImplementation();

		if (!(employee.getUsername().isBlank())) {
			try {
				System.out.println(signInTest.signIn(employee));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("signIn unsuccessful");
		}

	}

	public static void signUpTest(Employee employee) {
		EmployeeDAOImplementation signUpTest = new EmployeeDAOImplementation();

		if (signUpTest.isUsernameUnique(employee.getUsername())) {
			try {
				System.out.println(signUpTest.signUp(employee));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("signUp unsuccessful");
		}

	}

	public static void addListOfEmployee(List<Employee> emps) {
		EmployeeDAOImplementation add = new EmployeeDAOImplementation();

		emps.stream().forEach(emp -> {
			try {
				System.out.println(add.add(emp));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public static void getAllTest() {

		EmployeeDAOImplementation getAll = new EmployeeDAOImplementation();

		try {
			getAll.getAll().stream().forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getByNameTest(Employee emp) {

		EmployeeDAOImplementation getByName = new EmployeeDAOImplementation();

		try {

			Employee e = getByName.getByName(emp.getUsername());
			System.out.println(e.toString());

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void connectionTest() {
		try (DatabaseConnection con = new DatabaseConnection()) {
			if (con != null)
				System.out.println("connected");
			System.out.println("FINNISHED!!!");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void addEmployeeTest(String username, String firstName, String lastName, String phoneNumber) {

		EmployeeDAOImplementation add = new EmployeeDAOImplementation();

		try {
			System.out.println(add.add(new Employee(username, firstName, lastName, phoneNumber)));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void updateEmployeeTest(String username, String password, String employeeId, int employeeNumber) {

		EmployeeDAOImplementation update = new EmployeeDAOImplementation();
		Employee emp = new Employee();

		emp.setUsername(username);
		emp.setUserPassword(password);
		emp.setEmployeeId(employeeId);
		emp.setEmployeeNumber(employeeNumber);

		try {
			System.out.println(update.update(emp));
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void deleteEmployeeTest(String employeeId) {

		EmployeeDAOImplementation delete = new EmployeeDAOImplementation();

		Employee emp = new Employee();

		emp.setEmployeeId(employeeId);

		try {
			System.out.println(delete.delete(emp));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void isUsernameUniqueTest(Employee username) {

		EmployeeDAOImplementation isUsernameUnique = new EmployeeDAOImplementation();
		System.out.println(isUsernameUnique.isUsernameUnique(username.getUsername()));
	}

}
