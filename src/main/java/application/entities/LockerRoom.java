package application.entities;

import java.util.Map;

import application.entities.messages.Notification;

public class LockerRoom {

	private String roomId;
	
	private Location location;
	
	private Map<String, Locker> lockerMap;
	
	
	public boolean isVacantLockerAvail() {
		return lockerMap.values().stream().filter( l -> l.isVacant()).findAny().isPresent();
	}
	
	public Notification engageLocker(Order o) {
		
		if(!isVacantLockerAvail()) {
			throw new RuntimeException("No vacant Locker not available");
		}
		
		Locker locker = lockerMap.values().stream().filter( l -> l.isVacant()).findAny().get();
		Notification info = locker.useLocker(o);
		info.setLockerRoomId(roomId);
		return info;
		
	}
	
	public boolean isEquals(String roomId) {
		return this.roomId.equals(roomId);
	}
	
	public Order clear(String lockerId, String otp) {
		 if(lockerMap.containsKey(lockerId))
			 return lockerMap.get(lockerId).clearLocker(otp); 
		
		 throw new RuntimeException("Invalid LockerId"); 
	}
	
	public void clearLocker(String lockerId){
		if(!lockerMap.containsKey(lockerId))
			throw new RuntimeException("Invalid lockerId, not locker found!");
		lockerMap.get(lockerId).clearLocker();
	}
	
}
