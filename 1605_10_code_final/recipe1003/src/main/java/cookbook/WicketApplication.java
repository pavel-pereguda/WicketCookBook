package cookbook;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.openid4java.OpenIDException;
import org.openid4java.discovery.Identifier;

import cookbook.openid.OpenIdConsumer;

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

		OpenIdConsumer consumer = new OpenIdConsumer(
				"http://ivaynberg.dnsalias.com") {

			@Override
			protected void onLoginSuccessful(Identifier identifier, Page page) {
				MySession session = (MySession) page.getSession();
				session.setUsername(identifier.getIdentifier());
				if (!page.continueToOriginalDestination()) {
					page.setResponsePage(getHomePage());
				}
			}

			@Override
			protected void onLoginFailed(String identity,
					OpenIDException cause, Page page) {
				LoginPage login = new LoginPage();
				login.error("OpenID Authentication Failed");
				page.setResponsePage(login);
			}

		};
		consumer.init(this);
	}

}
