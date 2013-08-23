package br.com.atius.core.persistence;

import br.com.atius.core.domain.Group;
import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class GroupDAO extends LDAPCrud<Group, String> {

	private static final long serialVersionUID = 1L;

}
