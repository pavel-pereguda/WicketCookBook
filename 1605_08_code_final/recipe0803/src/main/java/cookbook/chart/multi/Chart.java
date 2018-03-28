package cookbook.chart.multi;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.link.ILinkListener;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.target.basic.StringRequestTarget;
import org.apache.wicket.util.collections.MiniMap;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

public class Chart extends WebComponent implements IHeaderContributor,
		IResourceListener, ILinkListener {
	private static final ResourceReference OFC = new ResourceReference(
			Chart.class, "open-flash-chart.swf");
	private static final TextTemplate DECL = new PackagedTextTemplate(
			Chart.class, "Chart.template");
	private static final TextTemplate DATA = new PackagedTextTemplate(
			Chart.class, "Chart.data.template");
	private static final ResourceReference SWF = new ResourceReference(
			Chart.class, "swfobject.js");

	public Chart(String id) {
		super(id);
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
		String id = getMarkupId();
		params.put("id", id);
		params.put("width", 500);
		params.put("height", 200);
		params.put("swf", swf);
		params.put("data", dataUrl);
		String decl = DECL.asString(params);

		response.renderOnDomReadyJavascript(decl);

		final String func = "chart_" + getMarkupId();

		response.renderJavascript(
				String.format(
						"function %s(chart,x) { window.location='%s&chart='+chart+'&x='+x;}",
						func, urlFor(ILinkListener.INTERFACE)), func);

		for (int i = 0; i < 2; i++)
			response.renderJavascript(String.format(
					"function %s_%d(x) { %s(%d,x); }", func, i+1, func, i), func
					+ "_" + i);
	}

	private String getData() {
		Map<String, Object> params = new MiniMap<String, Object>(2);
		params.put("onclick1", "chart_" + getMarkupId() + "_1");
		params.put("onclick2", "chart_" + getMarkupId() + "_2");
		return DATA.asString(params);
	}

	public final void onLinkClicked() {
		int x = Integer.parseInt(getRequest().getParameter("x"));
		int chart = Integer.parseInt(getRequest().getParameter("chart"));
		onClick(chart, x);
	}

	protected void onClick(int chart, int x) {
	}
}
