package br.ufpa.ctic.atius.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.LDAPCrud;
import br.gov.frameworkdemoiselle.util.Faces;
import br.gov.frameworkdemoiselle.util.StringUtils;
import br.ufpa.ctic.atius.domain.InetOrgPerson;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@PersistenceController
public class WebsiteDomainDAO extends LDAPCrud<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	private Map<String, String[]> websiteDomain2entry(WebsiteDomain websiteDomain) {
		Map<String, String[]> entry = new HashMap<String, String[]>();
		entry.put("objectClass", websiteDomain.getObjectClass());
		entry.put("cn", new String[] { websiteDomain.getCn() });
		entry.put("serverAlias", new String[] { websiteDomain.getServerAlias() });
		entry.put("serverName", new String[] { websiteDomain.getServerName() });
		entry.put("documentRoot", new String[] { websiteDomain.getDocumentRoot() });
		entry.put("availability", new String[] { websiteDomain.getAvailability() });
		entry.put("adminId", new String[] { websiteDomain.getAdminId().getMail() });
		entry.put("ownerId", new String[] { websiteDomain.getOwnerId().getMail() });
		entry.put("websiteCategory", new String[] { websiteDomain.getWebsiteCategory() });
		entry.put("websiteProfile", new String[] { websiteDomain.getWebsiteProfile() });
		entry.put("blockCount", new String[] { websiteDomain.getBlockCount().toString() });
		entry.put("inodeCount", new String[] { websiteDomain.getInodeCount().toString() });
		entry.put("uid", new String[] { websiteDomain.getUid() });
		entry.put("uidNumber", new String[] { "4000" });
		entry.put("gidNumber", new String[] { websiteDomain.getGidNumber() });
		entry.put("loginShell", new String[] { websiteDomain.getLoginShell() });
		entry.put("homeDirectory", new String[] { websiteDomain.getHomeDirectory() });
		return entry;
	}

	private WebsiteDomain entry2websiteDomain(Map<String, String[]> entry) {
		WebsiteDomain websiteDomain = new WebsiteDomain();
		try {
			websiteDomain.setObjectClass(entry.get("objectClass"));
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
		} catch (Exception e) {
		}
		return websiteDomain;
	}

	public WebsiteDomain load(String serverName) {
		return findByServerName(serverName);
	}

	public void insert(WebsiteDomain websiteDomain) {
		try {
			String serverDn = getEntryManager().getReference("(&(objectClass=domainContainer)(cn=atalaia.ufpa.br))");
			getEntryManager().persist(websiteDomain2entry(websiteDomain), "serverName=" + websiteDomain.getServerName() + "," + serverDn);
		} catch (Exception e) {
			Faces.addMessage(new DefaultMessage("Ocorreu um erro ao acessar a base LDAP."));
			Faces.validationFailed();
		}
	}

	public void delete(String serverName) {
		String dn = getEntryManager().getReference(String.format("(&(objectClass=websiteDomain)(serverName=%s))", serverName));
		getEntryManager().remove(dn);
	}

	public List<WebsiteDomain> findBySearchFilter(String searchFilter) {
		List<WebsiteDomain> websiteDomains = new ArrayList<WebsiteDomain>();
		Collection<Map<String, String[]>> entries = getEntryManager().createQuery(searchFilter).getResultCollection();
		for (Map<String, String[]> entry : entries) {
			websiteDomains.add(entry2websiteDomain(entry));
		}
		return websiteDomains;
	}

	public List<WebsiteDomain> findAll() {
		return findBySearchFilter("objectClass=websiteDomain");
	}

	public List<WebsiteDomain> find(String search) {
		if (StringUtils.isBlank(search))
			return findAll();
		return findBySearchFilter(String.format("(&(objectClass=websiteDomain)(|(cn=*%1$s*)(serverName=*%1$s*)))", search));
	}

	public List<WebsiteDomain> findByCategory(String category) {
		if (StringUtils.isBlank(category))
			return findAll();
		return findBySearchFilter(String.format("(&(objectClass=websiteDomain)(websiteCategory=%1$s))", category));
	}

	public List<WebsiteDomain> findByCategory(String category, String search) {
		if (StringUtils.isBlank(category))
			return find(search);
		if (StringUtils.isBlank(search))
			return findByCategory(category);
		return findBySearchFilter(String.format("(&(objectClass=websiteDomain)(websiteCategory=%1$s)(|(cn=*%2$s*)(serverName=*%2$s*)))",
				category, search));
	}

	public WebsiteDomain findByServerName(String serverName) {
		serverName = StringUtils.null2empty(serverName);
		Map<String, String[]> entry = getEntryManager().createQuery(
				String.format("(&(objectClass=websiteDomain)(serverName=%s))", serverName)).getSingleResult();
		if (entry.size() != 0) {
			return entry2websiteDomain(entry);
		}
		return new WebsiteDomain();
	}

}
