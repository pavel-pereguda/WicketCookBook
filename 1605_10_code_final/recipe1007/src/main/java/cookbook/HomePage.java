package cookbook;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {

	private int counter = 0;

	public HomePage() {

		add(new Label("counter", new PropertyModel<Integer>(this, "counter")));

		add(new Link<Void>("increment") {
			public void onClick() {
				counter++;
			}
		});
	}
}
