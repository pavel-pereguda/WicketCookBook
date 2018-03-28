package cookbook.forms;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {
	private String username;
	private String password;

	public HomePage() {
		Form<?> form = new HttpsForm<Void>("form");
		add(form);
		form.add(new TextField("username", new PropertyModel(this, "username")));
		form.add(new PasswordTextField("password", new PropertyModel(this, "username")));
	}
}
