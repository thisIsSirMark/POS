package employee;

import java.util.function.Function;
import java.util.regex.Pattern;

public interface EmployeeValidation extends Function<Employee, ValidationResult> {

	public static EmployeeValidation isEmpty() throws Exception {

		return input -> !(input.getFirstname().isEmpty()) & !(input.getLastname().isEmpty())
				& !(input.getUsername().isEmpty()) & !(input.getPassword().isEmpty()) ? ValidationResult.SUCCESS
						: ValidationResult.EMPTY;

	}

	public static EmployeeValidation isPasswordValid() throws Exception {
		return password -> Pattern.matches("[a-zA-Z0-9]{8,}", password.getPassword()) ? ValidationResult.SUCCESS
				: ValidationResult.USERNAME_OR_PASSWORD_IS_INVALID;
	}

	public static EmployeeValidation isPhoneNumberValid() throws Exception {
		return phoneNumber -> Pattern.matches("^[9]{1}\\d{2}[- ]?\\d{3}[- ]?\\d{4}$", phoneNumber.getPhoneNumber())
				? ValidationResult.SUCCESS
				: ValidationResult.PHONE_NUMBER_INVALID;
	}

	public static EmployeeValidation isUsernameUnique() throws Exception {
		return username -> (new EmployeeRequest().isUsernameUnique(username)) ? ValidationResult.SUCCESS
				: ValidationResult.USERNAME_OR_PASSWORD_IS_INVALID;

	};

	public static EmployeeValidation isLegalAge() {
		return age -> age.getAge() >= 18 ? ValidationResult.SUCCESS : ValidationResult.MINOR;
	}

	public default EmployeeValidation and(EmployeeValidation other) {
		return employee -> {
			ValidationResult result = this.apply(employee);
			return result.equals(ValidationResult.SUCCESS) ? other.apply(employee) : result;
		};

	}

}
