package cookbook;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class NameEditor extends FormComponentPanel<Name>
{
	private final FormComponent<String> first;
	private final FormComponent<String> last;

	public NameEditor(String id, IModel<Name> model)
	{
		super(id, model);
		first = new TextField<String>("first", new Model<String>());
		add(first);

		last = new TextField<String>("last", new Model<String>());
		add(last);

		onModelChanged(); // trigger the initial initialization
		
		add(new BothNamesFilledInValidator());
		// MORE - validation from outside
	}

	@Override
	protected void onModelChanged()
	{
		super.onModelChanged();
		Name name = getModelObject();
		if (name != null)
		{
			first.setModelObject(name.getFirst());
			last.setModelObject(name.getLast());
		} else {
			first.setModelObject(null);
			last.setModelObject(null);
		}
	}
	
	@Override
	protected void convertInput()
	{
		Name name = new Name();
		name.setFirst(first.getConvertedInput());
		name.setLast(last.getConvertedInput());
		if (Strings.isEmpty(name.getFirst()) && Strings.isEmpty(name.getLast()))
		{
			name = null;
		}
		setConvertedInput(name);
	}

	// MORE updatemodel

	private static class BothNamesFilledInValidator implements IValidator<Name>
	{
		public void validate(IValidatable<Name> validatable)
		{
			Name name = validatable.getValue();
			if (Strings.isEmpty(name.getFirst()) || Strings.isEmpty(name.getLast()))
			{
				ValidationError error = new ValidationError();
				error.addMessageKey(getClass().getSimpleName());
				validatable.error(error);
			}
		}
	}
	
}
