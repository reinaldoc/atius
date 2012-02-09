package br.ufpa.ctic.atius.websites.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.enumeration.contrib.Comparison;
import br.gov.frameworkdemoiselle.enumeration.contrib.Logic;
import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;
import br.gov.frameworkdemoiselle.util.core.MenuContext;
import br.ufpa.ctic.atius.websites.domain.DomainContainer;
import br.ufpa.ctic.atius.websites.domain.InetOrgPerson;
import br.ufpa.ctic.atius.websites.domain.WebsiteCategory;
import br.ufpa.ctic.atius.websites.domain.WebsiteDomain;
import br.ufpa.ctic.atius.websites.domain.WebsiteProfile;
import br.ufpa.ctic.atius.websites.persistence.WebsiteDomainDAO;

@BusinessController
public class WebsiteDomainBC extends DelegateCrud<WebsiteDomain, String, WebsiteDomainDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private InetOrgPersonBC inetOrgPersonBC;

	@Inject
	private WebsiteCategoryBC websiteCategoryBC;

	@Inject
	private WebsiteProfileBC websiteProfileBC;

	@Inject
	private DomainContainerBC domainContainerBC;

	@Inject
	private MenuContext menuContext;

	public String getSelectedMenu() {
		return menuContext.getSelected("MenuSites");
	}

	public void selectMenu(String itemName) {
		menuContext.select("MenuSites", itemName);
	}

	public List<WebsiteCategory> getOrderedWebsiteCategories() {
		return websiteCategoryBC.getOrderedWebsiteCategories();
	}

	public List<String> getWebsiteProfiles() {
		return websiteProfileBC.getNames();
	}

	public void insert(WebsiteDomain websiteDomain) {
		WebsiteProfile websiteProfile = websiteProfileBC.load(websiteDomain.getWebsiteProfile());
		if (Strings.isBlank(websiteProfile.getWebserverName())) {
			Faces.addMessage(new DefaultMessage("Não foi possível identificar o webserver do tipo " + websiteDomain.getWebsiteProfile()));
			Faces.validationFailed();
			return;
		}
		DomainContainer domainContainer = domainContainerBC.getNextFreeUidNumber(websiteProfile.getWebserverName());
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

	public List<WebsiteDomain> find(String search) {
		QueryConfig<WebsiteDomain> queryConfig = getQueryConfig();
		queryConfig.getFilter().put("cn", search);
		queryConfig.getFilter().put("serverName", search);
		queryConfig.getFilter().put("adminId", search);
		queryConfig.getFilter().put("ownerId", search);
		queryConfig.setFilterComparison(Comparison.CONTAINS);
		queryConfig.setFilterLogic(Logic.OR);
		return findAll();
	}

	public List<WebsiteDomain> findByCategory(String category) {
		QueryConfig<WebsiteDomain> queryConfig = getQueryConfig();
		if (!"Todos".equals(category))
			queryConfig.getFilter().put("websiteCategory", category);
		return findAll();
	}

	public List<WebsiteDomain> findByCategory(String category, String search) {
		if (Strings.isBlank(search))
			return findByCategory(category);
		if (Strings.isBlank(category) || "Todos".equals(category))
			return find(search);
		return getDelegate().findByCategory(category, search);
	}
}
