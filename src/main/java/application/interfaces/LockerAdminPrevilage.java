package application.interfaces;

public interface LockerAdminPrevilage extends LockerUserPrevilage{

	public void vacantLocker(String roomId, String lockerId);
}
