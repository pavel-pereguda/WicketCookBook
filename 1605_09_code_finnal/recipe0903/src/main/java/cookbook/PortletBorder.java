package cookbook;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class PortletBorder extends Border {

	public PortletBorder(String id, final String portletId, IModel<String> title) {
		super(id, Model.of(portletId));
		add(new Label("title", title));

		add(new Link("up") {
			public void onClick() {
				getPortalContainer().move(portletId, -1);
			}
		});
		add(new Link("down") {
			public void onClick() {
				getPortalContainer().move(portletId, 1);
			}
		});
		add(new Link("left") {
			public void onClick() {
				getPortalContainer().slide(portletId, -1);
			}
		});
		add(new Link("right") {
			public void onClick() {
				getPortalContainer().slide(portletId, 1);
			}
		});
	}

	private PortalContainer getPortalContainer() {
		return findParent(PortalContainer.class);
	}

	public String getPortletId() {
		return getDefaultModelObjectAsString();
	}

}
