package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends WebPage {

	@SpringBean
	EntriesDao entries;

	private String text;

	public HomePage(final PageParameters parameters) {
		DataView<Entry> view = new DataView<Entry>("entries",
				new EntriesProvider(entries)) {
			@Override
			protected void populateItem(Item<Entry> item) {
				item.add(new Label("text", new PropertyModel<String>(item
						.getModel(), "text")));
				item.add(new Label("created", new PropertyModel<String>(item
						.getModel(), "created")));
			}
		};
		view.setItemsPerPage(5);
		add(view);
		add(new PagingNavigator("pager", view));

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
