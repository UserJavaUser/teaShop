package by.htp.ishop.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToLogInCommand implements Command{

	private static final String PAGE = "index.jsp"; 

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		request.getRequestDispatcher(PAGE).forward(request, response);
		
	}

}