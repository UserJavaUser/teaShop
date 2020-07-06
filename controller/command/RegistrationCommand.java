package by.htp.ishop.controller.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.bean.User;
import by.htp.ishop.controller.validation.LogInValidator;
import by.htp.ishop.controller.validation.RegistrationValidator;
import by.htp.ishop.service.CartService;
import by.htp.ishop.service.ClientService;
import by.htp.ishop.service.impl.ServiceException;
import by.htp.ishop.service.impl.ServiceFactory;

public class RegistrationCommand implements Command{
	
	private final static String FIRST_NAME = "firstName";
	private final static String LAST_NAME = "lastName";
	private final static String EMAIL = "email";
	private final static String LOGIN = "login";
	private final static String PASSWORD = "password";
	private final static String REG_PAGE = "WEB-INF/jsp/registration.jsp";

	private final static String USER = "user";
	private final static String CART = "cart";
	private final static String ERROR_LOGIN = "error_login";
	private final static String ERROR_PASSWORD = "error_password";
	private final static String ERROR_FIRSTNAME = "error_firstName";
	private final static String ERROR_LASTNAME = "error_lastName";
	private final static String ERROR_EMAIL = "error_email";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		String firstName = request.getParameter(FIRST_NAME);
		String lastName = request.getParameter(LAST_NAME);
		String email = request.getParameter(EMAIL);
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);

		RegistrationValidator regValidator = new RegistrationValidator();
		LogInValidator logInValidator = new LogInValidator();

		String validationFirstName = regValidator.validateFirstName(firstName);
		if(validationFirstName != null) {
			request.setAttribute(ERROR_FIRSTNAME, validationFirstName);
		}

		String validationLastName = regValidator.validateLastName(lastName);
		if(validationLastName != null) {
			request.setAttribute(ERROR_LASTNAME, validationLastName);
		}

		String validationEmail = regValidator.validateEmail(email);
		if(validationEmail != null) {
			request.setAttribute(ERROR_EMAIL, validationEmail);
		}

		String validationLogin = logInValidator.validateLogin(login);
		if(validationEmail != null) {
			request.setAttribute(ERROR_LOGIN, validationLogin);
		}

		String validationPassword = logInValidator.validatePassword(password);
		if(validationPassword != null) {
			request.setAttribute(ERROR_PASSWORD, validationPassword);
		}

		List<String> errors = new ArrayList<>();
		errors.add(validationLogin);
		errors.add(validationPassword);
		errors.add(validationEmail);
		errors.add(validationLastName);
		errors.add(validationFirstName);
		for(String error : errors) {
			if(error != null) {
				request.getRequestDispatcher(REG_PAGE).forward(request, response);
				return; 
			}
		}
		
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService clientServiceimpl = factory.getClientService();
		CartService cartServiceImpl = factory.getCartService();
		try {
			clientServiceimpl.registration(firstName, lastName, email, login, password);
			User user = clientServiceimpl.getUserIdByLogin(login);
			cartServiceImpl.addNewCart(user.getId());
			Cart cart = cartServiceImpl.getCardByUserId(user.getId());
			HttpSession session = request.getSession(true);
			session.setAttribute(USER, user);
			session.setAttribute(CART, cart);
			response.sendRedirect("Controller?command=display_items");
		} catch (ServiceException e) {
			throw new CommandException("Registration is failed!");
		}
		
	}

}
