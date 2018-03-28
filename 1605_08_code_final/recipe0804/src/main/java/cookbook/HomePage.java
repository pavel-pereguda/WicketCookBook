package cookbook;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import cookbook.chart.cleaner.Chart;

public class HomePage extends WebPage {

	public HomePage() {
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);

		add(new Chart("chart", Model.of("GOOG")) {
			@Override
			protected void onClick(int x, AjaxRequestTarget target) {
				info("Clicked at x=" + x);
				target.addComponent(feedback);
			}
		});
	}
}
