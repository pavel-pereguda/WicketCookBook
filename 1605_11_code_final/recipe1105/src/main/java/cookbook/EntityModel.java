package cookbook;

import java.io.Serializable;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EntityModel<T extends Identifiable<?>> implements IModel<T> {

	@SpringBean
	private EntityLoader loader;

	private Serializable id;
	private final Class<T> clazz;

	private T instance;

	public EntityModel(Class<T> clazz, Serializable id) {
		this.clazz = clazz;
		this.id = id;
		InjectorHolder.getInjector().inject(this);
	}

	@SuppressWarnings("unchecked")
	public EntityModel(T entity) {
		this((Class<T>) entity.getClass(), entity.getId());
		instance = entity;
	}

	public T getObject() {
		if (instance == null) {
			if (id != null) {
				instance = (T) loader.load(clazz, id);
			}
		}
		return instance;
	}

	public void setObject(T object) {
		if (object == null) {
			id = null;
		} else {
			id = object.getId();
		}
		instance = object;
	}

	public void detach() {
		if (instance != null && instance.getId() != null) {
			this.id = instance.getId();
		}
		if (this.id!=null) {
			instance = null;
		}
	}
}
