package cookbook;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.Component.IVisitor;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget.IJavascriptResponse;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class AjaxFeedbackUpdater implements AjaxRequestTarget.IListener {

	public void onBeforeRespond(Map<String, Component> map,
			final AjaxRequestTarget target) {

		target.getPage().visitChildren(FeedbackPanel.class,
				new IVisitor<FeedbackPanel>() {
					public Object component(FeedbackPanel component) {
						if (component.getOutputMarkupId()) {
							target.addComponent(component);
						}
						return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
					}
				});
	}

	public void onAfterRespond(Map<String, Component> map,
			IJavascriptResponse response) {
	}
}
