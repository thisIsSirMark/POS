package employee;

public class EmployeeRequest {

	public static boolean registerNewEmployee(Employee employee) {

		int numberOfAffectedRows = 0;

		try (EmployeeDatabaseConnectionCRUD employeeDB = new EmployeeDatabaseConnectionCRUD()) {

			String pass = employee.getPassword();

			HashGenerator hash = new HashGenerator();

			hash.setInputPassword(pass);

			employee.setPassword(hash.generatePassword());

			numberOfAffectedRows = employeeDB.add(employee);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return numberOfAffectedRows > 0 ? true : false;
	}

	public boolean isUsernameUnique(Employee employee) {

		boolean result = false;
		try (EmployeeDatabaseConnectionCRUD db = new EmployeeDatabaseConnectionCRUD()) {

			Employee emp = db.getByNameOrTitle(employee);

			result = emp.getUsername().isEmpty();

			db.close();

		} catch (Exception e) {

		}

		return result;

	}

	public static boolean login(Employee employee) {

		boolean success = true;

		try (EmployeeDatabaseConnectionCRUD employeeDb = new EmployeeDatabaseConnectionCRUD()) {

			HashGenerator hash = new HashGenerator();

			hash.setInputPassword(employee.getPassword());

//			employeeDb.getByNameOrTitle(employee).get();

			hash.setSQLPassword(employeeDb.getByNameOrTitle(employee).getPassword());

			String[] sqlPass = employee.getPassword().split(":");

			hash.setIteration(Integer.valueOf(sqlPass[0]));
			hash.setSalt(sqlPass[1]);

			String generatedPass = hash.generatePassword();

			System.out.println(generatedPass);

			success = hash.compareSQLPassword(generatedPass);

//			@SuppressWarnings("unused")
//			LoginSession session = new LoginSession(employee);

		} catch (Exception e) {
			e.printStackTrace();

			success = false;
		}

		return success;
	}

	public static boolean logIn(Employee employee) {

		HashGenerator hash = new HashGenerator();

		try (EmployeeDatabaseConnectionCRUD employeeDb = new EmployeeDatabaseConnectionCRUD()) {

			String inputUsername = employee.getUsername();
			System.out.println(inputUsername);
			hash.setInputPassword(employee.getPassword());
			String inputPass = hash.generatePassword();
			System.out.println(inputPass);

			employeeDb.getByNameOrTitle(employee);

			String sqlUsername = employee.getUsername();
			System.out.println(sqlUsername);
			hash.setSQLPassword(employee.getPassword());
			System.out.println(employee.getPassword());

			return inputUsername.equals(sqlUsername) & hash.compareSQLPassword(inputPass);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;

	}

}
