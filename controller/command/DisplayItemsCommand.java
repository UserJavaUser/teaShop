package by.htp.ishop.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ishop.service.ItemService;
import by.htp.ishop.service.impl.ServiceException;
import by.htp.ishop.service.impl.ServiceFactory;

public class DisplayItemsCommand implements Command{

	private final static String PAGE = "WEB-INF/jsp/main.jsp";
	private final static String GOODS = "goods";
	private final static String CATEGORIES = "categories";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		ItemService impl = factory.getItemService();
		try {
			request.setAttribute(CATEGORIES, impl.getCategories());
			request.setAttribute(GOODS, impl.getAllItems());
			request.getRequestDispatcher(PAGE).forward(request, response);
		} catch (ServiceException e) {
			throw new CommandException("Items aren't found!");
		}
		
	}
}
