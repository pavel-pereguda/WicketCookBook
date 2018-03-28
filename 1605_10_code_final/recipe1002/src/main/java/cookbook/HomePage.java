package cookbook;

import javax.servlet.http.Cookie;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RedirectToUrlException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.protocol.http.WebResponse;

public class HomePage extends WebPage {
	public HomePage(final PageParameters parameters) {
		add(new Label("username", new AbstractReadOnlyModel<String>() {
			public String getObject() {
				return ((MySession) getSession()).getUsername();
			}
		}));

		add(new Link("logout") {
			public void onClick() {
				getSession().invalidate();
				final String urlToHome = urlFor(getApplication().getHomePage(),
						null).toString();
				throw new RedirectToUrlException(urlToHome);
			}

		});
	}
}
