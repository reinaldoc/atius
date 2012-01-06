package br.ufpa.ctic.atius.business;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.domain.InetOrgPerson;
import br.ufpa.ctic.atius.persistence.InetOrgPersonDAO;

@BusinessController
public class InetOrgPersonBC extends DelegateCrud<InetOrgPerson, String, InetOrgPersonDAO> {

	private static final long serialVersionUID = 1L;

	public List<InetOrgPerson> findPerson(String search) {
		return getDelegate().findPerson(search);
	}

	public InetOrgPerson loadByEmail(String mail) {
		return getDelegate().findByEmail(mail);
	}

}