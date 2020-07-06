package by.htp.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.htp.ishop.bean.CategoryName;
import by.htp.ishop.bean.Item;
import by.htp.ishop.bean.ItemView;
import by.htp.ishop.dao.ItemDAO;
import by.htp.ishop.dao.connection_pool.ConnectionPool;
import by.htp.ishop.dao.connection_pool.ConnectionPoolException;

public class SQLItemDAO implements ItemDAO{

	private static final String GET_ALL_ITEMS = "SELECT i.id, i.name, i.productcode, i.price, i.quantity, i.category_id, c.name AS category_name FROM mydb.items AS i JOIN mydb.category AS c ON i.category_id = c.id";
	private static final String ADD_ITEM = "INSERT INTO mydb.items(name, productcode, price, quantity, categoryid) VALUES(?, ?, ?, ?, ?)";
	private static final String GET_ITEMS_BY_CATEGORY = "SELECT i.id, i.name, i.productcode, i.price, i.quantity, i.category_id, c.name AS category_name FROM mydb.items AS i JOIN mydb.category AS c ON i.category_id = c.id WHERE c.name IN ";
	private static final String GET_ITEM_BY_ID = "SELECT * FROM mydb.items WHERE id = ?";


	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String PRODUCT_CODE = "productcode";
	private static final String PRICE= "price";
	private static final String QUANTITY = "quantity";
	private static final String CATEGORY_ID = "category_id";
	private static final String CATEGORY_NAME = "category_name";

	@Override
	public List<ItemView> getAllItems() throws DAOException {
		List<ItemView> items = new ArrayList<>();

		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(GET_ALL_ITEMS);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(ID);
				String name = rs.getString(NAME);
				String productCode = rs.getString(PRODUCT_CODE);
				double price = rs.getDouble(PRICE);
				int quantity = rs.getInt(QUANTITY);
				int categoryId = rs.getInt(CATEGORY_ID);
				String categoryName = rs.getString(CATEGORY_NAME);
				Item item = new Item(id, name, productCode, price, quantity, categoryId);
				ItemView itemView = new ItemView(item, categoryName);
				items.add(itemView);
			}
			return items;
		}catch(ConnectionPoolException e) {
			throw new DAOException(e);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		finally{
			cPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public void addNewItem(Item item) throws DAOException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item getItemById(int itemId) throws DAOException{
		Item item = null;

		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			ps = con.prepareStatement(GET_ITEM_BY_ID);
			ps.setInt(1, itemId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(ID);
				String name = rs.getString(NAME);
				String productCode = rs.getString(PRODUCT_CODE);
				double price = rs.getDouble(PRICE);
				int quantity = rs.getInt(QUANTITY);
				int categoryId = rs.getInt(CATEGORY_ID);
				item = new Item(id, name, productCode, price, quantity, categoryId);
			}
			return item;
		}catch(ConnectionPoolException e) {
			throw new DAOException(e);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		finally{
			cPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public void updateItem(int itemId, String field) throws DAOException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItem(int itemId) throws DAOException{
		// TODO Auto-generated method stub
		
	}

	public List<ItemView> findItemByParameter(String parameter, List<String> values) throws DAOException {
		List<ItemView> items = new ArrayList<>();
		
		ConnectionPool cPool = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cPool = ConnectionPool.getInstance();
			cPool.initPoolData();
			con = cPool.takeConnection();
			StringBuilder query = new StringBuilder(GET_ITEMS_BY_CATEGORY);
			query.append("(");
			for(String value : values) {
				query.append("'" + value + "'");
				if(values.indexOf(value) < (values.size() - 1)) {
					query.append(",");
				}
				else {
					query.append(")");
				}
			}
			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();

			while(rs.next()) {
				int id = rs.getInt(ID);
				String name = rs.getString(NAME);
				String productCode = rs.getString(PRODUCT_CODE);
				double price = rs.getDouble(PRICE);
				int quantity = rs.getInt(QUANTITY);
				int categoryId = rs.getInt(CATEGORY_ID);
				String categoryName = rs.getString(CATEGORY_NAME);
				Item item = new Item(id, name, productCode, price, quantity, categoryId);
				ItemView itemView = new ItemView(item, categoryName);
				items.add(itemView);
			}
			return items;
		}catch(ConnectionPoolException e) {
			throw new DAOException(e);
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		finally{
			cPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public List<CategoryName> getCategories() throws DAOException {
		List<CategoryName> enumValues = Arrays.asList(CategoryName.values());
		return enumValues;
	}


}
