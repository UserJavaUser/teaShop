package by.htp.ishop.dao;

import by.htp.ishop.bean.AuthData;
import by.htp.ishop.bean.User;
import by.htp.ishop.dao.impl.DAOException;

public interface UserDAO {
	AuthData logIn(String login) throws DAOException;

	void registration(User user, AuthData authData) throws DAOException;

	User getUserIdByLogin(String login) throws DAOException;
}
