package cookbook;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new MySession(request);
	}

	@Override
	protected void init() {
		super.init();
		getSecuritySettings().setAuthorizationStrategy(new AuthStrategy());
	}

}
