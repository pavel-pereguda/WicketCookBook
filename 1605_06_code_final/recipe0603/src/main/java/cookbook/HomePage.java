package cookbook;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {

		add(new FeedbackPanel("feedback") {
			@Override
			protected void onConfigure() {
				setVisible(anyMessage(FeedbackMessage.ERROR));
			}
		});
		
		Form<?> form = new Form<Void>("form");
		add(form);
		List<ITab> tabs = new ArrayList<ITab>();

		tabs.add(new AbstractTab(new Model("User Information")) {
			public Panel getPanel(String panelId) {
				return new UserPanel(panelId);
			}
		});

		tabs.add(new AbstractTab(new Model("Marketing Information")) {

			public Panel getPanel(String panelId) {
				return new MarketingPanel(panelId);
			}
		});

		form.add(new FormTabbedPanel("tabs", tabs));
	}
}
