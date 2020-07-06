package by.htp.ishop.controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.bean.CartItemView;
import by.htp.ishop.service.CartItemService;
import by.htp.ishop.service.impl.ServiceException;
import by.htp.ishop.service.impl.ServiceFactory;

public class GoToEditCartCommand implements Command{

	private static final String PAGE = "WEB-INF/jsp/editcart.jsp";
	private static final String CART_ITEMS = "cart_items";
	private static final String CART = "cart";

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		HttpSession session = request.getSession(true);
		ServiceFactory factory = ServiceFactory.getInstance();
		CartItemService cartItemService = factory.getCartItemService();
		try {
			Cart cart = (Cart) session.getAttribute(CART);
			List<CartItemView> items = cartItemService.getCartItems(cart.getId());
			request.setAttribute(CART_ITEMS, items); 
			request.setAttribute(CART, cart);
			request.getRequestDispatcher(PAGE).forward(request, response);
		}catch(ServiceException e) {
			throw new CommandException("Items aren't found");
		}
		
	}

}
