package google.design.EventQueue.v2;

import java.util.LinkedList;

public class EventQueue {

	private LinkedList<Event> mQueue;
	
	public EventQueue() {
		mQueue = new LinkedList<Event>();
	}
	
	public synchronized Token addEvent(Event event) {
		if (event.mCmd != Cmd.IncCount) {
			event.mToken = new Token();
		}
		
		mQueue.addLast(event);
		return event.mToken;
	}
	
	public synchronized Event fetchEvent() {
		return mQueue.poll();
	}
	
}
