package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.component.listener.IListenerInterfaceRequestTarget;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see cookbook.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * Constructor
	 */
	public WicketApplication() {
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public AjaxRequestTarget newAjaxRequestTarget(Page page) {
		Component component = null;
		RequestCycle cycle = page.getRequestCycle();
		if (cycle.getRequestTarget() instanceof IListenerInterfaceRequestTarget) {
			component = ((IListenerInterfaceRequestTarget) cycle.getRequestTarget())
					.getTarget();
		}

		AjaxRequestTarget target = super.newAjaxRequestTarget(page);
		if (component != null) {
			target.addListener(new AjaxFeedbackUpdater(component));
		}
		
		return target;
	}

}
