package br.com.atius.core.business;

import br.com.atius.core.domain.OrganizationalPerson;
import br.com.atius.core.persistence.OrganizationalPersonDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

@BusinessController
public class OrganizationalPersonBC extends DelegateCrud<OrganizationalPerson, String, OrganizationalPersonDAO> {

	private static final long serialVersionUID = 1L;

}
