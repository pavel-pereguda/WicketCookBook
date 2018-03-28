package cookbook.panelized;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {

	private Map<String, Object> data = new HashMap<String, Object>();

	public HomePage(final PageParameters parameters) {
		add(new FeedbackPanel("feedback"));

		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				info(data.toString());
			}
		};
		add(form);

		String def = "Name:string:name,Age:integer:age,Favorite Color:choice:favcolor:red-blue-green";

		form.add(new DynaForm("dynaform",
				new PropertyModel<Map<String, Object>>(this, "data"), Model
						.of(def)));

	}
}
