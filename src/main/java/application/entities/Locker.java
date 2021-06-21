package application.entities;

import application.constants.OrderStatus;
import application.entities.messages.Notification;
import application.services.OTPService;

public class Locker {
	
	private String lockerId;
	
	private String verificationCode;
	
	private Order currentOrder;
	
	private LocalDateTime lockedTime;
	
	public boolean isVacant() {
		return currentOrder == null;
	}
	
	public Order clearLocker(String otp) {
		
		if(!verificationCode.equals(otp))
			throw new RuntimeException("OTP not valid");
		Order o = currentOrder;
		invalidateCurrentOrder();
		return o;
	}

	private void invalidateCurrentOrder(){
		currentOrder = null;
		verificationCode = null;
	}

	public void clearLocker(){

		if(!LocalDateTime.now().minusDays(3).isAfter(lockedTime))
			throw new RuntimeException("Locker cannot be cleared before 3 days");

		invalidateCurrentOrder();

	}

	

	public Notification useLocker(Order o) {
		
		if(!isVacant())
			throw new RuntimeException("Locker already occupied!");
		
		this.currentOrder = o;
		this.currentOrder.updateState(OrderStatus.DELIVERED);
		this.verificationCode = OTPService.generateOTP();
		lockedTime = LocalDateTime.now();
		Notification info = new Notification(lockerId, o.orderId(), o.getUserId(), verificationCode);
		return info;
	}
}
