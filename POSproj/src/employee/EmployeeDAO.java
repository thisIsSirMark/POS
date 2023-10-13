package employee;

import database.DataAccessObject;

public interface EmployeeDAO extends DataAccessObject<Employee> {

	boolean isUsernameUnique(String employee) throws NullPointerException;

	public EmployeeValidationResult signIn(Employee employee) throws NullPointerException, Exception;

	public EmployeeValidationResult signUp(Employee employee) throws Exception;

}
