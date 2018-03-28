package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class HomePage extends WebPage {

  public HomePage(final PageParameters parameters) {
    super(parameters);

    add(new FeedbackPanel("feedback"));
    TextField<String> username = new TextField<String>("username", Model.of(""));
    username.setRequired(true);
    username.add(new UsernameValidator());

    FormComponent<String> password1 = new PasswordTextField("password1", Model.of(""));
    password1.setLabel(Model.of("Password"));

    FormComponent< ? > password2 = new PasswordTextField("password2", Model.of(""));



    Form< ? > form = new Form<Void>("form") {
      @Override
      protected void onSubmit() {
        info("Form successfully submitted");
      }
    };

    add(form);
    form.add(username);
    form.add(password1);
    form.add(password2);
  }

}
