package by.htp.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import by.htp.ishop.bean.Cart;
import by.htp.ishop.bean.CartItem;
import by.htp.ishop.bean.CartItemView;
import by.htp.ishop.dao.OrderDAO;
import by.htp.ishop.dao.connection_pool.ConnectionPool;
import by.htp.ishop.dao.connection_pool.ConnectionPoolException;

public class SQLOrderDAO implements OrderDAO{

	private static final String ADD_ORDER = "INSERT INTO mydb.orders (sum, user_id) VALUES (?, ?)";
	private static final String ADD_ORDER_ITEMS = "INSERT INTO mydb.orders_items (orders_id, items_id) VALUES (?, ?)";

	@Override
	public void addNewOrder(Cart cart, double sum, List<CartItemView> items) throws DAOException {
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;

		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(ADD_ORDER,Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, sum);
			ps.setInt(2, cart.getUserId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();  
			id = rs.getInt(1);
			ps.close();

			for(CartItemView item: items) {
				ps = con.prepareStatement(ADD_ORDER_ITEMS);
				ps.setInt(1, id);
				ps.setInt(2, item.getId());
				ps.execute();
			}
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
