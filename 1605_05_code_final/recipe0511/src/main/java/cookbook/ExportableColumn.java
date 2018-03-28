package cookbook;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;

public interface ExportableColumn<T> extends IColumn<T> {
	void exportCsv(T object, CsvWriter writer);
}
