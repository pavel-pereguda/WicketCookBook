package cookbook.chart.webresource;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

public class Chart extends WebComponent implements IHeaderContributor {
	private static final ResourceReference OFC = new ResourceReference(
			Chart.class, "open-flash-chart.swf");
	private static final TextTemplate DECL = new PackagedTextTemplate(
			Chart.class, "Chart.template");
	private static final ResourceReference SWF = new ResourceReference(
			Chart.class, "swfobject.js");

	public Chart(String id, IModel<String> symbol) {
		super(id, symbol);
		setOutputMarkupId(true);
	}

	@Override
	protected boolean getStatelessHint() {
		return false;
	}

	public void renderHead(IHeaderResponse response) {
		response.renderJavascriptReference(SWF);

		final CharSequence swf = urlFor(OFC);
		String dataUrl = StockResource.urlFor(getDefaultModelObjectAsString());
		dataUrl = RequestUtils.toAbsolutePath(dataUrl);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", getMarkupId());
		params.put("width", 500);
		params.put("height", 100);
		params.put("swf", swf);
		params.put("data", dataUrl);
		String decl = DECL.asString(params);

		response.renderOnDomReadyJavascript(decl);
	}

}
