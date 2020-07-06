package by.htp.ishop.bean;

import java.util.List;

public class Cart {
	private int id;
	private int userId;
	private List<CartItem> items;

	public Cart() {
		
	}
	
	public Cart(int id, int userId) {
		super();
		this.id = id;
		this.userId = userId;
	}

	public Cart(int id, int userId, List<CartItem> items) {
		super();
		this.id = id;
		this.userId = userId;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (id != other.id)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
}
