package by.htp.ishop.service;

import java.util.List;

import by.htp.ishop.bean.CartItemView;
import by.htp.ishop.service.impl.ServiceException;

public interface CartItemService {

	void addNewCartItem(int itemId, int cartId) throws ServiceException;

	List<CartItemView> getCartItems(int cartId) throws ServiceException;

	void deleteCartItem(int id) throws ServiceException;

	double getCartSum(List<CartItemView> items);

}
