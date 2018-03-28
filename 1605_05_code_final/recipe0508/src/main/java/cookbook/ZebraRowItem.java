package cookbook;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public class ZebraRowItem<T> extends Item<T> {

	public ZebraRowItem(String id, int index, IModel<T> model) {
		super(id, index, model);
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		if ((getIndex() + 1) % 2 == 0) {
			tag.append("class", "zebra", " ");
		}
	}
}
