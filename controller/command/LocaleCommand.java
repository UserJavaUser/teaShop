package by.htp.ishop.controller.command;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		String param = request.getParameter("local");
		String currentURL = request.getParameter("currentURL");
		currentURL = currentURL.replaceAll("WEB-INF\\/[a-z]{0,}\\/[a-z]{0,}.[a-z]{0,}", "Controller"); 
		String command = request.getParameter("command");
		request.getSession(true).setAttribute("local", request.getParameter("local"));
		response.sendRedirect(currentURL);
		
	}

}
