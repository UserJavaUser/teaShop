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
import by.htp.ishop.service.OrderService;
import by.htp.ishop.service.impl.ServiceException;
import by.htp.ishop.service.impl.ServiceFactory;

public class MakeOrderCommand implements Command{

	private final static String CART = "cart";
	private final static String SUM = "sum";
	private final static String PAGE = "Controller?command=go_to_main";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException, ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		OrderService orderService = factory.getOrderService();
		CartItemService cartItemService = factory.getCartItemService();
		HttpSession session = request.getSession(true);
		Cart cart = (Cart) session.getAttribute(CART);

		try {
			List<CartItemView> items = cartItemService.getCartItems(cart.getId());
			double sum = cartItemService.getCartSum(items);
			orderService.addNewOrder(cart, sum, items);
			response.sendRedirect(PAGE);
		}catch(ServiceException e) {
			throw new CommandException("Order isn't created");
		}
		
	}

}
