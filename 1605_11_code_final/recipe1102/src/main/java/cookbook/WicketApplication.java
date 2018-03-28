package cookbook;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		addComponentInstantiationListener(new GuiceComponentInjector(this,
				new ApplicationModule()));
	}
}
