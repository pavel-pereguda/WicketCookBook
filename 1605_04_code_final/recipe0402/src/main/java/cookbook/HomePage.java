package cookbook;

import java.util.Map;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.collections.MicroMap;
import org.apache.wicket.validation.validator.EmailAddressValidator;

public class HomePage extends WebPage {

	TextField<String> email;

	public HomePage() {
		add(new Label("title", new ResourceModel("subscribe")));
		add(new FeedbackPanel("feedback"));

		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				subscribe();
			}
		};
		add(form);

		email = new TextField<String>("email", Model.of(""));
		email.setRequired(true);
		email.add(EmailAddressValidator.getInstance());
		email.setLabel(new ResourceModel("email"));
		form.add(email);
		form.add(new SimpleFormComponentLabel("emailLabel", email));
	}

	private void subscribe() {
		String addr = email.getModelObject();
		Map vars = new MicroMap("email", addr);
		info(getString("subscribed", Model.ofMap(vars)));
		email.clearInput();
	}
}
