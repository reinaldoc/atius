package br.com.atius.web.persistence;

import br.com.atius.web.domain.WebsiteCategory;
import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class WebsiteCategoryDAO extends LDAPCrud<WebsiteCategory, String> {

	private static final long serialVersionUID = 1L;

}
