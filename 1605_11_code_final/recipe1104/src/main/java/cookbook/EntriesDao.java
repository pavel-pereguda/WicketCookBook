package cookbook;

import java.util.List;

public interface EntriesDao {
	List<Entry> list(final int offset, final int count);
	int count();
	void save(Entry entry);
	Entry load(Long id);
}