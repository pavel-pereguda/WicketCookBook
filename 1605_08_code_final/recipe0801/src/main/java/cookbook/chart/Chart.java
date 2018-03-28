package cookbook.chart;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.target.basic.StringRequestTarget;

import cookbook.StockService;
import cookbook.StockService.Data;

public class Chart extends WebComponent implements IHeaderContributor,
		IResourceListener {
	private static final ResourceReference OFC = new ResourceReference(
			Chart.class, "open-flash-chart.swf");
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

	private String getData() {
		Data data = new StockService().getData(getDefaultModelObjectAsString());

		String json = "{ ";
		json += "\"bg_colour\": \"#FFFFFF\",\"title\":{\"text\":\""
				+ getDefaultModelObjectAsString() + "\"}";
		json += ",\"x_axis\": { \"steps\":\"86400\",\"stroke\":\"1\",\"colour\":\"#0000FF\",\"grid-colour\":\"#FFFFFF\", \"labels\":{\"text\":\"#date:d M Y#\"}}";
		json += ",\"y_axis\": { \"stroke\":\"1\",\"colour\":\"#0000FF\",\"grid-colour\":\"#FFFFFF\",\"min\": "
				+ (Math.round(data.min) - 1)
				+ ", \"max\": "
				+ (Math.round(data.max) + 1) + ", \"steps\": 10 }";
		json += ",\"elements\": [ { \"type\": \"line\", \"dot-style\":{\"tip\":\"#date:d M Y# - #y#\"},\"width\":\"1\", \"values\": [ ";

		for (int i = 0; i < data.points.length; i++) {
			if (i > 0) {
				json += ",";
			}
			json += "{\"x\":" + data.points[i].date.getTime() / 1000;
			json += ",\"y\":" + data.points[i].value + "}";
		}
		json += "]}]";

		json += "}";
		return json;
	}

	public void renderHead(IHeaderResponse response) {
		response.renderJavascriptReference(SWF);

		final CharSequence swf = urlFor(OFC);
		String dataUrl = urlFor(IResourceListener.INTERFACE).toString();
		dataUrl = RequestUtils.toAbsolutePath(dataUrl);

		String script = "swfobject.embedSWF(\"" + swf + "\",\"" + getMarkupId();
		script += "\",\"500\",\"300\"";
		script += ",\"9.0.0\",\"expressInstall.swf\"";
		script += ",{\"data-file\":\"" + dataUrl + "\"});";

		response.renderOnDomReadyJavascript(script);
	}
}
