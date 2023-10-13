package employee;

public class EmployeeControl {

	public static EmployeeValidationResult signUp(Employee emp) {

		EmployeeDAOImplementation employeeSignUp = new EmployeeDAOImplementation();

		EmployeeValidationResult result = null;

		try {

			if (EmployeeValidation.isEmpty().and(EmployeeValidation.isLegalAge())
					.and(EmployeeValidation.isPasswordValid()).and(EmployeeValidation.isPhoneNumberValid())
					.and(EmployeeValidation.isUsernameUnique()).apply(emp).equals(EmployeeValidationResult.SUCCESS)
					& employeeSignUp.isUsernameUnique(emp.getUsername()))
				;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

}
