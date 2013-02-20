package br.ufpa.ctic.atius.web.view.list;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.ufpa.ctic.atius.web.business.WebsiteCategoryBC;
import br.ufpa.ctic.atius.web.domain.WebsiteCategory;

@ViewController
public class WebsiteCategoryListMB extends AbstractListPageBean<WebsiteCategory, String> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteCategoryBC bc;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		selectMenu(getFirstWebsiteCategory());
	}

	private String getFirstWebsiteCategory() {
		if (getResultList() != null && getResultList().size() > 0)
			return getResultList().get(0).getName();
		else
			return "Todos";
	}

	@Override
	protected List<WebsiteCategory> handleResultList(QueryConfig<WebsiteCategory> queryConfig) {
		return bc.getOrderedWebsiteCategories();
	}

}
