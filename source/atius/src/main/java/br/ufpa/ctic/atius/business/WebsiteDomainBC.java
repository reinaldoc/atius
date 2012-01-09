package br.ufpa.ctic.atius.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.util.Faces;
import br.gov.frameworkdemoiselle.util.MenuContext;
import br.gov.frameworkdemoiselle.util.StringUtils;
import br.ufpa.ctic.atius.domain.DomainContainer;
import br.ufpa.ctic.atius.domain.InetOrgPerson;
import br.ufpa.ctic.atius.domain.WebsiteCategory;
import br.ufpa.ctic.atius.domain.WebsiteDomain;
import br.ufpa.ctic.atius.domain.WebsiteProfile;
import br.ufpa.ctic.atius.persistence.WebsiteDomainDAO;

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
		try {
			WebsiteProfile websiteProfile = websiteProfileBC.load(websiteDomain.getWebsiteProfile());
			if (StringUtils.isBlank(websiteProfile.getWebserverName())) {
				Faces.addMessage(new DefaultMessage("Não foi possível identificar o webserver do tipo " + websiteDomain.getWebsiteProfile()));
				Faces.validationFailed();
				return;
			}
			DomainContainer domainContainer = domainContainerBC.load(websiteProfile.getWebserverName());
			if (domainContainer.getNextUidNumber() == null) {
				Faces.addMessage(new DefaultMessage("O webserver indicado não foi encontrado"));
				Faces.validationFailed();
				return;
			}
			if (domainContainerBC.setNextUidNumber(domainContainer.getDn(), domainContainer.getNextUidNumber().intValue() + 1))
				websiteDomain.setUidNumber(String.valueOf(domainContainer.getNextUidNumber().intValue()));
			else {
				Faces.addMessage(new DefaultMessage("O uidNumber sugerido não esta disponível"));
				Faces.validationFailed();
				return;
			}
			websiteDomain.setDn("serverName=" + websiteDomain.getServerName() + "," + domainContainer.getDn());
			getDelegate().insert(websiteDomain);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String serverName) {
		getDelegate().delete(serverName);
	}

	public boolean domainAvailable(String serverName) {
		if (serverName != null && serverName.length() > 8) {
			WebsiteDomain websiteDomain = getDelegate().load(serverName);
			if (websiteDomain.getServerName() == null)
				return true;
		}
		return false;
	}

	public List<InetOrgPerson> findPerson(String search) {
		return inetOrgPersonBC.findPerson(search);
	}

	public List<WebsiteDomain> find(String search) {
		return getDelegate().find(search);
	}

	public List<WebsiteDomain> findByCategory(String category) {
		if ("Todos".equals(category))
			return getDelegate().findAll();
		return getDelegate().findByCategory(category);
	}

	public List<WebsiteDomain> findByCategory(String category, String search) {
		if ("Todos".equals(category))
			return getDelegate().find(search);
		return getDelegate().findByCategory(category, search);
	}
}
