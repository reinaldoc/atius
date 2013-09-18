package br.com.atius.core.persistence;

import br.com.atius.core.domain.OrganizationalPerson;
import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class OrganizationalPersonDAO extends LDAPCrud<OrganizationalPerson, String> {

	private static final long serialVersionUID = 1L;

}
