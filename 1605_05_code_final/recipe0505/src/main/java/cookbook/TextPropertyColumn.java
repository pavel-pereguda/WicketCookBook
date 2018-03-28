package cookbook;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class TextPropertyColumn<ROW, PROP> extends AbstractColumn<ROW> {

	private final String property;

	public TextPropertyColumn(IModel<String> displayModel, String property) {
		this(displayModel, property, null);
	}

	public TextPropertyColumn(IModel<String> displayModel, String property,
			String sort) {
		super(displayModel, sort);
		this.property = property;
	}

	public void populateItem(Item<ICellPopulator<ROW>> cellItem,
			String componentId, IModel<ROW> rowModel) {
		cellItem.add(new TextPanel(componentId, new PropertyModel<PROP>(
				rowModel, property)));
	}

	protected TextField<PROP> newTextField(String id, IModel<PROP> model) {
		return new TextField<PROP>(id, model);
	}

	private class TextPanel extends Panel {

		public TextPanel(String id, IModel<PROP> model) {
			super(id);
			add(newTextField("field", model));
		}
	}

}
