package employee;

import java.util.function.Function;
import java.util.regex.Pattern;

public interface EmployeeValidation extends Function<Employee, EmployeeValidationResult> {

	public static EmployeeValidation isEmpty() throws Exception {

		return input -> !(input.getFirstName().isEmpty()) & !(input.getLastName().isEmpty())
				& !(input.getUsername().isEmpty()) & !(input.getUserPassword().isEmpty())
						? EmployeeValidationResult.SUCCESS
						: EmployeeValidationResult.EMPTY;

	}

	public static EmployeeValidation isPasswordValid() throws Exception {
		return password -> Pattern.matches("[a-zA-Z0-9]{8,}", password.getUserPassword())
				? EmployeeValidationResult.SUCCESS
				: EmployeeValidationResult.USERNAME_OR_PASSWORD_IS_INVALID;
	}

	public static EmployeeValidation isPhoneNumberValid() throws Exception {
		return phoneNumber -> Pattern.matches("^[9]{1}\\d{2}[- ]?\\d{3}[- ]?\\d{4}$", phoneNumber.getPhoneNumber())
				? EmployeeValidationResult.SUCCESS
				: EmployeeValidationResult.PHONE_NUMBER_INVALID;
	}

	public static EmployeeValidation isUsernameUnique() throws Exception {
		return username -> (new EmployeeDAOImplementation().isUsernameUnique(username.getUsername()))
				? EmployeeValidationResult.SUCCESS
				: EmployeeValidationResult.USERNAME_OR_PASSWORD_IS_INVALID;

	};

	public static EmployeeValidation isLegalAge() {
		return age -> age.getAge() >= 18 ? EmployeeValidationResult.SUCCESS : EmployeeValidationResult.UNDER_LEGAL_AGE;
	}

	public default EmployeeValidation and(EmployeeValidation other) {
		return employee -> {
			EmployeeValidationResult result = this.apply(employee);
			return result.equals(EmployeeValidationResult.SUCCESS) ? other.apply(employee) : result;
		};

	}
}
