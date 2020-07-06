package by.htp.ishop.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command{

	private final static String USER = "user";
	private final static String CART = "cart";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		try {
			HttpSession session = request.getSession(true);
			session.removeAttribute(USER);
			session.removeAttribute(CART);
			response.sendRedirect("Controller?command=display_items");
		} catch (IllegalStateException e) {
			throw new CommandException("Can't log out!");
		}
		
	}

}
