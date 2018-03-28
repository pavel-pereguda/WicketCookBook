package cookbook.fieldborder;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class FieldBorder extends Border {

	public FieldBorder(String id) {
		super(id);
	}

	@Override
	protected void onBeforeRender() {
		if (get("label") == null) {
			createComponents();
		}
		super.onBeforeRender();
	}

	private void createComponents() {
		final FormComponent<?> fc = getFormComponent();
		
		add(new SimpleFormComponentLabel("label", fc));
		
		add(new FeedbackPanel("feedback", new ContainerFeedbackMessageFilter(
				this)));
		
		add(new WebMarkupContainer("required") {
			@Override
			protected void onConfigure() {
				super.onConfigure();
				setVisible(fc.isRequired());
			}
		});
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		if (!getFormComponent().isValid()) {
			tag.append("class", "error", " ");
		}
	}

	private FormComponent<?> getFormComponent() {
		return (FormComponent<?>) visitChildren(FormComponent.class,
				new IVisitor<FormComponent<?>>() {
					public Object component(FormComponent<?> component) {
						return component;
					}
				});
	}
}
