package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {
  private Time time;

  public HomePage(final PageParameters parameters) {
    add(new FeedbackPanel("feedback"));

    TextField<Time> time = new TextField<Time>("time", new PropertyModel<Time>(this, "time"));

    Form< ? > form = new Form<Void>("form") {
      @Override
      protected void onSubmit() {
        info("You entered: " + HomePage.this.time);
      }
    };

    add(form);
    form.add(time);
  }
}
