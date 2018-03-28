package cookbook;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

public class LoginPage extends WebPage {
	private String username;
	private String password;

	public LoginPage() {
		add(new FeedbackPanel("feedback"));
		
		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				login();
			}
		};
		add(form);

		form.add(new TextField("username", new PropertyModel<String>(this,
				"username")).setRequired(true));
		form.add(new PasswordTextField("password", new PropertyModel<String>(
				this, "password")));

	}
	
	private void login() {
		if (((MySession)getSession()).login(username, password)) {
			if (!continueToOriginalDestination()) {
				setResponsePage(getApplication().getHomePage());
			}
		} else {
			error("Invalid credentials");
		}
	}
}
