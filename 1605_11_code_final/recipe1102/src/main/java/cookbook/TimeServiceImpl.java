package cookbook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServiceImpl implements TimeService {

	public String getTime() {
		return SimpleDateFormat.getDateTimeInstance().format(new Date());
	}

}
