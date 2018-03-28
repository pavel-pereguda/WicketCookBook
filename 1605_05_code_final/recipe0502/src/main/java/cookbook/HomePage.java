package cookbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static List<Contact> contacts = Arrays.asList(new Contact[] {
			new Contact("Homer Simpson", "homer@fox.com", "555-1211"),
			new Contact("Charles Montgomery Burns", "cmb@fox.com", "555-5322"),
			new Contact("Ned Flanders", "green@fox.com", "555-9732") });

	private String filter;

	public HomePage(final PageParameters parameters) {
		List<IColumn<Contact>> columns = new ArrayList<IColumn<Contact>>();
		columns.add(new PropertyColumn<Contact>(Model.of("Name"), "name"));
		columns.add(new PropertyColumn<Contact>(Model.of("Email"), "email"));
		columns.add(new PropertyColumn<Contact>(Model.of("Phone"), "phone"));

		Form<?> form = new Form<Void>("form");
		add(form);
		form.add(new TextField<String>("filter", new PropertyModel<String>(
				this, "filter")));

		add(new DefaultDataTable<Contact>("contacts", columns,
				new ContactsProvider(), 10));
	}

	private class ContactsProvider extends SortableDataProvider<Contact> {
		private transient List<Contact> filtered;

		private List<Contact> getFiltered() {
			if (filtered == null) {
				filtered = filter();
			}
			return filtered;
		}

		private List<Contact> filter() {
			List<Contact> filtered = new ArrayList<Contact>(contacts);
			if (filter != null) {
				String upper = filter.toUpperCase();

				Iterator<Contact> it = filtered.iterator();

				while (it.hasNext()) {
					Contact contact = it.next();
					if (contact.name.toUpperCase().indexOf(upper) < 0
							&& contact.email.toUpperCase().indexOf(upper) < 0) {
						it.remove();
					}
				}
			}
			return filtered;
		}

		@Override
		public void detach() {
			filtered = null;
			super.detach();
		}

		public Iterator<? extends Contact> iterator(int first, int count) {
			return getFiltered().subList(first,
					Math.min(first + count, getFiltered().size())).iterator();
		}

		public int size() {
			return getFiltered().size();
		}

		public IModel<Contact> model(Contact object) {
			return Model.of(object);
		}

	}

}
