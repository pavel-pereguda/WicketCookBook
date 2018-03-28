package cookbook;

import org.apache.wicket.protocol.http.IRequestLogger;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		getRequestLoggerSettings().setRequestLoggerEnabled(true);
	}

	@Override
	protected IRequestLogger newRequestLogger() {
		return new ExtendedRequestLogger();
	}

}
