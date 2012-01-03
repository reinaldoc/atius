package br.ufpa.ctic.atius.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.domain.WebsiteDomain;
import br.ufpa.ctic.atius.persistence.WebsiteDomainDAO;

@BusinessController
public class WebsiteDomainBC extends DelegateCrud<WebsiteDomain, String, WebsiteDomainDAO> {

	private static final long serialVersionUID = 1L;

	public boolean domainAvailable(String serverName) {
		if (serverName != null && serverName.length() > 8) {
			WebsiteDomain websiteDomain = getDelegate().findByServerName(serverName);
			if (websiteDomain.getServerName() == null)
				return true;
		}
		return false;
	}

}
