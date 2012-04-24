package br.ufpa.ctic.atius.panel.web.view.list;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.enumeration.contrib.Logic;
import br.gov.frameworkdemoiselle.fuselage.view.app.FuselageMB;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.ufpa.ctic.atius.web.business.WebsiteDomainBC;
import br.ufpa.ctic.atius.web.domain.WebsiteCategory;
import br.ufpa.ctic.atius.web.domain.WebsiteDomain;

@ViewController
public class WebsiteDomainListMB extends AbstractListPageBean<WebsiteDomain, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteDomainBC bc;

	List<WebsiteCategory> websiteCategories;

	List<String> websiteProfiles;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		selectMenu(getFirstWebsiteCategory());
	}

	private String getFirstWebsiteCategory() {
		if (getWebsiteCategories().size() > 0)
			return getWebsiteCategories().get(0).getName();
		else
			return "Todos";
	}

	@Override
	protected List<WebsiteDomain> handleResultList(QueryConfig<WebsiteDomain> queryConfig) {
		queryConfig.getFilter().put("adminId", Faces.getManagedProperty("#{fuselageMB}", FuselageMB.class).getUsername());
		queryConfig.getFilter().put("ownerId", "");
		queryConfig.setFilterLogic(Logic.OR);
		return bc.findAll();
	}

	public List<WebsiteCategory> getWebsiteCategories() {
		if (websiteCategories == null)
			websiteCategories = bc.getOrderedWebsiteCategories();
		return websiteCategories;
	}

	public List<String> getWebsiteProfiles() {
		if (websiteProfiles == null)
			websiteProfiles = bc.getWebsiteProfiles();
		return websiteProfiles;
	}

}
