package application.entities.messages;

public class LockerVacantMessage {

	private String roomId;
	
	private String lockerId;

	public LockerVacantMessage(String roomId, String lockerId) {
		super();
		this.roomId = roomId;
		this.lockerId = lockerId;
	}

	public String getRoomId() {
		return roomId;
	}

	public String getLockerId() {
		return lockerId;
	}
	
	
}
