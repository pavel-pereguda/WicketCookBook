package cookbook;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public AjaxRequestTarget newAjaxRequestTarget(Page page) {
		AjaxRequestTarget target = super.newAjaxRequestTarget(page);
		target.addListener(new AjaxFeedbackUpdater());
		return target;
	}

}
