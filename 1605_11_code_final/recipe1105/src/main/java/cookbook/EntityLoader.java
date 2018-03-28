package cookbook;

import java.io.Serializable;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class EntityLoader extends JpaDaoSupport {

	public EntityLoader() {
	}

	@Autowired
	public EntityLoader(EntityManagerFactory emf) {
		setEntityManagerFactory(emf);
	}

	public <T> T load(Class<T> clazz, Serializable id) {
		return getJpaTemplate().find(clazz, id);
	}
}
