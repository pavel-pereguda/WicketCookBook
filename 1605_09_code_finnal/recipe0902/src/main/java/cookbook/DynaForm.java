package cookbook;

import java.util.Map;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class DynaForm extends Panel {
	private static final String FCID = "fc";

	private final IModel<String> definition;

	public DynaForm(String id, IModel<Map<String, Object>> model,
			IModel<String> definition) {
		super(id, model);
		this.definition = definition;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		RepeatingView fields = new RepeatingView("fields");
		add(fields);

		String[] parts = definition.getObject().split(",");
		for (String part : parts) {
			String[] segments = part.split(":");
			String label = segments[0];
			String type = segments[1];
			String key = segments[2];
			addComponent(fields, label, type, key);
		}
	}

	private void addComponent(RepeatingView fields, String label, String type,
			String key) {
		WebMarkupContainer field = new WebMarkupContainer(
				fields.newChildId());
		fields.add(field);

		field.add(new Label("label", label));

		FormComponent<?> fc = null;

		if (type.equals("string")) {
			IModel<String> model = new PropertyModel<String>(
					getDefaultModel(), key);
			fc = new TextField<String>(FCID, model);
		} else if (type.equals("integer")) {
			IModel<Integer> model = new PropertyModel<Integer>(
					getDefaultModel(), key);
			fc = new TextField<Integer>(FCID, model, Integer.class);
		}

		field.add(fc);
		fc.setLabel(Model.of(label));
	}

	@Override
	protected void onDetach() {
		definition.detach();
		super.onDetach();
	}

}
