package cookbook;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AbortException;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.http.WebResponse;

public class CsvExportLink<T> extends Link<Void> {
	private final DataTable<T> table;

	public CsvExportLink(String id, DataTable<T> table) {
		super(id);
		this.table = table;
	}

	@Override
	public void onClick() {
		WebResponse response = (WebResponse) getResponse();

		response.setAttachmentHeader("export.csv");
		response.setContentType("text/csv");

		OutputStream out = getResponse().getOutputStream();
		CsvWriter writer = new CsvWriter(out);

		List<ExportableColumn<T>> exportable = getExportableColumns();

		Pager pager = new Pager(100, table.getDataProvider().size());
		for (int i = 0; i < pager.pages(); i++) {

			Iterator<? extends T> it = table.getDataProvider().iterator(
					pager.offset(i), pager.count(i));

			while (it.hasNext()) {
				T object = it.next();
				for (ExportableColumn<T> col : exportable) {
					col.exportCsv(object, writer);
				}
				writer.endLine();
			}

		}

		writer.close();

		throw new AbortException();
	}

	private List<ExportableColumn<T>> getExportableColumns() {
		List<ExportableColumn<T>> exportable = new ArrayList<ExportableColumn<T>>(
				table.getColumns().length);
		for (IColumn<?> column : table.getColumns()) {
			if (column instanceof ExportableColumn<?>) {
				exportable.add((ExportableColumn<T>) column);
			}
		}
		return exportable;
	}
}
