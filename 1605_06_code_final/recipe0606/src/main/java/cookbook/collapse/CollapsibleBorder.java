package cookbook.collapse;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CollapsibleBorder extends Border {

	public CollapsibleBorder(String id, IModel<String> collapse,
			IModel<String> expand) {
		super(id, new Model<Boolean>());
		setCollapsed(false);

		final WebMarkupContainer body = new WebMarkupContainer("body") {
			@Override
			protected void onConfigure() {
				setVisible(!isCollapsed());
			}
		};
		body.setOutputMarkupPlaceholderTag(true);
		
		add(body);
		body.add(getBodyContainer());

		AjaxLink toggle = new AjaxLink("toggle") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				setCollapsed(!isCollapsed());
				target.addComponent(body);
				target.addComponent(this);
			}
		};
		add(toggle);

		toggle.add(new Label("caption", new ExpandCollapseModel(collapse,
				expand)));
	}

	public void setCollapsed(boolean collapsed) {
		setDefaultModelObject(collapsed);
	}

	public boolean isCollapsed() {
		return Boolean.TRUE.equals(getDefaultModelObject());
	}

	private class ExpandCollapseModel extends AbstractReadOnlyModel<String> {
		private final IModel<String> collapse;
		private final IModel<String> expand;

		public ExpandCollapseModel(IModel<String> collapse,
				IModel<String> expand) {
			this.collapse = collapse;
			this.expand = expand;
		}

		@Override
		public String getObject() {
			if (isCollapsed()) {
				return expand.getObject();
			} else {
				return collapse.getObject();
			}
		}

		@Override
		public void detach() {
			collapse.detach();
			expand.detach();
		}

	}
}
