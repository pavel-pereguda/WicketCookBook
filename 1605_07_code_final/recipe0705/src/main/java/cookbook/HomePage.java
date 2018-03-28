package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

public class HomePage extends WebPage {

	public HomePage() {

		add(new FeedbackPanel("feedback").setOutputMarkupPlaceholderTag(true));

		Form<?> form = new Form<Void>("form");
		add(form);

		Component username = new TextField<String>("username", Model.of(""))
				.setRequired(true).add(StringValidator.minimumLength(6));

		username.add(new AjaxFormComponentUpdatingBehavior("onblur") {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
			}
		});
		form.add(username);

		Component password = new PasswordTextField("password", Model.of(""))
				.setRequired(true);

		password.add(new AjaxFormComponentUpdatingBehavior("onblur") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
			}

		});

		form.add(password);

	}
}
