package br.gov.frameworkdemoiselle.ldap.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.ldap.configuration.EntryManagerConfig;
import br.gov.frameworkdemoiselle.ldap.exception.EntryException;
import br.gov.frameworkdemoiselle.ldap.internal.ClazzUtils;

@RequestScoped
public class EntryQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntryManagerConfig entryManagerConfig;

	private EntryQueryMap queryMap;

	@SuppressWarnings("rawtypes")
	public List getResultList() {
		getQueryMap().setDnAsAttibute(false);
		return ClazzUtils.getEntryObjectList(getQueryMap().getResult(),
				ClazzUtils.getRequiredClassForSearchFilter(getSearchFilter(), entryManagerConfig.getLdapentryPackages()));
	}

	public Object getSingleResult() {
		getQueryMap().setDnAsAttibute(false);
		getQueryMap().setMaxResults(2);
		Map<String, Map<String, String[]>> map = getQueryMap().getResult();
		if (map.size() == 1)
			return ClazzUtils.getEntryObjectList(map,
					ClazzUtils.getRequiredClassForSearchFilter(getSearchFilter(), entryManagerConfig.getLdapentryPackages())).get(0);
		return null;
	}

	public void setMaxResults(int maxResult) {
		getQueryMap().getLdapConstraints().setMaxResults(maxResult);
	}

	public String getSearchFilter() {
		return getQueryMap().getSearchFilter();
	}

	public void setSearchFilter(String searchFilter) {
		getQueryMap().setSearchFilter(searchFilter);
	}

	private EntryQueryMap getQueryMap() {
		if (queryMap == null)
			throw new EntryException("EntryQueryMap is null (implementation error)");
		return queryMap;
	}

	public void setQueryMap(EntryQueryMap queryMap) {
		this.queryMap = queryMap;
	}
}
