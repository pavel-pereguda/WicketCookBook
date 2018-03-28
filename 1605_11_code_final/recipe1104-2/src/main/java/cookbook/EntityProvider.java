package cookbook;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;

import cookbook.QueryParam.SortDir;

public abstract class EntityProvider<T> extends SortableDataProvider<T> {

	public Iterator<T> iterator(int first, int count) {
		SortParam sp = getSort();
		final List<QueryParam.SortParam> sort;
		if (sp == null) {
			sort = Collections.emptyList();
		} else {
			sort = Collections.singletonList(new QueryParam.SortParam(sp
					.getProperty(), sp.isAscending() ? SortDir.ASC
					: SortDir.DESC));
		}
		return iterator(new QueryParam(first, count, sort));
	}

	protected abstract Iterator<T> iterator(QueryParam param);

}
