package br.com.atius.core.view.list;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.atius.core.business.GroupBC;
import br.com.atius.core.domain.Group;
import br.gov.frameworkdemoiselle.enumeration.contrib.Comparison;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;

@ViewController
public class GroupListMB extends AbstractListPageBean<Group, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private GroupBC bc;

	@Override
	protected List<Group> handleResultList(QueryConfig<Group> queryConfig) {
		return bc.findAll();
	}

	public List<String> getResultListFilteredByName(String query) {
		QueryConfig<Group> queryConfig = getQueryConfig();
		queryConfig.getFilter().put("cn", query);
		queryConfig.setMaxResults(10);
		queryConfig.setFilterComparison(Comparison.CONTAINS);
		List<String> result = new ArrayList<String>();
		for (Group group : bc.findAll())
			result.add(group.getCn());
		return result;
	}

}
