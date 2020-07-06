package by.htp.ishop.dao;

import java.util.List;

import by.htp.ishop.bean.CategoryName;
import by.htp.ishop.bean.Item;
import by.htp.ishop.bean.ItemView;
import by.htp.ishop.dao.impl.DAOException;

public interface ItemDAO {

	void addNewItem(Item item) throws DAOException;

	Item getItemById(int itemId) throws DAOException;

	void updateItem(int itemId, String field) throws DAOException;

	void deleteItem(int itemId) throws DAOException;

	List<ItemView> findItemByParameter(String parameter, List<String> values) throws DAOException;

	List<ItemView> getAllItems()throws DAOException;

	List<CategoryName> getCategories()throws DAOException;
}
