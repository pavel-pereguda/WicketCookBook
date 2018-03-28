package cookbook;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.apache.wicket.model.LoadableDetachableModel;
import org.jboss.seam.solder.beanManager.BeanManagerLocator;
import org.jboss.seam.wicket.util.NonContextual;

public class TimeModel extends LoadableDetachableModel<String> {

	@Inject
	private TimeService service;

	public TimeModel() {
		BeanManager manager = new BeanManagerLocator().getBeanManager();
		NonContextual.of(TimeModel.class, manager).existingInstance(this)
				.inject();
	}

	@Override
	protected String load() {
		return service.getTime();
	}

}
