package cookbook.enhanced;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class PortletBorder extends Border {

	public PortletBorder(String id, final String portletId, IModel<String> title) {
		super(id, Model.of(portletId));
		add(new Label("title", title));

		add(new MoveLink("up", -1));
		add(new MoveLink("down", 1));
		add(new SlideLink("left", -1));
		add(new SlideLink("right", 1));
	}

	private PortalContainer getPortalContainer() {
		return findParent(PortalContainer.class);
	}

	public String getPortletId() {
		return getDefaultModelObjectAsString();
	}

	public class MoveLink extends Link<Void> {
		private final int delta;

		public MoveLink(String id, int delta) {
			super(id);
			this.delta = delta;
		}

		public void onClick() {
			getPortalContainer().move(getPortletId(), delta);
		}

		protected void onConfigure() {
			super.onConfigure();
			setEnabled(getPortalContainer().getLayout().canMove(getPortletId(),
					delta));
		}

	}

	public class SlideLink extends Link<Void> {
		private final int delta;

		public SlideLink(String id, int delta) {
			super(id);
			this.delta = delta;
		}

		public void onClick() {
			getPortalContainer().slide(getPortletId(), delta);
		}

		protected void onConfigure() {
			super.onConfigure();
			setEnabled(getPortalContainer().getLayout().canSlide(
					getPortletId(), delta));
		}

	}

}
