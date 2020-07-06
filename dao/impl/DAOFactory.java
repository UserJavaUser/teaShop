package by.htp.ishop.dao.impl;

import by.htp.ishop.dao.CartDAO;
import by.htp.ishop.dao.CartItemDAO;
import by.htp.ishop.dao.ItemDAO;
import by.htp.ishop.dao.OrderDAO;
import by.htp.ishop.dao.UserDAO;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final ItemDAO sqlItemImpl = new SQLItemDAO();
	private final CartDAO sqlCartImpl = new SQLCartDAO();
	private final CartItemDAO sqlCartItemImpl = new SQLCartItemDAO();
	private final OrderDAO sqlOrderImpl = new SQLOrderDAO();

	private DAOFactory() {
		
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return sqlUserImpl;
	}

	public ItemDAO getItemDAO() {
		return sqlItemImpl;
	}

	public CartDAO getCartDAO() {
		return sqlCartImpl;
	}

	public CartItemDAO getCartItemDAO() {
		return sqlCartItemImpl;
	}

	public OrderDAO getOrderDAO() {
		return sqlOrderImpl;
	}
}
