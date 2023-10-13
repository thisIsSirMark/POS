package employee;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class Employee {

	private String phoneNumber, firstname, lastname, username, employeeId;

	private String password;

	private Integer employeeNumber;

//	private Integer year, month, day;

	private Date dob;

	public Employee() {

	}

	public Employee(String firstname, String lastname, String username, String password, String phoneNumber,
			LocalDate dob) throws Exception {
		super();

		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setUsername(username);
		this.setPassword(password);
		this.setDob(dob);
		this.setPhoneNumber(phoneNumber);
	}

	public void setFirstname(String firstname) throws Exception {

//		if (new EmployeeValidation().isEmpty().apply(firstname).equals(ValidationResult.SUCCESS))
		this.firstname = firstname;
	}

	public void setLastname(String lastname) throws Exception {

//		if (new EmployeeValidation().isEmpty().apply(lastname).equals(ValidationResult.SUCCESS))
		this.lastname = lastname;
	}

	public void setUsername(String username) throws Exception {

//		if (new EmployeeValidation().isEmpty().apply(lastname).equals(ValidationResult.SUCCESS))
		this.username = username;
	}

	public void setPassword(String password) throws Exception {

//		if (new EmployeeValidation().isPasswordValid().apply(password).equals(ValidationResult.SUCCESS))
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) throws Exception {

//		if (new EmployeeValidation().isPhoneNumberValid().apply(phoneNumber).equals(ValidationResult.SUCCESS))
		this.phoneNumber = phoneNumber;
	}

	public void setDob(LocalDate dob) {
		this.dob = Date.valueOf(dob);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getUsername() {
		return username;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getPassword() {
		return password;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public Integer getAge() {
		return Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
	}

	public Date getDob() {
		return dob;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

}
