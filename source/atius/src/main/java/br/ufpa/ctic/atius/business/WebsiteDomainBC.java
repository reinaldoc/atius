package br.ufpa.ctic.atius.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.domain.InetOrgPerson;
import br.ufpa.ctic.atius.domain.WebsiteDomain;
import br.ufpa.ctic.atius.persistence.WebsiteDomainDAO;

@BusinessController
public class WebsiteDomainBC extends DelegateCrud<WebsiteDomain, String, WebsiteDomainDAO> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InetOrgPersonBC inetOrgPersonBC;
	
	public void insert(WebsiteDomain websiteDomain) {
		getDelegate().insertEntry(websiteDomain);
	}

	public boolean domainAvailable(String serverName) {
		if (serverName != null && serverName.length() > 8) {
			WebsiteDomain websiteDomain = getDelegate().findByServerName(serverName);
			if (websiteDomain.getServerName() == null)
				return true;
		}
		return false;
	}
	
	public List<InetOrgPerson> findPerson(String search) {
		return inetOrgPersonBC.findPerson(search);
	}
	
	public List<WebsiteDomain> findWebsiteDomain(String search) {
		return getDelegate().findWebsiteDomain(search);
	}

}
