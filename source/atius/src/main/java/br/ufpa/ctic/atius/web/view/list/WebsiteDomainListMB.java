package br.ufpa.ctic.atius.web.view.list;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
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
		return bc.find(getResultFilter(), getSelectedMenu());
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
