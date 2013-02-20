package br.com.atius.web.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.atius.web.common.WebConfig;
import br.com.atius.web.domain.WebsiteCategory;
import br.com.atius.web.persistence.WebsiteCategoryDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class WebsiteCategoryBC extends DelegateCrud<WebsiteCategory, String, WebsiteCategoryDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebConfig webConfig;

	public void insert(WebsiteCategory websiteCategory) {
		websiteCategory.setParentDN(webConfig.getCategoryContainerDN());
		websiteCategory.setOrder(getCategoryNextOrder());
		getDelegate().insert(websiteCategory);
	}

	public List<WebsiteCategory> getOrderedWebsiteCategories() {
		List<WebsiteCategory> list = findAll();

		Map<Long, WebsiteCategory> orderIdx = new HashMap<Long, WebsiteCategory>();
		List<Long> orderIdxList = new ArrayList<Long>();
		for (WebsiteCategory category : list) {
			orderIdxList.add(new Long(category.getOrder()));
			orderIdx.put(new Long(category.getOrder()), category);
		}
		Collections.sort(orderIdxList);

		List<WebsiteCategory> orderedList = new ArrayList<WebsiteCategory>();
		for (Long idx : orderIdxList) {
			orderedList.add(orderIdx.get(idx));
		}
		return orderedList;
	}

	public String getCategoryNextOrder() {
		int i = 0;
		for (WebsiteCategory websiteCategory : findAll()) {
			int value = new Long(websiteCategory.getOrder()).intValue();
			if (value > i)
				i = value;
		}
		return String.valueOf(++i);
	}

}
