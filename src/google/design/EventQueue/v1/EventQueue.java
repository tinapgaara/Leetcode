package google.design.EventQueue.v1;

import java.util.LinkedList;

public class EventQueue {

	private LinkedList<Event> mQueue;
	
	public EventQueue() {
		mQueue = new LinkedList<Event>();
	}
	
	public synchronized Token addEvent(Event event) {
		Event lastEvent = mQueue.getLast();
		if (event.mCmd == Cmd.IncCount) {
			if ( (lastEvent != null) && (lastEvent.mCmd == Cmd.IncCount) ) {	// combine this event with the last event
				lastEvent.mParam += event.mParam;
				return null;	// no token for IncCount command
			}
		}
		else {
			if (event.mCmd == lastEvent.mCmd) {	// just reuse the last event
				lastEvent.mToken.incRefCount();
				return lastEvent.mToken; 		// ignore this event
			}
			else {
			    event.mToken = new Token();
			}
		}
		
		mQueue.addLast(event);
		return event.mToken;
	}
	
	public synchronized Event fetchEvent() {
		return mQueue.poll();
	}
	
}
