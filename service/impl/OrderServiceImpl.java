package by.htp.ishop.service.impl;

import java.util.List;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.bean.CartItem;
import by.htp.ishop.bean.CartItemView;
import by.htp.ishop.dao.OrderDAO;
import by.htp.ishop.dao.impl.DAOFactory;
import by.htp.ishop.service.OrderService;

public class OrderServiceImpl implements OrderService{

	@Override
	public void addNewOrder(Cart cart, double sum, List<CartItemView> items) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();
		try {
			orderDAO.addNewOrder(cart, sum, items);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

}
