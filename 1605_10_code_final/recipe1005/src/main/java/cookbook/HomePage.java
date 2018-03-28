package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		add(new BookmarkablePageLink("link", SecurePage.class));
	}
}
