package employee;

import java.sql.Date;

public class Employee {

	private String employeeId, username, userPassword, firstName, lastName, phoneNumber;
	private int age, employeeNumber;
	private Date dateOfBirth;
	private boolean isActive, isOnShift;

	public Employee(String username, String firstName, String lastName, String phoneNumber) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public Employee() {

	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getUsername() {
		return username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isOnShift() {
		return isOnShift;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setOnShift(boolean isOnShift) {
		this.isOnShift = isOnShift;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", age=" + age + ", employeeNumber="
				+ employeeNumber + ", dateOfBirth=" + dateOfBirth + ", onShift=" + isOnShift + ", password="
				+ userPassword + "]";
	}
}
