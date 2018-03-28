package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public interface Portlet {
	IModel<String> getTitle();
	Component newContentComponent(String id);
}
