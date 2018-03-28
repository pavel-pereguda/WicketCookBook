package cookbook;

import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage
{
	private Country country;
	private City city;

	public HomePage(final PageParameters parameters)
	{
		country = Database.getCountries().get(0);

		add(new FeedbackPanel("feedback"));

		Form<?> form = new Form<Void>("form")
		{
			@Override
			protected void onSubmit()
			{
				info("Your favorite city is: " + city.getName() + " in " + country.getName());
			}
		};
		add(form);

		DropDownChoice<Country> countries = new DropDownChoice<Country>("countries",
			new PropertyModel<Country>(this, "country"), new CountriesModel(),
			new ChoiceRenderer<Country>("name", "code"))
		{
			@Override
			protected boolean wantOnSelectionChangedNotifications()
			{
				return true;
			}

			@Override
			protected void onSelectionChanged(Country newSelection)
			{
				city = null;
			}
		};
		countries.setRequired(true);
		form.add(countries);

		DropDownChoice<City> cities = new DropDownChoice<City>("cities", new PropertyModel<City>(
			this, "city"), new CitiesModel(), new ChoiceRenderer<City>("name", "code"));
		cities.setRequired(true);
		form.add(cities);
	}

	private static class CountriesModel extends LoadableDetachableModel<List<? extends Country>>
	{
		@Override
		protected List<? extends Country> load()
		{
			return Database.getCountries();
		}
	}

	private class CitiesModel extends LoadableDetachableModel<List<? extends City>>
	{
		@Override
		protected List<? extends City> load()
		{
			return Database.getCities(country.getCode());
		}
	}
}
