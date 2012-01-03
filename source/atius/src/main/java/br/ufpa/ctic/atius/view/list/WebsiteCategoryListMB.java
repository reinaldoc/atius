package br.ufpa.ctic.atius.view.list;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.ufpa.ctic.atius.business.WebsiteCategoryBC;
import br.ufpa.ctic.atius.domain.WebsiteCategory;

@ViewController
public class WebsiteCategoryListMB extends AbstractListPageBean<WebsiteCategory, String> {

	private static final long serialVersionUID = 1L;

	List<WebsiteCategory> websiteCategories;

	@Inject
	private WebsiteCategoryBC bc;

	public List<WebsiteCategory> getWebsiteCategories() {
		setWebsiteCategories();
		return websiteCategories;
	}

	public void setWebsiteCategories() {
		if (websiteCategories == null)
			websiteCategories = bc.getOrderedWebsiteCategories();
	}

	@Override
	protected List<WebsiteCategory> handleResultList() {
		// TODO Auto-generated method stub
		return null;
	}

}
