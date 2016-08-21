package google.design.EventQueue.v2;

public class Event {

	public Cmd mCmd;
	public long mTimestamp;
	public Token mToken;
	
	public Event(Cmd cmd) {
		mCmd = cmd;
		mTimestamp = System.currentTimeMillis();
	}
}
