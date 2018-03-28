package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.time.Duration;

public class HomePage extends WebPage
{
	private String account;
	private Integer amount;

	public HomePage(final PageParameters parameters)
	{
		add(new FeedbackPanel("feedback"));
		Form<?> form = new SubmitOnceForm<Void>("form")
		{
			@Override
			protected void onSubmit()
			{
				Bank.withdraw(account, amount);
				Duration.seconds(5).sleep();
				setResponsePage(new ConfirmationPage(account, amount));
			}

			@Override
			protected void onRepeatSubmit()
			{
				setResponsePage(new ConfirmationPage(account, amount));
			}
		};
		add(form);

		TextField<String> account = new TextField<String>("account", new PropertyModel<String>(
			this, "account"));
		account.setRequired(true);
		form.add(account);

		TextField<Integer> amount = new TextField<Integer>("amount", new PropertyModel<Integer>(
			this, "amount"), Integer.class);
		amount.setRequired(true);
		form.add(amount);
	}

}
