package by.htp.ishop.controller.validation;

public class RegistrationValidator {
	
	private static final String EMPTY_FIRST_NAME = "First Name is required";
	private static final String EMPTY_LAST_NAME = "Last Name is required";
	private static final String EMPTY_EMAIL = "Email is required";
	private static final String INVALID_LOGIN_AND_PASSWORD = "Invalid Login and/or Password, please try again.";

	public String validateFirstName(String firstName) {
		String message = null;
		if(firstName == null || firstName.isEmpty()) {
			message = EMPTY_FIRST_NAME;
		}
		return message;
	}

	public String validateLastName(String lastName) {
		String message = null;
		if(lastName == null ||lastName.isEmpty()) {
			message = EMPTY_LAST_NAME;
		}
		return message;
	}

	public String validateEmail(String email) {
		String message = null;
		if(email == null || email.isEmpty()) {
			message = EMPTY_EMAIL;
		}
		return message;
	}

}
