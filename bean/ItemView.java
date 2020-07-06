package by.htp.ishop.bean;

public class ItemView extends Item{
	
	private String categoryName;

	public ItemView() {
		
	}

	public ItemView(Item item,
			String categoryName) {
		super(item.getId(), item.getName(), item.getProductCode(), item.getPrice(), item.getQuantity(), item.getCategoryId());
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemView other = (ItemView) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}

}
