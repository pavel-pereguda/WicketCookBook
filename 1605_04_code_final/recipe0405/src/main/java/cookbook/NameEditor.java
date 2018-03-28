package cookbook;

import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

public class NameEditor extends Panel {

	public NameEditor(String id, IModel<Name> model) {
		super(id, model);
		add("first").add("last");
	}

	private NameEditor add(String id) {
		IModel<String> model = new PropertyModel<String>(getDefaultModel(), id);
		TextField<String> tf = new TextField<String>(id, model);
		tf.setLabel(new ResourceModel("name." + id));
		add(tf).add(new SimpleFormComponentLabel(id + "Label", tf));
		return this;
	}

}
