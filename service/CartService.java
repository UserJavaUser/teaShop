package by.htp.ishop.service;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.service.impl.ServiceException;

public interface CartService {
	void addNewCart(int userId) throws ServiceException;
	
	Cart getCardByUserId(int userId) throws ServiceException;
}
