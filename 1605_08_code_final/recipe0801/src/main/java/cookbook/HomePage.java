package cookbook;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

import cookbook.chart.Chart;

public class HomePage extends WebPage {

	public HomePage() {
		add(new Chart("chart", Model.of("GOOG")));
	}
}
