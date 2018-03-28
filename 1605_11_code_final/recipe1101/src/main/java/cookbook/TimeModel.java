package cookbook;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TimeModel extends LoadableDetachableModel<String> {

	@SpringBean
	private TimeService service;

	public TimeModel() {
		InjectorHolder.getInjector().inject(this);
	}
	
	@Override
	protected String load() {
		return service.getTime();
	}

}
