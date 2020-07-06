package by.htp.ishop.service;

import by.htp.ishop.bean.User;
import by.htp.ishop.service.impl.ServiceException;

public interface ClientService {
	boolean logIn(String login, String password) throws ServiceException;

	void registration(String firstName, String lastName, String email, String loginRegistration,
			String passRegistration) throws ServiceException;

	User getUserIdByLogin(String login) throws ServiceException;
}
