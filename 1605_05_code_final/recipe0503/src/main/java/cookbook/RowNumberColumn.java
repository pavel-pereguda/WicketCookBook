package cookbook;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class RowNumberColumn<T> extends AbstractColumn<T> {

	public RowNumberColumn(IModel<String> displayModel) {
		super(displayModel);
	}

	public void populateItem(Item<ICellPopulator<T>> cellItem,
			String componentId, IModel<T> rowModel) {
		cellItem.add(new Label(componentId, new RowNumberModel(cellItem)));
	}

	private static class RowNumberModel extends AbstractReadOnlyModel<Integer> {
		private final Item<?> cellItem;

		public RowNumberModel(Item<?> cellItem) {
			this.cellItem = cellItem;
		}

		@Override
		public Integer getObject() {
			return cellItem.findParent(Item.class).getIndex()+1;
		}

	}

}
