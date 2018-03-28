package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends WebPage {

	@SpringBean
	private TimeService service;

	public HomePage(final PageParameters parameters) {

		add(new Label("time", new PropertyModel<String>(this, "service.time")));
	}
}
