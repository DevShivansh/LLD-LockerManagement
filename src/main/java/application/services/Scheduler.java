package application.services;

import java.util.Queue;

import application.constants.OrderStatus;
import application.entities.Order;
import application.entities.users.DeliveryAgent;
import application.entities.users.User;
import application.repository.UserRepository;

public class Scheduler implements Runnable {
	
	private Queue<Order> orders;

	private MessageService<String> notificationService;
	
	public Scheduler(Queue<Order> orders) {
		this.orders = orders;
		notificationService = new MessageService<String>();
	}
	


	@Override
	public void run() {
		
		while(true) {
			
			if(!orders.isEmpty()) {
				
				Order o = orders.poll();
				o.updateState(OrderStatus.IN_TRANSIT);
				User u = UserRepository.findById(o.getUserId());
				DeliveryAgent agent = AgentService.findDeliveryAgent(u.userCity());
				agent.engage();
				notificationService.notify(agent.id(), o.orderId());
				
			}
			
		}
		
	}

}
