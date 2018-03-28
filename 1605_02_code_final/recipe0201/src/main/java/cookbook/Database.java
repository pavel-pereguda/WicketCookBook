package cookbook;

import java.util.ArrayList;
import java.util.List;

public class Database
{
	private static final String COUNTRIES = "USA,United States of America,D,Germany,F,France,GB,Great Britain";
	private static final String CITIES = "USA,NY,New York,USA,CL,Cleveland,USA,AU,Austin,USA,LA,Los Angeles,D,BE,Berlin,D,BO,Bonn,F,PA,Paris,F,NI,Nice,GB,LO,London,GB,GL,Glasgow,GB,LI,Liverpool";

	public static List<Country> getCountries()
	{
		List<Country> countries = new ArrayList<Country>();

		String[] parts = COUNTRIES.split(",");
		for (int i = 0; i < parts.length / 2; i++)
		{
			Country country = new Country(parts[i * 2], parts[i * 2 + 1]);
			countries.add(country);
		}
		return countries;
	}

	public static List<City> getCities(String countryCode)
	{
		List<City> cities = new ArrayList<City>();
		String[] parts = CITIES.split(",");
		for (int i = 0; i < parts.length / 3; i++)
		{
			String code= parts[i * 3];
			if (code.equals(countryCode))
			{
				City city = new City(parts[i * 3 + 1], parts[i * 3 + 2]);
				cities.add(city);
			}
		}
		return cities;
	}
}
