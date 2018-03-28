package cookbook;

import org.apache.wicket.protocol.http.WebApplication;

import cookbook.chart.StockResource;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see cookbook.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		StockResource.register(this);
	}

}
