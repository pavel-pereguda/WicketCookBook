package cookbook;


import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

import cookbook.chart.Chart;


public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		add(new Chart("chart1", Model.of("GOOG")));
		add(new Chart("chart2", Model.of("MSFT")));
		add(new Chart("chart3", Model.of("AAPL")));
	}
}
