package cookbook;

import java.util.Date;
import java.util.HashMap;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.StringResourceModel;

public class HomePage extends WebPage {

	private int counter;
	private Date lastClick;

	public HomePage(final PageParameters parameters) {

		add(new Label("count", new StringResourceModel("count",
				new LoadableDetachableModel() {
					@Override
					protected Object load() {
						HashMap map = new HashMap();
						map.put("count", counter);
						map.put("timestamp", (lastClick == null) ? "?"
								: lastClick);
						return map;
					}
				})));

		add(new Link<Void>("increment") {

			@Override
			public void onClick() {
				counter++;
				lastClick = new Date();
			}

		});
	}
}
