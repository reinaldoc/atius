package br.ufpa.ctic.atius.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.LDAPCrud;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@PersistenceController
public class WebsiteDomainDAO extends LDAPCrud<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	public List<WebsiteDomain> findAll() {
		List<WebsiteDomain> websiteDomains = new ArrayList<WebsiteDomain>();
		Collection<Map<String, String[]>> entries = getEntryManager().createQuery("objectClass=websiteDomain").getResultCollection();
		for (Map<String, String[]> entry : entries) {
			WebsiteDomain websiteDomain = new WebsiteDomain();
			websiteDomain.setCn(entry.get("cn")[0]);
			websiteDomain.setServerName(entry.get("serverName")[0]);
			websiteDomain.setWebsiteCategory(entry.get("websiteCategory")[0]);
			websiteDomain.setWebsiteProfile(entry.get("websiteProfile")[0]);
			websiteDomains.add(websiteDomain);
		}
		return websiteDomains;
	}
}
