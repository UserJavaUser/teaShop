package by.htp.ishop.bean;

public class CartView extends Cart{
	double sum;

	public CartView() {
		
	}

	public CartView(double sum) {
		super();
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(sum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		CartView other = (CartView) obj;
		if (Double.doubleToLongBits(sum) != Double.doubleToLongBits(other.sum))
			return false;
		return true;
	}

}
