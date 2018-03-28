package cookbook;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

public class MySession extends WebSession {

	private String username;

	public MySession(Request request) {
		super(request);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
