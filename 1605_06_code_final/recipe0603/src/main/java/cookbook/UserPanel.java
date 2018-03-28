package cookbook;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;

public class UserPanel extends Panel {

	public UserPanel(String id) {
		super(id);

		add(new TextField<String>("email", Model.of("")).setRequired(true)
				.setLabel(Model.of("Email"))
				.add(EmailAddressValidator.getInstance()));
		add(new TextField<String>("name", Model.of("")).setRequired(true)
				.setLabel(Model.of("Name")));

	}
}
