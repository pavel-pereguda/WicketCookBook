package cookbook;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

class EntriesProvider implements IDataProvider<Entry> {
	private final EntriesDao entries;

	public EntriesProvider(EntriesDao entries) {
		this.entries = entries;
	}

	public Iterator<Entry> iterator(int first, int count) {
		return entries.list(first, count).iterator();
	}

	public int size() {
		return entries.count();
	}

	public IModel<Entry> model(Entry object) {
		final Long id = object.getId();
		return new LoadableDetachableModel<Entry>(object) {
			@Override
			protected Entry load() {
				return entries.load(id);
			}
		};
	}

	public void detach() {
	}

}