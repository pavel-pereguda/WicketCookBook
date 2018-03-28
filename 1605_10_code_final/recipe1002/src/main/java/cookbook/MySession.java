package cookbook;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class MySession extends WebSession {

	private String username;

	public MySession(Request request) {
		super(request);
	}

	public boolean login(String username, String password) {
		if (username.equals(password)) {
			this.username = username;
			return true;
		}
		return false;
	}

	public String getUsername() {
		return username;
	}

}
