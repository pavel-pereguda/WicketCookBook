package cookbook;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;

public class SubmitOnceFormBad<T> extends Form<T>
{
	private boolean submitted = false;

	public SubmitOnceFormBad(String id)
	{
		super(id);
	}

	@Override
	public void process(IFormSubmittingComponent submittingComponent)
	{
		if (submitted)
		{
			error(getString("alreadySubmitted"));
		}

		super.process(submittingComponent);

		if (!hasError())
		{
			submitted = true;
		}
	}


}
