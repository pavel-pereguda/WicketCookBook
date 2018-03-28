package cookbook;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AbortException;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.http.WebResponse;

public class ExportToolbar<T> extends AbstractToolbar {

	public ExportToolbar(final DataTable<T> table) {
		super(table);

		WebMarkupContainer span = new WebMarkupContainer("span") {
			@Override
			protected void onComponentTag(ComponentTag tag) {
				tag.put("colspan", table.getColumns().length);
			}
		};
		add(span);

		span.add(new CsvExportLink<T>("csv", table));
	}
	
}
