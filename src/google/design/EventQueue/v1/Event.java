package google.design.EventQueue.v1;

public class Event {

	public Cmd mCmd;
	public long mParam;
	public Token mToken;
	
	public Event(Cmd cmd) {
		this(cmd, 0L);
	}
	
	public Event(Cmd cmd, long param) {
		mCmd = cmd;
		mParam = param;
	}
}
