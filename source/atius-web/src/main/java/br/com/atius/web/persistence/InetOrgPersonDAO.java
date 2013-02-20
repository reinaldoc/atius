package br.com.atius.web.persistence;

import br.com.atius.web.domain.InetOrgPerson;
import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class InetOrgPersonDAO extends LDAPCrud<InetOrgPerson, String> {

	private static final long serialVersionUID = 1L;

}