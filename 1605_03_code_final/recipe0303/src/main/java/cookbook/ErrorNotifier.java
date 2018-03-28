package cookbook;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.Panel;

public class ErrorNotifier extends Panel {

	public ErrorNotifier(String id) {
		super(id);
	}

	@Override
	public boolean isVisible() {
		for (FeedbackMessage message : getSession().getFeedbackMessages()) {
			if (message.isError()) {
				return true;
			}
		}
		return false;
	}

}
