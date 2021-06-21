package application.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import application.entities.Locker;
import application.entities.LockerRoom;

public class LockerRepository {

	private static Map<String, List<LockerRoom>> lockerRooms = new HashMap<>();

	public static LockerRoom findAnyLockerRoomByCity(String city) {

		if (!lockerRooms.containsKey(city))
			throw new RuntimeException("No locker service available for the city");

		Optional<LockerRoom> room = lockerRooms.get(city).stream().filter(r -> r.isVacantLockerAvail()).findFirst();

		if (!room.isPresent())
			throw new RuntimeException("No vacant lockers available across city");

		return room.get();
	}

	public static LockerRoom findLockerRoomByCityAndRoomId(String city, String roomId) {

		Optional<LockerRoom> r = lockerRooms.get(city).stream().filter(room -> room.isEquals(roomId)).findFirst();
		if (!r.isPresent())
			throw new RuntimeException("Invalid roomId, no Locker room found!");
		return r.get();
	}

	public static LockerRoom findLockerByRoomId(String roomId) {

		Optional<LockerRoom> r = lockerRooms.values().stream().flatMap(list -> list.stream())
				.collect(Collectors.toList()).stream().filter(l -> l.isEquals(roomId)).findFirst();
		if (!r.isPresent())
			throw new RuntimeException("Invalid roomId, no Locker room found!");
		return r.get();
	}

}
