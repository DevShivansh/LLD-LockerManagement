package application.entities.users;

import application.constants.AgentStatus;
import application.constants.OrderStatus;
import application.entities.Order;
import application.entities.messages.Notification;
import application.interfaces.LockerUserPrevilage;
import application.interfaces.MessageReader;
import application.services.LockerService;
import application.services.MessagePoller;
import application.services.MessageService;
import application.services.OrderService;

public class PickupAgent implements MessageReader<Notification> {

	private User<Notification> user;
	
	private AgentStatus status;
	
	private MessagePoller<Notification> messagePoller;
	
	private LockerUserPrevilage lockerService;
	
	public PickupAgent(User<Notification> user) {
		super();
		this.user = user;
		lockerService = LockerService.getUserInstance();
		messagePoller = new MessagePoller<Notification>(user.getMessages(), this);
		messagePoller.run();
	}

	public boolean isAvailable() {
		return status == AgentStatus.AVAILABLE ? true : false;
	}
	
	public void engage() {
		status = AgentStatus.ENGAGED;
	}
	
	
	public String agentLocationCity() {
		return user.userCity();
	}
	
	
	public String id() {
		return user.id();
	}

	
	@Override
	public void readMessage(Notification n) {
		
		Order o = lockerService.vacantLocker(n);
		o.updateState(OrderStatus.RECEIVED);
		OrderService.addReturns(o);
		status = AgentStatus.AVAILABLE;
	}

		
}
