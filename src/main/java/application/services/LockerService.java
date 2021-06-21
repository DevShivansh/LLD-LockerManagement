package application.services;

import application.entities.LockerRoom;
import application.entities.Order;
import application.entities.messages.Notification;
import application.interfaces.LockerAdminPrevilage;
import application.interfaces.LockerUserPrevilage;
import application.repository.LockerRepository;

public class LockerService implements LockerAdminPrevilage, LockerUserPrevilage{

	private static LockerUserPrevilage userLockerService = new LockerService();
	
	private static LockerAdminPrevilage adminLockerService = new LockerService();
	
	public static LockerUserPrevilage getUserInstance() {
		return userLockerService;
	}
	
	public static LockerAdminPrevilage getAdminInstance() {
		return adminLockerService;
	}
	
	public Notification useLockerService(Order o, String city) {
		
		LockerRoom room = LockerRepository.findAnyLockerRoomByCity(city);
		Notification info = room.engageLocker(o);
		return info;
		
	}
	
	public Order vacantLocker(Notification info) {
		
		LockerRoom room = LockerRepository.findLockerByRoomId(info.getLockerRoomId());
		return room.clear(info.getLockerId(), info.getOTP());
		
		
	}

	@Override
	public void vacantLocker(String roomId, String lockerId) {
		
		LockerRoom room = LockerRepository.findLockerByRoomId(roomId);
	}
	
}
