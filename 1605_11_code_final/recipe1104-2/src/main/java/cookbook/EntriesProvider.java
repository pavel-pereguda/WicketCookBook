package cookbook;

import java.util.Iterator;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

class EntriesProvider extends EntityProvider<Entry> {
	private final Entries entries;

	public EntriesProvider(Entries entries) {
		this.entries = entries;
	}

	@Override
	protected Iterator<Entry> iterator(QueryParam param) {
		return entries.list(param).iterator();
	}

	public int size() {
		return entries.count();
	}

	public IModel<Entry> model(Entry object) {
		final Long id = object.getId();
		return new LoadableDetachableModel<Entry>() {
			@Override
			protected Entry load() {
				return entries.load(id);
			}
		};
	}

	public void detach() {
	}

}