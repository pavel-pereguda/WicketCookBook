package cookbook;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class MarketingPanel extends Panel {

	public MarketingPanel(String id) {
		super(id);

		add(new CheckBox("company", Model.of(true)));
		add(new CheckBox("partners", Model.of(true)));
	}

}
