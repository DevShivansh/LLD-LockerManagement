package application.entities.users;

import application.entities.messages.LockerVacantMessage;
import application.interfaces.LockerAdminPrevilage;
import application.interfaces.MessageReader;
import application.services.LockerService;
import application.services.MessagePoller;

public class Admin implements MessageReader<LockerVacantMessage> {

	private User<LockerVacantMessage> user;
	
	private MessagePoller<LockerVacantMessage> messagePoller;
	
	private LockerAdminPrevilage lockerService;
	
	public Admin(User<LockerVacantMessage> user) {
		super();
		this.user = user;
		lockerService = LockerService.getAdminInstance();
		this.messagePoller = new MessagePoller<LockerVacantMessage>(user.getMessages(), this);
		messagePoller.run();
	}


	@Override
	public void readMessage(LockerVacantMessage m) {
		lockerService.vacantLocker(m.getRoomId(), m.getLockerId());
	}
	
	
	
}
