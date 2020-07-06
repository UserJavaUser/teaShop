package by.htp.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ishop.bean.CartItem;
import by.htp.ishop.bean.CartItemView;
import by.htp.ishop.bean.Item;
import by.htp.ishop.bean.ItemView;
import by.htp.ishop.dao.CartItemDAO;
import by.htp.ishop.dao.connection_pool.ConnectionPool;
import by.htp.ishop.dao.connection_pool.ConnectionPoolException;

public class SQLCartItemDAO implements CartItemDAO{

	private static final String ADD_CART_ITEM = "INSERT INTO mydb.cart_items(item_id, quantity, cart_id) VALUES (?, ?, ?)";
	private static final String GET_CART_ITEMS = "SELECT ci.id, ci.item_id, ci.quantity, ci.cart_id, i.name, i.price " + 
			"FROM mydb.cart_items AS ci " + 
			"JOIN mydb.items AS i " + 
			"ON ci.item_id = i.id " + 
			"WHERE ci.cart_id LIKE ? " +
			"AND ci.id NOT IN(SELECT items_id FROM mydb.orders_items)";
	private static final String SELECT_CART_ITEM = "SELECT * FROM mydb.cart_items " + 
			"WHERE item_id = ? " + 
			"AND cart_id = ? "+
			"AND id NOT IN(SELECT items_id FROM mydb.orders_items)";
	private static final String UPDATE_CART_ITEM_QUANTITY = "UPDATE cart_items " + 
			"SET quantity = ? " + 
			"WHERE id = ? " ;
	private static final String DELETE_CART_ITEM = "DELETE FROM mydb.cart_items WHERE id = ?";
	private static final String CART_ITEM_ID = "id";
	private static final String ITEM_ID = "item_id";
	private static final String QUANTITY = "quantity";
	private static final String CART_ID = "cart_id";
	private static final String NAME = "name";
	private static final String PRICE = "price";

	@Override
	public void addNewCartItem(int itemId, int quantity, int cartId) throws DAOException {
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(ADD_CART_ITEM);
			ps.setInt(1, itemId);
			ps.setInt(2, quantity);
			ps.setInt(3, cartId);
			ps.execute();
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
	public List<CartItemView> getCartItems(int cartId) throws DAOException {
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CartItemView> cartItems =  new ArrayList<>();
		
		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(GET_CART_ITEMS);
			ps.setInt(1, cartId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(CART_ITEM_ID);
				int itemId = rs.getInt(ITEM_ID); 
				int quantity = rs.getInt(QUANTITY);
				String name = rs.getString(NAME);
				double price = rs.getDouble(PRICE);
				CartItem cartItem = new CartItem(id, itemId, quantity, cartId);
				CartItemView cartItemView = new CartItemView(cartItem, name, price);
				cartItems.add(cartItemView);
			}

		}catch(ConnectionPoolException e) {
			throw new DAOException(e);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		finally{
			cPool.closeConnection(con, ps, rs);
		}
		return cartItems;
	}

	@Override
	public CartItem readCartItem(int itemId, int cartId) throws DAOException {
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CartItem cartItem = null;
		
		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(SELECT_CART_ITEM);
			ps.setInt(1, itemId);
			ps.setInt(2, cartId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(CART_ITEM_ID);
				int quantity = rs.getInt(QUANTITY);
				cartItem = new CartItem(id, itemId, quantity, cartId);
			}
		}catch(ConnectionPoolException e) {
			throw new DAOException(e);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		finally{
			cPool.closeConnection(con, ps, rs);
		}
		return cartItem;
	}

	@Override
	public void updateCartItemQuantity(int id, int quantity) throws DAOException {
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(UPDATE_CART_ITEM_QUANTITY);
			ps.setInt(1, quantity);
			ps.setInt(2, id);
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
	public void deleteCartItem(int id) throws DAOException {
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(DELETE_CART_ITEM);
			ps.setInt(1, id);
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

}
