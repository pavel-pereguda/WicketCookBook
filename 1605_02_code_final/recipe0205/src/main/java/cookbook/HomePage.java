package cookbook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage
{
	public HomePage(final PageParameters parameters)
	{

		Form<?> form = new Form<Void>("form");
		add(form);

		CheckBoxMultipleChoice<Date> range = new CheckBoxMultipleChoice<Date>("range",
			new Model<ArrayList<Date>>(new ArrayList<Date>()),
			new DateRangeModel(), new Renderer());
		range.setPrefix("<div class='date'>");
		range.setSuffix("</div>");
		range.add(new CheckBoxShiftClickEnabler());
		form.add(range);
	}

	private static class Renderer implements IChoiceRenderer<Date>
	{
		public Object getDisplayValue(Date object)
		{
			return new SimpleDateFormat("EEE, MMM d").format(object);
		}

		public String getIdValue(Date object, int index)
		{
			return String.valueOf(index);
		}

	}
}
