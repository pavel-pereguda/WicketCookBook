package cookbook;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

public abstract class EntityProvider<T extends Identifiable<?>> implements
		IDataProvider<T> {

	public IModel<T> model(T object) {
		return new EntityModel<T>(object);
	}

	public void detach() {
	}

}
