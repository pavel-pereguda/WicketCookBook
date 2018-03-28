package cookbook;

import java.util.Date;

import org.apache.wicket.util.time.Duration;
import org.apache.wicket.util.time.Time;

public class StockService {
	public Data getData(String symbol) {
		Data data = new Data();
		data.points = new Point[28];

		Time start = Time.now().subtract(Duration.days(data.points.length));
		double value = Math.random() * 400.0d + 100.0d;
		data.min = 1000;
		data.max = 0;

		for (int i = 0; i < data.points.length; i++) {
			double factor = Math.random() > 0.2d ? 1 : -1;
			value = value + factor * (value * 0.1d * Math.random());

			data.min = Math.min(data.min, value);
			data.max = Math.max(data.max, value);

			data.points[i] = new Point();
			data.points[i].date = start.add(Duration.days(i)).toDate();
			data.points[i].value = value;
		}

		return data;
	}

	public static class Data {
		public Point[] points;
		public double min;
		public double max;
	}

	public static class Point {
		public Date date;
		public double value;
	}
}