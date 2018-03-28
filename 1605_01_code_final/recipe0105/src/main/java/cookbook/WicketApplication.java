package cookbook;

import org.apache.wicket.IConverterLocator;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.convert.ConverterLocator;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 * 
 * @see cookbook.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  public Class<HomePage> getHomePage() {
    return HomePage.class;
  }

  @Override
  protected IConverterLocator newConverterLocator() {
    ConverterLocator locator = (ConverterLocator)super.newConverterLocator();
    locator.set(Time.class, new TimeConverter());
    return locator;
  }
}
