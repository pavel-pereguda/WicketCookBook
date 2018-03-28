package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;


public class HomePage extends WebPage
{

	private Name name;
	private String email;

	public HomePage(final PageParameters parameters)
	{

		add(new FeedbackPanel("feedback"));

		Form<?> form = new Form<Void>("form")
		{
			@Override
			protected void onSubmit()
			{
				info("Registering: " + name + ", " + email);
			}
		};
		add(form);

		FormComponent<Name> name = new NameEditor("name", new PropertyModel<Name>(this, "name"));
		name.setRequired(true);
		form.add(name);

		FormComponent<String> email = new TextField<String>("email", new PropertyModel<String>(
			this, "email"));
		email.setRequired(true);
		email.add(EmailAddressValidator.getInstance());
		form.add(email);
	}
}
