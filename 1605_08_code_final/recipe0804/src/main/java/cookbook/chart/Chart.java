package cookbook.chart;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.target.basic.StringRequestTarget;
import org.apache.wicket.util.collections.MicroMap;
import org.apache.wicket.util.template.PackagedTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

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

	private final CallbackBehavior ajax;

	public Chart(String id, IModel<String> symbol) {
		super(id, symbol);
		setOutputMarkupId(true);
		ajax = new CallbackBehavior();
		add(ajax);
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

		response.renderJavascript(String.format(
				"function chart_%s(x) { %s }",
				id, ajax.getCallbackScript()), "chart_" + id);
	}

	private String getData() {
		return DATA.asString(new MicroMap("onclick", "chart_" + getMarkupId()));
	}

	protected void onClick(int x, AjaxRequestTarget target) {
		
	}

	private class CallbackBehavior extends AbstractDefaultAjaxBehavior {

		@Override
		protected void respond(AjaxRequestTarget target) {
			int x = Integer.parseInt(getRequest().getParameter("x"));
			onClick(x, target);
		}

		public CharSequence getCallbackUrl(boolean active) {
			return super.getCallbackUrl(active) + "&x='+x+'";
		};

		@Override
		public CharSequence getCallbackScript() {
			return super.getCallbackScript();
		}
	}
}
