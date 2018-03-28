package cookbook;

import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TimeService.class).toInstance(new TimeServiceImpl());
	}
}
