package cookbook;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public abstract class AbstractTabWithCount implements ITab {
	private final IModel<String> title;

	public AbstractTabWithCount(IModel<String> title) {
		this.title = new TitleModel(title);
	}

	public IModel<String> getTitle() {
		return title;
	}

	public boolean isVisible() {
		return true;
	}

	protected abstract int getCount();

	private class TitleModel extends LoadableDetachableModel<String> {
		private final IModel<String> delegate;

		public TitleModel(IModel<String> delegate) {
			this.delegate = delegate;
		}

		@Override
		protected String load() {
			return delegate.getObject() + " (" + getCount() + ")";
		}

		@Override
		public void detach() {
			super.detach();
			delegate.detach();
		}
	}

}
