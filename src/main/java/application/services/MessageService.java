package application.services;

import application.entities.users.User;
import application.repository.UserRepository;

public class MessageService<T> {

	
	public void notify(String userId, T message) {
		
		User<T> user = UserRepository.findById(userId);
		
		user.addMessage(message);
	}

}
