package cookbook;

import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.Strings;

public class TimeConverter implements IConverter {

  public Object convertToObject(String val, Locale locale) {
    if (Strings.isEmpty(val)) {
      return null;
    }

    String value = val.toLowerCase();

    if (!Pattern.matches("[0-9]{1,2}:[0-9]{2}(am|pm)", value)) {
      error(value, "format");
    }

    int colon = value.indexOf(':');
    int hour = Integer.parseInt(value.substring(0, colon));
    int minute = Integer.parseInt(value.substring(colon + 1, colon + 3));
    String meridian = value.substring(colon + 3, colon + 5);

    if (hour < 1 || hour > 12) {
      error(value, "hour");
    }
    if (minute < 0 || minute > 59) {
      error(value, "minute");
    }

    Time time = new Time();
    time.setHour(hour);
    time.setMinute(minute);
    time.setAm("am".equals(meridian));

    return time;
  }

  private void error(String value, String errorKey) {
    ConversionException e = new ConversionException("Error converting value: " + value +
        " to an instance of: " + Time.class.getName());
    e.setSourceValue(value);
    e.setResourceKey(getClass().getSimpleName() + "." + errorKey);
    throw e;
  }

  public String convertToString(Object value, Locale locale) {
    if (value == null) {
      return null;
    }
    Time time = (Time)value;

    return String.format("%d:%02d%s", time.getHour(), time.getMinute(), ((time.isAm())
        ? "am"
        : "pm"));
  }

}
