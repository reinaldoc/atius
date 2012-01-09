package br.ufpa.ctic.atius.persistence;

import java.util.HashMap;
import java.util.Map;

import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.util.StringUtils;
import br.ufpa.ctic.atius.domain.DomainContainer;
import br.ufpa.ctic.atius.domain.WebsiteDomain;

@PersistenceController
public class DomainContainerDAO extends LDAPCrud<DomainContainer, String> {

	private static final long serialVersionUID = 1L;

	private Map<String, String[]> domainContainer2entry(DomainContainer domainContainer) {
		Map<String, String[]> entry = new HashMap<String, String[]>();
		entry.put("objectClass", domainContainer.getObjectClass());
		if (domainContainer.getCn() != null)
			entry.put("cn", new String[] { domainContainer.getCn() });
		if (domainContainer.getNextUidNumber() != null)
			entry.put("nextUidNumber", new String[] { domainContainer.getNextUidNumber().toString() });
		return entry;
	}

	private DomainContainer entry2domainContainer(Map<String, String[]> entry) {
		DomainContainer domainContainer = new DomainContainer();
		if (entry.size() == 0)
			return domainContainer;
		domainContainer.setDn(entry.get("dn")[0]);
		domainContainer.setObjectClass(entry.get("objectClass"));
		domainContainer.setCn(entry.get("cn")[0]);
		try {
			domainContainer.setNextUidNumber(new Integer(entry.get("nextUidNumber")[0]));
		} catch (Exception e) {
			domainContainer.setNextUidNumber(null);
		}
		return domainContainer;
	}

	public DomainContainer load(String domainContainerName) {
		DomainContainer domainContainer = new DomainContainer();
		if (!StringUtils.isBlank(domainContainerName)) {
			Map<String, String[]> entry = getEntryManager().createQuery("(&(objectClass=domainContainer)(cn=" + domainContainerName + "))")
					.getSingleResult();
			domainContainer = entry2domainContainer(entry);
		}
		return domainContainer;
	}

	public void update(DomainContainer domainContainer) {
		getEntryManager().merge(domainContainer2entry(domainContainer), domainContainer.getDn());
	}

	public DomainContainer getReference(String dn) {
		return entry2domainContainer(getEntryManager().getReference(dn));
	}

}