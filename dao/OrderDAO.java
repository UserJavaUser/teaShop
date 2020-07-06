package by.htp.ishop.dao;

import java.util.List;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.bean.CartItemView;
import by.htp.ishop.dao.impl.DAOException;

public interface OrderDAO {
	void addNewOrder(Cart cart, double sum, List<CartItemView> items) throws DAOException;

}
