package cookbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.Component.IVisitor;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget.IJavascriptResponse;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class AjaxFeedbackUpdater implements AjaxRequestTarget.IListener {

	private final Component component;

	public AjaxFeedbackUpdater(Component component) {
		this.component = component;
	}

	public void onBeforeRespond(Map<String, Component> map,
			AjaxRequestTarget target) {

		final List<FeedbackPanel> feedbacks = new ArrayList<FeedbackPanel>();

		target.getPage().visitChildren(FeedbackPanel.class,
				new IVisitor<FeedbackPanel>() {
					public Object component(FeedbackPanel component) {
						if (component.getOutputMarkupId()) {
							feedbacks.add(component);
						}
						return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
					}
				});

		FeedbackMessage scope = new FeedbackMessage(component, "-",
				FeedbackMessage.ERROR);

		for (FeedbackPanel feedback : feedbacks) {
			if (feedback.anyErrorMessage()) {
				target.addComponent(feedback);
			} else if (feedback.getFilter() != null
					&& feedback.getFilter().accept(scope)) {
				target.addComponent(feedback);
			}
		}

	}

	public void onAfterRespond(Map<String, Component> map,
			IJavascriptResponse response) {
	}
}
