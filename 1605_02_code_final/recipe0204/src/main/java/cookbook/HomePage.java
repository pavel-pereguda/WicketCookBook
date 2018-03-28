package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {

	private final ChallengeModel challengeModel = new ChallengeModel();

	public HomePage(final PageParameters parameters) {
		add(new FeedbackPanel("feedback"));

		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				info("Comment successfully submitted");
			}
		};
		add(form);
		
		form.add(new TextField<String>("name", Model.of("")));
		form.add(new TextArea<String>("comment", Model.of("")));
		form.add(new Captcha("captcha"));
	}
}
