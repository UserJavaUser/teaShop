package by.htp.ishop.controller.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ishop.service.ItemService;
import by.htp.ishop.service.impl.ServiceException;
import by.htp.ishop.service.impl.ServiceFactory;

public class ItemsByCategoryCommand implements Command{

	private static final String PAGE = "WEB-INF/jsp/main.jsp";
	private static final String CATEGORY_NAME = "category_name";
	private static final String GOODS = "goods";
	private static final String CATEGORIES = "categories";
	private static final String LIST_OF_VALUES = "listOfValues";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		ItemService impl = factory.getItemService();
		String[]values = request.getParameterValues(CATEGORY_NAME);
		List<String> searchedValues = Arrays.asList(values);

		try {
			request.setAttribute(LIST_OF_VALUES, searchedValues.toString());
			request.setAttribute(CATEGORIES, impl.getCategories());
			request.setAttribute(GOODS, impl.findItemByParameter(null, searchedValues));
			request.getRequestDispatcher(PAGE).forward(request, response);
		}catch(ServiceException e){
			throw new CommandException("Items by category aren't found!");
		}
	}

}
