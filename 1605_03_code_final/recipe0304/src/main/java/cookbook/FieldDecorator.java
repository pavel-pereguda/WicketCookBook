package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.Response;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.string.Strings;

public class FieldDecorator extends AbstractBehavior {
	
	@Override
	public void bind(Component component) {
		component.setOutputMarkupId(true);
	}

	@Override
	public void beforeRender(Component component) {
		FormComponent<?> fc = (FormComponent<?>) component;
		Response r = component.getResponse();

		r.write("<div id=\"");
		r.write(component.getMarkupId()+"_fd");
		r.write("\">");
		
		String label = (fc.getLabel() != null) ? fc.getLabel().getObject()
				: null;
		if (label != null) {
			r.write("<label for=\"");
			r.write(fc.getMarkupId());
			r.write("\"");
			if (fc.isValid() == false) {
				r.write(" class=\"error\"");
			}
			r.write(">");
			r.write(Strings.escapeMarkup(label));
			if (fc.isRequired()) {
				r.write("<span class=\"required\">*</span>");
			}
			r.write("</label>");
		}

		super.beforeRender(component);
	}

	@Override
	public void onRendered(Component component) {
		FormComponent<?> fc = (FormComponent<?>) component;
		Response r = component.getResponse();
		FeedbackMessages messages = fc.getSession().getFeedbackMessages();

		if (messages.hasMessageFor(component)) {
			r.write("<ul class=\"feedbackPanel\">");
			IFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(
					component);

			for (FeedbackMessage message : messages.messages(filter)) {
				r.write("<li class=\"feedbackPanel");
				r.write(message.getLevelAsString().toUpperCase());
				r.write("\">");
				r.write(Strings.escapeMarkup(message.getMessage().toString()));
				r.write("</li>");
			}
			r.write("</ul>");
		}
		r.write("</div>");
	}

	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		FormComponent<?> fc = (FormComponent<?>) component;
		if (fc.isValid() == false) {
			String cl = tag.getAttribute("class");
			if (cl == null) {
				tag.put("class", "error");
			} else {
				tag.put("class", "error " + cl);
			}
		}
	}
}
