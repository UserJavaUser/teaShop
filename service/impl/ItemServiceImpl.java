package by.htp.ishop.service.impl;

import java.util.List;

import by.htp.ishop.bean.CategoryName;
import by.htp.ishop.bean.Item;
import by.htp.ishop.bean.ItemView;
import by.htp.ishop.dao.ItemDAO;
import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.dao.impl.DAOFactory;
import by.htp.ishop.service.ItemService;

public class ItemServiceImpl implements ItemService{

	@Override
	public List<ItemView> getAllItems() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		ItemDAO itemDAO = daoFactory.getItemDAO();
		List<ItemView> items;
		try {
			items = itemDAO.getAllItems();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return items;
	}

	@Override
	public void addNewItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item getItemById(int itemId) throws ServiceException{
		DAOFactory daoFactory = DAOFactory.getInstance();
		ItemDAO itemDAO = daoFactory.getItemDAO();
		Item item;
		try {
			item = itemDAO.getItemById(itemId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return item;
	}

	@Override
	public void updateItem(int itemId, String field) throws ServiceException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItem(int itemId) throws ServiceException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ItemView> findItemByParameter(String parameter, List<String> value) throws ServiceException{
		DAOFactory daoFactory = DAOFactory.getInstance();
		ItemDAO itemDAO = daoFactory.getItemDAO();
		List<ItemView> items;
		try {
			items = itemDAO.findItemByParameter(parameter, value);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return items;
	
	}

	@Override
	public List<CategoryName> getCategories() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		ItemDAO itemDAO = daoFactory.getItemDAO();
		List<CategoryName> categories;
		try {
			categories = itemDAO.getCategories();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return categories;
	}

}
