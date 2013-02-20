package br.com.atius.web.persistence;

import br.com.atius.web.domain.WebsiteProfile;
import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class WebsiteProfileDAO extends LDAPCrud<WebsiteProfile, String> {

	private static final long serialVersionUID = 1L;

}