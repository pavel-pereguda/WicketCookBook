package cookbook;

import java.util.List;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.SubmitLink;

public class FormTabbedPanel extends TabbedPanel {
	public FormTabbedPanel(String id, List<ITab> tabs) {
		super(id, tabs);
	}

	@Override
	protected WebMarkupContainer newLink(String linkId, final int index) {
		return new SubmitLink(linkId) {
			@Override
			public void onSubmit() {
				setSelectedTab(index);
			}
		};
	}

}