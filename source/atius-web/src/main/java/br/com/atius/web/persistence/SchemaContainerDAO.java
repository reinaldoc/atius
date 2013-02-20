package br.com.atius.web.persistence;

import br.com.atius.web.domain.SchemaContainer;
import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class SchemaContainerDAO extends LDAPCrud<SchemaContainer, String> {

	private static final long serialVersionUID = 1L;

}