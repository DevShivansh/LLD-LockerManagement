package application.entities.users;

import java.util.HashMap;
import java.util.Map;

import application.constants.OrderStatus;
import application.entities.Order;
import application.entities.messages.Notification;
import application.interfaces.LockerUserPrevilage;
import application.interfaces.MessageReader;
import application.services.LockerService;
import application.services.MessagePoller;
import application.services.OrderService;

public class Customer implements MessageReader<Notification> {

	private User<Notification> user;
	
	private Map<String, Order> orderBag;

	private MessagePoller<Notification> messagePoller;
	
	private LockerUserPrevilage lockerService;
	
	public Customer(User<Notification> user) {
		super();
		this.user = user;
		lockerService = LockerService.getUserInstance();
		this.orderBag = new HashMap<String, Order>();
		this.messagePoller = new MessagePoller<Notification>(user.getMessages(), this);
		messagePoller.run();
	}



	public void returnOrder(String orderId) {
		Order o = orderBag.get(orderId);
		orderBag.remove(orderId);
		OrderService.handleReturn(o, user.userCity());
	}



	@Override
	public void readMessage(Notification n) {
		Order o = lockerService.vacantLocker(n);
		o.updateState(OrderStatus.RECEIVED);
		orderBag.put(o.orderId(), o);
	}
	
	
	
}
