package cookbook.panelized;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
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
			final String label = segments[0];
			String type = segments[1];
			String key = segments[2];

			WebMarkupContainer field = new WebMarkupContainer(
					fields.newChildId());
			fields.add(field);

			field.add(new Label("label", label));

			Panel fc = null;

			if (type.equals("string")) {
				IModel<String> model = new PropertyModel<String>(
						getDefaultModel(), key);
				fc = new TextFieldPanel<String>(FCID, model) {
					protected void onCustomize(TextField<String> field) {
						field.setLabel(Model.of(label));
					};
				};
			} else if (type.equals("integer")) {
				IModel<Integer> model = new PropertyModel<Integer>(
						getDefaultModel(), key);
				fc = new TextFieldPanel<Integer>(FCID, model) {
					protected void onCustomize(TextField<Integer> field) {
						field.setType(Integer.class);
						field.setLabel(Model.of(label));
					};
				};
			} else if (type.equals("choice")) {
				List<String> choices = Arrays.asList(segments[3].split("-"));
				IModel<String> model = new PropertyModel<String>(
						getDefaultModel(), key);
				fc = new ChoiceFieldPanel<String>(FCID, model,
						Model.ofList(choices)) {
					protected void onCustomize(DropDownChoice<String> field) {
						field.setLabel(Model.of(label));
					};
				};
			}

			field.add(fc);

		}
	}

	@Override
	protected void onDetach() {
		definition.detach();
		super.onDetach();
	}

}
