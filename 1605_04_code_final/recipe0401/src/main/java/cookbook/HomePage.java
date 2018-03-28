package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {

	private Name name;

	public HomePage(final PageParameters parameters) {
		name = new Name("John", "Jacob", "Doe");

		add(new NameLabel("label", new PropertyModel<Name>(this, "name")));

		Form<?> form = new Form<Void>("form");
		add(form);
		form.add(new NameEditor("editor", new PropertyModel<Name>(this, "name")));
	}
}
