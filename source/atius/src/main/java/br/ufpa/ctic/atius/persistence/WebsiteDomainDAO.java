package br.ufpa.ctic.atius.persistence;

import java.util.List;

import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@PersistenceController
public class WebsiteDomainDAO extends LDAPCrud<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<WebsiteDomain> findByCategory(String category, String search) {
		String f = String.format(
				"(&(objectClass=websiteDomain)(websiteCategory=%1$s)(|(cn=*%2$s*)(serverName=*%2$s*)(adminId=*%2$s*)(ownerId=*%2$s*)))", category,
				search);
		return getEntryManager().createQuery(f).getResultList();
	}

}
