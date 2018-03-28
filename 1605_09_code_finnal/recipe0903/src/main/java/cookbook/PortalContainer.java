package cookbook;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

public abstract class PortalContainer extends Panel {

	private RepeatingView cols;

	public PortalContainer(String id, IModel<PortalLayout> model) {
		super(id, model);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		PortalLayout layout = getLayout();

		cols = new RepeatingView("column");
		add(cols);

		for (int i = 0; i < layout.getColumnCount(); i++) {
			WebMarkupContainer item = new WebMarkupContainer("" + i);
			RepeatingView col = new PortletRepeatingView("portlet");
			cols.add(item.add(col));

			for (String portletId : layout.getColumn(i)) {
				Portlet portlet = getPortlet(portletId);

				PortletBorder border = new PortletBorder(cols.newChildId(),
						portletId, portlet.getTitle());

				col.add(border);

				border.getBodyContainer().add(
						portlet.newContentComponent("content"));
			}

		}
	}

	protected abstract Portlet getPortlet(String portletId);

	protected PortalLayout getLayout() {
		return (PortalLayout) getDefaultModelObject();
	}

	private RepeatingView getColumn(int i) {
		return (RepeatingView) ((WebMarkupContainer) cols.get("" + i))
				.get("portlet");
	}

	public void move(String portletId, int delta) {
		getLayout().move(portletId, delta);
	}

	public void slide(final String portletId, int delta) {
		getLayout().slide(portletId, delta);
		PortletBorder border = findPortletBorder(portletId);
		border.getParent().remove(border);
		getColumn(getLayout().getPortletColumn(portletId)).add(border);
	}

	private PortletBorder findPortletBorder(final String portletId) {
		return (PortletBorder) cols.visitChildren(PortletBorder.class,
				new IVisitor<PortletBorder>() {
					public Object component(PortletBorder component) {
						if (component.getPortletId().equals(portletId)) {
							return component;
						}
						return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
					}
				});
	}

	private final class PortletRepeatingView extends RepeatingView {
		private PortletRepeatingView(String id) {
			super(id);
		}

		@Override
		protected Iterator<? extends Component> renderIterator() {
			return iterator(new PortletRenderOrderComparator());
		}
	}

	private class PortletRenderOrderComparator implements
			Comparator<Component>, Serializable {

		public int compare(Component o1, Component o2) {
			String lhs = ((PortletBorder) o1).getPortletId();
			String rhs = ((PortletBorder) o2).getPortletId();
			return getLayout().getPortletPosition(lhs)
					- getLayout().getPortletPosition(rhs);
		}

	}

}
