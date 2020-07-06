package by.htp.ishop.dao;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.dao.impl.DAOException;

public interface CartDAO {
	void addNewCart(int userId) throws DAOException;
	
	Cart getCardByUserId(int userId) throws DAOException;

}
