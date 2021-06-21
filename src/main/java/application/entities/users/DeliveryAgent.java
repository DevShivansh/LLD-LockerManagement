package application.entities.users;

import application.constants.AgentStatus;
import application.entities.Order;
import application.entities.messages.Notification;
import application.interfaces.LockerUserPrevilage;
import application.interfaces.MessageReader;
import application.services.LockerService;
import application.services.MessagePoller;
import application.services.MessageService;
import application.services.OrderService;

public class DeliveryAgent implements MessageReader<String> {

	private User<String> user;
	
	private AgentStatus status;
	
	private MessagePoller<String> messagePoller;
	
	private MessageService<Notification> messageService;
	
	private LockerUserPrevilage lockerService;
	
	public DeliveryAgent(User<String> user) {
		super();
		this.user = user;
		lockerService = LockerService.getUserInstance();
		messageService = new MessageService<Notification>();
		messagePoller = new MessagePoller<String>(user.getMessages(), this);
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
	public void readMessage(String orderId) {
		Order o = OrderService.getOrder(orderId);
		Notification message = lockerService.useLockerService(o, agentLocationCity());
		message.setDeliveryAgentId(id());
		messageService.notify(message.getCustomerId(), message);
		status = AgentStatus.AVAILABLE;
	}

	
	
}
