package cookbook.lazy;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.model.IModel;

public class LazyCountTabbedPanel extends TabbedPanel {

	public LazyCountTabbedPanel(String id, List<ITab> tabs) {
		super(id, tabs);
	}

	@Override
	protected Component newTitle(String titleId, IModel<?> titleModel, int index) {
		if (getTabs().get(index) instanceof AbstractTabWithCount) {
			return new LazyCountLabel(titleId, titleModel, index);
		} else {
			return super.newTitle(titleId, titleModel, index);
		}
	}

}
