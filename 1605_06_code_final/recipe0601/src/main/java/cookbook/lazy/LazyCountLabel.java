package cookbook.lazy;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.time.Duration;

public class LazyCountLabel extends Label {

	private final IModel<?> title;

	private String count;

	public LazyCountLabel(String id, IModel<?> model, final int index) {
		super(id);
		this.title = model;

		setDefaultModel(new LoadableDetachableModel<String>() {

			@Override
			protected String load() {
				if (count == null) {
					return "" + title.getObject();
				} else {
					return title.getObject() + " (" + count + ")";
				}
			}
		});

		setOutputMarkupId(true);

		add(new AbstractAjaxTimerBehavior(Duration.milliseconds(100)) {
			@Override
			protected void onTimer(AjaxRequestTarget target) {
				TabbedPanel panel = findParent(TabbedPanel.class);
				ITab tab = panel.getTabs().get(index);
				if (tab instanceof AbstractTabWithCount) {
					count = "" + ((AbstractTabWithCount) tab).getCount();
				}
				target.addComponent(LazyCountLabel.this);
				stop();
			}
		});
	}

	@Override
	protected void onDetach() {
		title.detach();
		super.onDetach();
	}

}
