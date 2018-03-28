package cookbook;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.Count;

/**
 * @author Pavel Pereguda
 */
public class AnotherPage extends WebPage{




  public AnotherPage () {
      Customer customer = new Customer();
      customer.setAddress(new Address());
      customer.getAddress().set

  }
}
