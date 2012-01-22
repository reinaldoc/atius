package br.gov.frameworkdemoiselle.ldap.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.ldap.configuration.EntryManagerConfig;
import br.gov.frameworkdemoiselle.ldap.internal.ClazzUtils;

@RequestScoped
public class EntryQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntryManagerConfig entryManagerConfig;

	@Inject
	private EntryQueryMap queryMap;

	private String searchFilter;

	private int maxResults;

	@PostConstruct
	public void init() {
		queryMap.init();
		queryMap.setSearchFilter(searchFilter);
		queryMap.setMaxResults(maxResults);
	}

	@SuppressWarnings("rawtypes")
	public List getResultList() {
		init();
		queryMap.setDnAsAttibute(false);
		return ClazzUtils.getEntryObjectList(queryMap.getResult(),
				ClazzUtils.getRequiredClassForSearchFilter(searchFilter, entryManagerConfig.getLdapentryPackages()));
	}

	public Object getSingleResult() {
		init();
		queryMap.setDnAsAttibute(false);
		queryMap.setMaxResults(2);
		Map<String, Map<String, String[]>> map = queryMap.getResult();
		if (map.size() == 1)
			return ClazzUtils.getEntryObjectList(map,
					ClazzUtils.getRequiredClassForSearchFilter(searchFilter, entryManagerConfig.getLdapentryPackages())).get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByExample(T entry, boolean isConjunction, int maxResult) {
		Map<String, String[]> map = ClazzUtils.getStringsMap(entry);
		String filter = "";
		for (Map.Entry<String, String[]> mapEntry : map.entrySet())
			for (String value : mapEntry.getValue())
				if (isConjunction)
					filter = filter + "(" + mapEntry.getKey() + "=" + value + ")";
				else
					filter = filter + "(" + mapEntry.getKey() + "=*" + value + "*)";
		if (filter.isEmpty())
			return null;
		if (isConjunction)
			setSearchFilter("(&(objectClass=" + entry.getClass().getSimpleName() + ")(&" + filter + "))");
		else
			setSearchFilter("(&(objectClass=" + entry.getClass().getSimpleName() + ")(|" + filter + "))");
		setMaxResults(maxResult);
		return getResultList();
	}

	public <T> List<T> findByExample(T entry, boolean isConjunction) {
		return findByExample(entry, isConjunction, entryManagerConfig.getFindByExampleMaxresult());
	}

	public void setMaxResults(int maxResult) {
		this.maxResults = maxResult;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

}
