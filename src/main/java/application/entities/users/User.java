package application.entities.users;

import java.util.LinkedList;
import java.util.Queue;

import application.constants.UserType;
import application.entities.Location;

public class User<T> {
	
	private String id;
	
	private String name;
	
	private Location location;
	
	private Queue<T> messages = new LinkedList<T>();
	
	public String userCity() {
		return location.city();
	}

	public String id() {
		return id;
	}
	
	public void addMessage(T n) {
		messages.add(n);
	}
	
	public Queue<T> getMessages() {
		return messages;
	}
	
	public T readNextMessage() {
		if(!messages.isEmpty())
			return messages.poll();
		return null;
	}
	
	

}
