package cookbook;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {

		List<ITab> tabs = new ArrayList<ITab>();

		tabs.add(new AbstractTab(new Model("Pending")) {

			public Panel getPanel(String panelId) {
				return new PendingOrdersPanel(panelId);
			}
		});

		tabs.add(new AbstractTab(new Model("Processed")) {
			public Panel getPanel(String panelId) {
				return new ProcessedOrdersPanel(panelId);
			}
		});

		tabs.add(new AbstractTab(new Model("Failed")) {
			public Panel getPanel(String panelId) {
				return new FailedOrdersPanel(panelId);
			}
		});

		add(new AjaxTabbedPanel("tabs", tabs));
	}
}
