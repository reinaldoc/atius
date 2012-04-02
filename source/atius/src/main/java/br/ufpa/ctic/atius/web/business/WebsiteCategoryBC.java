package br.ufpa.ctic.atius.web.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.web.domain.WebsiteCategory;
import br.ufpa.ctic.atius.web.persistence.WebsiteCategoryDAO;

@BusinessController
public class WebsiteCategoryBC extends DelegateCrud<WebsiteCategory, String, WebsiteCategoryDAO> {

	private static final long serialVersionUID = 1L;

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

}
