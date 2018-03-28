package cookbook;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class ConfirmationPage extends WebPage
{
	public ConfirmationPage(String account, Integer amount)
	{
		add(new Label("amount", String.valueOf(amount)));
		add(new Label("account", account));
		add(new Label("balance", String.valueOf(Bank.getBalance(account))));
	}
}
