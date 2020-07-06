package by.htp.ishop.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToRegistrationCommand implements Command{

	private static final String PAGE = "WEB-INF/jsp/registration.jsp"; 

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		request.getRequestDispatcher(PAGE).forward(request, response);
		
	}

}