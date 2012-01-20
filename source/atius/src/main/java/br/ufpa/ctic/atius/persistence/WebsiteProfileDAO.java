package br.ufpa.ctic.atius.persistence;

import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.util.Strings;
import br.ufpa.ctic.atius.domain.WebsiteProfile;

@PersistenceController
public class WebsiteProfileDAO extends LDAPCrud<WebsiteProfile, String> {

	private static final long serialVersionUID = 1L;

	private WebsiteProfile entry2websiteProfile(Map<String, String[]> entry) {
		WebsiteProfile websiteProfile = new WebsiteProfile();
		if (entry.size() == 0)
			return websiteProfile;
		websiteProfile.setDn(entry.get("dn")[0]);
		websiteProfile.setObjectClass(entry.get("objectClass"));
		websiteProfile.setName(entry.get("cn")[0]);
		websiteProfile.setWebserverName(entry.get("webserverName")[0]);
		websiteProfile.setSchemaserverName(entry.get("schemaserverName")[0]);
		return websiteProfile;
	}

	@SuppressWarnings("unchecked")
	public List<WebsiteProfile> findBySearchFilter(String searchFilter) {
		return getEntryManager().createQuery(searchFilter).getResultList();
	}

	public List<WebsiteProfile> findAll() {
		return findBySearchFilter("objectClass=websiteProfile");
	}

	public WebsiteProfile load(String profileName) {
		WebsiteProfile websiteProfile = new WebsiteProfile();
		if (!Strings.isBlank(profileName)) {
			return (WebsiteProfile) getEntryManager().createQuery(String.format("(&(objectClass=websiteProfile)(cn=%s))", profileName))
					.getSingleResult();
		}
		return websiteProfile;
	}

}