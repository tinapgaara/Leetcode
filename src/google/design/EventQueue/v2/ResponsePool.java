package google.design.EventQueue.v2;

import java.util.HashMap;

public class ResponsePool {

	private HashMap<Token, Response> mResponses;
	
	public ResponsePool() {
		mResponses = new HashMap<Token, Response>();
	}
	
	public synchronized void addResponse(Token token, Response response) {
		mResponses.put(token, response);
	}
	
	public synchronized Response fetchResponse(Token token) {		
		return mResponses.remove(token);
	}
}
