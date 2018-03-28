package cookbook;

import java.util.List;

public interface Entries {
	List<Entry> list(QueryParam param);
	int count();
	void save(Entry entry);
	Entry load(Long id);
}