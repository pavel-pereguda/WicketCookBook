package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

public class AjaxFieldValidator extends AjaxFormComponentUpdatingBehavior {
	private final Component feedback;

	public AjaxFieldValidator(Component feedback) {
		super("onblur");
		this.feedback = feedback;
	}

	@Override
	protected void onUpdate(AjaxRequestTarget target) {
		target.addComponent(feedback);
	}

	@Override
	protected void onError(AjaxRequestTarget target, RuntimeException e) {
		target.addComponent(feedback);
	}

}
