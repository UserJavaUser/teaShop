package by.htp.ishop.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.ishop.bean.CartItem;
import by.htp.ishop.bean.CartItemView;
import by.htp.ishop.dao.CartItemDAO;
import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.dao.impl.DAOFactory;
import by.htp.ishop.service.CartItemService;

public class CartItemServiceImpl implements CartItemService{

	@Override
	public void addNewCartItem(int itemId, int cartId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		CartItemDAO cartItemDAO = factory.getCartItemDAO();
		int quantity = 0;
		try {
			CartItem cartItem = cartItemDAO.readCartItem(itemId, cartId);
			if(cartItem == null) {
				quantity = 1;
				cartItemDAO.addNewCartItem(itemId, quantity, cartId);
			}
			else {
				quantity = cartItem.getQuantity() + 1;
				cartItemDAO.updateCartItemQuantity(cartItem.getId(), quantity);
			}
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<CartItemView> getCartItems(int cartId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		CartItemDAO itemDAO = factory.getCartItemDAO();
		List<CartItemView> cartItems = new ArrayList<CartItemView>();
		try {
			cartItems = itemDAO.getCartItems(cartId);
			for(CartItemView cartItemView : cartItems) {
				if(cartItemView.getQuantity() > 1) {
					double price = cartItemView.getQuantity() * cartItemView.getPrice();
					cartItemView.setPrice(price);
				}
			}
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return cartItems;
	}
	
	public double getCartSum(List<CartItemView> items) {
		double sum = 0;
		for(CartItemView cartItem : items) {
			sum += cartItem.getPrice();
		}
		return sum;
	}

	@Override
	public void deleteCartItem(int id) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		CartItemDAO cartItemDAO = factory.getCartItemDAO();
		try {
			cartItemDAO.deleteCartItem(id);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
	}

}
