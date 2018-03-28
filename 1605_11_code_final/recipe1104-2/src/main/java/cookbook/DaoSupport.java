package cookbook;

import javax.persistence.Query;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import cookbook.QueryParam.SortParam;

public class DaoSupport extends JpaDaoSupport {
	protected String getOrderByString(QueryParam param) {
		StringBuilder builder = new StringBuilder();
		for (SortParam sp : param.getSort()) {
			if (builder.length() > 0) {
				builder.append(", ");
			}
			builder.append(sp.getData());
			builder.append(" ");
			builder.append(sp.getDir().name());
		}
		return builder.toString();
	}

	protected String applyOrderBy(String hql, QueryParam param) {
		if (!param.getSort().isEmpty()) {
			hql += " ORDER BY " + getOrderByString(param);
		}
		return hql;
	}

	protected Query applyPaging(Query query, QueryParam param) {
		query.setFirstResult(param.getOffset());
		query.setMaxResults(param.getCount());
		return query;
	}
}
