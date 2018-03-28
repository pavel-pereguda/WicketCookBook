package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

public class HomePage extends WebPage {
	public HomePage(final PageParameters parameters) {

		final Component feedback = new FeedbackPanel("feedback")
				.setOutputMarkupId(true);
		add(feedback);

		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onConfigure() {
				super.onConfigure();
				processChildren();
			}

			private void processChildren() {
				visitChildren(FormComponent.class,
						new IVisitor<FormComponent>() {

							public Object component(FormComponent component) {
								process(component);
								return CONTINUE_TRAVERSAL;
							}
						});
			}

			private void process(FormComponent component) {
				for (IBehavior behavior : component.getBehaviors()) {
					if (behavior instanceof AjaxFormComponentUpdatingBehavior) {
						return;
					}
				}
				component.add(new AjaxFormValidatingBehavior(this, "onblur") {
					@Override
					protected void onSubmit(AjaxRequestTarget target) {
						target.addComponent(feedback);
					}

					@Override
					protected void onError(AjaxRequestTarget target) {
						target.addComponent(feedback);
					}
				});
			}

		};
		add(form);

		form.add(new TextField<String>("username", Model.of("")).setRequired(
				true).add(StringValidator.minimumLength(6)));

		form.add(new PasswordTextField("password", Model.of(""))
				.setRequired(true));

	}
}
