package br.com.atius.catalog.persistence;

import java.util.ArrayList;
import java.util.List;

import br.com.atius.catalog.domain.ServiceItem;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class ServiceItemDAO extends JPACrud<ServiceItem, Integer> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<ServiceItem> search(String search) {

		List<ServiceItem> result = new ArrayList<ServiceItem>();

		List<String> fields = new ArrayList<String>();
		fields.add("i.description");
		fields.add("i.group.name");
		fields.add("i.group.description");
		String sql = "select i from ServiceItem i where lower(i.name) like '%" + search.toLowerCase() + "%'";
		for (String field : fields)
			sql = sql + " or lower(" + field + ") like '%" + search.toLowerCase() + "%'";
		sql = sql + " order by i.group.name, i.name";

		result.addAll(createQuery(sql).getResultList());

		fields = new ArrayList<String>();
		fields.add("i.description");
		fields.add("i.subgroup.name");
		fields.add("i.subgroup.description");
		sql = "select i from ServiceItem i where lower(i.name) like '%" + search.toLowerCase() + "%'";
		for (String field : fields)
			sql = sql + " or lower(" + field + ") like '%" + search.toLowerCase() + "%'";
		sql = sql + " order by i.subgroup.name, i.name";
		result.addAll(createQuery(sql).getResultList());

		return result;
	}

}
