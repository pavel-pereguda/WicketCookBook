package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RedirectToUrlException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class HomePage extends WebPage {
	public HomePage(final PageParameters parameters) {
		add(new Label("username", new AbstractReadOnlyModel<String>() {
			public String getObject() {
				return ((MySession) getSession()).getUsername();
			}
		}));
	}
}
