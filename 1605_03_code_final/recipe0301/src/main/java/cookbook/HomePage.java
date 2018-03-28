package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;

public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		add(new FeedbackPanel("feedback"));

		Form<?> form = new Form<Void>("form");
		add(form);

		TextField<String> name = new TextField<String>("name", Model.of(""));
		name.setLabel(Model.of("Name"));
		name.setRequired(true);
		name.add(new ErrorClassAppender());
		form.add(name);

		TextField<String> email = new TextField<String>("email", Model.of(""));
		email.setLabel(Model.of("Email"));
		email.setRequired(true);
		email.add(EmailAddressValidator.getInstance());
		email.add(new ErrorClassAppender());
		form.add(email);
	}
}
