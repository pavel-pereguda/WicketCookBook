package cookbook.ajax;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import cookbook.CreditCardPanel;
import cookbook.Order;
import cookbook.PayPalPanel;
import cookbook.Payment;

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

		final DropDownChoice<PaymentType> switcher = new DropDownChoice<PaymentType>(
				"type", new Model<PaymentType>(null), Arrays.asList(PaymentType
						.values()));
		switcher.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				setupPayment(switcher.getModelObject());
				paymentPanel.setOutputMarkupId(true);
				target.addComponent(paymentPanel);
			}
		});
		form.add(switcher);

		paymentPanel = new WebMarkupContainer("payment")
				.setOutputMarkupId(true);
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
