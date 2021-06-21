package application.entities;

import application.constants.OrderStatus;
import application.constants.OrderType;

public class Order {

	private String orderId;
	
	private String userId;
	
	private OrderType orderType;
	
	private OrderStatus status;
	
	private String desc;

	public String orderId() {
		// TODO Auto-generated method stub
		return orderId;
	}
	
	public String getUserId() {
		return userId;
	}

	public OrderType orderType() {
		// TODO Auto-generated method stub
		return orderType;
	}

	public void updateState(OrderStatus inTransit) {
		this.status = inTransit;
		
	}
}
