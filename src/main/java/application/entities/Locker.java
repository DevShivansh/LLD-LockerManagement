package application.entities;

import application.constants.OrderStatus;
import application.entities.messages.Notification;
import application.services.OTPService;

public class Locker {
	
	private String lockerId;
	
	private String verificationCode;
	
	private Order currentOrder;
	
	
	public boolean isVacant() {
		return currentOrder == null;
	}
	
	public Order clearLocker(String otp) {
		
		if(!verificationCode.equals(otp))
			throw new RuntimeException("OTP not valid");
		Order o = currentOrder;
		currentOrder = null;
		verificationCode = null;
		return o;
	}

	

	public Notification useLocker(Order o) {
		
		if(!isVacant())
			throw new RuntimeException("Locker already occupied!");
		
		this.currentOrder = o;
		this.currentOrder.updateState(OrderStatus.DELIVERED);
		this.verificationCode = OTPService.generateOTP();
		Notification info = new Notification(lockerId, o.orderId(), o.getUserId(), verificationCode);
		return info;
	}
}
