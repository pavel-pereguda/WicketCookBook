package cookbook;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TimeServiceImpl implements TimeService {

	public String getTime() {
		return SimpleDateFormat.getDateTimeInstance().format(new Date());
	}

}
