package cookbook;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

public class NameLabel extends Panel {

	public NameLabel(String id, IModel<Name> model) {
		super(id, model);

		add(new Label("firstLabel", new ResourceModel("name.first")));
		add(new Label("first", new PropertyModel<String>(model, "first")));

		add(new Label("middleLabel", new ResourceModel("name.middle")));
		add(new Label("middle", new PropertyModel<String>(model, "middle")));

		add(new Label("lastLabel", new ResourceModel("name.last")));
		add(new Label("last", new PropertyModel<String>(model, "last")));
	}

}
