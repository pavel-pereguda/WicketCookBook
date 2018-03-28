package cookbook;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;

public class AjaxTabbedPanel extends TabbedPanel {
	public AjaxTabbedPanel(String id, List<ITab> tabs) {
		super(id, tabs);
		setOutputMarkupId(true);
	}

	@Override
	protected WebMarkupContainer newLink(String linkId, final int index) {
		return new AjaxLink(linkId) {
			@Override
			public void onClick(AjaxRequestTarget target) {
				setSelectedTab(index);
				target.addComponent(AjaxTabbedPanel.this);
			}

		};
	}

}