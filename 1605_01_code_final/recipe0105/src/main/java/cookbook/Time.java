package cookbook;

import java.io.Serializable;

public class Time implements Serializable {
  private int hour;
  private int minute;
  private boolean am = true;

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public boolean isAm() {
    return am;
  }

  public void setAm(boolean am) {
    this.am = am;
  }

  @Override
  public String toString() {
    return String.format("[Time hour=%d minute=%d am=%b]", hour, minute, am);
  }
}
