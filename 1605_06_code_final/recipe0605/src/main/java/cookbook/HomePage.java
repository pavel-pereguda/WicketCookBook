package cookbook;

import java.util.Date;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import cookbook.rounded.RoundedCornersBorder;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {

		RoundedCornersBorder border = new RoundedCornersBorder("border");
		add(border);
		
		border.getBodyContainer().add(
				new Label("time", Model.of(new Date().toString())));
	}
}
