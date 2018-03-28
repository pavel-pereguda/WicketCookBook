package cookbook;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.LoadableDetachableModel;

public class LocaleSwitcher extends DropDownChoice<Locale> {

	public LocaleSwitcher(String id) {
		super(id);
		setModel(new Current());
		setChoices(new Available());
		setChoiceRenderer(new Renderer());
	}

	private static class Renderer implements IChoiceRenderer<Locale> {

		public Object getDisplayValue(Locale object) {
			return object.getDisplayName();
		}

		public String getIdValue(Locale object, int index) {
			return object.toString();
		}

	}

	private static class Current extends LoadableDetachableModel<Locale> {
		@Override
		protected Locale load() {
			return Session.get().getLocale();
		}
	}

	private static class Available extends
			LoadableDetachableModel<List<Locale>> {
		@Override
		protected List<Locale> load() {
			List<Locale> list = Arrays.asList(Locale.getAvailableLocales());
			Collections.sort(list, new Comparator<Locale>() {

				public int compare(Locale o1, Locale o2) {
					return o1.getDisplayName().compareTo(o2.getDisplayName());
				}

			});
			return list;
		}

	}

	@Override
	protected boolean wantOnSelectionChangedNotifications() {
		return true;
	}

	@Override
	protected void onSelectionChanged(Locale newSelection) {
		getSession().setLocale(newSelection);
	}

}
