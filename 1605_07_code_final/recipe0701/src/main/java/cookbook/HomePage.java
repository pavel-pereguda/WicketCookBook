package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

public class HomePage extends WebPage {
	public HomePage(final PageParameters parameters) {

		final Component feedback = new FeedbackPanel("feedback")
				.setOutputMarkupId(true);
		add(feedback);

		Form<?> form = new Form<Void>("form");
		add(form);

		form.add(new TextField<String>("username", Model.of("")).setRequired(
				true).add(StringValidator.minimumLength(6)));

		form.add(new PasswordTextField("password", Model.of(""))
				.setRequired(true));

		form.add(new AjaxButton("submit") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.addComponent(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.addComponent(feedback);
			}

		});

	}
}
