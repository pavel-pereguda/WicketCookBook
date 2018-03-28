package cookbook;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {

	private Order order = new Order();

	private Component paymentPanel;

	public HomePage(final PageParameters parameters) {
		add(new FeedbackPanel("feedback"));

		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				info(order.toString());
			}
		};
		add(form);

		form.add(new DropDownChoice<PaymentType>("type",
				new Model<PaymentType>(null), Arrays.asList(PaymentType
						.values())) {
			@Override
			protected boolean wantOnSelectionChangedNotifications() {
				return true;
			}

			@Override
			protected void onSelectionChanged(PaymentType newSelection) {
				setupPayment(newSelection);
			}
		});

		paymentPanel = new EmptyPanel("payment");
		form.add(paymentPanel);
	}

	private void setupPayment(PaymentType type) {
		IModel<Payment> paymentModel = new PropertyModel<Payment>(this,
				"order.payment");
		String id = paymentPanel.getId();
		Component replacement = null;

		switch (type) {
		case CreditCard:
			replacement = new CreditCardPanel(id, paymentModel);
			break;
		case PayPal:
			replacement = new PayPalPanel(id, paymentModel);
			break;
		}

		paymentPanel.replaceWith(replacement);
		paymentPanel = replacement;
	}

	public static enum PaymentType {
		CreditCard, PayPal;
	}
}
