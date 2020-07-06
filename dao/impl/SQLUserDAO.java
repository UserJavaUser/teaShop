package by.htp.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.htp.ishop.bean.AuthData;
import by.htp.ishop.bean.User;
import by.htp.ishop.dao.UserDAO;
import by.htp.ishop.dao.connection_pool.ConnectionPool;
import by.htp.ishop.dao.connection_pool.ConnectionPoolException;

public class SQLUserDAO implements UserDAO{

	private static final String LOG_IN = "SELECT salt, password FROM mydb.auth_data WHERE login LIKE ?";
	private static final String REGISTRATION = "INSERT INTO mydb.users(firstName, lastName, email) VALUES(?, ?, ?)";
	private static final String AUTH = "INSERT INTO mydb.auth_data(login, salt, password, user_id) VALUES(?, ?, ?, ?)";
	private static final String GET_USER = "SELECT u.id, firstName, lastName, email, address, phone FROM mydb.users " +
	"u JOIN mydb.auth_data a ON u.id = a.user_id WHERE a.login LIKE ?";
	private static final String ID = "id";
	private static final String LOGIN = "login";
	private static final String SALT = "salt";
	private static final String PASSWORD = "password";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String ADDRESS = "address";

	public AuthData logIn(String login) throws DAOException {

		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AuthData authData = null;

		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(LOG_IN);
			ps.setString(1, login);
			rs = ps.executeQuery();
			while(rs.next()) {
				byte[] authSalt = rs.getBytes(SALT);
				byte[] authHash = rs.getBytes(PASSWORD);
				authData = new AuthData(login, authSalt, authHash);
			}
		}catch(ConnectionPoolException e) {
			throw new DAOException(e);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		finally{
			cPool.closeConnection(con, ps, rs);
		}
		return authData;
	}

	@Override
	public void registration(User user, AuthData authData) throws DAOException {
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;

		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(REGISTRATION,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();  
			id = rs.getInt(1);
			ps.close();
			ps = con.prepareStatement(AUTH);
			ps.setString(1, authData.getLogin());
			ps.setBytes(2, authData.getSalt());
			ps.setBytes(3, authData.getHash());
			ps.setInt(4, id);
			ps.execute();
			con.commit();
		}catch(ConnectionPoolException e) {
			throw new DAOException(e);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		finally{
			cPool.closeConnection(con, ps);
		}
	}

	@Override
	public User getUserIdByLogin(String login) throws DAOException {
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		int id = 0;
		String firstName = null;
		String lastName = null;
		String email = null;
		String address = null;
		String phone = null;

		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(GET_USER);
			ps.setString(1, login);
			rs = ps.executeQuery();
			while(rs.next()) {
			id = rs.getInt(ID);
			firstName = rs.getString(FIRST_NAME);
			lastName = rs.getString(LAST_NAME);
			email = rs.getString(EMAIL);
			address = rs.getString(ADDRESS);
			phone = rs.getString(PHONE);
			}
			user = new User(id, firstName, lastName, email, address, phone);
			return user;
		}catch(ConnectionPoolException e) {
			throw new DAOException(e);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		finally{
			cPool.closeConnection(con, ps);
		}	
	}

}
