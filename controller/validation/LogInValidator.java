package by.htp.ishop.controller.validation;

public class LogInValidator {

	private static final String EMPTY_LOGIN = "Login is required";
	private static final String EMPTY_PASSWORD = "Password is required";
	public static final String INVALID_LOGIN_AND_PASSWORD = "Invalid Login and/or Password, please try again.";

	
	public String validateLogin(String login) {
		String message = null;
		if(login == null || login.isEmpty()) {
			message = EMPTY_LOGIN;
		}
		return message;
	}
	
	public String validatePassword(String password) {
		String message = null;
		if(password == null || password.isEmpty()) {
			message = EMPTY_PASSWORD;
		}
		return message;
	}

}
