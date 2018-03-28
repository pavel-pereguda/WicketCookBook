package cookbook;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsRequestCycleProcessor;
import org.apache.wicket.request.IRequestCycleProcessor;

public class WicketApplication extends WebApplication {

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	protected IRequestCycleProcessor newRequestCycleProcessor() {
		return new HttpsRequestCycleProcessor(new HttpsConfig(8080, 8443));
	}

}
