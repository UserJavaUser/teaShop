package by.htp.ishop.service.impl;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.dao.CartDAO;
import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.dao.impl.DAOFactory;
import by.htp.ishop.service.CartService;

public class CartServiceImpl implements CartService{

	@Override
	public void addNewCart(int userId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		CartDAO cartDAO = factory.getCartDAO();
		try {
			cartDAO.addNewCart(userId);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Cart getCardByUserId(int userId) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		CartDAO cartDAO = factory.getCartDAO();
		try {
			Cart cart = cartDAO.getCardByUserId(userId);
			return cart;
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

}
