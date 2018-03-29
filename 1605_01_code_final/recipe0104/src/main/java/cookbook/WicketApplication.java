package cookbook;

import org.apache.wicket.protocol.http.WebApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 * 
 * @see cookbook.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
  private static final List<Person> personsDB;
  static
  {
    personsDB = new ArrayList<Person>();
    personsDB.add(new Person("Fritz", "Fritzel"));
    personsDB.add(new Person("Ghan", "Phariounimn"));
    personsDB.add(new Person("Jan", "Klaasen"));
    personsDB.add(new Person("Hank", "Plaindweller"));
  }

  /**
   * @return persons db
   */
  public static List<Person> getPersons()
  {
    return personsDB;
  }

  @Override
  public void init(){
    super.init();
    getResourceSettings().setThrowExceptionOnMissingResource(false);
  }
  /**
   *
   *
   * Constructor
   */
  public WicketApplication() {
  }

  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  public Class<CheckGroupPage> getHomePage() {
    return CheckGroupPage.class;
  }

}
