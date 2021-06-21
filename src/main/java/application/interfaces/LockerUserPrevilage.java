package application.interfaces;

import application.entities.Order;
import application.entities.messages.Notification;

public interface LockerUserPrevilage {

	public Notification useLockerService(Order o, String city);
	
	public Order vacantLocker(Notification info);
}
