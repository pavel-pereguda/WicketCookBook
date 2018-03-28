package cookbook.chart.swfobject;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.target.basic.StringRequestTarget;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

import cookbook.StockService;
import cookbook.StockService.Data;

public class Chart extends WebComponent implements IResourceListener {
	private static final ResourceReference OFC = new ResourceReference(
			Chart.class, "open-flash-chart.swf");

	private static final TextTemplate DATA = new PackagedTextTemplate(
			Chart.class, "Chart.data.template");

	public Chart(String id, IModel<String> symbol) {
		super(id, symbol);
		add(new SWFObject() {
			protected Config getConfig() {
				String params = String.format(
						"\"data-file\":\"%s\"",
						RequestUtils.toAbsolutePath(urlFor(
								IResourceListener.INTERFACE).toString()));
				return new Config(OFC, 500, 300, params);
			}
		});
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
