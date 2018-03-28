package cookbook.enhanced;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortalLayout implements Serializable {
	private List<List<String>> columns = new ArrayList<List<String>>();

	public PortalLayout(int columnCount) {
		for (int i = 0; i < columnCount; i++) {
			List<String> column = new ArrayList<String>();
			columns.add(column);
		}
	}

	public int getColumnCount() {
		return columns.size();
	}

	public List<String> getColumn(int index) {
		return columns.get(index);
	}

	public void add(int column, String id) {
		getColumn(column).add(id);
	}

	public int getPortletPosition(String id) {
		for (List<String> column : columns) {
			int position = column.indexOf(id);
			if (position >= 0) {
				return position;
			}
		}
		return -1;
	}

	public int getPortletColumn(String id) {
		for (int i = 0; i < columns.size(); i++) {
			if (columns.get(i).contains(id)) {
				return i;
			}
		}
		return -1;
	}

	public void move(String id, int delta) {
		List<String> column = columns.get(getPortletColumn(id));
		int pos = column.indexOf(id);
		Collections.swap(column, pos, pos + delta);
	}

	public boolean canMove(String id, int delta) {
		List<String> column = columns.get(getPortletColumn(id));
		int pos = column.indexOf(id);
		int moved = pos + delta;
		return moved >= 0 && moved < column.size();
	}

	public void slide(String id, int delta) {
		int col = getPortletColumn(id);
		columns.get(col).remove(id);
		columns.get(col + delta).add(id);
	}

	public boolean canSlide(String id, int delta) {
		int col = getPortletColumn(id);
		int newCol = col + delta;
		return newCol >= 0 && newCol < columns.size();
	}

}
