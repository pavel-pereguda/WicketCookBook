package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		Form<?> form = new Form<Void>("form");
		add(form);
		form.add(new NameEditor("your", Model.of(new Name())));
		form.add(new NameEditor("spouse", Model.of(new Name())));
	}
}
