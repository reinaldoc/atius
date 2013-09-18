package br.com.atius.core.view.list;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.atius.core.business.OrganizationalPersonBC;
import br.com.atius.core.domain.OrganizationalPerson;
import br.gov.frameworkdemoiselle.enumeration.contrib.Comparison;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

@ViewController
public class OrganizationalPersonListMB extends AbstractListPageBean<OrganizationalPerson, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrganizationalPersonBC bc;

	@Override
	protected List<OrganizationalPerson> handleResultList(QueryConfig<OrganizationalPerson> queryConfig) {
		return bc.findAll();
	}

	public List<String> getResultListFilteredByName(String query) {
		QueryConfig<OrganizationalPerson> queryConfig = getQueryConfig();
		queryConfig.getFilter().put("cn", query);
		queryConfig.setMaxResults(10);
		queryConfig.setFilterComparison(Comparison.CONTAINS);
		List<String> result = new ArrayList<String>();
		for (OrganizationalPerson person : bc.findAll())
			result.add(person.getCn());
		return result;
	}

}
