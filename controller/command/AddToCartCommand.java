package by.htp.ishop.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.service.CartItemService;
import by.htp.ishop.service.impl.ServiceException;
import by.htp.ishop.service.impl.ServiceFactory;

public class AddToCartCommand implements Command{

	private final static String PAGE_URL = "Controller?command=display_items";
	private final static String ITEM_ID = "itemId";
	private final static String CART = "cart";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();

		CartItemService cartItemService = factory.getCartItemService();
		try {
			int itemId = Integer.parseInt(request.getParameter(ITEM_ID));
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute(CART);
			if(cart != null) {
				int cartId = cart.getId();
				cartItemService.addNewCartItem(itemId, cartId);
			}
		}catch(ServiceException e){
			throw new CommandException("Can't add to cart");
		}
		response.sendRedirect(PAGE_URL);
	}

}
