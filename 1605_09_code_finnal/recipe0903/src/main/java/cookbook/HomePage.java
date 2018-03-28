package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {

		PortalLayout layout = new PortalLayout(2);
		layout.add(0, "1");
		layout.add(0, "2");
		layout.add(1, "3");

		add(new PortalContainer("container", Model.of(layout)) {
			@Override
			protected Portlet getPortlet(String portletId) {
				if ("1".equals(portletId)) {
					return new Portlet() {
						public IModel<String> getTitle() {
							return Model.of("Top Sellers This Week");
						}

						public Component newContentComponent(String id) {
							return new TopSellersPanel(id);
						}
					};
				} else if ("2".equals(portletId)) {
					return new Portlet() {
						public IModel<String> getTitle() {
							return Model.of("Inventory Warnings");
						}

						public Component newContentComponent(String id) {
							return new InventoryWarningsPanel(id);
						}
					};
				} else {
					return new Portlet() {
						public IModel<String> getTitle() {
							return Model.of("Recent Orders");
						}

						public Component newContentComponent(String id) {
							return new RecentOrdersPanel(id);
						}
					};
				}
			}
		});

	}
}
