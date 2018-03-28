package cookbook;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.LoadableDetachableModel;

import com.google.inject.Inject;

public class TimeModel extends LoadableDetachableModel<String> {

	@Inject
	private TimeService service;

	public TimeModel() {
		InjectorHolder.getInjector().inject(this);
	}

	@Override
	protected String load() {
		return service.getTime();
	}

}
