package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.util.collections.MicroMap;
import org.apache.wicket.util.string.interpolator.MapVariableInterpolator;

public class CheckBoxShiftClickEnabler extends AbstractBehavior
{
	private static final String template = "$$(document).ready(function(){$$('#${markupId} input:checkbox').enableRangeSelection();})";

	private String markupId;

	@Override
	public void bind(Component component)
	{
		component.setOutputMarkupId(true);
		markupId = component.getMarkupId();
		super.bind(component);
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		response.renderJavascriptReference(new ResourceReference(getClass(), "jquery.js"));
		response.renderJavascriptReference(new ResourceReference(getClass(),
			"jquery.shift_click_enabler.js"));

		MicroMap<String, Object> vars = new MicroMap<String, Object>("markupId", markupId);
		String javascript = new MapVariableInterpolator(template, vars).toString();
		String key = getClass().getName() + "#" + markupId;
		response.renderJavascript(javascript, key);
	}
}
