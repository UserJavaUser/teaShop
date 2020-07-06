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

public class DeleteCartItemCommand implements Command{

	private static final String CART_PAGE = "Controller?command=open_cart";
	private static final String CART_ITEM_ID = "cartItemId";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		CartItemService cartItemService = factory.getCartItemService();

		try {
			int cartItemId = Integer.parseInt(request.getParameter(CART_ITEM_ID));
			cartItemService.deleteCartItem(cartItemId);
			response.sendRedirect(CART_PAGE);
		}catch(ServiceException e) {
			throw new CommandException("Items aren't found");
		}
		
	}

}
