package cookbook;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;
import org.apache.wicket.request.IRequestCodingStrategy;
import org.apache.wicket.request.IRequestCycleProcessor;

public class WicketApplication extends WebApplication {

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	protected IRequestCycleProcessor newRequestCycleProcessor() {
		return new WebRequestCycleProcessor() {
			protected IRequestCodingStrategy newRequestCodingStrategy() {
				return new CryptedUrlWebRequestCodingStrategy(
						super.newRequestCodingStrategy());
			}
		};
	}

}
