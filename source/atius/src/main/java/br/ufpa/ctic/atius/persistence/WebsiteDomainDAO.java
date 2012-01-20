package br.ufpa.ctic.atius.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.util.Strings;
import br.ufpa.ctic.atius.domain.InetOrgPerson;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@PersistenceController
public class WebsiteDomainDAO extends LDAPCrud<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	private WebsiteDomain entry2websiteDomain(Map<String, String[]> entry) {
		WebsiteDomain websiteDomain = new WebsiteDomain();
		if (entry.size() == 0)
			return websiteDomain;
		websiteDomain.setDn(entry.get("dn")[0]);
		websiteDomain.setObjectClass(entry.get("objectClass"));
		websiteDomain.setCn(entry.get("cn")[0]);
		websiteDomain.setServerName(entry.get("serverName")[0]);
		websiteDomain.setDocumentRoot(entry.get("documentRoot")[0]);
		websiteDomain.setServerAlias(entry.get("serverAlias")[0]);
		websiteDomain.setAvailability(entry.get("availability")[0]);
		InetOrgPerson inetOrgPerson = new InetOrgPerson();
		inetOrgPerson.setMail(entry.get("adminId")[0]);
		websiteDomain.setAdminId(inetOrgPerson);
		inetOrgPerson = new InetOrgPerson();
		inetOrgPerson.setMail(entry.get("ownerId")[0]);
		websiteDomain.setOwnerId(inetOrgPerson);
		websiteDomain.setWebsiteCategory(entry.get("websiteCategory")[0]);
		websiteDomain.setWebsiteProfile(entry.get("websiteProfile")[0]);
		if (entry.get("blockCount") != null)
			websiteDomain.setBlockCount(new Integer(entry.get("blockCount")[0]));
		if (entry.get("inodeCount") != null)
			websiteDomain.setInodeCount(new Integer(entry.get("inodeCount")[0]));
		if (entry.get("uid") != null)
			websiteDomain.setUid(entry.get("uid")[0]);
		if (entry.get("uidNumber") != null)
			websiteDomain.setUidNumber(entry.get("uidNumber")[0]);
		if (entry.get("gidNumber") != null)
			websiteDomain.setGidNumber(entry.get("gidNumber")[0]);
		if (entry.get("loginShell") != null)
			websiteDomain.setLoginShell(entry.get("loginShell")[0]);
		if (entry.get("homeDirectory") != null)
			websiteDomain.setHomeDirectory(entry.get("homeDirectory")[0]);
		return websiteDomain;
	}

	public void insert(WebsiteDomain websiteDomain) {
		getEntryManager().setVerbose(true);
		getEntryManager().persist(websiteDomain);
		// getEntryManager().persist(websiteDomain2entry(websiteDomain),
		// websiteDomain.getDn());
	}

	public void delete(String serverName) {
		String dn = getEntryManager().findReference(String.format("(&(objectClass=websiteDomain)(serverName=%s))", serverName));
		getEntryManager().remove(dn);
	}

	public List<WebsiteDomain> findBySearchFilter(String searchFilter) {
		return getEntryManager().createQuery(searchFilter).getResultList();
	}

	public List<WebsiteDomain> findAll() {
		return findBySearchFilter("objectClass=websiteDomain");
	}

	public List<WebsiteDomain> find(String search) {
		if (Strings.isBlank(search))
			return findAll();
		return findBySearchFilter(String.format("(&(objectClass=websiteDomain)(|(cn=*%1$s*)(serverName=*%1$s*)))", search));
	}

	public List<WebsiteDomain> findByCategory(String category) {
		if (Strings.isBlank(category))
			return findAll();
		return findBySearchFilter(String.format("(&(objectClass=websiteDomain)(websiteCategory=%1$s))", category));
	}

	public List<WebsiteDomain> findByCategory(String category, String search) {
		if (Strings.isBlank(category))
			return find(search);
		if (Strings.isBlank(search))
			return findByCategory(category);
		return findBySearchFilter(String.format("(&(objectClass=websiteDomain)(websiteCategory=%1$s)(|(cn=*%2$s*)(serverName=*%2$s*)))", category,
				search));
	}

	public WebsiteDomain load(String serverName) {
		WebsiteDomain websiteDomain = new WebsiteDomain();
		if (!Strings.isBlank(serverName)) {
			return (WebsiteDomain) getEntryManager().createQuery(String.format("(&(objectClass=websiteDomain)(serverName=%s))", serverName))
					.getSingleResult();
		}
		return websiteDomain;
	}

}
