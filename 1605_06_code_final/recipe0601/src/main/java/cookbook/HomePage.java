package cookbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import cookbook.lazy.AbstractTabWithCount;
import cookbook.lazy.LazyCountTabbedPanel;
/**
 * Homepage
 */
public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {

		List<ITab> tabs = new ArrayList<ITab>();

		tabs.add(new AbstractTabWithCount(new Model("Pending")) {

			public Panel getPanel(String panelId) {
				return new PendingOrdersPanel(panelId);
			}

		
			protected int getCount() {
				return new Random().nextInt(10);
			}
		});

		tabs.add(new AbstractTabWithCount(new Model("Processed")) {
			public Panel getPanel(String panelId) {
				return new ProcessedOrdersPanel(panelId);
			}

		
			protected int getCount() {
				return new Random().nextInt(10);
			}
		});

		tabs.add(new AbstractTabWithCount(new Model("Failed")) {
			public Panel getPanel(String panelId) {
				return new FailedOrdersPanel(panelId);
			}

		
			protected int getCount() {
				return new Random().nextInt(10);
			}

		});

		add(new LazyCountTabbedPanel("tabs", tabs));
	}
}
