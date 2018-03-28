package cookbook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

public class DateRangeModel extends LoadableDetachableModel<List<? extends Date>>
{

	@Override
	protected List<? extends Date> load()
	{
		List<Date> dates = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();

		int currentMonth = cal.get(Calendar.MONTH);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		do
		{
			dates.add(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);

		}
		while (cal.get(Calendar.MONTH) == currentMonth);

		return dates;
	}

}
