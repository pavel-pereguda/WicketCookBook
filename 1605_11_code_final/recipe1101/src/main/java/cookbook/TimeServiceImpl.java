package cookbook;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService {
	public String getTime() {
		return SimpleDateFormat.getDateTimeInstance().format(new Date());
	}
}
