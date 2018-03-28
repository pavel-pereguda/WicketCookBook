package cookbook;

import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class PayPalPanel extends FormComponentPanel<Payment> {

	private final PayPalPayment bean = new PayPalPayment();

	public PayPalPanel(String id, IModel<Payment> model) {
		super(id, model);
		add(new TextField("username", new PropertyModel(this, "bean.username")));
	}

	@Override
	protected void convertInput() {
		setConvertedInput(bean);
	}

}
