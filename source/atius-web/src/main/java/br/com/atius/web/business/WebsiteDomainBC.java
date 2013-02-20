package br.com.atius.web.business;

import java.util.List;

import javax.inject.Inject;

import br.com.atius.web.common.WebConfig;
import br.com.atius.web.domain.DomainContainer;
import br.com.atius.web.domain.InetOrgPerson;
import br.com.atius.web.domain.WebsiteDomain;
import br.com.atius.web.persistence.WebsiteDomainDAO;
import br.gov.frameworkdemoiselle.enumeration.contrib.Comparison;
import br.gov.frameworkdemoiselle.enumeration.contrib.Logic;
import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

@BusinessController
public class WebsiteDomainBC extends DelegateCrud<WebsiteDomain, String, WebsiteDomainDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebConfig webConfig;

	@Inject
	private InetOrgPersonBC inetOrgPersonBC;

	@Inject
	private DomainContainerBC domainContainerBC;

	public void insert(WebsiteDomain websiteDomain) {
		DomainContainer domainContainer = domainContainerBC.getNextFreeUidNumber(websiteDomain);
		if (domainContainer == null) {
			Faces.addMessage(new DefaultMessage("O uidNumber sugerido não esta disponível"));
			Faces.validationFailed();
			return;
		}
		websiteDomain.setUidNumber(String.valueOf(domainContainer.getNextUidNumber()));
		websiteDomain.setDn("serverName=" + websiteDomain.getServerName() + "," + domainContainer.getDn());
		getDelegate().insert(websiteDomain);
	}

	public void disable(WebsiteDomain websiteDomain) {
		websiteDomain.setAvailability("disabled");
		update(websiteDomain);
	}

	public void enable(WebsiteDomain websiteDomain) {
		websiteDomain.setAvailability("enabled");
		update(websiteDomain);
	}

	public boolean domainAvailable(String serverName) {
		if (serverName != null && serverName.length() > 8) {
			WebsiteDomain websiteDomain = getDelegate().load(serverName);
			if (websiteDomain == null || websiteDomain.getServerName() == null)
				return true;
		}
		return false;
	}

	public List<InetOrgPerson> findPerson(String search) {
		return inetOrgPersonBC.findPerson(search);
	}

	public List<WebsiteDomain> find(String search, String category) {
		QueryConfig<WebsiteDomain> queryConfig = getQueryConfig();
		queryConfig.setGeneric(webConfig.getWebsiteContainerDN());

		if (Strings.isNotBlank(search) && !"Todos".equals(category))
			return getDelegate().findByCategory(category, search);

		if (Strings.isBlank(search)) {
			if (!"Todos".equals(category))
				queryConfig.getFilter().put("websiteCategory", category);
			return findAll();
		}

		queryConfig.getFilter().put("cn", search);
		queryConfig.getFilter().put("serverName", search);
		queryConfig.getFilter().put("adminId", search);
		queryConfig.getFilter().put("ownerId", search);
		queryConfig.setFilterComparison(Comparison.CONTAINS);
		queryConfig.setFilterLogic(Logic.OR);
		return findAll();
	}

}
