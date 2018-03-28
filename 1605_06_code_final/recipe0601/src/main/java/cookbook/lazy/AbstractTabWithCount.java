package cookbook.lazy;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.model.IModel;

public abstract class AbstractTabWithCount extends AbstractTab {

	public AbstractTabWithCount(IModel<String> title) {
		super(title);
	}

	protected abstract int getCount();

}
