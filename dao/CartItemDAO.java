package by.htp.ishop.dao;

import java.util.List;

import by.htp.ishop.bean.CartItem;
import by.htp.ishop.bean.CartItemView;
import by.htp.ishop.dao.impl.DAOException;

public interface CartItemDAO {
	void addNewCartItem(int itemId, int quantity, int cartId) throws DAOException;

	CartItem readCartItem(int itemId, int cartId) throws DAOException;

	void updateCartItemQuantity(int id, int quantity) throws DAOException;

	void deleteCartItem(int id) throws DAOException;

	List<CartItemView> getCartItems(int cartId)throws DAOException;

}
