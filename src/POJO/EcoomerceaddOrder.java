package POJO;

import java.util.List;

public class EcoomerceaddOrder {

	public List<OrderDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}

	private List<OrderDetails> orders;
}
