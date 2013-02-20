package br.com.atius.web.business;

import java.util.List;

import br.com.atius.web.domain.InetOrgPerson;
import br.com.atius.web.persistence.InetOrgPersonDAO;
import br.gov.frameworkdemoiselle.enumeration.contrib.Comparison;
import br.gov.frameworkdemoiselle.enumeration.contrib.Logic;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

@BusinessController
public class InetOrgPersonBC extends DelegateCrud<InetOrgPerson, String, InetOrgPersonDAO> {

	private static final long serialVersionUID = 1L;

	public List<InetOrgPerson> findPerson(String search) {
		QueryConfig<InetOrgPerson> queryConfig = getQueryConfig();
		queryConfig.getFilter().put("cn", search);
		queryConfig.getFilter().put("mail", search);
		queryConfig.setMaxResults(5);
		queryConfig.setFilterLogic(Logic.OR);
		queryConfig.setFilterComparison(Comparison.CONTAINS);
		return findAll();
	}

}