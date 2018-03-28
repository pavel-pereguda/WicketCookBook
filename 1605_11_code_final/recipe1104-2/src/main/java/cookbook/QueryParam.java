package cookbook;

import java.util.Collections;
import java.util.List;

public class QueryParam {
	private final int offset;
	private final int count;
	private final List<SortParam> sort;

	public QueryParam(int offset, int count, List<SortParam> sort) {
		this.offset = offset;
		this.count = count;
		this.sort = sort;
	}

	public int getOffset() {
		return offset;
	}

	public int getCount() {
		return count;
	}

	public List<SortParam> getSort() {
		if (sort == null) {
			return Collections.emptyList();
		} else {
			return Collections.unmodifiableList(sort);
		}
	}

	public static enum SortDir {
		ASC, DESC
	}

	public static class SortParam {
		private final String data;
		private final SortDir dir;

		public SortParam(String data, SortDir dir) {
			this.data = data;
			this.dir = dir;
		}

		public String getData() {
			return data;
		}

		public SortDir getDir() {
			return dir;
		}
	}
}
