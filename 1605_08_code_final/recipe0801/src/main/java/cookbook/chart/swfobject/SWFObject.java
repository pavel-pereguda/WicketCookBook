package cookbook.chart.swfobject;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

public abstract class SWFObject extends AbstractBehavior {

	private static final TextTemplate DECL = new PackagedTextTemplate(
			Chart.class, "SWFObject.template");
	private static final ResourceReference SWF = new ResourceReference(
			Chart.class, "swfobject.js");

	private Component parent;

	protected abstract Config getConfig();

	@Override
	public void bind(Component component) {
		parent = component;
		parent.setOutputMarkupId(true);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderJavascriptReference(SWF);

		Config config = getConfig();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", parent.getMarkupId());
		params.put("width", config.width);
		params.put("height", config.height);
		params.put("swf", parent.urlFor(config.swf));
		String extra = (config.params != null) ? config.params : "";
		params.put("params", extra);

		String decl = DECL.asString(params);
		response.renderOnDomReadyJavascript(decl);
	}

	public static class Config {
		private ResourceReference swf;
		private int width;
		private int height;
		private String params;

		public Config(ResourceReference swf, int width, int height,
				String params) {
			this.swf = swf;
			this.width = width;
			this.height = height;
			this.params = params;
		}

	}
}
