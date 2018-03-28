package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private String name = "Bart Simpson";
	private String email = "bart@thesimpsons.com";
	private String review = "Has a problem with authority";

	public HomePage(final PageParameters parameters) {

		Form<?> form = new Form<Void>("form");
		add(form);

		form.add(new TextField<String>("name", new PropertyModel<String>(this,
				"name")).setRequired(true));

		form.add(new TextField<String>("email", new PropertyModel<String>(this,
				"email")).setRequired(true));

		Component review = new TextArea<String>("review",
				new PropertyModel<String>(this, "review")).setRequired(true);
		
		Permissions.of(review).render(Permission.VIEW_REVIEW).enable(Permission.EDIT_REVIEW);
		
		form.add(review);

	}
}
