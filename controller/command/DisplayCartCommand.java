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

public class DisplayCartCommand implements Command{
	
	private final static String PAGE = "WEB-INF/jsp/cart.jsp";
	private final static String CART_ITEMS = "cart_items";
	private final static String CART = "cart";
	private final static String SUM = "sum";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		CartItemService cartItemService = factory.getCartItemService();
		HttpSession session = request.getSession(true);
		Cart cart = (Cart) session.getAttribute(CART);
		if(cart == null) {
			throw new CommandException("Cart isn't found");
		}
		try {
			List<CartItemView> items = cartItemService.getCartItems(cart.getId());
			request.setAttribute(CART_ITEMS, items); 
			request.setAttribute(SUM, String.valueOf(cartItemService.getCartSum(items)));
			request.getRequestDispatcher(PAGE).forward(request, response);
		}catch(ServiceException e) {
			throw new CommandException("Items aren't found");
		}
		
	}



}
