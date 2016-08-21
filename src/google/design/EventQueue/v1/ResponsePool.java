package google.design.EventQueue.v1;

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
		
		Response res = mResponses.get(token);
		if (res != null) {
			int refCount = token.decRefCount();
			if (refCount <= 0) mResponses.remove(token);
		}
		return res;
	}
}
