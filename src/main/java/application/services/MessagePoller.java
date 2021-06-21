package application.services;

import java.util.Queue;

import application.interfaces.MessageReader;

public class MessagePoller<T> implements Runnable {

	private Queue<T> messages;
	
	private MessageReader<T> reader;
	 
	
	
	public MessagePoller(Queue<T> messages, MessageReader<T> reader) {
		super();
		this.messages = messages;
		this.reader = reader;
	}



	@Override
	public void run() {
		
		while(true) {
			if(!messages.isEmpty()) {
				reader.readMessage(messages.poll());
			}
		}
		
	}

}
