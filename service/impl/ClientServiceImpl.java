package by.htp.ishop.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import by.htp.ishop.bean.AuthData;
import by.htp.ishop.bean.User;
import by.htp.ishop.dao.UserDAO;
import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.dao.impl.DAOFactory;
import by.htp.ishop.service.ClientService;

public class ClientServiceImpl implements ClientService{

	public boolean logIn(String login, String password) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoObjectFactory.getUserDAO();

		try {
			AuthData authData = userDAO.logIn(login);
			if(authData == null) {
				return false;
			}
			byte[] salt = authData.getSalt();
			byte[] hashedPassword = EncodingUtils.getHash(password, salt);
			if(authData.getHash().length != hashedPassword.length) {
				return false;
			}
			if(Arrays.equals(hashedPassword, authData.getHash())) {
				return true;
			}

		} catch (DAOException e) {
			throw new ServiceException("User isn't found!");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Error while hashing");
		}
		return false; 
	}

	@Override
	public void registration(String firstName, String lastName, String email, String login,
			String password) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoObjectFactory.getUserDAO();
		try {
			User user = new User(firstName, lastName, email);
			byte[]salt = EncodingUtils.getSalt();
			byte[]hash = EncodingUtils.getHash(password, salt);
			AuthData authData = new AuthData(login, salt, hash);
			userDAO.registration(user, authData);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Error while hashing");
		}
	}

	@Override
	public User getUserIdByLogin(String login) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoObjectFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.getUserIdByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

}
