package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {

	private int counter = 0;

	public HomePage(final PageParameters parameters) {

		final Component label = new Label("counter",
				new PropertyModel<Integer>(this, "counter"))
				.setOutputMarkupId(true);
		add(label);

		add(new IndicatingAjaxLink<Void>("buy") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				counter++;
				target.addComponent(label);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new BlockUIDecorator();
			}

		});
	}
}
