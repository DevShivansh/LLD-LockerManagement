package application.services;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import application.entities.Order;
import application.entities.messages.Notification;
import application.entities.users.PickupAgent;

public class OrderService {
	
	private static Queue<Order> orders = new LinkedList<>();
	
	private static Queue<Order> returns = new LinkedList<>();
	
	private static Scheduler orderScheduler;
	
	private static MessageService<Notification> messageService = new MessageService<Notification>();
	
	static {
		orderScheduler = new Scheduler(orders);
		orderScheduler.run();
	}
	

	public static void handleReturn(Order o, String city) {
		
		Notification info = LockerService.getUserInstance().useLockerService(o, city);
		PickupAgent agent = AgentService.findPickupAgent(city);
		messageService.notify(agent.id(), info);
		
	}


	public static Order getOrder(String orderId) {
		Optional<Order> order = orders.stream().filter(o -> o.orderId().equals(orderId)).findFirst();
		return order.isPresent() ? order.get() : null;
	}
	
	public static void addReturns(Order o) {
		returns.add(o);
	}

}
