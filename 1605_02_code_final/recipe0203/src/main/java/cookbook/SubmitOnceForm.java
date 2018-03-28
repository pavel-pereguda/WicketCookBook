package cookbook;

import java.util.ArrayList;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;

public class SubmitOnceForm<T> extends Form<T>
{
	private static MetaDataKey<ArrayList<FormToken>> PROCESSED = new MetaDataKey<ArrayList<FormToken>>()
	{
	};

	public SubmitOnceForm(String id)
	{
		super(id);
	}

	protected void onRepeatSubmit()
	{
		error(getString("alreadySubmitted"));
	}

	@Override
	public void process(IFormSubmittingComponent submittingComponent)
	{
		if (isAlreadyProcessed())
		{
			onRepeatSubmit();
		}
		else
		{
			super.process(submittingComponent);
			updateProcessedForms();
		}
	}

	private FormToken getToken()
	{
		return new FormToken(getPage().getPageReference(), getPageRelativePath());
	}

	private synchronized boolean isAlreadyProcessed()
	{
		ArrayList<FormToken> tokens = getSession().getMetaData(PROCESSED);

		if (tokens != null)
		{
			FormToken token = getToken();
			if (tokens.contains(token))
			{
				return true;
			}
		}
		return false;
	}

	private synchronized void updateProcessedForms()
	{
		if (hasError())
		{
			return;
		}

		ArrayList<FormToken> tokens = getSession().getMetaData(PROCESSED);
		if (tokens == null)
		{
			tokens = new ArrayList<FormToken>();
		}

		FormToken token = getToken();

		if (!tokens.contains(token))
		{
			tokens.add(token);
			while (tokens.size() > 20)
			{
				tokens.remove(0);
			}
			getSession().setMetaData(PROCESSED, tokens);
		}
	}

}
