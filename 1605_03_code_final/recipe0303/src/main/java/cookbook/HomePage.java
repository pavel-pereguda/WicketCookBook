package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;

public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		Form<?> form = new Form<Void>("form");
		add(form);

		TextField<String> name = new TextField<String>("name", Model.of(""));
		name.setLabel(Model.of("Name"));
		name.setRequired(true);
		form.add(name);
		form.add(new FeedbackPanel("nameFeedback",
				new ComponentFeedbackMessageFilter(name)));

		TextField<String> email = new TextField<String>("email", Model.of(""));
		email.setLabel(Model.of("Email"));
		email.setRequired(true);
		email.add(EmailAddressValidator.getInstance());
		form.add(email);
		form.add(new FeedbackPanel("emailFeedback",
				new ComponentFeedbackMessageFilter(email)));
	}
}
