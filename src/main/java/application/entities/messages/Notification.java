package application.entities.messages;

public class Notification {

	private String orderId;
	
	private String customerId;
	
	private String deliveryAgentId;
	
	private String verificationCode;
	
	private String lockerRoomId;
	
	private String lockerId;
	
	
	public Notification(String orderId, String customerId, String lockerId, String verificationCode) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.lockerId = lockerId;
		this.verificationCode = verificationCode;
	}

	public void setLockerRoomId(String lockerRoomId) {
		this.lockerRoomId = lockerRoomId;
	}

	
	public void setDeliveryAgentId(String deliveryAgentId) {
		this.deliveryAgentId = deliveryAgentId;
	}

	public String getCustomerId() {
		return customerId;
	}
	
	public String getDeliveryAgentId() {
		return deliveryAgentId;
	}

	public boolean verifyOTP(String otp) {
		return verificationCode.equals(otp);
	}
	
	public String getLockerRoomId() {
		return lockerRoomId;
	}
	
	public String getLockerId() {
		return lockerId;
	}
	
	public String getOTP() {
		return verificationCode;

	}
}
