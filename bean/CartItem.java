package by.htp.ishop.bean;

import java.io.Serializable;

public class CartItem implements Serializable{

	private static final long serialVersionUID = -6913853311803064723L;

	private int id;
	private int itemId;
	private int quantity;
	private int cartId;

	public CartItem() {
		
	}

	public CartItem(int id, int itemId, int quantity, int cartId) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.quantity = quantity;
		this.cartId = cartId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + id;
		result = prime * result + itemId;
		result = prime * result + quantity;
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
		CartItem other = (CartItem) obj;
		if (cartId != other.cartId)
			return false;
		if (id != other.id)
			return false;
		if (itemId != other.itemId)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
