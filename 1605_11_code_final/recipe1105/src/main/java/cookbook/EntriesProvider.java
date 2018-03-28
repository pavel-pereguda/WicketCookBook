package cookbook;

import java.util.Iterator;

class EntriesProvider extends EntityProvider<Entry> {
	private final Entries entries;

	public EntriesProvider(Entries entries) {
		this.entries = entries;
	}

	public Iterator<Entry> iterator(int first, int count) {
		return entries.list(first, count).iterator();
	}

	public int size() {
		return entries.count();
	}

}