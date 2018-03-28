package cookbook;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.ILinkListener;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public abstract class ClickableRowItem<T> extends Item<T> implements
		ILinkListener {

	public ClickableRowItem(String id, int index, IModel<T> model) {
		super(id, index, model);
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		tag.put("onclick", "window.location='"
				+ urlFor(ILinkListener.INTERFACE) + "'");
		tag.put("class", "clickable");
	}

	public final void onLinkClicked() {
		onClick();
	}

	protected abstract void onClick();
}
