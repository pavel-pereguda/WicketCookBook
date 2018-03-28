package cookbook.chart;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.template.TextTemplate;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.util.time.Time;

import cookbook.StockService;
import cookbook.StockService.Data;

public class StockResource extends Resource {

	private static final String KEY = StockResource.class.getName();
	private static final String PARAM = "symbol";

	private static final TextTemplate DATA = new PackagedTextTemplate(
			Chart.class, "Chart.data.template");

	public static String urlFor(String symbol) {
		return RequestCycle.get().urlFor(new ResourceReference(KEY)) + "?"
				+ PARAM + "=" + symbol;
	}

	public static void register(WebApplication application) {

		application.getSharedResources().add(KEY, new StockResource());
	}

	public StockResource() {
		setCacheable(false);
	}
	
	@Override
	public IResourceStream getResourceStream() {
		Request request = RequestCycle.get().getRequest();
		String symbol = request.getParameter(PARAM);
		return new StringResourceStream(getData(symbol));
	}

	private String getData(String symbol) {
		String values = new String();
		Data data = new StockService().getData(symbol);
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
		params.put("title", symbol);
		return DATA.asString(params);
	}

}
