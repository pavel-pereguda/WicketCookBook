package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import cookbook.fieldborder.FieldBorder;

public class HomePage extends WebPage {

    public HomePage(final PageParameters parameters) {
    	Form<?> form=new Form<Void>("form");
    	add(form);
    	
    	final TextField<String> name = new TextField<String>("name", Model.of(""));
    	name.setLabel(Model.of("Name"));
    	name.setRequired(true);

    	FieldBorder border=new FieldBorder("nameBorder");
    	form.add(border);
    	border.getBodyContainer().add(name);
    	
		TextField<String> email=new TextField<String>("email", Model.of(""));
		email.setLabel(Model.of("Email"));
    	email.setRequired(true);
    	email.add(EmailAddressValidator.getInstance());
    	
    	border=new FieldBorder("emailBorder");
    	form.add(border);
    	border.getBodyContainer().add(email);

    	
    }
}
