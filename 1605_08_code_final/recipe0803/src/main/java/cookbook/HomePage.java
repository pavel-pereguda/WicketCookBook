package cookbook;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import cookbook.chart.multi.Chart;

public class HomePage extends WebPage {

	public HomePage() {
		add(new FeedbackPanel("feedback"));

		add(new Chart("chart") {
			@Override
			protected void onClick(int chart, int x) {
				info("Clicked graph " + chart + " at x=" + x);
			}
		});
	}
}
