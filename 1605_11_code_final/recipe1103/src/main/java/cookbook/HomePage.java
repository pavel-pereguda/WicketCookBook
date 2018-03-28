package cookbook;

import javax.inject.Inject;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {

	@Inject
	private TimeService service;

	public HomePage(final PageParameters parameters) {
		add(new Label("time", new PropertyModel<String>(this, "service.time")));
	}
}
