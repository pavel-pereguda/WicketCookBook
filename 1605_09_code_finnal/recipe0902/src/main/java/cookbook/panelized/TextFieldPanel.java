package cookbook.panelized;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class TextFieldPanel<T> extends Panel {

	public TextFieldPanel(String id, IModel<T> model) {
		super(id, model);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		TextField<T> field = new TextField<T>("field",
				(IModel<T>) getDefaultModel());

		add(field);
		onCustomize(field);
	}

	protected void onCustomize(TextField<T> field) {
	}
}
