package cookbook;

import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class CreditCardPanel extends FormComponentPanel<Payment> {

	private final CreditCardPayment bean = new CreditCardPayment();

	public CreditCardPanel(String id, IModel<Payment> model) {
		super(id, model);
		add(new TextField<String>("name", new PropertyModel<String>(this, "bean.name")));
		add(new TextField<String>("card", new PropertyModel<String>(this, "bean.card")));
		add(new TextField<String>("ccv", new PropertyModel<String>(this, "bean.ccv")));
	}

	@Override
	protected void convertInput() {
		setConvertedInput(bean);
	}

}
