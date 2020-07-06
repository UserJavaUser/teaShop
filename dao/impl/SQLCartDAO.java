package by.htp.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.dao.CartDAO;
import by.htp.ishop.dao.connection_pool.ConnectionPool;
import by.htp.ishop.dao.connection_pool.ConnectionPoolException;

public class SQLCartDAO implements CartDAO{

	private static final String ID = "id";
	private static final String USER_ID = "user_id";
	private static final String ADD_CART = "INSERT INTO mydb.cart(user_id) VALUES (?)";
	private static final String GET_CART = "SELECT * FROM mydb.cart WHERE cart.user_id = ?";

	@Override
	public void addNewCart(int userId) throws DAOException {

		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(ADD_CART);
			ps.setInt(1, userId);
			ps.executeUpdate();
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
	public Cart getCardByUserId(int userId) throws DAOException {
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cartId = 0;
		int userIdForCart = 0;
		Cart cart = null;

		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(GET_CART);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				cartId = rs.getInt(ID);
				userIdForCart = rs.getInt(USER_ID);
				cart = new Cart(cartId, userIdForCart);
			}

			return cart;
		}catch(ConnectionPoolException e) {
			throw new DAOException(e);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		finally{
			cPool.closeConnection(con, ps, rs);
		}

	}

}
