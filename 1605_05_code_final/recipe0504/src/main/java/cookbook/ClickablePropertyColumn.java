package cookbook;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public abstract class ClickablePropertyColumn<T> extends AbstractColumn<T> {

	private final String property;

	public ClickablePropertyColumn(IModel<String> displayModel, String property) {
		this(displayModel, property, null);
	}

	public ClickablePropertyColumn(IModel<String> displayModel,
			String property, String sort) {
		super(displayModel, sort);
		this.property = property;
	}

	public void populateItem(Item<ICellPopulator<T>> cellItem,
			String componentId, IModel<T> rowModel) {
		cellItem.add(new LinkPanel(componentId, rowModel,
				new PropertyModel<Object>(rowModel, property)));
	}

	protected abstract void onClick(IModel<T> clicked);

	private class LinkPanel extends Panel {

		public LinkPanel(String id, IModel<T> rowModel, IModel<?> labelModel) {
			super(id);
			Link<T> link = new Link<T>("link", rowModel) {

				@Override
				public void onClick() {
					ClickablePropertyColumn.this.onClick(getModel());
				}

			};
			add(link);
			link.add(new Label("label", labelModel));
		}

	}

}
