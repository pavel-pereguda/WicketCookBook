package cookbook.forms;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.RequestUtils;

public class HttpsForm<T> extends Form<T> {

	public HttpsForm(String id, IModel<T> model) {
		super(id, model);
	}

	public HttpsForm(String id) {
		super(id);
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		String action=tag.getAttribute("action");
		String absolute=RequestUtils.toAbsolutePath(action);
		absolute=absolute.replaceFirst("http://", "https://");
		absolute=absolute.replaceFirst(":8080", ":8443");
		tag.put("action", absolute);
	}
	
}
