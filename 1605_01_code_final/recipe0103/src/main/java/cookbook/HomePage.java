package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

public class HomePage extends WebPage {

  public HomePage(final PageParameters parameters) {

    add(new FeedbackPanel("feedback"));

    final TextField< ? > username = new TextField<String>("username", Model.of(""));
    username.setRequired(true);

    FormComponent<String> password1 = new PasswordTextField("password1", Model.of(""));
    password1.setLabel(Model.of("Password"));
    password1.add(StringValidator.minimumLength(8));
    password1.add(new PasswordPolicyValidator());

    FormComponent< ? > password2 = new PasswordTextField("password2", Model.of(""));

    Form< ? > form = new Form<Void>("form") {
      @Override
      protected void onSubmit() {

        info("Form successfully submitted blah blah");
      }
    };

    form.add(new EqualPasswordInputValidator(password1, password2));
    add(form);
    form.add(username);
    form.add(password1);
    form.add(password2);
  }
}
