package cookbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static List<Contact> contacts = Arrays.asList(
			new Contact("Homer Simpson", "homer@fox.com", "555-1211"),
			new Contact("Charles Montgomery Burns", "cmb@fox.com", "555-5322"),
			new Contact("Ned Flanders", "green@fox.com", "555-9732") );

	public HomePage(final PageParameters parameters) {
		List<IColumn<Contact>> columns = new ArrayList<IColumn<Contact>>();
		columns.add(new PropertyColumn<Contact>(Model.of("Name"), "name",
				"name"));
		columns.add(new PropertyColumn<Contact>(Model.of("Email"), "email",
				"email"));
		columns.add(new PropertyColumn<Contact>(Model.of("Phone"), "phone"));

		add(new DefaultDataTable<Contact>("contacts", columns,
				new ContactsProvider(), 10));
	}

	private static class ContactsProvider extends SortableDataProvider<Contact> {
		public ContactsProvider() {
			setSort("name", true);
		}

		public Iterator<? extends Contact> iterator(int first, int count) {
			List<Contact> data = new ArrayList<Contact>(contacts);
			Collections.sort(data, new Comparator<Contact>() {

				public int compare(Contact o1, Contact o2) {
					int dir = getSort().isAscending() ? 1 : -1;

					if ("name".equals(getSort().getProperty())) {
						return dir * (o1.name.compareTo(o2.name));
					} else {
						return dir * (o1.email.compareTo(o2.email));
					}
				}
			});
			return data.subList(first, Math.min(first + count, data.size()))
					.iterator();
		}

		public int size() {
			return contacts.size();
		}

		public IModel<Contact> model(Contact object) {
			return Model.of(object);
		}

	}
}
