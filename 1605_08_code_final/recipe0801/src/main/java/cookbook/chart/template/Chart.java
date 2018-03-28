package cookbook.chart.template;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.target.basic.StringRequestTarget;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

import cookbook.StockService;
import cookbook.StockService.Data;

public class Chart extends WebComponent implements IHeaderContributor,
		IResourceListener {
	private static final ResourceReference OFC = new ResourceReference(
			Chart.class, "open-flash-chart.swf");
	private static final TextTemplate DECL = new PackagedTextTemplate(
			Chart.class, "Chart.template");
	private static final TextTemplate DATA = new PackagedTextTemplate(
			Chart.class, "Chart.data.template");
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

	public final void onResourceRequested() {
		String data = getData();
		IRequestTarget response = new StringRequestTarget(data);
		getRequestCycle().setRequestTarget(response);
	}

	public void renderHead(IHeaderResponse response) {
		response.renderJavascriptReference(SWF);

		final CharSequence swf = urlFor(OFC);
		String dataUrl = urlFor(IResourceListener.INTERFACE).toString();
		dataUrl = RequestUtils.toAbsolutePath(dataUrl);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", getMarkupId());
		params.put("width", 500);
		params.put("height", 300);
		params.put("swf", swf);
		params.put("data", dataUrl);
		String decl = DECL.asString(params);

		response.renderOnDomReadyJavascript(decl);
	}

	private String getData() {
		String values = new String();
		Data data = new StockService().getData(getDefaultModelObjectAsString());
		for (int i = 0; i < data.points.length; i++) {
			if (i > 0) {
				values += ",";
			}
			values += "{\"x\":" + data.points[i].date.getTime() / 1000;
			values += ",\"y\":" + data.points[i].value + "}";
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("min", data.min - 1);
		params.put("max", data.max + 1);
		params.put("values", values);
		params.put("title", getDefaultModelObjectAsString());
		return DATA.asString(params);
	}
}
