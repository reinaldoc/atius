package br.ufpa.ctic.atius.websites.persistence;

import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.ufpa.ctic.atius.websites.domain.WebsiteProfile;

@PersistenceController
public class WebsiteProfileDAO extends LDAPCrud<WebsiteProfile, String> {

	private static final long serialVersionUID = 1L;

}