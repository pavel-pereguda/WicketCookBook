package cookbook;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends WebPage {

	@SpringBean
	Entries entries;

	private String text;

	public HomePage(final PageParameters parameters) {
		List<IColumn<Entry>> cols = new ArrayList<IColumn<Entry>>();
		cols.add(new PropertyColumn<Entry>(Model.of("Created"), "created",
				"created"));
		cols.add(new PropertyColumn<Entry>(Model.of("Message"), "text", "text"));
		add(new DefaultDataTable<Entry>("entries", cols, new EntriesProvider(
				entries), 5));

		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				Entry entry = new Entry();
				entry.setText(text);
				entries.save(entry);
				text = null;
			}
		};
		add(form);
		form.add(new TextArea<String>("text", new PropertyModel<String>(this,
				"text")));
	}
}
