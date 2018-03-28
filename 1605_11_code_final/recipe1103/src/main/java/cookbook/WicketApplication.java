package cookbook;

import org.jboss.seam.wicket.SeamApplication;

public class WicketApplication extends SeamApplication {
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}
}
