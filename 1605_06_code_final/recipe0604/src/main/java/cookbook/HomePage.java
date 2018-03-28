package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;

public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);

		Form<?> form = new Form<Void>("form");
		add(form);

		form.add(new TextField<String>("email", Model.of("")).setRequired(true)
				.setLabel(Model.of("Email"))
				.add(EmailAddressValidator.getInstance()));
		form.add(new TextField<String>("name", Model.of("")).setRequired(true)
				.setLabel(Model.of("Name")));
		form.add(new CheckBox("company", Model.of(true)));
		form.add(new CheckBox("partners", Model.of(true)));

		form.add(new AjaxButton("create") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				info("User created...");
				target.addComponent(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.addComponent(feedback);
			}

		});
	}
}
