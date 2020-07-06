package by.htp.ishop.service;

import java.util.List;

import by.htp.ishop.bean.CategoryName;
import by.htp.ishop.bean.Item;
import by.htp.ishop.bean.ItemView;
import by.htp.ishop.service.impl.ServiceException;

public interface ItemService {
	void addNewItem(Item item);

	Item getItemById(int itemId) throws ServiceException;

	void updateItem(int itemId, String field) throws ServiceException;

	void deleteItem(int itemId) throws ServiceException;

	List<ItemView> findItemByParameter(String parameter, List<String> value) throws ServiceException;

	List<ItemView> getAllItems() throws ServiceException;

	List<CategoryName> getCategories() throws ServiceException;
	
}
