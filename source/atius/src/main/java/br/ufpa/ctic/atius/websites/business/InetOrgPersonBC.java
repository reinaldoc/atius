package br.ufpa.ctic.atius.websites.business;

import java.util.List;

import br.gov.frameworkdemoiselle.enumeration.contrib.Comparison;
import br.gov.frameworkdemoiselle.enumeration.contrib.Logic;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.ufpa.ctic.atius.websites.domain.InetOrgPerson;
import br.ufpa.ctic.atius.websites.persistence.InetOrgPersonDAO;

@BusinessController
public class InetOrgPersonBC extends DelegateCrud<InetOrgPerson, String, InetOrgPersonDAO> {

	private static final long serialVersionUID = 1L;

	public List<InetOrgPerson> findPerson(String search) {
		getQueryConfig().getFilter().put("cn", search);
		getQueryConfig().getFilter().put("mail", search);
		getQueryConfig().setMaxResults(5);
		getQueryConfig().setFilterLogic(Logic.OR);
		getQueryConfig().setFilterComparison(Comparison.CONTAINS);
		return findAll();
	}

}