package br.ufpa.ctic.atius.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.LDAPCrud;
import br.gov.frameworkdemoiselle.util.StringUtils;
import br.ufpa.ctic.atius.domain.InetOrgPerson;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@PersistenceController
public class WebsiteDomainDAO extends LDAPCrud<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	public WebsiteDomain load(String id) {
		return findByServerName(id);
	}

	public void insertEntry(WebsiteDomain websiteDomain) {
		try {
			String serverDn = getEntryManager().createQuery("(&(objectClass=domainContainer)(cn=atalaia.ufpa.br))")
					.getSingleDn();
			getEntryManager().persist(websiteDomain2entry(websiteDomain),
					"serverName=" + websiteDomain.getServerName() + "," + serverDn);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	private WebsiteDomain entry2websiteDomain(Map<String, String[]> entry) {
		WebsiteDomain websiteDomain = new WebsiteDomain();
		websiteDomain.setCn(entry.get("cn")[0]);
		websiteDomain.setServerName(entry.get("serverName")[0]);
		websiteDomain.setWebsiteCategory(entry.get("websiteCategory")[0]);
		websiteDomain.setWebsiteProfile(entry.get("websiteProfile")[0]);
		InetOrgPerson inetOrgPerson = new InetOrgPerson();
		inetOrgPerson.setMail(entry.get("adminId")[0]);
		websiteDomain.setAdminId(inetOrgPerson);
		inetOrgPerson = new InetOrgPerson();
		inetOrgPerson.setMail(entry.get("ownerId")[0]);
		websiteDomain.setOwnerId(inetOrgPerson);
		return websiteDomain;
	}

	private Map<String, String[]> websiteDomain2entry(WebsiteDomain websiteDomain) {
		Map<String, String[]> entry = new HashMap<String, String[]>();
		entry.put("objectClass", new String[] { "websiteDomain" });
		entry.put("cn", new String[] { websiteDomain.getCn() });
		entry.put("serverName", new String[] { websiteDomain.getServerName() });
		entry.put("websiteCategory", new String[] { websiteDomain.getWebsiteCategory() });
		entry.put("websiteProfile", new String[] { websiteDomain.getWebsiteProfile() });
		entry.put("adminId", new String[] { websiteDomain.getAdminId().getMail() });
		entry.put("ownerId", new String[] { websiteDomain.getOwnerId().getMail() });
		entry.put("serverAlias", new String[] { "www." + websiteDomain.getServerName() });
		entry.put("documentRoot", new String[] { "/var/www/" + websiteDomain.getServerName().split("\\.")[0] });
		return entry;
	}

	public List<WebsiteDomain> find(String searchFilter) {
		List<WebsiteDomain> websiteDomains = new ArrayList<WebsiteDomain>();
		Collection<Map<String, String[]>> entries = getEntryManager().createQuery(searchFilter).getResultCollection();
		for (Map<String, String[]> entry : entries) {
			websiteDomains.add(entry2websiteDomain(entry));
		}
		return websiteDomains;
	}

	public List<WebsiteDomain> findAll() {
		return find("objectClass=websiteDomain");
	}

	public List<WebsiteDomain> findWebsiteDomain(String searchDomain) {
		if (StringUtils.isEmpty(searchDomain))
			return findAll();
		return find("(&(objectClass=websiteDomain)(|(cn=*" + searchDomain + "*)(serverName=*" + searchDomain + "*)))");
	}

	public WebsiteDomain findByServerName(String serverName) {
		serverName = StringUtils.null2empty(serverName);
		WebsiteDomain websiteDomain = new WebsiteDomain();
		Map<String, String[]> entry = getEntryManager().createQuery(
				"(&(objectClass=websiteDomain)(serverName=" + serverName + "))").getSingleResult();
		if (entry.size() != 0) {
			websiteDomain = entry2websiteDomain(entry);
		}
		return websiteDomain;
	}

}
