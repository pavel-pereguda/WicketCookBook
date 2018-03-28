package cookbook;


import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {

  public HomePage(final PageParameters parameters) {
    add(new FeedbackPanel("feedback"));

    TextField< ? > username = new TextField<String>("username", new Model<String>());
    username.setRequired(true);

    FormComponent< ? > password1 = new PasswordTextField("password1", new Model<String>());
    FormComponent< ? > password2 = new PasswordTextField("password2", new Model<String>());

    Form< ? > form = new Form<Void>("form") {
      @Override
      protected void onSubmit() {
        info("Form successfully submitted");
      }
    };

    form.add(new cookbook.SameValueValidator(password1, password2));
    add(form);
    form.add(username);
    form.add(password1);
    form.add(password2);
  }
}
