package cookbook;

import java.util.Date;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import cookbook.collapse.CollapsibleBorder;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		CollapsibleBorder border = new CollapsibleBorder("border",
				Model.of("Collapse"), Model.of("Expand"));
		add(border);

		border.getBodyContainer().add(
				new Label("time", Model.of(new Date().toString())));
		border.getBodyContainer().add(
				new Label("locale", Model.of(getLocale().toString())));

	}
}
