package cookbook.panelized;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class ChoiceFieldPanel<T> extends Panel {

	private final IModel<List<? extends T>> choices;

	public ChoiceFieldPanel(String id, IModel<T> model,
			IModel<List<? extends T>> choices) {
		super(id, model);
		this.choices = choices;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		DropDownChoice<T> field = new DropDownChoice<T>("field",
				(IModel<T>) getDefaultModel(), choices);
		add(field);
		onCustomize(field);
	}

	protected void onCustomize(DropDownChoice<T> field) {
	}

}
