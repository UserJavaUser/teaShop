package by.htp.ishop.service;

import java.util.List;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.bean.CartItemView;
import by.htp.ishop.service.impl.ServiceException;

public interface OrderService {
	void addNewOrder(Cart cart, double sum, List<CartItemView> items) throws ServiceException;
}
