package by.htp.ishop.service.impl;

import by.htp.ishop.service.CartItemService;
import by.htp.ishop.service.CartService;
import by.htp.ishop.service.ClientService;
import by.htp.ishop.service.ItemService;
import by.htp.ishop.service.OrderService;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final ClientService clientService = new ClientServiceImpl();
	private final ItemService itemService = new ItemServiceImpl();
	private final CartService cartService = new CartServiceImpl();
	private final CartItemService cartItemService = new CartItemServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();

	private ServiceFactory() {
		
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public CartService getCartService() {
		return cartService;
	}

	public CartItemService getCartItemService() {
		return cartItemService;
	}

	public OrderService getOrderService() {
		return orderService;
	}
}
