package by.htp.ishop.controller.command;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.bean.User;
import by.htp.ishop.controller.validation.LogInValidator;
import by.htp.ishop.service.CartService;
import by.htp.ishop.service.ClientService;
import by.htp.ishop.service.impl.ServiceException;
import by.htp.ishop.service.impl.ServiceFactory;

public class LogInCommand implements Command{

	private final static String LOGIN = "login";
	private final static String PASSWORD = "password";
	private final static String USER = "user";
	private final static String CART = "cart";
	private final static String INDEX_PAGE = "index.jsp";
	private final static String ERROR = "error";
	private final static String ERROR_LOGIN = "error_login";
	private final static String ERROR_PASSWORD = "error_password";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		
		LogInValidator logInValidator = new LogInValidator();
		String validationForLogin = logInValidator.validateLogin(login);
		String validationForPassword = logInValidator.validatePassword(password);

		if(validationForLogin != null || validationForPassword != null) {
			request.setAttribute(ERROR_LOGIN, validationForLogin);
			request.setAttribute(ERROR_PASSWORD, validationForPassword);
			request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
		}
		else {
			ServiceFactory factory = ServiceFactory.getInstance();
			ClientService clientServiceimpl = factory.getClientService();
			CartService cartServiceImpl = factory.getCartService();
			try {
				boolean isLoggedIn = clientServiceimpl.logIn(login, password);
				if(!isLoggedIn) {
					request.setAttribute(ERROR, logInValidator.INVALID_LOGIN_AND_PASSWORD);
					request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
				}
				else {
					User user = clientServiceimpl.getUserIdByLogin(login);
					Cart cart = cartServiceImpl.getCardByUserId(user.getId());
					HttpSession session = request.getSession(true);
					session.setAttribute(USER, user);
					session.setAttribute(CART, cart);
					response.sendRedirect("Controller?command=display_items");
				}
				
			} catch (ServiceException e) {
				throw new CommandException("Can't sign in!");
			}
		}
	}
	
}


