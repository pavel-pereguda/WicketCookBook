package cookbook;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EntriesImpl extends DaoSupport implements Entries {

	@Autowired
	public EntriesImpl(EntityManagerFactory emf) {
		setEntityManagerFactory(emf);
	}

	@SuppressWarnings("unchecked")
	public List<Entry> list(final QueryParam param) {
		return getJpaTemplate().executeFind(new JpaCallback<List<Entry>>() {
			public List<Entry> doInJpa(EntityManager em)
					throws PersistenceException {
				Query q = em.createQuery(applyOrderBy(
						"SELECT e FROM Entry e", param));
				return applyPaging(q, param).getResultList();
			}
		});
	}

	public void save(Entry entry) {
		entry.setCreated(new Date());
		getJpaTemplate().persist(entry);
	}

	public Entry load(Long id) {
		return getJpaTemplate().find(Entry.class, id);
	}

	public int count() {
		return getJpaTemplate().execute(new JpaCallback<Integer>() {

			public Integer doInJpa(EntityManager em)
					throws PersistenceException {
				Number count = (Number) em.createQuery(
						"SELECT COUNT(*) FROM Entry").getSingleResult();
				return count.intValue();
			}

		});
	}
}
